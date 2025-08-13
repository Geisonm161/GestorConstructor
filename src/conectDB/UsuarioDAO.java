package conectDB;

import clases.Usuario;
import java.sql.*;
import java.util.regex.Pattern;

/**
 *
 * @author Katherine Matos
 */
public class UsuarioDAO {
    private conectMysql conexion;

    public UsuarioDAO() {
        conexion = new conectMysql();
    }

    // Validar correo 
    public boolean esCorreoValido(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(correo).matches();
    }
    
    // Registrar Usuario
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO users (full_name, correo, password, telefono) VALUES (?, ?, ?, ?)";
        try (Connection cn = conexion.conectar(); 
             PreparedStatement statement = cn.prepareStatement(sql)) {

            if (cn == null) {
                System.err.println("No se pudo establecer conexión con la base de datos");
                return false;
            }

            statement.setString(1, usuario.getFullName());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getPassword()); 
            statement.setString(4, usuario.getTelefono());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario registrado exitosamente");
                return true;
            } else {
                System.err.println("No se pudo registrar el usuario");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    // Verificar si un correo existe
    public boolean correoExiste(String correo) {
        String sql = "SELECT COUNT(*) FROM users WHERE correo = ?";
        try (Connection cn = conexion.conectar(); 
             PreparedStatement statement = cn.prepareStatement(sql)) {

            if (cn == null) {
                return false;
            }

            statement.setString(1, correo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar correo: " + e.getMessage());
        }
        return false;
    }

    // Autenticar Usuario
    public Usuario autenticarUsuario(String correo, String password) {
        String sql = "SELECT id, full_name, correo, telefono FROM users WHERE correo = ? AND password = ?";
        try (Connection cn = conexion.conectar(); 
             PreparedStatement statement = cn.prepareStatement(sql)) {

            if (cn == null) {
                return null;
            }

            statement.setString(1, correo);
            statement.setString(2, password); 

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setFullName(resultSet.getString("full_name"));
                    usuario.setCorreo(resultSet.getString("correo"));
                    usuario.setTelefono(resultSet.getString("telefono"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
        }
        return null;
    }
    
    // Actualizar Usuario
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE users SET full_name = ?, correo = ?, telefono = ? WHERE id = ?";
        try (Connection cn = conexion.conectar(); 
             PreparedStatement statement = cn.prepareStatement(sql)) {

            if (cn != null) {
                statement.setString(1, usuario.getFullName());
                statement.setString(2, usuario.getCorreo());
                statement.setString(3, usuario.getTelefono());
                statement.setInt(4, usuario.getId());
                
                int result = statement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
        return false;
    }
}
