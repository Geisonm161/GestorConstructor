// ProductDAO.java
package conectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Product;

public class ProductDAO {

    private final Connection cn;

    /**
     * Crea una instancia del DAO con una conexión ya establecida.
     *
     * @param cn conexión JDBC válida y abierta hacia la base de datos.
     */
    public ProductDAO(Connection cn) {
        this.cn = cn;
    }

    /**
     * Recupera todos los productos junto con su tienda asociada.
     * <p>
     * La consulta hace un {@code JOIN} entre {@code products} y {@code stores}
     * para obtener, además del detalle del producto, el nombre, dirección y
     * contacto de la ferretería. Los alias en el SELECT coinciden con los
     * nombres de las propiedades del modelo {@link Product}.
     * </p>
     *
     * @return lista con todos los productos encontrados (posiblemente vacía, nunca {@code null}).
     * @throws SQLException si ocurre un error al ejecutar la consulta o al leer el {@link ResultSet}.
     */
    public List<Product> findAll() throws SQLException {
        String query = """
            SELECT p.id,
                   p.name AS productName,
                   p.description AS productDescription,
                   CAST(p.price AS DECIMAL(10,2)) AS price,
                   p.unit,
                   p.image_path AS imagePath,
                   s.name AS hardwareStoreName,
                   s.address,
                   s.contact,
                   p.category_id AS categoryId
            FROM products p
            JOIN stores s ON s.id = p.store_id
            """;

        List<Product> productList = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product pd = new Product();
                pd.setFullInfoProduct(
                        rs.getInt("id"),
                        rs.getString("productName"),
                        rs.getString("productDescription"),
                        rs.getFloat("price"),
                        rs.getString("unit"),
                        rs.getString("imagePath"),
                        rs.getString("hardwareStoreName"),
                        rs.getString("address"),
                        rs.getString("contact"),
                        rs.getInt("categoryId")
                );
                productList.add(pd);
            }
        }
        return productList;
    }
}