package controlador;

import modelo.ConexionBD;
import vista.AsignacionIncidenciaVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AsignacionIncidenciaControlador {
    private AsignacionIncidenciaVista vista;

    public AsignacionIncidenciaControlador() {
        this.vista = new AsignacionIncidenciaVista();
        cargarIncidencias();
        cargarTecnicos();
        agregarEventos();
        vista.setVisible(true);
    }

    private void cargarIncidencias() {
        List<String> incidencias = ConexionBD.obtenerIncidenciasPendientes();
        for (String incidencia : incidencias) {
            vista.getComboIncidencias().addItem(incidencia);
        }
    }

    private void cargarTecnicos() {
        List<String> tecnicos = ConexionBD.obtenerTecnicos();
        for (String tecnico : tecnicos) {
            vista.getComboTecnicos().addItem(tecnico);
        }
    }

    private void agregarEventos() {
        vista.getBtnAsignar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarIncidencia();
            }
        });
        
        vista.getBtnCancelar().addActionListener(e -> vista.dispose());
    }

    private void asignarIncidencia() {
        String incidencia = (String) vista.getComboIncidencias().getSelectedItem();
        String tecnico = (String) vista.getComboTecnicos().getSelectedItem();

        if (incidencia == null || tecnico == null) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una incidencia y un técnico", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean asignado = ConexionBD.asignarIncidencia(incidencia, tecnico);
        if (asignado) {
            JOptionPane.showMessageDialog(vista, "Incidencia asignada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al asignar la incidencia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
