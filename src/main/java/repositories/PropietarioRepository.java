package repositories;

import Model.Propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropietarioRepository {

    // 1. Guardar propietario en MySQL
    public boolean guardarPropietario(Propietario propietario) {
        String sql = "INSERT INTO usuario (nombre, apellido, doc_identidad, correo, password, id_rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, propietario.getName());
            ps.setString(2, propietario.getLastname());
            ps.setString(3, propietario.getIdentification());
            ps.setString(4, propietario.getEmail());
            ps.setString(5, propietario.getPassword());
            ps.setInt(6, 2); // ID 2 = Rol PROPIETARIO

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar en MySQL: " + e.getMessage());
            return false;
        }
    }

    // 2. Obtener lista de propietarios desde MySQL (Resuelve el error de RestauranteService)
    public List<Propietario> getPropietarios() {
        List<Propietario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE id_rol = 2";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Propietario p = new Propietario(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("doc_identidad"),
                        "", // teléfono si aplica
                        null, // fecha de nacimiento si aplica
                        rs.getString("correo"),
                        rs.getString("password")
                );
                p.setRole("PROPIETARIO");
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar propietarios en MySQL: " + e.getMessage());
        }

        return lista;
    }

    // 3. Mostrar propietarios en consola
    public void mostrarPropietarios() {
        List<Propietario> lista = getPropietarios();
        System.out.println("\n--- LISTA DE PROPIETARIOS REGISTRADOS EN BD ---");
        for (Propietario p : lista) {
            System.out.println("Nombre: " + p.getName() + " " + p.getLastname() +
                    " | Documento: " + p.getIdentification() +
                    " | Correo: " + p.getEmail());
        }
        System.out.println("-----------------------------------------------\n");
    }
}