package repositories;

import Model.Restaurante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestauranteRepository {

    // Cambiado a 'guardar' para coincidir exactamente con RestauranteService.java
    public boolean guardar(Restaurante restaurante) {
        String sql = "INSERT INTO restaurante (nombre, nit, id_propietario) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, restaurante.getNombre());
            ps.setString(2, restaurante.getNit());
            ps.setInt(3, 1); // ID de prueba de propietario

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar restaurante en MySQL: " + e.getMessage());
            return false;
        }
    }

    public List<Restaurante> getRestaurantes() {
        List<Restaurante> lista = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurante r = new Restaurante();
                r.setNombre(rs.getString("nombre"));
                r.setNit(rs.getString("nit"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar restaurantes: " + e.getMessage());
        }
        return lista;
    }
}