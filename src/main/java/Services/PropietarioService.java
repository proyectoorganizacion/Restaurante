package Services;

import Model.Propietario;
import Model.SesionUsuario;
import repositories.PropietarioRepository;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PropietarioService {
    private PropietarioRepository propietarioRepository;

    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    public void registrarPropietario(Propietario propietario){

        if (!SesionUsuario.getInstancia().estaAutenticado()) {
            throw new IllegalArgumentException("Debe iniciar sesión para realizar esta acción.");
        }

        Propietario usuarioActual = SesionUsuario.getInstancia().getUsuarioAutenticado();
        if (!"ADMINISTRADOR".equalsIgnoreCase(usuarioActual.getRole())) {
            throw new IllegalArgumentException("Acceso denegado: Solo el ADMINISTRADOR puede crear propietarios.");
        }

        validarPropietario(propietario);

        propietario.setRole("PROPIETARIO");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(propietario.getPassword());
        propietario.setPassword(passwordHash);

        propietarioRepository.guardarPropietario(propietario);
        System.out.println("Propietario registrado exitosamente.");
    }

    private void validarPropietario(Propietario propietario){

        if (propietario.getIdentification().isBlank()) {
            throw new IllegalArgumentException("Identification is required");
        }

        if (!propietario.getIdentification().matches("\\d+")) {
            throw new IllegalArgumentException("Identification must contain only numbers");
        }

        if (propietario.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (!propietario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (propietario.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone is required");
        }

        if (propietario.getPhone().length() > 13) {
            throw new IllegalArgumentException("Phone must contain a maximum of 13 characters");
        }

        if (!propietario.getPhone().matches("^\\+?\\d+$")) {
            throw new IllegalArgumentException(
                    "Phone must contain only numbers and an optional + at the beginning"
            );
        }

        if (propietario.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (propietario.getBirthdate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birthdate cannot be in the future");
        }

        if (Period.between(propietario.getBirthdate(), LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("The owner must be at least 18 years old");
        }
    }
}