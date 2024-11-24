import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.File;

/**
 * Clase ImagenPanel que extiende JPanel.
 * Permite establecer una imagen de fondo en un panel.
 */
public class ImagenPanel extends JPanel {
    private Image imagen; // Atributo para almacenar la imagen de fondo

    /**
     * Constructor que carga una imagen desde el classpath o una ruta absoluta.
     *
     * @param rutaImagen Ruta del archivo de la imagen a utilizar como fondo.
     */
    public ImagenPanel(String rutaImagen) {
        // Intenta cargar la imagen desde el classpath
        URL urlImagen = getClass().getResource(rutaImagen);
        if (urlImagen != null) {
            this.imagen = new ImageIcon(urlImagen).getImage();
        } else {
            // Si no se encuentra en el classpath, intenta cargarla desde el sistema de archivos
            File archivo = new File(rutaImagen);
            if (archivo.exists()) {
                this.imagen = new ImageIcon(archivo.getAbsolutePath()).getImage();
            } else {
                System.err.println("No se encontró la imagen en la ruta: " + rutaImagen);
            }
        }
    } 

    /**
     * Sobrescribe el método paintComponent para dibujar la imagen de fondo.
     * 
     * @param g Objeto Graphics utilizado para dibujar en el panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método de la clase padre para realizar el pintado predeterminado
        if (imagen != null) { // Verifica que la imagen no sea nula antes de dibujarla
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
