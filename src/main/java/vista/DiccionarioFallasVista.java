package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class DiccionarioFallasVista extends JFrame {
    private JComboBox<String> cmbIncidencias; // 🔹 Selección de incidencia
    private JTextField txtTituloRegistro; // 🔹 Para registrar fallas
    private JTextField txtTituloBusqueda; // 🔹 Para buscar fallas
    private JTextArea txtDescripcion, txtSolucion;
    private JButton btnGuardar, btnBuscar;
    private JTable tablaFallos;
    private DefaultTableModel modeloTabla;

    public DiccionarioFallasVista() {
        setTitle("Diccionario de Fallas");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de Registro
        JPanel panelRegistro = new JPanel(new GridLayout(5, 2, 10, 10));
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registrar Falla"));

        panelRegistro.add(new JLabel("Incidencia:"));
        cmbIncidencias = new JComboBox<>(); // 🔹 Se cargará con incidencias activas
        panelRegistro.add(cmbIncidencias);

        panelRegistro.add(new JLabel("Título:"));
        txtTituloRegistro = new JTextField();
        panelRegistro.add(txtTituloRegistro);

        panelRegistro.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextArea(2, 20);
        panelRegistro.add(new JScrollPane(txtDescripcion));

        panelRegistro.add(new JLabel("Solución:"));
        txtSolucion = new JTextArea(2, 20);
        panelRegistro.add(new JScrollPane(txtSolucion));

        btnGuardar = new JButton("Guardar");
        panelRegistro.add(btnGuardar);

        add(panelRegistro, BorderLayout.NORTH);

        // Panel de Consulta
        JPanel panelConsulta = new JPanel(new BorderLayout());
        panelConsulta.setBorder(BorderFactory.createTitledBorder("Buscar Fallas"));

        txtTituloBusqueda = new JTextField();
        panelConsulta.add(txtTituloBusqueda, BorderLayout.NORTH);

        btnBuscar = new JButton("Buscar");
        panelConsulta.add(btnBuscar, BorderLayout.SOUTH);

        // Tabla de Fallas
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Título", "Descripción", "Solución"}, 0);
        tablaFallos = new JTable(modeloTabla);
        panelConsulta.add(new JScrollPane(tablaFallos), BorderLayout.CENTER);

        add(panelConsulta, BorderLayout.CENTER);
    }

    public JComboBox<String> getCmbIncidencias() {
        return cmbIncidencias;
    }

    public JTextField getTxtTituloRegistro() { 
    return txtTituloRegistro; 
}

public JTextField getTxtTituloBusqueda() { 
    return txtTituloBusqueda; 
}


    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextArea getTxtSolucion() {
        return txtSolucion;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTable getTablaFallos() {
        return tablaFallos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}
