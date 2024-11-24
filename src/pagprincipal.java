import java.awt.*;
import javax.swing.*;

public class pagprincipal extends JFrame {

    private JPanel panelBotones; // Panel para los botones
    JPanel panelContenido; // Panel dinámico para cambiar contenido

    public pagprincipal() {
        super("Gestión de Usuarios");
        setSize(800, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el JLayeredPane para gestionar el fondo y los botones encima
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane); // Establecemos el contenido del JFrame como JLayeredPane

        // Crear el panel dinámico con fondo
        panelContenido = new ImagenPanel("src/img/land.jpg"); // Imagen de fondo
        panelContenido.setBounds(0, 0, 800, 500); // Hacer que el panel de fondo cubra toda la ventana
        panelContenido.setLayout(new BorderLayout());

        // Crear el panel de botones y establecer la disposición
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar los botones
        panelBotones.setOpaque(false); // Hacer el panel de botones transparente

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Columna
        gbc.gridy = 0; // Fila
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre botones

        // Crear los botones con efectos simplificados
        JButton btnMantenimiento = crearBoton("Mantenimiento Usuarios");
        JButton btnConsultarUsuarios = crearBoton("Mostrar Tabla Usuarios");
        JButton btnInsertarUsuario = crearBoton("Insertar Usuario");
        JButton btnConsultarPorId = crearBoton("Consultar Usuario por ID");
        JButton btnEliminarUsuario = crearBoton("Eliminar Usuario");
        JButton btnActualizarUsuario = crearBoton("Actualizar Usuario");

        // Agregar botones al panel de botones
        panelBotones.add(btnMantenimiento, gbc);
        gbc.gridy++; // Mover a la siguiente fila
        panelBotones.add(btnConsultarUsuarios, gbc);
        gbc.gridy++;
        panelBotones.add(btnInsertarUsuario, gbc);
        gbc.gridy++;
        panelBotones.add(btnConsultarPorId, gbc);
        gbc.gridy++;
        panelBotones.add(btnEliminarUsuario, gbc);
        gbc.gridy++;
        panelBotones.add(btnActualizarUsuario, gbc);

        // Configuración de acciones para los botones
        
        btnMantenimiento.addActionListener(e -> mostrarPanelMantenimiento());
        btnConsultarUsuarios.addActionListener(e -> mostrarPanelConsultarUsuarios());
        btnInsertarUsuario.addActionListener(e -> mostrarPanelInsertarUsuario());
        btnConsultarPorId.addActionListener(e -> mostrarPanelConsultarPorId());
        btnEliminarUsuario.addActionListener(e -> mostrarPanelEliminarUsuario());
        btnActualizarUsuario.addActionListener(e -> mostrarPanelActualizarUsuario());

        // Configuración de la ventana principal
        panelBotones.setBounds(250, 100, 300, 400); // Ajustar altura para incluir el nuevo botón
        panelContenido.setBounds(0, 0, 800, 500); // El fondo cubre toda la ventana

        // Agregar el fondo y los botones al JLayeredPane
        layeredPane.add(panelContenido, JLayeredPane.DEFAULT_LAYER); // Fondo en la capa por defecto
        layeredPane.add(panelBotones, JLayeredPane.PALETTE_LAYER); // Botones encima del fondo

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Método para crear botones personalizados con sombra al pasar el mouse
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(200, 50)); // Tamaño del botón
        boton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente personalizada
        boton.setBackground(new Color(66, 133, 244)); // Color de fondo del botón
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setFocusPainted(false); // Quitar el borde cuando se da click
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Agregar sombra al pasar el mouse
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(50, 50, 50), 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            }
        });

        return boton;
    }

    // Métodos para cambiar el contenido dinámico del JFrame
    private void mostrarPanelMantenimiento() {
        panelContenido.removeAll();
        panelContenido.add(new JLabel("Funcionalidad de Mantenimiento de Usuarios aún no implementada."), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void mostrarPanelConsultarUsuarios() {
        panelBotones.setVisible(false);
        panelContenido.removeAll();
        panelContenido.add(new ConsultarTodosPanel(this), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void mostrarPanelInsertarUsuario() {
        
    
        InsertarUsuarioGUI nuevo = new InsertarUsuarioGUI();
        nuevo.setVisible(true);
        nuevo.setLocationRelativeTo(this);
    }

    private void mostrarPanelConsultarPorId() {
        panelBotones.setVisible(false);
        panelContenido.removeAll();
        panelContenido.add(new ConsultarPorIdPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void mostrarPanelEliminarUsuario() {
        panelBotones.setVisible(false); 
        panelContenido.removeAll(); 
        panelContenido.add(new Eliminar_Usuario(this), BorderLayout.CENTER); 
        revalidate(); 
        repaint(); 
    }

    private void mostrarPanelActualizarUsuario() {
        panelBotones.setVisible(false);
        panelContenido.removeAll();
        panelContenido.add(new Actualizar_Usuario(this), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public JPanel getPanelBotones() {
        return panelBotones;
    }

    public static void main(String[] args) {
        new pagprincipal();
    }
}