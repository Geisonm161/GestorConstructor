// ProductDAO.java
package conectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Product;

public class ProductDAO {

    private final Connection cn;

    public ProductDAO(Connection cn) {
        this.cn = cn;
    }

    public List<Product> findAll() throws SQLException {
        String query = """
            SELECT p.name as "productName", p.description as "productDescription", p.price, p.unit, 
                               p.image_path as "imagePath", s.name as "hardwareStoreName", s.address, s.contact,
                               p.category_id as "categoryId"
                        FROM products p
                        JOIN stores s on s.id = p.store_id
        """;

        List<Product> productList = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product pd = new Product();
                pd.setFullInfoProduct(
                        rs.getString("productName"),
                        rs.getString("productDescription"),
                        rs.getString("price"),
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
