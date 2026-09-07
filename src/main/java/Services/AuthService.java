package Services;

import Model.Propietario;
import Model.SesionUsuario;
import repositories.PropietarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthService {
    private PropietarioRepository propietarioRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    public boolean login(String correo, String clave) throws Exception {
        if (correo == null || correo.trim().isEmpty() || clave == null || clave.trim().isEmpty()) {
            throw new Exception("El correo y la clave son obligatorios.");
        }

        Propietario usuario = propietarioRepository.getPropietarios().stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            throw new Exception("Correo o clave incorrectos.");
        }

        boolean claveCorrecta = usuario.getPassword().startsWith("$2a$") || usuario.getPassword().startsWith("$2b$")
                ? encoder.matches(clave, usuario.getPassword())
                : usuario.getPassword().equals(clave);

        if (!claveCorrecta) {
            throw new Exception("Correo o clave incorrectos.");
        }

        SesionUsuario.getInstancia().iniciarSesion(usuario);
        return true;
    }

    public void logout() {
        SesionUsuario.getInstancia().cerrarSesion();
    }
}