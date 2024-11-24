// Librerías necesarias
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pagprincipal extends JFrame {

    // Componentes de la interfaz gráfica
    private JPanel mainPanel;
    private JButton btnMantenimiento;
    private JButton btnConsultarUsuarios; // Nuevo botón
    private JLabel lbBienvenute;

    // Constructor
    public pagprincipal() {
        initComponents();
    }

    // Inicialización de componentes
    private void initComponents() {
        // Inicialización de los componentes
        mainPanel = new JPanel();
        btnMantenimiento = new JButton("Mantenimiento Usuarios");
        btnConsultarUsuarios = new JButton("Consultar Usuarios"); // Nuevo botón
        lbBienvenute = new JLabel("Bienvenido a Mantenimiento", SwingConstants.CENTER);

        // Configuración de la etiqueta
        lbBienvenute.setFont(new Font("Dialog", Font.BOLD, 14));

        // Configuración del diseño del panel principal
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.add(lbBienvenute, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 10, 10));
        panelBotones.add(btnMantenimiento);
        panelBotones.add(btnConsultarUsuarios);

        mainPanel.add(panelBotones, BorderLayout.CENTER);

        // Configuración del marco (JFrame)
        this.setTitle("Servidor GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Acción del botón "Mantenimiento Usuarios"
        btnMantenimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarMantenimiento();
            }
        });

        // Acción del botón "Consultar Usuarios"
        btnConsultarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConsultarUsuarios();
            }
        });
    }

    // Método para mostrar el panel ConsultarTodosPanel
    private void abrirConsultarUsuarios() {
        // Crear una instancia de ConsultarTodosPanel y cambiar el contenido
        ConsultarTodosPanel panelConsultar = new ConsultarTodosPanel(this);
        this.setContentPane(panelConsultar);
        this.revalidate(); // Actualizar el JFrame
        this.repaint();
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        // Crear y mostrar la GUI en el hilo de eventos de AWT
        EventQueue.invokeLater(() -> {
            new pagprincipal().setVisible(true);
        });
    }

    // Realizar la acción de mantenimiento de usuarios
    private void realizarMantenimiento() {
        // Abrir una nueva ventana, mostrar un mensaje, etc.
        JOptionPane.showMessageDialog(this, "Funcionalidad de Mantenimiento de Usuarios aún no implementada.");
    }
}
