
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

