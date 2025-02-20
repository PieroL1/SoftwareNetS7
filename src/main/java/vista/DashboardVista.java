package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

public class DashboardVista extends JFrame {
    private JButton btnRegistrarIncidencia, btnAsignarIncidencias, btnVerTareas, 
                   btnDiccionarioFallas, btnReportes, btnSolicitarRepuesto, btnConsultaHistorial;
    private String rolUsuario;
    private JPanel sidebarPanel, contentPanel;
    private JLabel lblUsuario, lblRol;
    private Color colorPrimario = new Color(41, 128, 185);
    private Color colorSecundario = new Color(52, 152, 219);
    private Color colorFondo = new Color(236, 240, 241);
    private Color colorTexto = new Color(44, 62, 80);
    
    public DashboardVista(String rolUsuario) {
        this.rolUsuario = rolUsuario;
        setTitle("Dashboard - Sistema de Soporte Técnico");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);
        
        // Panel lateral (sidebar)
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(colorPrimario);
        sidebarPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));
        
        // Información del usuario
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(colorPrimario);
        userInfoPanel.setBorder(new EmptyBorder(0, 0, 40, 0));
        
        // Ícono de usuario
        JLabel lblUserIcon = new JLabel("\uD83D\uDC64");
        lblUserIcon.setFont(new Font("Dialog", Font.PLAIN, 48));
        lblUserIcon.setForeground(Color.WHITE);
        lblUserIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblUsuario = new JLabel("Usuario Actual");
        lblUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblRol = new JLabel(rolUsuario);
        lblRol.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblRol.setForeground(new Color(214, 234, 248));
        lblRol.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        userInfoPanel.add(lblUserIcon);
        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        userInfoPanel.add(lblUsuario);
        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userInfoPanel.add(lblRol);
        
        sidebarPanel.add(userInfoPanel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Crear botones con estilo moderno
        btnRegistrarIncidencia = crearBotonMenu("Registrar Incidencia", "\uD83D\uDCDD");
        btnAsignarIncidencias = crearBotonMenu("Asignar Incidencias", "\uD83D\uDCC3");
        btnVerTareas = crearBotonMenu("Ver Tareas Asignadas", "\uD83D\uDCCB");
        btnDiccionarioFallas = crearBotonMenu("Diccionario de Fallas", "\uD83D\uDCD5");
        btnReportes = crearBotonMenu("Ver Reportes", "\uD83D\uDCCA");
        btnSolicitarRepuesto = crearBotonMenu("Solicitar Repuesto", "\uD83D\uDEE0");
        btnConsultaHistorial = crearBotonMenu("Consultar Historial", "\uD83D\uDDD3");
        
        // Añadir botones según el rol
        if (rolUsuario.equals("Usuario")) {
            sidebarPanel.add(btnRegistrarIncidencia);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        } else if (rolUsuario.equals("Técnico")) {
            sidebarPanel.add(btnRegistrarIncidencia);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebarPanel.add(btnVerTareas);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebarPanel.add(btnDiccionarioFallas);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebarPanel.add(btnSolicitarRepuesto);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebarPanel.add(btnConsultaHistorial);
        } else if (rolUsuario.equals("Jefe de Área")) {
            sidebarPanel.add(btnRegistrarIncidencia);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebarPanel.add(btnAsignarIncidencias);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebarPanel.add(btnConsultaHistorial);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebarPanel.add(btnReportes);
        }
        
        // Panel de contenido principal
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(colorFondo);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        // Banner de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setBackground(colorSecundario);
        welcomePanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        welcomePanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 200));
        
        JLabel lblBienvenido = new JLabel("Bienvenido al Sistema de Soporte Técnico");
        lblBienvenido.setFont(new Font("Dialog", Font.BOLD, 24));
        lblBienvenido.setForeground(Color.WHITE);
        
        JLabel lblDescripcion = new JLabel("<html>Seleccione una opción del menú para comenzar.<br>El sistema le permite gestionar incidencias técnicas de manera eficiente.</html>");
        lblDescripcion.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblDescripcion.setForeground(new Color(236, 240, 241));
        
        welcomePanel.add(lblBienvenido, BorderLayout.NORTH);
        welcomePanel.add(lblDescripcion, BorderLayout.CENTER);
        
        contentPanel.add(welcomePanel, BorderLayout.NORTH);
        
        // Panel de estadísticas (ejemplo)
        if (!rolUsuario.equals("Usuario")) {
            JPanel statsPanel = new JPanel();
            statsPanel.setLayout(new GridLayout(1, 3, 20, 0));
            statsPanel.setBackground(colorFondo);
            statsPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
            
            statsPanel.add(crearPanelEstadistica("Incidencias Activas", "12", new Color(46, 204, 113)));
            statsPanel.add(crearPanelEstadistica("Pendientes", "5", new Color(230, 126, 34)));
            statsPanel.add(crearPanelEstadistica("Resueltas hoy", "8", new Color(52, 152, 219)));
            
            contentPanel.add(statsPanel, BorderLayout.CENTER);
        }
        
        // Agregar paneles principales al frame
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private JButton crearBotonMenu(String texto, String icono) {
        JButton btn = new JButton(icono + " " + texto);
        btn.setFont(new Font("Dialog", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorPrimario);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(230, 40));
        
        // Efectos al pasar el mouse
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(colorSecundario);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(colorPrimario);
            }
        });
        
        return btn;
    }
    
    private JPanel crearPanelEstadistica(String titulo, String valor, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 14));
        lblTitulo.setForeground(colorTexto);
        lblTitulo.setBorder(new EmptyBorder(15, 15, 5, 15));
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Dialog", Font.BOLD, 32));
        lblValor.setForeground(color);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Getters para los botones
    public JButton getBtnRegistrarIncidencia() { return btnRegistrarIncidencia; }
    public JButton getBtnAsignarIncidencias() { return btnAsignarIncidencias; }
    public JButton getBtnVerTareas() { return btnVerTareas; }
    public JButton getBtnDiccionarioFallas() { return btnDiccionarioFallas; }
    public JButton getBtnReportes() { return btnReportes; }
    public JButton getBtnSolicitarRepuesto() { return btnSolicitarRepuesto; }
    public JButton getBtnConsultaHistorial() { return btnConsultaHistorial; }
}