package vista;

import javax.swing.*;
import java.awt.*;

public class ConsultaHistorialVista extends JFrame {
    private JTextField txtNombreEquipo;
    private JButton btnConsultar;
    private JTable tablaResultados;
    private JScrollPane scrollPane;

    public ConsultaHistorialVista() {
        setTitle("Consulta de Historial");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Nombre del equipo:"));
        txtNombreEquipo = new JTextField(20);
        panelSuperior.add(txtNombreEquipo);
        btnConsultar = new JButton("Consultar");
        panelSuperior.add(btnConsultar);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        // Crear la tabla y el scroll
        tablaResultados = new JTable();
        scrollPane = new JScrollPane(tablaResultados);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getTxtNombreEquipo() {
        return txtNombreEquipo;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public JTable getTablaResultados() {
        return tablaResultados;
    }
}
