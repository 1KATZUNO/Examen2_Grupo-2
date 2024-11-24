import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Eliminar_Usuario extends ImagenPanel {

    @SuppressWarnings("unused")
    public Eliminar_Usuario() {
        // ----->  Ruta de la imagen de fondo
        super("src/img/fondo_Eliminar.jpg");

        // -----> Configuración del diseño
        setLayout(new GridBagLayout());

        //  -----> Crear componentes
        JLabel label = new JLabel("Ingrese ID de Usuario a eliminar:");
        JTextField txtIdUsuario = new JTextField(15);
        JButton btnEliminar = new JButton();
        JButton btnVolver = new JButton();

        // ----->  Personalizar fuentes y tamaños
        Font fuenteGrande = new Font("Arial", Font.BOLD, 20);
        label.setFont(fuenteGrande);
        label.setForeground(Color.WHITE); 

        txtIdUsuario.setFont(fuenteGrande);

        //  -----> Eventos color de `JLabel`
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                label.setForeground(Color.BLUE);
            }
        });

        //  -----> íconos para los botones usando el método redimensionarIcono
        btnEliminar.setIcon(redimensionarIcono("src/icons/Icono_Eliminar.png", 100, 100));
        btnVolver.setIcon(redimensionarIcono("src/icons/Icono_Volver.png", 100, 100));

        // -----> tamaño de los botones y eliminar el fondo
        btnEliminar.setPreferredSize(new Dimension(100, 100));
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);

        btnVolver.setPreferredSize(new Dimension(100, 100));
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);

        //  -----> tooltips (mensajes informativos) a los botones
        btnEliminar.setToolTipText("Eliminar usuario");
        btnVolver.setToolTipText("Volver al menú principal");

        //  -----> GridBagConstraints para centrar y espaciar componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        //  -----> Agregar componentes
        add(label, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtIdUsuario, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(btnEliminar, gbc);

        gbc.gridx = 1;
        add(btnVolver, gbc);

        //  -----> Acción al presionar el botón "Eliminar"
        btnEliminar.addActionListener(e -> {
            String idUsuarioStr = txtIdUsuario.getText().trim();
            if (idUsuarioStr.isEmpty()) {
                JOptionPane.showMessageDialog(Eliminar_Usuario.this, "Por favor ingrese un ID de usuario.");
                return;
            }
            try {
                int idUsuario = Integer.parseInt(idUsuarioStr);
                eliminarUsuario(idUsuario);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Eliminar_Usuario.this, "El ID debe ser un número válido.");
            }
        });

        //  -----> Acción al presionar el botón "Volver"
        btnVolver.addActionListener(e -> {
            JFrame principalFrame = (JFrame) SwingUtilities.getWindowAncestor(Eliminar_Usuario.this);
            principalFrame.setContentPane(((pagprincipal) principalFrame).getPanelBotones());
            principalFrame.revalidate();
            principalFrame.repaint();
        });
    }

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

    private ImageIcon redimensionarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }

   
}
