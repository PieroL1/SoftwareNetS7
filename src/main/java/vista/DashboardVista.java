package vista;

import javax.swing.*;
import java.awt.*;

public class DashboardVista extends JFrame {
    private JButton btnRegistrarIncidencia, btnAsignarIncidencias, btnVerTareas, btnDiccionarioFallas, btnReportes, btnSolicitarRepuesto, btnConsultaHistorial;
    private String rolUsuario;

    public DashboardVista(String rolUsuario) {
        this.rolUsuario = rolUsuario;
        setTitle("Dashboard - Sistema de Soporte");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));

        btnRegistrarIncidencia = new JButton("Registrar Incidencia");
        btnAsignarIncidencias = new JButton("Asignar Incidencias");
        btnVerTareas = new JButton("Ver Tareas Asignadas");
        btnDiccionarioFallas = new JButton("Diccionario de Fallas");
        btnReportes = new JButton("Ver Reportes");
        btnSolicitarRepuesto = new JButton("Solicitar Repuesto");
        btnConsultaHistorial = new JButton("Consultar Historial");

        if (rolUsuario.equals("Usuario")) {
            add(btnRegistrarIncidencia);
        } else if (rolUsuario.equals("Técnico")) {
            add(btnRegistrarIncidencia);
            add(btnVerTareas);
            add(btnDiccionarioFallas);
            add(btnSolicitarRepuesto);
            add(btnConsultaHistorial);
        } else if (rolUsuario.equals("Jefe de Área")) {
            add(btnRegistrarIncidencia);
            add(btnAsignarIncidencias);
            add(btnConsultaHistorial);
            add(btnReportes);
        }

        setVisible(true);
    }

    public JButton getBtnRegistrarIncidencia() { return btnRegistrarIncidencia; }
    public JButton getBtnAsignarIncidencias() { return btnAsignarIncidencias; }
    public JButton getBtnVerTareas() { return btnVerTareas; }
    public JButton getBtnDiccionarioFallas() { return btnDiccionarioFallas; }
    public JButton getBtnReportes() { return btnReportes; }
    public JButton getBtnSolicitarRepuesto() { return btnSolicitarRepuesto; }
    public JButton getBtnConsultaHistorial() { return btnConsultaHistorial; }
}
