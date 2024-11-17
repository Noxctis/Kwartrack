package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        String server = "localhost";
        String port = "3306";
        String database = "kwartrack";
        String userName = "root";
        String password = "root";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName,
                    password);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to the database. Reason: " + e.getMessage());
        }
    }

    // Get the connection (reconnect if necessary)
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed() || !connection.isValid(2)) {
            DatabaseConnection.getInstance().connectToDatabase(); // Reconnect if invalid
        }
        return connection;
    }

    // Close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Failed to close database connection: " + e.getMessage());
            }
        }
    }

    public void setConnection(Connection connection) {
        DatabaseConnection.connection = connection;
    }
}
