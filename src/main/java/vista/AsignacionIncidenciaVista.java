package vista;

import javax.swing.*;
import java.awt.*;

public class AsignacionIncidenciaVista extends JFrame {
    private JComboBox<String> comboIncidencias, comboTecnicos;
    private JButton btnAsignar, btnCancelar;

    public AsignacionIncidenciaVista() {
        setTitle("Asignar Incidencia");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Seleccionar Incidencia:"));
        comboIncidencias = new JComboBox<>();
        add(comboIncidencias);

        add(new JLabel("Seleccionar Técnico:"));
        comboTecnicos = new JComboBox<>();
        add(comboTecnicos);

        btnAsignar = new JButton("Asignar");
        btnCancelar = new JButton("Cancelar");
        add(btnAsignar);
        add(btnCancelar);
    }

    public JComboBox<String> getComboIncidencias() { return comboIncidencias; }
    public JComboBox<String> getComboTecnicos() { return comboTecnicos; }
    public JButton getBtnAsignar() { return btnAsignar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
