package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/kwartrack";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/kwartrack", "root", "password");
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        return null;
    }
}

}