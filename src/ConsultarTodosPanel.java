
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class ConsultarTodosPanel extends ImagenPanel {

    private JTable tablaUsuarios;

    public ConsultarTodosPanel(JFrame principalFrame) {
        // Ruta absoluta de tu imagen
        super("demo/src/main/java/com/example/img/nature.jpg");

        // Establecer el Layout del panel
        setLayout(new BorderLayout());

        // Crear el panel de tabla con BorderLayout
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setOpaque(false); // Hacer el panel transparente para mostrar el fondo

        // Crear la tabla
        tablaUsuarios = new JTable();
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.setGridColor(Color.BLACK);

        // Cambiar el fondo de las filas alternas
        tablaUsuarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    comp.setBackground(new Color(235, 235, 235));
                } else {
                    comp.setBackground(Color.WHITE);
                }
                return comp;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Hacer transparente también el viewport
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        // Cargar íconos y redimensionarlos
        ImageIcon iconMostrarOriginal = new ImageIcon("demo/src/main/java/com/example/icons/mostrar.png");
        ImageIcon iconVolverOriginal = new ImageIcon("demo/src/main/java/com/example/icons/back.png");

        // Escalar imágenes a un tamaño adecuado (20x20 píxeles)
        Image iconMostrarEscalado = iconMostrarOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image iconVolverEscalado = iconVolverOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        // Crear nuevos íconos con las imágenes escaladas
        ImageIcon iconMostrar = new ImageIcon(iconMostrarEscalado);
        ImageIcon iconVolver = new ImageIcon(iconVolverEscalado);

        // Botón para cargar los datos
        JButton btnMostrarTodos = new JButton(" Mostrar Todos los Usuarios", iconMostrar);
        btnMostrarTodos.setBackground(new Color(34, 139, 34));
        btnMostrarTodos.setForeground(Color.WHITE);
        btnMostrarTodos.setFont(new Font("Arial", Font.BOLD, 14));

        // Botón para volver a la ventana principal
        JButton btnVolver = new JButton(" Volver", iconVolver);
        btnVolver.setBackground(new Color(255, 69, 0));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setOpaque(false); // Hacer transparente el panel de botones
        panelBotones.add(btnMostrarTodos);
        panelBotones.add(btnVolver);

        // Agregar paneles al diseño principal
        add(panelTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción al presionar el botón "Cargar Todos los Usuarios"
        btnMostrarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarTodosLosUsuarios();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }); 
        // Acción al presionar el botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTabla();

                principalFrame.setContentPane(((PrincipalFrame) principalFrame).getPanelBotones());
                principalFrame.revalidate();
                principalFrame.repaint();
            }
        });
    }

    private void cargarTodosLosUsuarios() throws ClassNotFoundException {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            limpiarTabla();

            conn = ConexionBaseDeDatos.obtenerConexion();
            System.out.println("Conexión establecida: " + (conn != null));
            cstmt = conn.prepareCall("{CALL ConsultarUsuariosTodos()}");

            rs = cstmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("P_Nombre");
            model.addColumn("S_Nombre");
            model.addColumn("P_Apellido");
            model.addColumn("S_Apellido");
            model.addColumn("Login");
            model.addColumn("Clave");
            model.addColumn("Fecha_Creación");

            tablaUsuarios.setModel(model);

            while (rs.next()) {
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

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
        model.setRowCount(0);

        for (int i = model.getColumnCount() - 1; i >= 0; i--) {
            model.setColumnCount(0);
        }
    }
}
        
