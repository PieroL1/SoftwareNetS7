package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class AsignacionIncidenciaVista extends JFrame {
    private JComboBox<String> comboIncidencias, comboTecnicos;
    private JButton btnAsignar, btnCancelar;
    private Color colorPrimario = new Color(41, 128, 185);
    private Color colorFondo = new Color(236, 240, 241);
    private Color colorTexto = new Color(44, 62, 80);

    public AsignacionIncidenciaVista() {
        setTitle("Asignar Incidencia");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(colorFondo);
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblIncidencia = new JLabel("Seleccionar Incidencia:");
        lblIncidencia.setFont(new Font("Dialog", Font.BOLD, 14));
        lblIncidencia.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 0;
        panelPrincipal.add(lblIncidencia, gbc);

        comboIncidencias = new JComboBox<>();
        comboIncidencias.setFont(new Font("Dialog", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 0;
        panelPrincipal.add(comboIncidencias, gbc);

        JLabel lblTecnico = new JLabel("Seleccionar Técnico:");
        lblTecnico.setFont(new Font("Dialog", Font.BOLD, 14));
        lblTecnico.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrincipal.add(lblTecnico, gbc);

        comboTecnicos = new JComboBox<>();
        comboTecnicos.setFont(new Font("Dialog", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 1;
        panelPrincipal.add(comboTecnicos, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(colorFondo);

        btnAsignar = new JButton("Asignar");
        btnAsignar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnAsignar.setBackground(colorPrimario);
        btnAsignar.setForeground(Color.WHITE);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);

        panelBotones.add(btnAsignar);
        panelBotones.add(btnCancelar);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JComboBox<String> getComboIncidencias() { return comboIncidencias; }
    public JComboBox<String> getComboTecnicos() { return comboTecnicos; }
    public JButton getBtnAsignar() { return btnAsignar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
