package controlador;

import modelo.ConexionBD;
import vista.SolicitarRepuestoVista;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SolicitarRepuestosControlador {
    private SolicitarRepuestoVista vista;

    public SolicitarRepuestosControlador() {
        vista = new SolicitarRepuestoVista();
        vista.setVisible(true);
    }

    public static boolean registrarSolicitud(int idIncidencia, int idRepuesto, int cantidad) {
    String estado = "Pendiente";
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String fechaSolicitud = now.format(formatter);

    String sqlInsert = "INSERT INTO solicitudes_repuestos (id_incidencia, id_repuesto, cantidad, estado, fecha_solicitud) VALUES (?, ?, ?, ?, ?)";
    String sqlUpdateStock = "UPDATE repuestos SET stock = stock - ? WHERE id_repuesto = ?";
    String sqlCheckStock = "SELECT stock FROM repuestos WHERE id_repuesto = ?";

    try (Connection conn = ConexionBD.obtenerConexion()) {
        conn.setAutoCommit(false); // Iniciar transacción

        // Verificar stock disponible
        try (PreparedStatement checkStockStmt = conn.prepareStatement(sqlCheckStock)) {
            checkStockStmt.setInt(1, idRepuesto);
            ResultSet rs = checkStockStmt.executeQuery();
            if (rs.next()) {
                int stockDisponible = rs.getInt("stock");
                if (stockDisponible < cantidad) {
                    JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Repuesto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Insertar la solicitud
        try (PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {
            insertStmt.setInt(1, idIncidencia);
            insertStmt.setInt(2, idRepuesto);
            insertStmt.setInt(3, cantidad);
            insertStmt.setString(4, estado);
            insertStmt.setString(5, fechaSolicitud);

            if (insertStmt.executeUpdate() <= 0) {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Error al registrar la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Restar del stock
        try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdateStock)) {
            updateStmt.setInt(1, cantidad);
            updateStmt.setInt(2, idRepuesto);
            if (updateStmt.executeUpdate() <= 0) {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Error al actualizar el stock.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        conn.commit(); // Confirmar la transacción
        JOptionPane.showMessageDialog(null, "Solicitud registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

}
