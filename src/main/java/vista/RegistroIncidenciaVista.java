package vista;

import javax.swing.*;
import java.awt.*;

public class RegistroIncidenciaVista extends JFrame {
    private JTextField txtCodigoEquipo, txtDescripcion;
    private JButton btnRegistrar, btnCancelar;

    public RegistroIncidenciaVista() {
        setTitle("Registrar Incidencia");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Código del Equipo:"));
        txtCodigoEquipo = new JTextField();
        add(txtCodigoEquipo);

        add(new JLabel("Descripción del Problema:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");
        add(btnRegistrar);
        add(btnCancelar);
    }

    public JTextField getTxtCodigoEquipo() { return txtCodigoEquipo; }
    public JTextField getTxtDescripcion() { return txtDescripcion; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
