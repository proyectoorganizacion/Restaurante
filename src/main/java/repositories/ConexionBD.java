package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/restaurante_db?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Tu clave de MySQL

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}