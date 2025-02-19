package controlador;

import modelo.ConexionBD;
import vista.ConsultaTareasVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaTareasControlador {
    private ConsultaTareasVista vista;
    private int idTecnico; // ID del técnico que consulta sus tareas

    public ConsultaTareasControlador(int idTecnico) {
        this.vista = new ConsultaTareasVista();
        this.idTecnico = idTecnico;
        agregarEventos();
        cargarTareas();
        vista.setVisible(true);
    }

    private void agregarEventos() {
        vista.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTareas();
            }
        });
    }

    private void cargarTareas() {
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar datos

        String query = "SELECT i.id_incidencia, i.descripcion, i.estado, a.fecha_asignacion " +
                       "FROM incidencias i " +
                       "INNER JOIN asignaciones a ON i.id_incidencia = a.id_incidencia " +
                       "WHERE a.id_tecnico = ? " +
                       "ORDER BY a.fecha_asignacion DESC";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idTecnico);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_incidencia"),
                    rs.getString("descripcion"),
                    rs.getString("estado"),
                    rs.getString("fecha_asignacion")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al obtener tareas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
