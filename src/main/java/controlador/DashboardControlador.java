package controlador;

import vista.DashboardVista;
import vista.LoginVista;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DashboardControlador {
    private DashboardVista vista;
    private String rolUsuario;

    public DashboardControlador(String rolUsuario) {
        this.rolUsuario = rolUsuario;
        this.vista = new DashboardVista(rolUsuario);
        agregarEventos();
        agregarCierreVentana();
        vista.setVisible(true);
    }

    private void agregarEventos() {
        if (rolUsuario.equals("Usuario")) {
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirRegistroIncidencia());
        } else if (rolUsuario.equals("Técnico")) {
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirRegistroIncidencia());
            vista.getBtnVerTareas().addActionListener(e -> abrirConsultaTareas());
            vista.getBtnDiccionarioFallas().addActionListener(e -> abrirDiccionarioFallas());
            vista.getBtnSolicitarRepuesto().addActionListener(e -> abrirSolicitarRepuesto());
            vista.getBtnConsultaHistorial().addActionListener(e -> abrirConsultaHistorial());
        } else if (rolUsuario.equals("Jefe de Área")) {
            vista.getBtnRegistrarIncidencia().addActionListener(e -> abrirRegistroIncidencia());
            vista.getBtnAsignarIncidencias().addActionListener(e -> abrirAsignacionIncidencias());
            vista.getBtnConsultaHistorial().addActionListener(e -> abrirConsultaHistorial());
            vista.getBtnReportes().addActionListener(e -> abrirEstadosPC());
        }
    }

    private void agregarCierreVentana() {
        vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                vista.dispose();
                SwingUtilities.invokeLater(() -> new LoginVista().setVisible(true));
            }
        });
    }

    private void abrirRegistroIncidencia() {
        new RegistroIncidenciaControlador();
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
