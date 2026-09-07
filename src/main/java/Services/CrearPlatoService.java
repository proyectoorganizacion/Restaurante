package Services;

import Model.CrearPlato;
import Model.Propietario;
import Model.SesionUsuario;
import repositories.CrearPlatoRepository;

public class CrearPlatoService {
    private final CrearPlatoRepository crearPlatoRepository;

    public CrearPlatoService(CrearPlatoRepository crearPlatoRepository) {
        this.crearPlatoRepository = crearPlatoRepository;
    }

    public CrearPlato crearPlato(String rolUsuario, Long id, String nombre, Integer precio, String descripcion, String urlImagen, String categoria, String nitRestaurante) {

        if (!SesionUsuario.getInstancia().estaAutenticado()) {
            throw new IllegalArgumentException("Debe iniciar sesión para realizar esta acción.");
        }

        Propietario usuarioActual = SesionUsuario.getInstancia().getUsuarioAutenticado();
        if (!"PROPIETARIO".equalsIgnoreCase(usuarioActual.getRole())) {
            throw new IllegalArgumentException("Solo el propietario del restaurante puede crear platos.");
        }

        if (nombre == null || nombre.trim().isEmpty() ||
                descripcion == null || descripcion.trim().isEmpty() ||
                urlImagen == null || urlImagen.trim().isEmpty() ||
                categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        if (precio == null || precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser un número entero positivo mayor a 0.");
        }

        if (nitRestaurante == null || nitRestaurante.trim().isEmpty()) {
            throw new IllegalArgumentException("Todo plato debe estar asociado a un restaurante.");
        }

        CrearPlato nuevoPlato = new CrearPlato(id, nombre, precio, descripcion, urlImagen, categoria, nitRestaurante);
        crearPlatoRepository.guardar(nuevoPlato);

        return nuevoPlato;
    }

    public CrearPlato modificarPlato(
            Long idPlato,
            Integer nuevoPrecio,
            String nuevaDescripcion,
            String nitRestauranteUsuario) throws Exception {

        if (!SesionUsuario.getInstancia().estaAutenticado()) {
            throw new Exception("Debe iniciar sesión para realizar esta acción.");
        }

        Propietario usuarioActual = SesionUsuario.getInstancia().getUsuarioAutenticado();
        if (!"PROPIETARIO".equalsIgnoreCase(usuarioActual.getRole())) {
            throw new Exception("Solo el propietario del restaurante puede modificar platos.");
        }

        CrearPlato plato = crearPlatoRepository.buscarPorId(idPlato)
                .orElseThrow(() ->
                        new Exception("El plato con ID " + idPlato + " no existe.")
                );

        plato.modificarPlato(
                nuevoPrecio,
                nuevaDescripcion,
                nitRestauranteUsuario);

        return plato;
    }
}