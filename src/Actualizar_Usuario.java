import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Actualizar_Usuario extends JPanel {

    private JTextField txtId, txtNombre1Actual, txtNombre2Actual, txtApellido1Actual, txtApellido2Actual;
    private JTextField txtLoginActual, txtPasswordActual, txtFechaCreacionActual;
    private JTextField txtNombre1Nuevo, txtNombre2Nuevo, txtApellido1Nuevo, txtApellido2Nuevo;
    private JTextField txtLoginNuevo, txtPasswordNuevo, txtFechaCreacionNuevo;
    private Image fondoImagen; 

    public Actualizar_Usuario(pagprincipal parentFrame) {
        setLayout(new GridBagLayout());
        setOpaque(true);  

        // Cargar la imagen de fondo
        fondoImagen = new ImageIcon("src\\img\\fondo_Actualizar.jpg").getImage(); 

        // Inicializar campos de texto
        txtId = new JTextField(15);
        txtNombre1Actual = new JTextField(15);
        txtNombre2Actual = new JTextField(15);
        txtApellido1Actual = new JTextField(15);
        txtApellido2Actual = new JTextField(15);
        txtLoginActual = new JTextField(15);
        txtPasswordActual = new JTextField(15);
        txtFechaCreacionActual = new JTextField(15);
        txtNombre1Nuevo = new JTextField(15);
        txtNombre2Nuevo = new JTextField(15);
        txtApellido1Nuevo = new JTextField(15);
        txtApellido2Nuevo = new JTextField(15);
        txtLoginNuevo = new JTextField(15);
        txtPasswordNuevo = new JTextField(15);
        txtFechaCreacionNuevo = new JTextField(15);

      //  etiquetas
JLabel lblId = new JLabel("ID de Usuario:");
JLabel lblNombre1Actual = new JLabel("Primer Nombre Actual:");
JLabel lblNombre2Actual = new JLabel("Segundo Nombre Actual:");
JLabel lblApellido1Actual = new JLabel("Primer Apellido Actual:");
JLabel lblApellido2Actual = new JLabel("Segundo Apellido Actual:");
JLabel lblLoginActual = new JLabel("Login Actual:");
JLabel lblPasswordActual = new JLabel("Contraseña Actual:");
JLabel lblFechaCreacionActual = new JLabel("Fecha de Creación Actual:");
JLabel lblNombre1Nuevo = new JLabel("Nuevo Primer Nombre:");
JLabel lblNombre2Nuevo = new JLabel("Nuevo Segundo Nombre:");
JLabel lblApellido1Nuevo = new JLabel("Nuevo Primer Apellido:");
JLabel lblApellido2Nuevo = new JLabel("Nuevo Segundo Apellido:");
JLabel lblLoginNuevo = new JLabel("Nuevo Login:");
JLabel lblPasswordNuevo = new JLabel("Nueva Contraseña:");
JLabel lblFechaCreacionNuevo = new JLabel("Nueva Fecha de Creación:");

// Cambiar el color de los JLabels
Color colorTexto = Color.WHITE; 

lblId.setForeground(colorTexto);
lblNombre1Actual.setForeground(colorTexto);
lblNombre2Actual.setForeground(colorTexto);
lblApellido1Actual.setForeground(colorTexto);
lblApellido2Actual.setForeground(colorTexto);
lblLoginActual.setForeground(colorTexto);
lblPasswordActual.setForeground(colorTexto);
lblFechaCreacionActual.setForeground(colorTexto);
lblNombre1Nuevo.setForeground(colorTexto);
lblNombre2Nuevo.setForeground(colorTexto);
lblApellido1Nuevo.setForeground(colorTexto);
lblApellido2Nuevo.setForeground(colorTexto);
lblLoginNuevo.setForeground(colorTexto);
lblPasswordNuevo.setForeground(colorTexto);
lblFechaCreacionNuevo.setForeground(colorTexto);

        // ---->  COONFIGURACION DE LOS BOTONES 
        JButton btnConsultar = new JButton();
        JButton btnActualizar = new JButton();
        JButton btnVolver = new JButton();

        //  ----> Cargar íconos para los botones
        btnConsultar.setIcon(redimensionarIcono("src/icons/Icono_Consultar.png", 40, 40));
        btnConsultar.setToolTipText("Consultar usuario");

        btnActualizar.setIcon(redimensionarIcono("src/icons/Icono_Actualizar.png", 40, 40));
        btnActualizar.setToolTipText("Actualizar usuario");

        btnVolver.setIcon(redimensionarIcono("src/icons/Icono_Volver.png", 40, 40));
        btnVolver.setToolTipText("Volver al menú principal");

        //  ----> configuración del diseño de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campo ID y botón Consultar
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblId, gbc);

        gbc.gridx = 1;
        add(txtId, gbc);

        gbc.gridx = 2;
        add(btnConsultar, gbc);

        //  ----> Agregar los campos actuales y nuevos en filas
        agregarCampo(lblNombre1Actual, txtNombre1Actual, lblNombre1Nuevo, txtNombre1Nuevo, 1, gbc);
        agregarCampo(lblNombre2Actual, txtNombre2Actual, lblNombre2Nuevo, txtNombre2Nuevo, 2, gbc);
        agregarCampo(lblApellido1Actual, txtApellido1Actual, lblApellido1Nuevo, txtApellido1Nuevo, 3, gbc);
        agregarCampo(lblApellido2Actual, txtApellido2Actual, lblApellido2Nuevo, txtApellido2Nuevo, 4, gbc);
        agregarCampo(lblLoginActual, txtLoginActual, lblLoginNuevo, txtLoginNuevo, 5, gbc);
        agregarCampo(lblPasswordActual, txtPasswordActual, lblPasswordNuevo, txtPasswordNuevo, 6, gbc);
        agregarCampo(lblFechaCreacionActual, txtFechaCreacionActual, lblFechaCreacionNuevo, txtFechaCreacionNuevo, 7, gbc);

        // ---->  Botón Actualizar
        gbc.gridy = 8;
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnActualizar, gbc);

        // ---->  Botón Volver
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnVolver, gbc);

        // ---->  Acción para consultar
        btnConsultar.addActionListener(e -> {
            try {
                consultarUsuario(txtId.getText().trim(), txtNombre1Actual, txtNombre2Actual, txtApellido1Actual,
                        txtApellido2Actual, txtLoginActual, txtPasswordActual, txtFechaCreacionActual);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al consultar usuario: " + ex.getMessage());
            }
        });

        //  ----> Acción para actualizar
        btnActualizar.addActionListener(e -> {
            try {
                actualizarUsuario(txtId.getText().trim(), txtNombre1Nuevo.getText().trim(),
                        txtNombre2Nuevo.getText().trim(), txtApellido1Nuevo.getText().trim(),
                        txtApellido2Nuevo.getText().trim(), txtLoginNuevo.getText().trim(),
                        txtPasswordNuevo.getText().trim(), txtFechaCreacionNuevo.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar usuario: " + ex.getMessage());
            }
        });

        //  ----> Acción para volver
        btnVolver.addActionListener(e -> {
            parentFrame.getPanelBotones().setVisible(true);
            parentFrame.panelContenido.removeAll();
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    private void agregarCampo(JLabel lblActual, JTextField txtActual, JLabel lblNuevo, JTextField txtNuevo, int fila, GridBagConstraints gbc) {
        gbc.gridy = fila;
        gbc.gridx = 0;
        add(lblActual, gbc);
        gbc.gridx = 1;
        add(txtActual, gbc);
        gbc.gridx = 2;
        add(lblNuevo, gbc);
        gbc.gridx = 3;
        add(txtNuevo, gbc);
    }


    // ---->  CONSULTAR USUARIO EXISTENTE SP

    private void consultarUsuario(String id, JTextField... campos) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBaseDeDatos.obtenerConexion();
            cstmt = conn.prepareCall("{CALL ConsultarUsuarios(?)}");
            cstmt.setInt(1, Integer.parseInt(id));
            rs = cstmt.executeQuery();

            if (rs.next()) {
                String[] valores = {rs.getString("Primer_Nombre"), rs.getString("Segundo_Nombre"),
                        rs.getString("Primer_Apellido"), rs.getString("Segundo_Apellido"),
                        rs.getString("Login"), rs.getString("Clave"), rs.getString("Fecha_Creacion")};

                for (int i = 0; i < valores.length; i++) {
                    campos[i].setText(valores[i]);
                }
            } else {

                JOptionPane.showMessageDialog(this, " El usuario no se ha encontrado.");
            }
        } finally {
            if (rs != null) rs.close();
            if (cstmt != null) cstmt.close();
            if (conn != null) conn.close();
        }
    }



//  ----> SP Actualizar usuario existente 
    private void actualizarUsuario(String id, String... valores) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            conn = ConexionBaseDeDatos.obtenerConexion();
            cstmt = conn.prepareCall("{CALL ActualizarUsuarios(?, ?, ?, ?, ?, ?, ?, ?)}");
            cstmt.setInt(1, Integer.parseInt(id));
            for (int i = 0; i < valores.length; i++) {
                cstmt.setString(i + 2, valores[i]);
            }
            int rowsAffected = cstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente.");
                limpiarCamposTexto();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un usuario con el ID proporcionado.");
            }
        } finally {
            if (cstmt != null) cstmt.close();
            if (conn != null) conn.close();
        }
    }

    private void limpiarCamposTexto() {
        txtId.setText("");
        txtNombre1Actual.setText("");
        txtNombre2Actual.setText("");
        txtApellido1Actual.setText("");
        txtApellido2Actual.setText("");
        txtLoginActual.setText("");
        txtPasswordActual.setText("");
        txtFechaCreacionActual.setText("");
        txtNombre1Nuevo.setText("");
        txtNombre2Nuevo.setText("");
        txtApellido1Nuevo.setText("");
        txtApellido2Nuevo.setText("");
        txtLoginNuevo.setText("");
        txtPasswordNuevo.setText("");
        txtFechaCreacionNuevo.setText("");
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (fondoImagen != null) { 
            g.drawImage(fondoImagen, 0, 0, getWidth(), getHeight(), this); // Dibuja la imagen escalada para llenar el panel
        }
    }
    // ----> Redimensionar icono
    private ImageIcon redimensionarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
