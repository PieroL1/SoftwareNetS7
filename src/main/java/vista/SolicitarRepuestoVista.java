package vista;

import controlador.SolicitarRepuestosControlador;
import modelo.ConexionBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SolicitarRepuestoVista extends JFrame {
    private JComboBox<String> incidenciaComboBox;
    private JComboBox<String> repuestoComboBox;
    private JTextField cantidadField;
    private JButton solicitarButton;

    public SolicitarRepuestoVista() {
        setTitle("Solicitar Repuesto");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Incidencia:"));
        incidenciaComboBox = new JComboBox<>(cargarIncidencias());
        add(incidenciaComboBox);

        add(new JLabel("Repuesto:"));
        repuestoComboBox = new JComboBox<>(cargarRepuestos());
        add(repuestoComboBox);

        add(new JLabel("Cantidad:"));
        cantidadField = new JTextField();
        add(cantidadField);

        solicitarButton = new JButton("Solicitar Repuesto");
        solicitarButton.addActionListener(this::solicitarRepuesto);
        add(solicitarButton);
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
            JOptionPane.showMessageDialog(this, "Seleccione una incidencia y un repuesto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idIncidencia = obtenerIdDesdeSeleccion(incidenciaSeleccionada);
        int idRepuesto = obtenerIdDesdeSeleccion(repuestoSeleccionado);
        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadField.getText());
            if (cantidad <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = SolicitarRepuestosControlador.registrarSolicitud(idIncidencia, idRepuesto, cantidad);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Solicitud registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerIdDesdeSeleccion(String seleccion) {
        return Integer.parseInt(seleccion.split(" - ")[0]);
    }
}
