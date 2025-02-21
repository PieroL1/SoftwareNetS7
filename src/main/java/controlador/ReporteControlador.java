package controlador;

import modelo.ConexionBD;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReporteControlador {
    private JTable tablaReportes;
    private DefaultTableModel modeloTabla;

    public ReporteControlador(JTable tablaReportes) {
        this.tablaReportes = tablaReportes;
        this.modeloTabla = (DefaultTableModel) tablaReportes.getModel();
        cargarDatos();
    }

    private void cargarDatos() {
        String consulta = "SELECT u.nombre AS tecnico, COUNT(a.id_incidencia) AS total_reparaciones " +
                          "FROM asignaciones a " +
                          "JOIN usuarios u ON a.id_tecnico = u.id_usuario " +
                          "WHERE u.rol = 'Técnico' " +
                          "GROUP BY a.id_tecnico";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pst = conexion.prepareStatement(consulta);
             ResultSet rs = pst.executeQuery()) {
            
            modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar datos
            
            while (rs.next()) {
                String nombreTecnico = rs.getString("tecnico");
                int totalReparaciones = rs.getInt("total_reparaciones");
                modeloTabla.addRow(new Object[]{nombreTecnico, totalReparaciones});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        }
    }
}
