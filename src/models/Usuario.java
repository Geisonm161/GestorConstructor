package models;

/**
 *
 * @author Katherine
 */
public class Usuario {

    private int id;
    private String fullName;
    private String correo;
    private String password;
    private String telefono;

    public Usuario() {
    }

    public Usuario(String fullName, String correo, String password, String telefono) {
        this.fullName = fullName;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
    }

    public Usuario(int id, String fullName, String correo, String password, String telefono) {
        this.id = id;
        this.fullName = fullName;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", fullName='" + fullName + '\''
                + ", correo='" + correo + '\''
                + ", telefono='" + telefono + '\''
                + '}';
    }
}
