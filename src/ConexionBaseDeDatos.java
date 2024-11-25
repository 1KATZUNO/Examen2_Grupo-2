import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/github"; // URL de la base de datos
    private static final String USER = "root"; // Usuario
    private static final String PASSWORD = "Jkrloz21!!"; // Contraseña

    /**
     * Método para obtener una conexión a la base de datos.
     *
     * @return una instancia de Connection
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

   
}
