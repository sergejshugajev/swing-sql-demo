package ru.kpfu.itis.utilities;

import java.sql.*;

public final class Database {

    private Connection conn;
    private static Database database;

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "database_name";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";


    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(URL + DATABASE_NAME, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {

        if (database == null) {
            synchronized (Database.class) {
                if (database == null) {
                    database = new Database();
                }
            }
        }
        return database;
    }

    public static Connection getConnection() {

        return getInstance().conn;
    }

}
