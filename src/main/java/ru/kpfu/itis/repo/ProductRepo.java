package ru.kpfu.itis.repo;

import ru.kpfu.itis.entities.Product;
import ru.kpfu.itis.utilities.Database;
import ru.kpfu.itis.utilities.ManufacturerUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {


    public static void add(Product product) throws SQLException {
        String q = "insert into products(name, price, weight, manufacturer, category_id) values(?,?,?,?,?);";
        PreparedStatement p = Database.getConnection().prepareStatement(q);
        p.setNString(1, product.getName());
        p.setDouble(2, product.getPrice());
        p.setDouble(3, product.getWeight());
        p.setNString(4, product.getManufacturer().getCountry());
        p.setInt(5, product.getCategoryId());

        p.executeUpdate();
    }

    public static List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String q = "select * from products;";

        ResultSet set = Database.getConnection().createStatement().executeQuery(q);

        while (set.next()) {
            products.add(new Product(
                            set.getInt(1),
                            set.getNString(2),
                            set.getDouble(3),
                            set.getDouble(4),
                            ManufacturerUtils.getManufacturer(set.getNString(5)),
                            set.getInt(6)
                    )
            );
        }
        return products;
    }


    public static String[][] getTable() {
        try {
            List<Product> res = getAll();
            String[][] data = new String[res.size()][5];
            int i = 0;
            for (Product product : res) {
                data[i][0] = product.getName();
                data[i][1] = String.valueOf(product.getPrice());
                data[i][2] = String.valueOf(product.getWeight());
                data[i][3] = product.getManufacturer().getCountry();
                data[i][4] = CategoryRepo.getName(product.getCategoryId());
                i++;
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[][]{};
    }
}
