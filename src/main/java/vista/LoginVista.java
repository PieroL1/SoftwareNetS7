package vista;

import controlador.DashboardControlador;
import modelo.ConexionBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginVista extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel titleLabel;

    public LoginVista() {
        setTitle("Login - Sistema de Soporte");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titleLabel = new JLabel("Bienvenido al Sistema de Soporte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Correo electrónico:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        loginButton = new JButton("Iniciar sesión");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener((ActionEvent e) -> validarLogin());
    }

    private void validarLogin() {
    String email = emailField.getText();
    String password = new String(passwordField.getPassword());
    
    String rol = ConexionBD.obtenerRolUsuario(email, password);
    int idUsuario = ConexionBD.obtenerIdUsuario(email, password); // Nuevo método para obtener ID

    if (rol != null && idUsuario != -1) {
        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como " + rol, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        SwingUtilities.invokeLater(() -> new DashboardControlador(idUsuario, rol)); // Ahora pasamos el ID y el rol
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


}
