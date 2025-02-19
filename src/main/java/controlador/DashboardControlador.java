package controlador;

import vista.DashboardVista;
import javax.swing.*;

public class DashboardControlador {
    private DashboardVista vista;
    private String rolUsuario;

    public DashboardControlador(String rolUsuario) {
        this.rolUsuario = rolUsuario;
        this.vista = new DashboardVista(rolUsuario);
        agregarEventos();
        vista.setVisible(true);
    }

    private void agregarEventos() {
        if (rolUsuario.equals("Usuario")) {
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirRegistroIncidencia());
        } else if (rolUsuario.equals("Técnico")) {
            vista.getBtnVerTareas().addActionListener(e -> abrirConsultaTecnicos());
            vista.getBtnDiccionarioFallas().addActionListener(e -> abrirDiccionarioFallas());
        } else if (rolUsuario.equals("Jefe de Área")) {
            vista.getBtnAsignarIncidencias().addActionListener(e -> abrirAsignacionIncidencias());
            vista.getBtnReportes().addActionListener(e -> abrirReportes());
        }
    }

    private void abrirRegistroIncidencia() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Registro de Incidencias");
    }

    private void abrirConsultaTecnicos() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Consulta de Técnicos");
    }

    private void abrirDiccionarioFallas() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Diccionario de Fallas");
    }

    private void abrirAsignacionIncidencias() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Asignación de Incidencias");
    }

    private void abrirReportes() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Reportes");
    }
}
