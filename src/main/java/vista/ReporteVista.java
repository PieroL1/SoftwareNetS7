package vista;

import controlador.ReporteControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ReporteVista extends JFrame {
    private JTable tablaReportes;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JButton btnCerrar;
    private Color colorPrimario = new Color(41, 128, 185);
    private Color colorFondo = new Color(236, 240, 241);
    private Color colorTexto = new Color(44, 62, 80);

    public ReporteVista() {
        setTitle("Reporte de Técnicos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(colorFondo);
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Reporte de Técnicos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTitulo.setForeground(colorTexto);
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Técnico");
        modeloTabla.addColumn("Total Reparaciones");

        tablaReportes = new JTable(modeloTabla);
        tablaReportes.setFont(new Font("Dialog", Font.PLAIN, 14));
        scrollPane = new JScrollPane(tablaReportes);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(colorFondo);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnCerrar.setBackground(Color.RED);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnCerrar);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        new ReporteControlador(tablaReportes);
    }
}