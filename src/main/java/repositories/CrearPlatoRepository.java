package repositories;

import Model.CrearPlato;
import java.util.ArrayList;
import java.util.List;

public class CrearPlatoRepository {
    private final List<CrearPlato> listaPlatos = new ArrayList<>();

    public void guardar(CrearPlato plato) {
        listaPlatos.add(plato);
    }

    public List<CrearPlato> obtenerTodos() {
        return listaPlatos;
    }
}
