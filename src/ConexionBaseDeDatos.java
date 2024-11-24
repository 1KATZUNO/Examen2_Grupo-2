import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/github"; // URL de la base de datos
    private static final String USER = "root"; // Usuario
    private static final String PASSWORD = "2207"; // Contraseña

    /**
     * Método para obtener una conexión a la base de datos.
     *
     * @return una instancia de Connection
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Método principal para probar la conexión.
     */
    public static void main(String[] args) {
        // Probar la conexión
        try (Connection connection = obtenerConexion()) {
            if (connection != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}
