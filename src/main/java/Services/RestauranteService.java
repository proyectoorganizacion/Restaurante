package Services;

import Model.Propietario;
import Model.Restaurante;
import Model.SesionUsuario;
import repositories.PropietarioRepository;
import repositories.RestauranteRepository;

public class RestauranteService {
    private RestauranteRepository restauranteRepository;
    private PropietarioRepository propietarioRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, PropietarioRepository propietarioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.propietarioRepository = propietarioRepository;
    }

    public void crearRestaurante(Restaurante restaurante) throws Exception {

        if (!SesionUsuario.getInstancia().estaAutenticado()) {
            throw new Exception("Debe iniciar sesión para realizar esta acción.");
        }

        Propietario usuarioActual = SesionUsuario.getInstancia().getUsuarioAutenticado();
        if (!"ADMINISTRADOR".equalsIgnoreCase(usuarioActual.getRole())) {
            throw new Exception("Acceso denegado: Solo el ADMINISTRADOR puede crear restaurantes.");
        }

        if (restaurante.getNombre() == null || restaurante.getNombre().trim().isEmpty() ||
                restaurante.getNit() == null || restaurante.getNit().trim().isEmpty() ||
                restaurante.getDireccion() == null || restaurante.getDireccion().trim().isEmpty() ||
                restaurante.getTelefono() == null || restaurante.getTelefono().trim().isEmpty() ||
                restaurante.getUrlLogo() == null || restaurante.getUrlLogo().trim().isEmpty() ||
                restaurante.getIdPropietario() == null) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        boolean existePropietario = propietarioRepository.getPropietarios().stream()
                .anyMatch(p -> p.getIdentification().equals(restaurante.getIdPropietario()) &&
                        "PROPIETARIO".equalsIgnoreCase(p.getRole()));

        if (!existePropietario) {
            throw new Exception("El ID suministrado no corresponde a un usuario con rol de propietario válido.");
        }

        if (!restaurante.getNit().matches("^[0-9]+$")) {
            throw new Exception("El campo NIT debe ser únicamente numérico.");
        }

        if (!restaurante.getTelefono().matches("^\\+?[0-9]+$") || restaurante.getTelefono().length() > 13) {
            throw new Exception("El campo Teléfono debe ser numérico, máximo 13 caracteres y puede contener '+'.");
        }

        if (restaurante.getNombre().matches("^[0-9]+$")) {
            throw new Exception("El nombre del restaurante no puede estar compuesto únicamente por números.");
        }

        restauranteRepository.guardar(restaurante);
    }
}