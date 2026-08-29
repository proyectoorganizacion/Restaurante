package repositories;

import Model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class RestauranteRepository {
    private List<Restaurante> listaRestaurantes = new ArrayList<>();

    public void guardar(Restaurante restaurante) {
        listaRestaurantes.add(restaurante);
    }

    public List<Restaurante> obtenerTodos() {
        return listaRestaurantes;
    }
}
