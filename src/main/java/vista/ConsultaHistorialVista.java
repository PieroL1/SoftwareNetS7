package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

public class ConsultaHistorialVista extends JFrame {
    private JTextField txtNombreEquipo;
    private JButton btnConsultar;
    private JButton btnLimpiar;
    private JTable tablaResultados;
    private JScrollPane scrollPane;
    private JLabel lblEstado;

    public ConsultaHistorialVista() {
        // Configuración básica con tema moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setTitle("Consulta de Historial de Equipos");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        
        // Panel superior con diseño mejorado
JPanel panelSuperior = new JPanel(new GridBagLayout());
panelSuperior.setBackground(Color.WHITE);
panelSuperior.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
        new EmptyBorder(20, 25, 20, 25)));

GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(5, 5, 5, 5);
gbc.anchor = GridBagConstraints.LINE_END;

JLabel lblNombreEquipo = new JLabel("Nombre del equipo:");
lblNombreEquipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
lblNombreEquipo.setForeground(new Color(50, 50, 50));
gbc.gridx = 0;
gbc.gridy = 0;
panelSuperior.add(lblNombreEquipo, gbc);

// Campo de texto mejorado
txtNombreEquipo = new JTextField(40); // Aumentado de 35 a 40 caracteres
txtNombreEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
txtNombreEquipo.setBackground(Color.WHITE);
txtNombreEquipo.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
        new EmptyBorder(10, 15, 10, 15))); // Aumentado el padding horizontal
gbc.anchor = GridBagConstraints.LINE_START;
gbc.gridx = 1;
gbc.gridy = 0;
gbc.weightx = 1.0;
gbc.fill = GridBagConstraints.HORIZONTAL; // Asegura que el campo use todo el espacio disponible
panelSuperior.add(txtNombreEquipo, gbc);
        
        // Botones con estilo moderno
        btnConsultar = new JButton("Consultar");
        btnConsultar.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnConsultar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConsultar.setBackground(new Color(0, 123, 255)); // Azul moderno
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultar.setBackground(new Color(0, 105, 217));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultar.setBackground(new Color(0, 123, 255));
            }
        });
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setIcon(UIManager.getIcon("FileChooser.upFolderIcon"));
        btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLimpiar.setBackground(new Color(244, 67, 54)); // Rojo vibrante
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiar.setBackground(new Color(229, 57, 53));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiar.setBackground(new Color(244, 67, 54));
            }
        });
        
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelSuperior.add(btnConsultar, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        panelSuperior.add(btnLimpiar, gbc);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        // Configuración de la tabla con estilo mejorado
        String[] columnas = {"ID", "Fecha", "Equipo", "Resultado", "Competición", "Temporada"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaResultados = new JTable(modelo) {
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
        
        tablaResultados.setFillsViewportHeight(true);
        tablaResultados.getTableHeader().setReorderingAllowed(false);
        tablaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaResultados.setRowHeight(30);
        tablaResultados.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaResultados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaResultados.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaResultados.getTableHeader().setForeground(new Color(50, 50, 50));
        
        // Scroll pane personalizado
        scrollPane = new JScrollPane(tablaResultados);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior con estado
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 15));
        panelInferior.setBackground(Color.WHITE);
        panelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
        
        lblEstado = new JLabel("Listo para consultar");
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblEstado.setForeground(new Color(100, 100, 100));
        
        panelInferior.add(lblEstado);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Métodos getters
    public JTextField getTxtNombreEquipo() {
        return txtNombreEquipo;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTablaResultados() {
        return tablaResultados;
    }

    // Métodos de control
    public void setEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarResultados(Object[][] datos) {
        DefaultTableModel modelo = (DefaultTableModel) tablaResultados.getModel();
        modelo.setRowCount(0);

        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }

        if (datos.length > 0) {
            setEstado("Se encontraron " + datos.length + " registros");
        } else {
            setEstado("No se encontraron resultados para este equipo");
        }
    }

    public void limpiarFormulario() {
        txtNombreEquipo.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tablaResultados.getModel();
        modelo.setRowCount(0);
        setEstado("Listo para consultar");
        txtNombreEquipo.requestFocus();
    }
}