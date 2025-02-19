package controlador;

import modelo.ConexionBD;
import vista.RegistroIncidenciaVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroIncidenciaControlador {
    private RegistroIncidenciaVista vista;

    public RegistroIncidenciaControlador() {
        this.vista = new RegistroIncidenciaVista();
        agregarEventos();
        vista.setVisible(true);
    }

    private void agregarEventos() {
        vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarIncidencia();
            }
        });
        
        vista.getBtnCancelar().addActionListener(e -> vista.dispose());
    }

    private void registrarIncidencia() {
        String codigoEquipo = vista.getTxtCodigoEquipo().getText();
        String descripcion = vista.getTxtDescripcion().getText();

        if (codigoEquipo.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean registrado = ConexionBD.insertarIncidencia(codigoEquipo, descripcion);
        if (registrado) {
            JOptionPane.showMessageDialog(vista, "Incidencia registrada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrar la incidencia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
