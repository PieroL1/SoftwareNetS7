package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class DiccionarioFallasVista extends JFrame {
    private JComboBox<String> cmbIncidencias;
    private JTextField txtTituloRegistro;
    private JTextField txtTituloBusqueda;
    private JTextArea txtDescripcion, txtSolucion;
    private JButton btnGuardar, btnBuscar;
    private JTable tablaFallos;
    private DefaultTableModel modeloTabla;

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

    public DiccionarioFallasVista() {
        // Configuración básica con tema moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setTitle("Diccionario de Fallas");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        
        // Panel de Registro con diseño mejorado
        JPanel panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBackground(Color.WHITE);
        panelRegistro.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Registrar Falla"),
                new EmptyBorder(20, 25, 20, 25)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;
        
        // Incidencia
        JLabel lblIncidencia = new JLabel("Incidencia:");
        lblIncidencia.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblIncidencia.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRegistro.add(lblIncidencia, gbc);
        
        cmbIncidencias = new JComboBox<>();
        cmbIncidencias.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbIncidencias.setBackground(Color.WHITE);
        cmbIncidencias.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(8, 12, 8, 12)));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panelRegistro.add(cmbIncidencias, gbc);
        
        // Título
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(new Color(50, 50, 50));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRegistro.add(lblTitulo, gbc);
        
        txtTituloRegistro = new JTextField(30);
        txtTituloRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTituloRegistro.setBackground(Color.WHITE);
        txtTituloRegistro.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(8, 12, 8, 12)));
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panelRegistro.add(txtTituloRegistro, gbc);
        
        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDescripcion.setForeground(new Color(50, 50, 50));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRegistro.add(lblDescripcion, gbc);
        
        txtDescripcion = new JTextArea(3, 30);
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDescripcion.setBackground(Color.WHITE);
        txtDescripcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(8, 12, 8, 12)));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRegistro.add(new JScrollPane(txtDescripcion), gbc);
        
        // Solución
        JLabel lblSolucion = new JLabel("Solución:");
        lblSolucion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSolucion.setForeground(new Color(50, 50, 50));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRegistro.add(lblSolucion, gbc);
        
        txtSolucion = new JTextArea(3, 30);
        txtSolucion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSolucion.setBackground(Color.WHITE);
        txtSolucion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(8, 12, 8, 12)));
        txtSolucion.setLineWrap(true);
        txtSolucion.setWrapStyleWord(true);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRegistro.add(new JScrollPane(txtSolucion), gbc);
        
        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setBackground(new Color(0, 123, 255));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardar.setBackground(new Color(0, 105, 217));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardar.setBackground(new Color(0, 123, 255));
            }
        });
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelRegistro.add(btnGuardar, gbc);
        
        add(panelRegistro, BorderLayout.NORTH);
        
        // Panel de Consulta con diseño mejorado
        JPanel panelConsulta = new JPanel(new BorderLayout(15, 15));
        panelConsulta.setBackground(Color.WHITE);
        panelConsulta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Buscar Fallas"),
                new EmptyBorder(20, 25, 20, 25)));
        
        // Campo de búsqueda
        txtTituloBusqueda = new JTextField(30);
        txtTituloBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTituloBusqueda.setBackground(Color.WHITE);
        txtTituloBusqueda.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                new EmptyBorder(8, 12, 8, 12)));
        panelConsulta.add(txtTituloBusqueda, BorderLayout.NORTH);
        
        // Botón Buscar
        btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBuscar.setBackground(new Color(76, 175, 80));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscar.setBackground(new Color(57, 162, 60));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscar.setBackground(new Color(76, 175, 80));
            }
        });
        panelConsulta.add(btnBuscar, BorderLayout.SOUTH);
        
        // Tabla de Fallas con estilo mejorado
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Solución");
        
        tablaFallos = new JTable(modeloTabla) {
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
        
        tablaFallos.setFillsViewportHeight(true);
        tablaFallos.getTableHeader().setReorderingAllowed(false);
        tablaFallos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaFallos.setRowHeight(28);
        tablaFallos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaFallos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaFallos.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaFallos.getTableHeader().setForeground(new Color(50, 50, 50));
        
        JScrollPane scrollPane = new JScrollPane(tablaFallos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panelConsulta.add(scrollPane, BorderLayout.CENTER);
        
        add(panelConsulta, BorderLayout.CENTER);
    }

    // Métodos getters existentes...
}