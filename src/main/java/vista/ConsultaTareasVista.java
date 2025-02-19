package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultaTareasVista extends JFrame {
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    public ConsultaTareasVista() {
        setTitle("Consulta de Tareas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Tabla y modelo de datos
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Tarea");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Fecha Asignación");

        tablaTareas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaTareas);

        // Botón para actualizar
        btnActualizar = new JButton("Actualizar");

        // Agregar componentes al panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);

        // Agregar panel a la ventana
        add(panel);
    }

    public JTable getTablaTareas() {
        return tablaTareas;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }
}
