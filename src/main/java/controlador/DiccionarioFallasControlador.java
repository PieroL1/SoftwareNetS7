package controlador;

import modelo.ConexionBD;
import vista.DiccionarioFallasVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DiccionarioFallasControlador {
    private DiccionarioFallasVista vista;

    public DiccionarioFallasControlador() {
    vista = new DiccionarioFallasVista();
    vista.setVisible(true);
    cargarIncidencias(); // 🔹 Ahora se llama después de hacer visible la ventana
    agregarEventos();
}


    private void agregarEventos() {
        vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarFalla();
            }
        });

        vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFallas();
            }
        });
    }

    private void registrarFalla() {
    String incidenciaSeleccionada = vista.getCmbIncidencias().getSelectedItem().toString();
    String titulo = vista.getTxtTituloRegistro().getText().trim();
    
    String descripcion = vista.getTxtDescripcion().getText().trim();
    String solucion = vista.getTxtSolucion().getText().trim();

    // Verificar que no haya campos vacíos
    if (incidenciaSeleccionada.equals("No hay incidencias pendientes") || 
        titulo.isEmpty() || descripcion.isEmpty() || solucion.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener el ID de la incidencia (extraerlo del ComboBox)
    int idIncidencia = Integer.parseInt(incidenciaSeleccionada.split(" - ")[0]);

    // Insertar en diccionario de fallas
    String sqlFalla = "INSERT INTO diccionario_fallas (titulo, descripcion, solucion, fecha_registro) VALUES (?, ?, ?, NOW())";

    try (Connection conn = ConexionBD.obtenerConexion();
         PreparedStatement stmtFalla = conn.prepareStatement(sqlFalla, Statement.RETURN_GENERATED_KEYS)) {

        stmtFalla.setString(1, titulo);
        stmtFalla.setString(2, descripcion);
        stmtFalla.setString(3, solucion);
        stmtFalla.executeUpdate();

        // Obtener el ID de la falla recién insertada
        ResultSet rs = stmtFalla.getGeneratedKeys();
        int idFalla = 0;
        if (rs.next()) {
            idFalla = rs.getInt(1);
        }

        // Actualizar la incidencia con la nueva falla y fecha de solución
        if (idFalla > 0) {
            String sqlIncidencia = "UPDATE incidencias SET id_falla = ?, estado = 'Solucionado', fecha_solucion = NOW() WHERE id_incidencia = ?";
            try (PreparedStatement stmtIncidencia = conn.prepareStatement(sqlIncidencia)) {
                stmtIncidencia.setInt(1, idFalla);
                stmtIncidencia.setInt(2, idIncidencia);
                stmtIncidencia.executeUpdate();
            }

            // Insertar en historial de equipos
            String sqlHistorial = "INSERT INTO historial_equipos (id_equipo, id_incidencia, fecha, observacion) VALUES (?, ?, NOW(), ?)";
            try (PreparedStatement stmtHistorial = conn.prepareStatement(sqlHistorial)) {
                stmtHistorial.setInt(1, obtenerIdEquipo(idIncidencia));
                stmtHistorial.setInt(2, idIncidencia);
                stmtHistorial.setString(3, "Falla resuelta y registrada en diccionario");
                stmtHistorial.executeUpdate();
            }
        }

        JOptionPane.showMessageDialog(null, "Falla registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al registrar la falla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private int obtenerIdEquipo(int idIncidencia) {
    String sql = "SELECT id_equipo FROM incidencias WHERE id_incidencia = ?";
    try (Connection conn = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idIncidencia);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_equipo");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el ID del equipo: " + e.getMessage());
    }
    return -1;
}
    
    private void limpiarCampos() {
    vista.getTxtTituloRegistro().setText("");
    vista.getTxtDescripcion().setText("");
    vista.getTxtSolucion().setText("");
    cargarIncidencias(); // Recargar incidencias después de registrar
}




    private void buscarFallas() {
        String tituloBusqueda = vista.getTxtTituloBusqueda().getText().trim();
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);

        String sql = "SELECT * FROM diccionario_fallas WHERE titulo LIKE ? OR descripcion LIKE ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tituloBusqueda + "%");
            stmt.setString(2, "%" + tituloBusqueda + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_falla"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("solucion")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error en la búsqueda: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarIncidencias() {
    vista.getCmbIncidencias().removeAllItems(); // 🔹 Asegurar que el combo esté limpio

    String sql = "SELECT id_incidencia, descripcion FROM incidencias WHERE estado = 'Pendiente'";
    try (Connection conn = ConexionBD.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        boolean hayIncidencias = false;
        while (rs.next()) {
            int idIncidencia = rs.getInt("id_incidencia");
            String descripcion = rs.getString("descripcion");

            vista.getCmbIncidencias().addItem(idIncidencia + " - " + descripcion);
            hayIncidencias = true;
        }

        if (!hayIncidencias) {
            vista.getCmbIncidencias().addItem("No hay incidencias pendientes");
            vista.getCmbIncidencias().setEnabled(false); // 🔹 Deshabilitar si no hay datos
        } else {
            vista.getCmbIncidencias().setEnabled(true); // 🔹 Habilitar si hay datos
        }

    } catch (SQLException e) {
        System.err.println("Error al cargar incidencias: " + e.getMessage());
    }
}

}
