package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

public class ConsultaTareasVista extends JFrame {
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    public ConsultaTareasVista() {
        // Configuración básica con tema moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setTitle("Consulta de Tareas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con diseño mejorado
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20),
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230))));
        
        // Configuración de la tabla con estilo mejorado
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        modeloTabla.addColumn("ID Tarea");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Fecha Asignación");
        
        tablaTareas = new JTable(modeloTabla) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                
                if (!isRowSelected(row)) {
                    comp.setBackground(row % 2 == 0 ? 
                        new Color(255, 255, 255) : 
                        new Color(245, 248, 250));
                }
                
                return comp;
            }
        };
        
        tablaTareas.setFillsViewportHeight(true);
        tablaTareas.getTableHeader().setReorderingAllowed(false);
        tablaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaTareas.setRowHeight(28);
        tablaTareas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaTareas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaTareas.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaTareas.getTableHeader().setForeground(new Color(50, 50, 50));
        
        // Scroll pane personalizado
        JScrollPane scrollPane = new JScrollPane(tablaTareas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Botón actualizar con estilo moderno
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnActualizar.setBackground(new Color(0, 123, 255));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(0, 105, 217));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizar.setBackground(new Color(0, 123, 255));
            }
        });
        
        // Agregar componentes al panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);
        
        // Agregar panel a la ventana
        add(panel);
    }

    // Métodos getters
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