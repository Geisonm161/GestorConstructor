package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformationStores {

    private final Connection cn;

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

    public InformationStores(Connection cn) {
        this.cn = cn;
    }

    public List<String[]> load() {
        List<String[]> informationList = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(QUERY); ResultSet rs = ps.executeQuery()) {
            System.err.println("rs");
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String[] row = new String[cols];
                for (int i = 1; i <= cols; i++) {
                    row[i - 1] = rs.getString(i); // usa alias si quieres por nombre
                }
                System.out.println("otro" + row[1]);

                informationList.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar información: " + e.getMessage());
        }
        return informationList;
    }
}
