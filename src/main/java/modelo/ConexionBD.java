package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
public static boolean insertarIncidencia(String codigoEquipo, int idUsuarioRegistro, String descripcion) {
    String query = "INSERT INTO incidencias (id_equipo, id_usuario_registro, descripcion, estado, fecha_registro) " +
                   "VALUES ((SELECT id_equipo FROM equipos WHERE codigo_equipo = ?), ?, ?, 'Pendiente', NOW())";
    try (Connection conexion = obtenerConexion();
         PreparedStatement stmt = conexion.prepareStatement(query)) {
        stmt.setString(1, codigoEquipo);
        stmt.setInt(2, idUsuarioRegistro);
        stmt.setString(3, descripcion);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Error al insertar incidencia: " + e.getMessage());
        return false;
    }
}

    
    
    public static List<String> obtenerIncidenciasPendientes() {
        List<String> incidencias = new ArrayList<>();
        String query = "SELECT id_incidencia, descripcion FROM incidencias WHERE estado = 'Pendiente'";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                incidencias.add(rs.getInt("id_incidencia") + " - " + rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener incidencias pendientes: " + e.getMessage());
        }
        return incidencias;
    }

    public static List<String> obtenerTecnicos() {
        List<String> tecnicos = new ArrayList<>();
        String query = "SELECT id_usuario, nombre FROM usuarios WHERE rol = 'Técnico'";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tecnicos.add(rs.getInt("id_usuario") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener técnicos: " + e.getMessage());
        }
        return tecnicos;
    }

    public static boolean asignarIncidencia(String incidencia, String tecnico) {
        String query = "INSERT INTO asignaciones (id_incidencia, id_tecnico, fecha_asignacion) VALUES (?, ?, NOW())";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(incidencia.split(" - ")[0]));
            stmt.setInt(2, Integer.parseInt(tecnico.split(" - ")[0]));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al asignar incidencia: " + e.getMessage());
            return false;
        }
    }
    
    public static int obtenerIdUsuario(String email, String password) {
    String query = "SELECT id_usuario FROM usuarios WHERE email = ? AND password = ?";
    try (Connection conexion = obtenerConexion();
         PreparedStatement stmt = conexion.prepareStatement(query)) {
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_usuario"); // Devuelve el ID del usuario
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ID del usuario: " + e.getMessage());
    }
    return -1; // Devuelve -1 si no encuentra el usuario
}
    
    public static List<String> obtenerListaRepuestos() {
    List<String> repuestos = new ArrayList<>();
    String query = "SELECT id_repuesto, nombre FROM repuestos"; // Ajusta según tu tabla de BD

    try (Connection conexion = obtenerConexion();
         PreparedStatement stmt = conexion.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            repuestos.add(rs.getInt("id_repuesto") + " - " + rs.getString("nombre"));
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener repuestos: " + e.getMessage());
    }
    return repuestos;
}


}
