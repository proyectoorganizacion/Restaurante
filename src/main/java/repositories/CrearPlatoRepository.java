package repositories;

import Model.CrearPlato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrearPlatoRepository {

    // 1. Guardar plato en MySQL
    public boolean guardar(CrearPlato plato) {
        String sql = "INSERT INTO plato (nombre, precio, activo, id_restaurante) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, plato.getNombre());
            ps.setInt(2, plato.getPrecio()); // Convertido a Integer segun tu modelo
            ps.setBoolean(3, true);
            ps.setInt(4, 1); // ID de prueba de restaurante

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar plato en MySQL: " + e.getMessage());
            return false;
        }
    }

    public boolean guardarPlato(CrearPlato plato) {
        return guardar(plato);
    }

    // 2. Buscar plato por ID usando el constructor de 8 parametros de tu clase CrearPlato
    public Optional<CrearPlato> buscarPorId(Long id) {
        String sql = "SELECT * FROM plato WHERE id = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CrearPlato plato = new CrearPlato(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getInt("precio"),
                            "Sin descripción",
                            "http://imagen.com",
                            "General",
                            "12345"
                    );
                    return Optional.of(plato);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar plato por ID en MySQL: " + e.getMessage());
        }
        return Optional.empty();
    }

    // 3. Obtener todos los platos
    public List<CrearPlato> getPlatos() {
        List<CrearPlato> lista = new ArrayList<>();
        String sql = "SELECT * FROM plato";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CrearPlato plato = new CrearPlato(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getInt("precio"),
                        "Sin descripción",
                        "http://imagen.com",
                        "General",
                        "12345"
                );
                lista.add(plato);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar platos en MySQL: " + e.getMessage());
        }
        return lista;
    }
}