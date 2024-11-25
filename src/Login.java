import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/github";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Adal1108";
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JButton btnIniciarSesion;
    private JLabel lblMensaje;


    public Login() {
       this.setTitle("Inicio de Sesi\u00f3n - Simulador SSOP");
       this.setSize(1200, 700);
       this.setDefaultCloseOperation(3);
       this.setLocationRelativeTo((Component)null);
       ImagenPanel panelConFondo = new ImagenPanel("src/img/Principal.jpg");
       panelConFondo.setLayout(new GridBagLayout());
       this.campoUsuario = new JTextField(20);
       this.campoContraseña = new JPasswordField(20);
       this.btnIniciarSesion = new JButton("");
       this.lblMensaje = new JLabel("", 0);
       this.campoUsuario.setFont(this.campoUsuario.getFont().deriveFont(18.0F));
       this.campoContraseña.setFont(this.campoContraseña.getFont().deriveFont(18.0F));
       this.campoUsuario.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
       this.campoContraseña.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
       this.campoUsuario.setPreferredSize(new Dimension(200, 40));
       this.campoContraseña.setPreferredSize(new Dimension(200, 40));
       JLabel lblUsuario = new JLabel("Usuario:");
       JLabel lblContraseña = new JLabel("Contrase\u00f1a:");
       lblUsuario.setForeground(Color.BLACK);
       lblUsuario.setFont(new Font("Arial", 1, 25));
       lblContraseña.setForeground(Color.BLACK);
       lblContraseña.setFont(new Font("Arial", 1, 25));
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(15, 15, 15, 15);
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.gridwidth = 2;
       gbc.anchor = 10;
       panelConFondo.add(lblUsuario, gbc);
       ++gbc.gridy;
       panelConFondo.add(this.campoUsuario, gbc);
       ++gbc.gridy;
       panelConFondo.add(lblContraseña, gbc);
       ++gbc.gridy;
       panelConFondo.add(this.campoContraseña, gbc);
       ++gbc.gridy;
       gbc.gridwidth = 1;
       gbc.anchor = 13;
       panelConFondo.add(this.btnIniciarSesion, gbc);
       gbc.gridx = 1;
       gbc.anchor = 17;
       gbc.gridx = 0;
       ++gbc.gridy;
       gbc.gridwidth = 2;
       gbc.anchor = 10;
       panelConFondo.add(this.lblMensaje, gbc);
       this.add(panelConFondo);
       this.add(panelConFondo);
       btnIniciarSesion.setIcon(redimensionarIcono("src/icons/icono_login2.png", 60, 60));
       btnIniciarSesion.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = campoUsuario.getText();
            String password = new String(campoContraseña.getPassword());
            boolean isValid = validateUser(username, password);
            if (isValid) {
                JOptionPane.showMessageDialog(panelConFondo, "¡Bienvenido!", "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panelConFondo, "Usuario o contraseña inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }});
    
    }
    private static boolean validateUser(String username, String password) {
        boolean isValid = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Llamar al procedimiento almacenado
            String query = "{CALL ValidarUsuario(?,?)}";
            try (CallableStatement stmt = connection.prepareCall(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        isValid = true; 
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;
    }
    private ImageIcon redimensionarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}