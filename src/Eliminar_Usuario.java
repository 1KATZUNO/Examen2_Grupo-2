import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Eliminar_Usuario extends JPanel {

    @SuppressWarnings("unused")
    public Eliminar_Usuario(pagprincipal parentFrame) {
        // ----> Usar ImagenPanel como fondo
        ImagenPanel fondo = new ImagenPanel("src/img/fondo_Eliminar.jpg");
        fondo.setLayout(new GridBagLayout()); // Usar GridBagLayout para el diseño

        // ----> Crear componentes
        JLabel label = new JLabel("Ingrese ID de Usuario a eliminar:");
        JTextField txtIdUsuario = new JTextField(15);
        JButton btnEliminar = new JButton();
        JButton btnVolver = new JButton();

        // ----> Personalizar fuentes y tamaños
        Font fuenteGrande = new Font("Arial", Font.BOLD, 20);
        label.setFont(fuenteGrande);
        label.setForeground(Color.WHITE);

        txtIdUsuario.setFont(fuenteGrande);

        //  ----> Configurar íconos para los botones
        btnEliminar.setIcon(redimensionarIcono("src/icons/Icono_Eliminar.png", 100, 100));
        btnVolver.setIcon(redimensionarIcono("src/icons/Icono_Volver.png", 100, 100));

        btnEliminar.setPreferredSize(new Dimension(100, 100));
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);

        btnVolver.setPreferredSize(new Dimension(100, 100));
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);

        // ---->  Agregar tooltips
        btnEliminar.setToolTipText("Eliminar usuario");
        btnVolver.setToolTipText("Volver al menú principal");

        // ---->  Configurar GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        fondo.add(label, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        fondo.add(txtIdUsuario, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        fondo.add(btnEliminar, gbc);

        gbc.gridx = 1;
        fondo.add(btnVolver, gbc);

        //  ----> Agregar el fondo al panel principal
        setLayout(new BorderLayout());
        add(fondo, BorderLayout.CENTER);

        //  ----> Acción al presionar el botón "Eliminar"
        btnEliminar.addActionListener(e -> {
            String idUsuarioStr = txtIdUsuario.getText().trim();
            if (idUsuarioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un ID de usuario.");
                return;
            }
            try {
                int idUsuario = Integer.parseInt(idUsuarioStr);
                eliminarUsuario(idUsuario);
                txtIdUsuario.setText(""); // Limpiar el JTextField después de eliminar
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
            }
        });

        //  ----> Acción al presionar el botón "Volver"
        btnVolver.addActionListener(e -> {
            parentFrame.getPanelBotones().setVisible(true); // Mostrar botones principales
            parentFrame.panelContenido.removeAll(); // Limpiar el contenido
            parentFrame.panelContenido.add(new ImagenPanel("src/img/land.jpg")); // Restaurar el fondo del menú principal
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    // ----> SP ELIMINAR USUARIO
    private void eliminarUsuario(int idUsuario) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConexionBaseDeDatos.obtenerConexion();
            cstmt = conn.prepareCall("{CALL Eliminar(?)}");
            cstmt.setInt(1, idUsuario);
            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un usuario con el ID proporcionado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } finally {
            try {
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    // ----> REDIMENSIONAR ICONO
    private ImageIcon redimensionarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
