package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAO {

    // Method to register a new user
    public boolean registerUser(String username, String email, String password, String firstname, String lastname) {
        String insertQuery = "INSERT INTO users (username, email, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
    
            // Hash the password using BCrypt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    
            // Set the parameters
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, firstname);
            stmt.setString(5, lastname);
    
            // Execute the query
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if a row was inserted
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }
    

    // Method to login an existing user
    public boolean loginUser(String username, String password) {
        String sql = "SELECT password_hash FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    return BCrypt.checkpw(password, storedHash); // Verify password
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
