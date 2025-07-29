package conectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectMysql {
    private Connection cn;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(
                "jdbc:mysql://localhost/gestorconstructor?serverTimezone=UTC",
                "root",
                ""
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
