import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class pagprincipal extends JFrame {

    private JPanel panelBotones; // Panel para los botones
    private JPanel panelContenido; // Panel dinámico para cambiar contenido

    public pagprincipal() {
        super("Gestión de Usuarios");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel dinámico que cambiará su contenido
        panelContenido = new JPanel(new BorderLayout());

        // Crear el panel para los botones
        panelBotones = new JPanel(new FlowLayout());
        JButton btnMantenimiento = new JButton("Mantenimiento Usuarios");
        JButton btnConsultarUsuarios = new JButton("Consultar Usuarios");
        JButton btnInsertarUsuario = new JButton("Insertar Usuario");

        // Agregar botones al panel de botones
        panelBotones.add(btnMantenimiento);
        panelBotones.add(btnConsultarUsuarios);
        panelBotones.add(btnInsertarUsuario);

        // Configuración de acciones para los botones
        btnMantenimiento.addActionListener(e -> mostrarPanelMantenimiento());
        btnConsultarUsuarios.addActionListener(e -> mostrarPanelConsultarUsuarios());
        btnInsertarUsuario.addActionListener(e -> mostrarPanelInsertarUsuario());

        // Configuración de la ventana principal
        setLayout(new BorderLayout());
        add(panelBotones, BorderLayout.NORTH); // Panel de botones arriba
        add(panelContenido, BorderLayout.CENTER); // Panel de contenido dinámico

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Métodos para cambiar el contenido dinámico del JFrame
    private void mostrarPanelMantenimiento() {
        // Remover los componentes previos y agregar el contenido adecuado
        panelContenido.removeAll();
        panelContenido.add(new JLabel("Funcionalidad de Mantenimiento de Usuarios aún no implementada."), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void mostrarPanelConsultarUsuarios() {
        // Remover los componentes previos y agregar el panel correspondiente
        panelContenido.removeAll();
        panelContenido.add(new ConsultarTodosPanel(this), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void mostrarPanelInsertarUsuario() {
        // Remover los componentes previos y agregar el contenido adecuado
        panelContenido.removeAll();
        panelContenido.add(new JLabel("Funcionalidad de Insertar Usuario aún no implementada."), BorderLayout.CENTER);
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
