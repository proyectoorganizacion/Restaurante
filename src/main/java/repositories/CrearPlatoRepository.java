package repositories;

import Model.CrearPlato;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrearPlatoRepository {
    private final List<CrearPlato> listaPlatos = new ArrayList<>();

    public void guardar(CrearPlato plato) {
        listaPlatos.add(plato);
    }

    public List<CrearPlato> obtenerTodos() {
        return listaPlatos;
    }

// Modificar plato
    public Optional<CrearPlato> buscarPorId(Long id) {
        return listaPlatos.stream()
                .filter(plato -> plato.getId().equals(id))
                .findFirst();
    }
}
