
// librerías necesarias
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pagprincipal extends JFrame {

    // Componentes de la interfaz grfica
    private JPanel mainPanel;
    private JButton btnMantenimiento;
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
        lbBienvenute = new JLabel("Bienvenido a Mantenimiento", SwingConstants.CENTER);

        // Configuración de la etiqueta
        lbBienvenute.setFont(new Font("Dialog", Font.BOLD, 14));

        // Configuración del diseño del panel principal
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.add(lbBienvenute, BorderLayout.NORTH);
        mainPanel.add(btnMantenimiento, BorderLayout.CENTER);

        // Configuración del marco (JFrame)
        this.setTitle("Servidor GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        
        btnMantenimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                realizarMantenimiento();
            }
        });
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        // Crear y mostrar la GUI en el hilo de eventos de AWT
        EventQueue.invokeLater(() -> {
            new pagprincipal().setVisible(true);
        });
    }

    //realizar la acción de mantenimiento de usuarios
    private void realizarMantenimiento() {
      
        // abrir una nueva ventana, mostrar un mensaje, etc.
        JOptionPane.showMessageDialog(this, "Funcionalidad de Mantenimiento de Usuarios aún no implementada.");
    }

  
}
