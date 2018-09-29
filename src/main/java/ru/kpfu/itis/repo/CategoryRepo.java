package ru.kpfu.itis.repo;

import ru.kpfu.itis.entities.Category;
import ru.kpfu.itis.exceptions.NotValidCategoryException;
import ru.kpfu.itis.utilities.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepo {

    public static void add(Category category) throws NotValidCategoryException, SQLException {
        check(category);
        String q = "insert into categories(name,p_id) values(?,?);";
        PreparedStatement p = Database.getConnection().prepareStatement(q);
        p.setNString(1, category.getName());
        if (category.getParentId() == null) {
            p.setNull(2, Types.INTEGER);
        } else {
            p.setInt(2, category.getParentId());
        }
        p.executeUpdate();
    }


    public static List<Category> getAll() throws SQLException {
        String q = "select * from categories";
        ResultSet set = Database.getConnection().createStatement().executeQuery(q);

        List<Category> categories = new ArrayList<>();
        while (set.next()) {
            Integer parent = set.getObject(3) == null ? null : (Integer) set.getObject(3);
            categories.add(new Category(set.getInt(1), set.getNString(2), parent));
        }

        return categories;
    }


    public static String[] getAllCategoriesNames() {
        List<Category> info = null;
        try {
            info = getAll();
        } catch (SQLException e) {
            info = new ArrayList<>();
            e.printStackTrace();
        }
        String[] res = new String[info.size() + 1];
        int i = 0;
        for (Category c : info) {
            res[i] = c.getName();
            i++;
        }
        res[i] = "none";
        return res;
    }


    public static Integer getId(String name) throws SQLException {
        String s = "select id from categories where name = ?;";
        PreparedStatement p = Database.getConnection().prepareStatement(s);
        p.setNString(1, name);
        ResultSet set = p.executeQuery();
        if (set.next()) {
            return set.getObject(1) == null ? null : (Integer) set.getObject(1);
        }

        return null;
    }


    public static String getName(int id) throws SQLException {
        String s = "select name from categories where id = ?;";
        PreparedStatement p = Database.getConnection().prepareStatement(s);
        p.setInt(1, id);
        ResultSet set = p.executeQuery();
        if (set.next()) {
            return set.getNString(1);
        }
        return "none";
    }


    private static void check(Category category) throws NotValidCategoryException {
        if (category == null) {
            throw new NullPointerException("category must not be null!");
        }
        if (category.getName() == null || ("").equals(category.getName())) {
            throw new NotValidCategoryException("Category must have not empty name");
        }
    }

}
