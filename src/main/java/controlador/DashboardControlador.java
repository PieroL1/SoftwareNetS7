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
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirRegistroIncidencia());
            vista.getBtnVerTareas().addActionListener(e -> abrirConsultaTareas());
            vista.getBtnDiccionarioFallas().addActionListener(e -> abrirDiccionarioFallas());
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirConsultaHistorial());
        } else if (rolUsuario.equals("Jefe de Área")) {
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirRegistroIncidencia());
            vista.getBtnAsignarIncidencias().addActionListener(e -> abrirAsignacionIncidencias());
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirConsultaHistorial());
            vista.getBtnReportes().addActionListener(e -> abrirEstadosPC());
            
        }
    }

    private void abrirRegistroIncidencia() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Registro de Incidencias");
    }
    
    private void abrirConsultaHistorial() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Consulta Historial");
    }

    private void abrirConsultaTareas() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Consulta de Tareas");
    }

    private void abrirDiccionarioFallas() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Diccionario de Fallas");
    }

    private void abrirAsignacionIncidencias() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Asignación de Incidencias");
    }

    private void abrirEstadosPC() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Estados de PC");
    }
    
    private void abrirSolicitarRepuesto() {
        JOptionPane.showMessageDialog(vista, "Abrir módulo de Solicitar Repuesto");
    }
}
