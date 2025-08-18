package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de obtener información combinada de productos y tiendas
 * desde la base de datos.
 * 
 * <p>Realiza una consulta SQL que une las tablas {@code products} y 
 * {@code stores}, devolviendo un listado de filas con la información
 * correspondiente.</p>
 *
 * <p>Cada fila se representa como un arreglo de cadenas ({@code String[]}),
 * donde los índices corresponden al orden de las columnas seleccionadas
 * en la consulta.</p>
 *
 * Columnas devueltas por la consulta:
 * <ul>
 *   <li>nameProduct - Nombre del producto</li>
 *   <li>descriptionProduct - Descripción del producto</li>
 *   <li>priceProduct - Precio del producto</li>
 *   <li>categoryId - ID de la categoría</li>
 *   <li>imageProduct - Ruta de la imagen del producto</li>
 *   <li>nameStore - Nombre de la tienda</li>
 *   <li>addressStore - Dirección de la tienda</li>
 *   <li>contactStore - Contacto de la tienda</li>
 * </ul>
 *
 * @author Geison Medina
 */

public class InformationStores {

    /**
     * Conexión activa a la base de datos.
     */
    private final Connection cn;

    /**
     * Consulta SQL que obtiene información de productos y tiendas
     * uniendo ambas tablas por el ID de tienda.
     */
    private static final String QUERY
            = "SELECT p.name AS nameProduct, "
            + "       p.description AS descriptionProduct, "
            + "       p.price AS priceProduct, "
            + "       p.category_id AS categoryId, "
            + "       p.image_path AS imageProduct, "
            + "       s.name AS nameStore, "
            + "       s.address AS addressStore, "
            + "       s.contact AS contactStore "
            + "FROM products p "
            + "JOIN stores s ON s.id = p.store_id";

    /**
     * Crea una nueva instancia de {@code InformationStores}.
     *
     * @param cn conexión activa a la base de datos (no debe ser {@code null}).
     */
    public InformationStores(Connection cn) {
        this.cn = cn;
    }

    /**
     * Ejecuta la consulta SQL definida en {@link #QUERY} y carga
     * la información de productos y tiendas.
     *
     * @return lista de arreglos de cadenas con la información consultada.
     *         Si ocurre un error o no existen registros, se devuelve
     *         una lista vacía.
     */
    public List<String[]> load() {
        List<String[]> informationList = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(QUERY); ResultSet rs = ps.executeQuery()) {
            System.err.println("rs"); // Mensaje de depuración
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String[] row = new String[cols];
                for (int i = 1; i <= cols; i++) {
                    row[i - 1] = rs.getString(i); // Se puede usar alias por nombre
                }
                System.out.println("otro " + row[1]); // Depuración
                informationList.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar información: " + e.getMessage());
        }
        return informationList;
    }
}