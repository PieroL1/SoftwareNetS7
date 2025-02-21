package vista;
import controlador.DashboardControlador;
import modelo.ConexionBD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LoginVista extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel titleLabel;
    private JPanel mainPanel;
    private Color primaryColor = new Color(41, 128, 185); // Azul corporativo
    private Color secondaryColor = new Color(52, 73, 94); // Azul oscuro
    private Color accentColor = new Color(46, 204, 113); // Verde para botón
    private Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private Font regularFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);
    
    public LoginVista() {
        setTitle("Login - Sistema de Soporte");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        //setIconImage(new ImageIcon(getClass().getResource("/logo.png")).getImage());

        // Panel principal con gradiente
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, secondaryColor, 0, getHeight(), new Color(44, 62, 80));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Panel de contenido principal (tarjeta blanca)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 30, 30, 30));
        contentPanel.setMaximumSize(new Dimension(350, 400));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel para el título
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Título
        titleLabel = new JLabel("USER LOGIN");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(secondaryColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.add(titleLabel);
        
        
        
       // Panel para el logo
JPanel logoPanel = new JPanel();
logoPanel.setOpaque(false);
logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Cargar la imagen y redimensionarla
//ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
//Image image = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Ajusta el tamaño
//JLabel logoLabel = new JLabel(new ImageIcon(image));
//logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Agregar la imagen al panel del logo
//logoPanel.add(logoLabel);

// Agregar un espacio antes del título
titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
titlePanel.add(logoPanel);
titlePanel.add(Box.createRigidArea(new Dimension(10,10))); // Espacio entre imagen y título



        // Panel de formulario
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        // Crear contenedor para el campo de email con icono
        JPanel emailContainer = new JPanel();
        emailContainer.setLayout(new BorderLayout());
        emailContainer.setBackground(new Color(240, 240, 240));
        emailContainer.setBorder(new LineBorder(new Color(240, 240, 240), 1, true));
        emailContainer.setMaximumSize(new Dimension(300, 50));
        
        // Icono de usuario
        JLabel userIconLabel = new JLabel("");
        userIconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userIconLabel.setPreferredSize(new Dimension(40, 30));
        
        // Campo de email
        emailField = new JTextField();
        emailField.setBorder(null);
        emailField.setFont(regularFont);
        emailField.setBackground(new Color(240, 240, 240));
        
        emailContainer.add(userIconLabel, BorderLayout.WEST);
        emailContainer.add(emailField, BorderLayout.CENTER);
        
        // Crear contenedor para el campo de contraseña con icono
        JPanel passwordContainer = new JPanel();
        passwordContainer.setLayout(new BorderLayout());
        passwordContainer.setBackground(new Color(240, 240, 240));
        passwordContainer.setBorder(new LineBorder(new Color(240, 240, 240), 1, true));
        passwordContainer.setMaximumSize(new Dimension(300, 50));
        
        // Icono de candado
        JLabel lockIconLabel = new JLabel("");
        lockIconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lockIconLabel.setPreferredSize(new Dimension(40, 30));
        
        // Campo de contraseña
        passwordField = new JPasswordField();
        passwordField.setBorder(null);
        passwordField.setFont(regularFont);
        passwordField.setBackground(new Color(240, 240, 240));
        
        passwordContainer.add(lockIconLabel, BorderLayout.WEST);
        passwordContainer.add(passwordField, BorderLayout.CENTER);
        
        // Enlace de olvidó contraseña
        JLabel forgotPassword = new JLabel("Forgot Password?");
        forgotPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        forgotPassword.setForeground(new Color(150, 150, 150));
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Botón de login redondeado
        loginButton = new JButton("LOGIN");
        loginButton.setFont(buttonFont);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(90, 50, 90)); // Color morado
        loginButton.setBorder(new EmptyBorder(10, 15, 10, 15));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(300, 50));
        
        // Efecto hover para el botón
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(70, 40, 70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(90, 50, 90));
            }
        });
        
        // Footer con copyright
        JLabel footerLabel = new JLabel("© 2025 Sistema de Soporte.");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        footerLabel.setForeground(Color.GRAY);
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Agregar elementos al panel de formulario
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(emailContainer);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(passwordContainer);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(forgotPassword);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(loginButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(footerLabel);
        
        // Agregar paneles al panel de contenido
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(titlePanel);
        contentPanel.add(formPanel);
        
        // Añadir espacio superior para centrar
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(contentPanel);
        mainPanel.add(Box.createVerticalGlue());
        
        // Añadir el panel principal al frame
        add(mainPanel);
        
        // Eventos
        loginButton.addActionListener((ActionEvent e) -> validarLogin());
        getRootPane().setDefaultButton(loginButton);
    }

    private void validarLogin() {
        // Efecto visual al presionar botón
        Color originalColor = loginButton.getBackground();
        loginButton.setBackground(originalColor.darker().darker());
        Timer timer = new Timer(100, (e) -> loginButton.setBackground(originalColor));
        timer.setRepeats(false);
        timer.start();
        
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        
        // Mostrar indicador de carga
        loginButton.setEnabled(false);
        loginButton.setText("VERIFICANDO...");
        
        // Simulamos un pequeño retraso para mostrar el efecto visual
        Timer loadTimer = new Timer(800, (e) -> {
            String rol = ConexionBD.obtenerRolUsuario(email, password);
            int idUsuario = ConexionBD.obtenerIdUsuario(email, password);
            
            loginButton.setEnabled(true);
            loginButton.setText("LOGIN");
            
            if (rol != null && idUsuario != -1) {
                JOptionPane.showMessageDialog(this, 
                    "Inicio de sesión exitoso\nHas ingresado como: " + rol, 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                SwingUtilities.invokeLater(() -> new DashboardControlador(idUsuario, rol));
                dispose();
            } else {
                // Efecto de shake para indicar error
                shakeComponent(mainPanel);
                
                JOptionPane.showMessageDialog(this,
                    "Credenciales incorrectas\nPor favor, verifique su correo y contraseña",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    
                passwordField.setText("");
                passwordField.requestFocus();
            }
        });
        loadTimer.setRepeats(false);
        loadTimer.start();
    }
    
    private void shakeComponent(Component component) {
        final Point point = component.getLocation();
        final int delay = 50;
        final int distance = 10;
        
        Timer timer = new Timer(delay, null);
        timer.addActionListener(new AbstractAction() {
            private int direction = 1;
            private int steps = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (steps <= 4) {
                    component.setLocation(point.x + (direction * distance), point.y);
                    direction = -direction;
                    steps++;
                } else {
                    component.setLocation(point);
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
}