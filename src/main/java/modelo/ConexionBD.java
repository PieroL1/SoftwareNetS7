package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/softwarenet_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public static boolean validarUsuario(String email, String password) {
        String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            System.out.println("Intentando login con:");
            System.out.println("Email: " + email);
            System.out.println("Contraseña: " + password);

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            boolean existe = rs.next();
            System.out.println("¿Usuario encontrado?: " + existe);
            return existe;
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
            return false;
        }
    }



    public static String obtenerRolUsuario(String email, String password) {
        String query = "SELECT rol FROM usuarios WHERE email = ? AND password = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("rol");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rol del usuario: " + e.getMessage());
        }
        return null; 
    }
}
