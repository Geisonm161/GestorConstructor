package conectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión a una base de datos MySQL.
 * <p>
 * Provee el método {@link #conectar()} que inicializa el driver de MySQL y 
 * establece la conexión con la base de datos configurada.
 * </p>
 */
public class conectMysql {
    
    /** Objeto de conexión JDBC. */
    private Connection cn;

    /**
     * Establece la conexión con la base de datos MySQL.
     * <p>
     * Se utiliza el driver {@code com.mysql.cj.jdbc.Driver} y la URL 
     * {@code jdbc:mysql://localhost/gestorconstructor?serverTimezone=UTC}.
     * El usuario y la contraseña deben ajustarse según la configuración local.
     * </p>
     *
     * @return la conexión establecida ({@link Connection}) o {@code null} si ocurre algún error.
     */
    public Connection conectar() {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intentar establecer la conexión
            cn = DriverManager.getConnection(
                "jdbc:mysql://localhost/gestorconstructor?serverTimezone=UTC",
                "root", // usuario de la base de datos
                ""      // contraseña de la base de datos
            );
            System.out.println("CONECTADO");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e);
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e);
        }
        return cn;
    }
}
