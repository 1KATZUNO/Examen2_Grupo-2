import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ConsultarPorIdPanel extends ImagenPanel {

    public ConsultarPorIdPanel() {
        // Ruta relativa de  imagen de fondo
        super("src/img/nature2.jpg");

        // Establecer el Layout
        setLayout(new BorderLayout());

        // Crear los componentes
        JPanel panelEntrada = new JPanel(new FlowLayout());
        panelEntrada.setOpaque(false); // Panel transparente para mostrar el fondo
        JLabel label = new JLabel("Ingrese ID de Usuario:");
        label.setForeground(Color.WHITE); // Texto visible en fondo oscuro
        JTextField idField = new JTextField(10);

        // Cargar íconos y redimensionarlos
        ImageIcon consultarIcon = redimensionarIcono("src/icons/consultar.png", 20, 20);
        ImageIcon volverIcon = redimensionarIcono("src/icons/atras.png", 20, 20);

        // Crear botones con íconos
        JButton btnConsultar = new JButton("Consultar", consultarIcon);
        btnConsultar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnConsultar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnConsultar.setBackground(new Color(34, 139, 34));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 12));

        JButton btnVolver = new JButton("Volver", volverIcon);
        btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolver.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnVolver.setBackground(new Color(255, 69, 0));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));

        // Agregar componentes al panel de entrada
        panelEntrada.add(label);
        panelEntrada.add(idField);
        panelEntrada.add(btnConsultar);

        // Crear una tabla con colores adecuados
        JTable tablaUsuario = new JTable();
        tablaUsuario.setGridColor(Color.BLACK); // Color del borde
        tablaUsuario.setBackground(new Color(240, 240, 240)); // Fondo claro
        tablaUsuario.setForeground(Color.BLACK); // Texto oscuro
        tablaUsuario.setSelectionBackground(new Color(51, 153, 255)); // Fondo al seleccionar una fila
        tablaUsuario.setSelectionForeground(Color.WHITE); // Texto al seleccionar
        tablaUsuario.setFont(new Font("Arial", Font.PLAIN, 12));

        // Personalizar los encabezados de la tabla
        tablaUsuario.getTableHeader().setBackground(new Color(70, 130, 180)); // Fondo de encabezado
        tablaUsuario.getTableHeader().setForeground(Color.WHITE); // Texto de encabezado
        tablaUsuario.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tablaUsuario);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Crear un panel centrado para el botón "Volver"
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setOpaque(false);
        panelInferior.add(btnVolver);

        // Agregar componentes al JPanel principal
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Acción al presionar el botón Consultar
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idUsuarioStr = idField.getText().trim();

                if (idUsuarioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(ConsultarPorIdPanel.this, "Por favor ingrese un ID de usuario.");
                    return;
                }

                try {
                    int idUsuario = Integer.parseInt(idUsuarioStr);
                    consultarUsuarioPorId(idUsuario, tablaUsuario);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ConsultarPorIdPanel.this, "El ID de usuario debe ser un número válido.");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Acción al presionar el botón "Volver"
     // Acción al presionar el botón "Volver"
btnVolver.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Limpiar la tabla antes de volver
        limpiarTabla(tablaUsuario);

        // Obtener el JFrame y verificar si es una instancia de pagprincipal
        JFrame principalFrame = (JFrame) SwingUtilities.getWindowAncestor(ConsultarPorIdPanel.this);
        if (principalFrame instanceof pagprincipal) {
            pagprincipal pagPrincipalFrame = (pagprincipal) principalFrame;

            // Eliminar el contenido de la ventana de consulta y volver a mostrar los botones de la ventana principal
            pagPrincipalFrame.getPanelBotones().setVisible(true); // Hacer visibles los botones
            pagPrincipalFrame.panelContenido.removeAll(); // Eliminar cualquier panel cargado previamente
            pagPrincipalFrame.revalidate();
            pagPrincipalFrame.repaint();
        } else {
            JOptionPane.showMessageDialog(ConsultarPorIdPanel.this, "Error al volver al panel principal.");
        }
    }
});

    }

    private void consultarUsuarioPorId(int idUsuario, JTable tablaUsuario) throws ClassNotFoundException {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBaseDeDatos.obtenerConexion();
            cstmt = conn.prepareCall("{CALL ConsultarUsuarios(?)}");
            cstmt.setInt(1, idUsuario);

            rs = cstmt.executeQuery();

            // Crear un modelo de tabla
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("P_Nombre");
            model.addColumn("S_Nombre");
            model.addColumn("P_Apellido");
            model.addColumn("S_Apellido");
            model.addColumn("Login");
            model.addColumn("Clave");
            model.addColumn("Fecha_Creación");

            boolean found = false;
            while (rs.next()) {
                found = true;
                Object[] row = {
                        rs.getInt("idUsuarios"),
                        rs.getString("Primer_Nombre"),
                        rs.getString("Segundo_Nombre"),
                        rs.getString("Primer_Apellido"),
                        rs.getString("Segundo_Apellido"),
                        rs.getString("Login"),
                        rs.getString("Clave"),
                        rs.getString("Fecha_Creacion")
                };
                model.addRow(row);
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No se encontró un usuario con el ID proporcionado.");
            }

            tablaUsuario.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la consulta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    private void limpiarTabla(JTable tablaUsuario) {
        DefaultTableModel model = (DefaultTableModel) tablaUsuario.getModel();
        model.setRowCount(0); // Eliminar todas las filas
        model.setColumnCount(0); // Eliminar todas las columnas
    }

    private ImageIcon redimensionarIcono(String ruta, int ancho, int alto) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
