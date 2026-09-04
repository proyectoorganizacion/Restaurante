package Services;

import Model.CrearPlato;
import repositories.CrearPlatoRepository;

public class CrearPlatoService {
    private final CrearPlatoRepository crearPlatoRepository;

    public CrearPlatoService(CrearPlatoRepository crearPlatoRepository) {
        this.crearPlatoRepository = crearPlatoRepository;
    }

    public CrearPlato crearPlato(String rolUsuario, Long id, String nombre, Integer precio, String descripcion, String urlImagen, String categoria, Long idRestaurante) {

        if (!"PROPIETARIO".equalsIgnoreCase(rolUsuario)) {
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

        if (idRestaurante == null) {
            throw new IllegalArgumentException("Todo plato debe estar asociado a un restaurante.");
        }

        CrearPlato nuevoPlato = new CrearPlato(id, nombre, precio, descripcion, urlImagen, categoria, idRestaurante);

        crearPlatoRepository.guardar(nuevoPlato);

        return nuevoPlato;
    }
}