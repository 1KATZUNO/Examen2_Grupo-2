import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class pagprincipal extends JFrame {

    private JPanel panelBotones; // Panel para los botones
    JPanel panelContenido; // Panel dinámico para cambiar contenido

    public pagprincipal() {
        super("Gestión de Usuarios");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el JLayeredPane para gestionar el fondo y los botones encima
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane); // Establecemos el contenido del JFrame como JLayeredPane

        // Crear el panel dinámico con fondo
        panelContenido = new ImagenPanel("src/img/fondoM.jpg"); // Imagen de fondo
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

        // Crear los botones con colores, bordes redondeados y efectos
        JButton btnMantenimiento = crearBoton("Mantenimiento Usuarios");
        JButton btnConsultarUsuarios = crearBoton("Mostrar Tabla Usuarios");
        JButton btnInsertarUsuario = crearBoton("Insertar Usuario");
        JButton btnConsultarPorId = crearBoton("Consultar Usuario por ID");

        // Agregar botones al panel de botones
        panelBotones.add(btnMantenimiento, gbc);
        gbc.gridy++; // Mover a la siguiente fila
        panelBotones.add(btnConsultarUsuarios, gbc);
        gbc.gridy++;
        panelBotones.add(btnInsertarUsuario, gbc);
        gbc.gridy++;
        panelBotones.add(btnConsultarPorId, gbc);

        // Configuración de acciones para los botones
        btnMantenimiento.addActionListener(e -> mostrarPanelMantenimiento());
        btnConsultarUsuarios.addActionListener(e -> mostrarPanelConsultarUsuarios());
        btnInsertarUsuario.addActionListener(e -> mostrarPanelInsertarUsuario());
        btnConsultarPorId.addActionListener(e -> mostrarPanelConsultarPorId());

        // Configuración de la ventana principal
        panelBotones.setBounds(250, 100, 300, 300); // Colocar los botones en el centro de la ventana
        panelContenido.setBounds(0, 0, 800, 500); // El fondo cubre toda la ventana

        // Agregar el fondo y los botones al JLayeredPane
        layeredPane.add(panelContenido, JLayeredPane.DEFAULT_LAYER); // Fondo en la capa por defecto
        layeredPane.add(panelBotones, JLayeredPane.PALETTE_LAYER); // Botones encima del fondo

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Método para crear botones personalizados con bordes redondeados y efectos
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(200, 50)); // Tamaño del botón
        boton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente personalizada
        boton.setBackground(new Color(66, 133, 244)); // Color de fondo del botón
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setFocusPainted(false); // Quitar el borde cuando se da click

        // Borde redondeado utilizando un borde compuesto
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 133, 244), 2), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Efecto de sombra interna
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 133, 244), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        // Efecto al pasar el mouse sobre el botón
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(52, 103, 163)); // Color al pasar el mouse
                boton.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255), 3)); // Resplandor al pasar
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(66, 133, 244)); // Color original
                boton.setBorder(BorderFactory.createLineBorder(new Color(66, 133, 244), 2)); // Regresar al borde original
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton.setPreferredSize(new Dimension(210, 55)); // Pulsación
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                boton.setPreferredSize(new Dimension(200, 50)); // Regresar al tamaño original después de pulsar
            }
        });

        return boton;
    }

    // Métodos para cambiar el contenido dinámico del JFrame
    private void mostrarPanelMantenimiento() {
        // Remover los componentes previos y agregar el contenido adecuado
        panelContenido.removeAll();
        panelContenido.add(new JLabel("Funcionalidad de Mantenimiento de Usuarios aún no implementada."), BorderLayout.CENTER);
        panelBotones.setVisible(true);  // Asegurar que los botones sean visibles
        revalidate();
        repaint();
    }

    private void mostrarPanelConsultarUsuarios() {
        // Remover los componentes previos y agregar solo el panel de consulta
        panelBotones.setVisible(false);  // Ocultar los botones
        panelContenido.removeAll();
        panelContenido.add(new ConsultarTodosPanel(this), BorderLayout.CENTER);  // Aquí va el panel de consulta
        revalidate();
        repaint();
    }

    private void mostrarPanelInsertarUsuario() {
        // Remover los componentes previos y agregar el contenido adecuado
        panelContenido.removeAll();
        panelContenido.add(new JLabel("Funcionalidad de Insertar Usuario aún no implementada."), BorderLayout.CENTER);
        panelBotones.setVisible(true);  // Asegurar que los botones sean visibles
        revalidate();
        repaint();
    }

    private void mostrarPanelConsultarPorId() {
        // Remover los componentes previos y agregar el panel de consulta por ID
        panelBotones.setVisible(false);  // Ocultar los botones
        panelContenido.removeAll();
        panelContenido.add(new ConsultarPorIdPanel(), BorderLayout.CENTER);  // Agregar el panel ConsultarPorIdPanel
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
