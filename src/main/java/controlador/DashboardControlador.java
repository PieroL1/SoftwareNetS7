package controlador;

import vista.DashboardVista;
import vista.LoginVista;
import vista.ConsultaHistorialVista;
import vista.ConsultaTareasVista;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import vista.ReporteVista;

public class DashboardControlador {
    private int idUsuario;
    private DashboardVista vista;
    private String rolUsuario;

    public DashboardControlador(int idUsuario, String rolUsuario) {
        this.idUsuario = idUsuario;
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
            vista.getBtnSolicitarRepuesto().addActionListener(e -> abrirSolicitudRepuesto());
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
        new ConsultaHistorialControlador();
    }

    private void abrirConsultaTareas() {
        new ConsultaTareasControlador(idUsuario);
    }

    private void abrirDiccionarioFallas() {
        new DiccionarioFallasControlador();
    }

    private void abrirAsignacionIncidencias() {
        new AsignacionIncidenciaControlador();
    }

    private void abrirEstadosPC() {
        new ReporteVista().setVisible(true);
    }

    private void abrirSolicitudRepuesto() {
        SwingUtilities.invokeLater(() -> new SolicitarRepuestosControlador());
    }
}
