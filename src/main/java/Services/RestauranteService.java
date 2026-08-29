package Services;

import Model.Restaurante;
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

        // 1). Campos obligatorios
        if (restaurante.getNombre() == null || restaurante.getNombre().trim().isEmpty() ||
                restaurante.getNit() == null || restaurante.getNit().trim().isEmpty() ||
                restaurante.getDireccion() == null || restaurante.getDireccion().trim().isEmpty() ||
                restaurante.getTelefono() == null || restaurante.getTelefono().trim().isEmpty() ||
                restaurante.getUrlLogo() == null || restaurante.getUrlLogo().trim().isEmpty() ||
                restaurante.getIdPropietario() == null) {
            throw new Exception("Todos los campos (Nombre, NIT, Dirección, Teléfono, UrlLogo y ID Propietario) son obligatorios.");
        }

        // 2). Validar que el ID corresponda a un usuario existente (y con rol Propietario si la clase propietario tiene esa logica)
        boolean existePropietario = propietarioRepository.getPropietarios().stream()
                .anyMatch(p -> p.getIdentification().equals(restaurante.getIdPropietario()) &&
                        "PROPIETARIO".equalsIgnoreCase(p.getRole()));

        if (!existePropietario) {
            throw new Exception("El ID suministrado no corresponde a un usuario con rol de propietario válido.");
        }

        // 3). NIT unicamente numerico
        if (!restaurante.getNit().matches("^[0-9]+$")) {
            throw new Exception("El campo NIT debe ser únicamente numérico.");
        }

        // 4). Telefono: unicamente numeros, maximo 13 caracteres, puede tener '+' al inicio
        if (!restaurante.getTelefono().matches("^\\+?[0-9]+$")) {
            throw new Exception("El campo Teléfono solo debe contener números y opcionalmente el símbolo '+' al inicio.");
        }
        if (restaurante.getTelefono().length() > 13) {
            throw new Exception("El campo Teléfono debe contener un máximo de 13 caracteres.");
        }

        // 5). Nombre puede contener numeros, pero unicamente numeros
        if (restaurante.getNombre().matches("^[0-9]+$")) {
            throw new Exception("El nombre del restaurante no puede estar compuesto únicamente por números.");
        }

        // Guarda si supera todas las validaciones
        restauranteRepository.guardar(restaurante);
    }
}

