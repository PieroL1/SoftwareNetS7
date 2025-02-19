package controlador;

import modelo.ConexionBD;
import vista.ConsultaHistorialVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaHistorialControlador {
    private ConsultaHistorialVista vista;
    
    public ConsultaHistorialControlador() {
        this.vista = new ConsultaHistorialVista();
        agregarEventos();
        vista.setVisible(true);
    }
    
    private void agregarEventos() {
        vista.getBtnConsultar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarHistorial();
            }
        });
    }
    
    private void consultarHistorial() {
        String nombreEquipo = vista.getTxtNombreEquipo().getText().trim();
        if (nombreEquipo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el código del equipo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String query = "SELECT h.fecha, h.observacion FROM historial_equipos h INNER JOIN equipos e ON h.id_equipo = e.id_equipo WHERE e.codigo_equipo = ? ORDER BY h.fecha DESC";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombreEquipo);
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Fecha");
            model.addColumn("Observación");
            
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("fecha"), rs.getString("observacion")});
            }
            
            vista.getTablaResultados().setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al consultar el historial: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
