package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class RegistroIncidenciaVista extends JFrame {
    private JTextField txtCodigoEquipo;
    private JTextArea txtDescripcion;
    private JButton btnRegistrar, btnCancelar;
    private Color colorPrimario = new Color(41, 128, 185);
    private Color colorFondo = new Color(236, 240, 241);
    private Color colorTexto = new Color(44, 62, 80);

    public RegistroIncidenciaVista() {
        setTitle("Registrar Incidencia");
        setSize(500, 400);
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

        JLabel lblCodigo = new JLabel("Código del Equipo:");
        lblCodigo.setFont(new Font("Dialog", Font.BOLD, 14));
        lblCodigo.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 0;
        panelPrincipal.add(lblCodigo, gbc);

        txtCodigoEquipo = new JTextField();
        txtCodigoEquipo.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtCodigoEquipo.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1; gbc.gridy = 0;
        panelPrincipal.add(txtCodigoEquipo, gbc);

        JLabel lblDescripcion = new JLabel("Descripción del Problema:");
        lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 14));
        lblDescripcion.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrincipal.add(lblDescripcion, gbc);

        txtDescripcion = new JTextArea(6, 30);
        txtDescripcion.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setPreferredSize(new Dimension(250, 100));
        gbc.gridx = 1; gbc.gridy = 1;
        panelPrincipal.add(scrollDescripcion, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(colorFondo);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnRegistrar.setBackground(colorPrimario);
        btnRegistrar.setForeground(Color.WHITE);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTextField getTxtCodigoEquipo() { return txtCodigoEquipo; }
    public JTextArea getTxtDescripcion() { return txtDescripcion; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
