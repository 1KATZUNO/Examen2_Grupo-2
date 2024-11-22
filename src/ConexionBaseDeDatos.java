import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {
    public static void main(String[] args) {
        // Datos de la conexión
        String url = "jdbc:mysql://localhost:3306/github"; // URL de la base de datos
        String user = "root"; // Usuario
        String password = "2207"; // Contraseña
        
        // Establecer conexión
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}