package models;

/**
 *
 * @author Katherine
 */
public class Usuario {

    /** Identificador único del usuario. */
    private int id;
    /** Nombre completo del usuario. */
    private String fullName;
    /** Correo electrónico del usuario. */
    private String correo;
    /** Contraseña del usuario. */
    private String password;
    /** Número de teléfono del usuario. */
    private String telefono;

    /** Constructor vacío para inicialización por defecto. */
    public Usuario() {
    }

    /**
     * Crea un usuario sin ID (usualmente para nuevos registros).
     * 
     * @param fullName nombre completo
     * @param correo correo electrónico
     * @param password contraseña
     * @param telefono teléfono
     */
    public Usuario(String fullName, String correo, String password, String telefono) {
        this.fullName = fullName;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
    }

    /**
     * Crea un usuario con todos los datos.
     * 
     * @param id identificador único
     * @param fullName nombre completo
     * @param correo correo electrónico
     * @param password contraseña
     * @param telefono teléfono
     */
    public Usuario(int id, String fullName, String correo, String password, String telefono) {
        this.id = id;
        this.fullName = fullName;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
    }

    /** @return identificador único del usuario */
    public int getId() {
        return id;
    }

    /**
     * Define el identificador único del usuario.
     * 
     * @param id nuevo ID del usuario
     */
    public void setId(int id) {
        this.id = id;
    }

    /** @return nombre completo del usuario */
    public String getFullName() {
        return fullName;
    }

    /**
     * Define el nombre completo del usuario.
     * 
     * @param fullName nuevo nombre completo
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /** @return correo electrónico del usuario */
    public String getCorreo() {
        return correo;
    }

    /**
     * Define el correo electrónico del usuario.
     * 
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /** @return contraseña del usuario */
    public String getPassword() {
        return password;
    }

    /**
     * Define la contraseña del usuario.
     * 
     * @param password nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @return teléfono del usuario */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Define el teléfono del usuario.
     * 
     * @param telefono nuevo número telefónico
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Representación en texto del usuario.
     * 
     * @return cadena con id, nombre, correo y teléfono
     */
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