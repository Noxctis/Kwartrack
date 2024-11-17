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
            connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
        }
    
        public static Connection getConnection() {
            return connection;
    }

    public void setConnection(Connection connection) {
        DatabaseConnection.connection = connection;
    }
}
