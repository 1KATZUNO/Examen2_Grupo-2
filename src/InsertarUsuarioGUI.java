import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class InsertarUsuarioGUI extends JFrame {
    private JTextField txtPrimerNombre, txtSegundoNombre, txtPrimerApellido, txtSegundoApellido, txtLogin, txtFechaCreacion;
    private JPasswordField txtClave;
    private JButton btnInsertar, btnCancelar, btnVolver;

    public InsertarUsuarioGUI() {
        setTitle("Insertar Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        FondoPanel fondo = new FondoPanel();
        setContentPane(fondo);
        fondo.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblPrimerNombre = new JLabel("Primer Nombre:");
        lblPrimerNombre.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        fondo.add(lblPrimerNombre, gbc);
        txtPrimerNombre = new JTextField(20);
        gbc.gridx = 1;
        fondo.add(txtPrimerNombre, gbc);

        JLabel lblSegundoNombre = new JLabel("Segundo Nombre:");
        lblSegundoNombre.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        fondo.add(lblSegundoNombre, gbc);
        txtSegundoNombre = new JTextField(20);
        gbc.gridx = 1;
        fondo.add(txtSegundoNombre, gbc);

        JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
        lblPrimerApellido.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        fondo.add(lblPrimerApellido, gbc);
        txtPrimerApellido = new JTextField(20);
        gbc.gridx = 1;
        fondo.add(txtPrimerApellido, gbc);

        JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
        lblSegundoApellido.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        fondo.add(lblSegundoApellido, gbc);
        txtSegundoApellido = new JTextField(20);
        gbc.gridx = 1;
        fondo.add(txtSegundoApellido, gbc);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        fondo.add(lblLogin, gbc);
        txtLogin = new JTextField(20);
        gbc.gridx = 1;
        fondo.add(txtLogin, gbc);

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        fondo.add(lblClave, gbc);
        txtClave = new JPasswordField(20);
        gbc.gridx = 1;
        fondo.add(txtClave, gbc);

        JLabel lblFechaCreacion = new JLabel("Fecha de Creación (YYYY-MM-DD):");
        lblFechaCreacion.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        fondo.add(lblFechaCreacion, gbc);
        txtFechaCreacion = new JTextField(20);
        gbc.gridx = 1;
        fondo.add(txtFechaCreacion, gbc);

        // Agregar los botones de forma horizontal
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setOpaque(false);  
        // Botón Insertar
        btnInsertar = new JButton("Insertar Usuario");
        btnInsertar.setForeground(Color.WHITE);
        btnInsertar.setBackground(new Color(0, 123, 255)); 
        btnInsertar.setFocusPainted(false);  
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
        btnInsertar.setPreferredSize(new Dimension(200, 40));  // Establece el mismo tamaño para todos los botones
        btnInsertar.setBorderPainted(false);  
        ImageIcon iconInsertar = new ImageIcon(new ImageIcon("C:src/icons/image1 (2).png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));  // Escalar el icono
        btnInsertar.setIcon(iconInsertar);
        panelBotones.add(btnInsertar);

        //  Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(255, 0, 0));  // Color de fondo rojo
        btnCancelar.setFocusPainted(false); 
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setPreferredSize(new Dimension(200, 40));  // Establece el mismo tamaño para todos los botones
        btnCancelar.setBorderPainted(false);  
        ImageIcon iconCancelar = new ImageIcon(new ImageIcon("src/icons/image3.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));  // Escalar el icono
        btnCancelar.setIcon(iconCancelar);
        panelBotones.add(btnCancelar);

        // Volver
        btnVolver = new JButton("Volver al menú");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(16, 138, 53));  // Verde
        btnVolver.setFocusPainted(false); 
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setPreferredSize(new Dimension(200, 40));  // Establece el mismo tamaño para todos los botones
        btnVolver.setBorderPainted(false);  // Eliminar borde
        ImageIcon iconVolver = new ImageIcon(new ImageIcon("src/icons/image4.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));  // Escalar el icono
        btnVolver.setIcon(iconVolver);
        panelBotones.add(btnVolver);

        // Agregar el panel con los botones
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        fondo.add(panelBotones, gbc);

        // Acción para insertar usuario
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarUsuario();
            }
        });

        // Acción para cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Acción para volver a la página principal
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAPrincipal();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void insertarUsuario() {
        String primerNombre = txtPrimerNombre.getText();
        String segundoNombre = txtSegundoNombre.getText();
        String primerApellido = txtPrimerApellido.getText();
        String segundoApellido = txtSegundoApellido.getText();
        String login = txtLogin.getText();
        String clave = new String(txtClave.getPassword());
        String fechaCreacion = txtFechaCreacion.getText();

        String url = "jdbc:mysql://localhost:3306/MiBaseDeDatos";
        String user = "root";
        String password = "tu_contraseña";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String procedimiento = "{CALL InsertarUsuario(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = con.prepareCall(procedimiento);

            stmt.setString(1, primerNombre);
            stmt.setString(2, segundoNombre);
            stmt.setString(3, primerApellido);
            stmt.setString(4, segundoApellido);
            stmt.setString(5, login);
            stmt.setString(6, clave);
            stmt.setDate(7, java.sql.Date.valueOf(fechaCreacion));

            stmt.execute();
            JOptionPane.showMessageDialog(this, "Usuario insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al insertar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos
    private void clearFields() {
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtLogin.setText("");
        txtClave.setText("");
        txtFechaCreacion.setText("");
    }

    // Método para volver a la página principal
    private void volverAPrincipal() {
        this.dispose();  
    }

    public static void main(String[] args) {
        new InsertarUsuarioGUI();
    }

    class FondoPanel extends JPanel {
        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            imagen = new ImageIcon("src/img/insert.jpeg").getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}