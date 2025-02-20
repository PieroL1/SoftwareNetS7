package vista;

import controlador.SolicitarRepuestosControlador;
import modelo.ConexionBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class SolicitarRepuestoVista extends JFrame {
    private JComboBox<String> incidenciaComboBox;
    private JComboBox<String> repuestoComboBox;
    private JTextField cantidadField;
    private JButton solicitarButton;

    public SolicitarRepuestoVista() {
        // Configuración básica con tema moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setTitle("Solicitar Repuesto");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con diseño mejorado
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(255, 255, 255));
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30),
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230))));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.LINE_END;
        
        // Incidencia
        JLabel lblIncidencia = new JLabel("Incidencia:");
        lblIncidencia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblIncidencia.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2; // Peso fijo para etiquetas
        panelPrincipal.add(lblIncidencia, gbc);
        
        incidenciaComboBox = new JComboBox<>(cargarIncidencias());
        incidenciaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        incidenciaComboBox.setBackground(new Color(255, 255, 255));
        incidenciaComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(10, 15, 10, 15)));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8; // Peso fijo para campos
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(incidenciaComboBox, gbc);
        
        // Repuesto
        JLabel lblRepuesto = new JLabel("Repuesto:");
        lblRepuesto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblRepuesto.setForeground(new Color(50, 50, 50));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        panelPrincipal.add(lblRepuesto, gbc);
        
        repuestoComboBox = new JComboBox<>(cargarRepuestos());
        repuestoComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        repuestoComboBox.setBackground(new Color(255, 255, 255));
        repuestoComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(10, 15, 10, 15)));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(repuestoComboBox, gbc);
        
        // Cantidad
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCantidad.setForeground(new Color(50, 50, 50));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        panelPrincipal.add(lblCantidad, gbc);
        
        cantidadField = new JTextField(20);
        cantidadField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cantidadField.setBackground(new Color(255, 255, 255));
        cantidadField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(10, 15, 10, 15)));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(cantidadField, gbc);
        
        // Botón Solicitar
        solicitarButton = new JButton("Solicitar Repuesto");
        solicitarButton.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        solicitarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        solicitarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        solicitarButton.setBackground(new Color(0, 123, 255));
        solicitarButton.setForeground(new Color(255, 255, 255));
        solicitarButton.setFocusPainted(false);
        solicitarButton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        solicitarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                solicitarButton.setBackground(new Color(0, 105, 217));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                solicitarButton.setBackground(new Color(0, 123, 255));
            }
        });
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.8;
        gbc.insets = new Insets(15, 8, 8, 8);
        panelPrincipal.add(solicitarButton, gbc);
        
        add(panelPrincipal);
    }

    private String[] cargarIncidencias() {
        List<String> incidencias = ConexionBD.obtenerIncidenciasPendientes();
        return incidencias.toArray(new String[0]);
    }

    private String[] cargarRepuestos() {
        List<String> repuestos = ConexionBD.obtenerListaRepuestos();
        return repuestos.toArray(new String[0]);
    }

    private void solicitarRepuesto(ActionEvent e) {
        String incidenciaSeleccionada = (String) incidenciaComboBox.getSelectedItem();
        String repuestoSeleccionado = (String) repuestoComboBox.getSelectedItem();

        if (incidenciaSeleccionada == null || repuestoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione una incidencia y un repuesto.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idIncidencia = obtenerIdDesdeSeleccion(incidenciaSeleccionada);
        int idRepuesto = obtenerIdDesdeSeleccion(repuestoSeleccionado);
        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadField.getText());
            if (cantidad <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Ingrese una cantidad válida.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = SolicitarRepuestosControlador.registrarSolicitud(idIncidencia, idRepuesto, cantidad);
        if (exito) {
            JOptionPane.showMessageDialog(this, 
                "Solicitud registrada exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Error al registrar la solicitud.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerIdDesdeSeleccion(String seleccion) {
        return Integer.parseInt(seleccion.split(" - ")[0]);
    }
}