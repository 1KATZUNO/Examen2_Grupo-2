import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;
//import javax.swing.border.Border;

public class pagprincipal extends JFrame {
    pagprincipal Carlitos = new pagprincipal();


    private JPanel panelBotones; // Panel para los botones
    JPanel panelContenido; // Panel dinámico para cambiar contenido

    @SuppressWarnings("unused")
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
        gbc.fill = GridBagConstraints.HORIZONTAL; // Asegurarse de que los botones se estiren horizontalmente

        // Crear los botones con efectos y iconos
        JButton btnConsultarUsuarios = crearBoton("Mostrar Tabla Usuarios", "src/icons/consultar.png");
        JButton btnInsertarUsuario = crearBoton("Insertar Usuario", "src/icons/insertar.png");
        JButton btnConsultarPorId = crearBoton("Consultar Usuario ID", "src/icons/consultar_id.png");
        JButton btnEliminarUsuario = crearBoton("Eliminar Usuario", "src/icons/eliminar_id.png");
        JButton btnActualizarUsuario = crearBoton("Actualizar Usuario", "src/icons/actualizar_bd.jpg");

        // Agregar botones al panel de botones
        panelBotones.add(btnConsultarUsuarios, gbc);
        gbc.gridy++; // Mover a la siguiente fila
        panelBotones.add(btnInsertarUsuario, gbc);
        gbc.gridy++;
        panelBotones.add(btnConsultarPorId, gbc);
        gbc.gridy++;
        panelBotones.add(btnEliminarUsuario, gbc);
        gbc.gridy++;
        panelBotones.add(btnActualizarUsuario, gbc);

        // Configuración de acciones para los botones
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

    // Método para crear botones personalizados con iconos, efectos de cambio de color y bordes
    private JButton crearBoton(String texto, String iconPath) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(200, 50)); // Tamaño del botón
        boton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente personalizada
        boton.setBackground(new Color(66, 133, 244)); // Color de fondo del botón
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setFocusPainted(false); // Quitar el borde cuando se da click
        boton.setBorder(BorderFactory.createLineBorder(new Color(40, 120, 200), 2)); // Borde con color

        // Cargar el icono
        if (iconPath != null) {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage(); // Convertir a imagen
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar el icono
            icon = new ImageIcon(newImg);
            boton.setIcon(icon); // Establecer el icono
            boton.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto a la derecha del icono
            boton.setVerticalTextPosition(SwingConstants.CENTER); // Alinear el texto en el centro verticalmente
        }

        // Agregar el efecto de cambio de color al pasar el mouse
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(40, 120, 200)); // Cambiar color al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(66, 133, 244)); // Restaurar color original cuando se salga el mouse
            }
        });

        return boton;
    }

    // Métodos para cambiar el contenido dinámico del JFrame
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
        this.dispose();
    }

    private void mostrarPanelConsultarPorId() {
        panelBotones.setVisible(false);
        panelContenido.removeAll();
        panelContenido.add(new ConsultarPorIdPanel(this), BorderLayout.CENTER);
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
    
    
}
