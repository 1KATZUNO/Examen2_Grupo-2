import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Eliminar_Usuario extends ImagenPanel {

    public Eliminar_Usuario() {
        // Ruta de la imagen de fondo
        super("src/img/fondo_Eliminar.jpg");

        // Configuración del diseño
        setLayout(new GridBagLayout());

        // Crear componentes
        JLabel label = new JLabel("Ingrese ID de Usuario a eliminar:");
        JTextField txtIdUsuario = new JTextField(15);
        JButton btnEliminar = new JButton();
        JButton btnVolver = new JButton();

        // Personalizar fuentes y tamaños
        Font fuenteGrande = new Font("Arial", Font.BOLD, 20);
        label.setFont(fuenteGrande);
        label.setForeground(Color.WHITE); // Color inicial

        txtIdUsuario.setFont(fuenteGrande);

        // Configurar eventos para cambiar color de `JLabel`
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

    }
   
}
