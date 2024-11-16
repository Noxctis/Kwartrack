package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO {

    public User registerUser(String username, String email, String password, String firstname, String lastname) {
        // Check if the username or email already exists
        if (checkIfUsernameExists(username)) {
            System.out.println("Username already exists.");
            return null;
        }
        if (checkIfEmailExists(email)) {
            System.out.println("Email already exists.");
            return null;
        }

        // Validate password strength
        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            System.out
                    .println("Password must be at least 8 characters long, contain an uppercase letter, and a number.");
            return null;
        }

        String insertQuery = "INSERT INTO users (username, email, password_hash, firstname, lastname) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

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
            if (rowsAffected > 0) {
                // Retrieve the generated userID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userID = generatedKeys.getInt(1); // Retrieve generated userID
                        // Return a User object
                        return new User(userID, firstname, lastname, email, username);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Registration failed: " + e.getMessage());
        }
        return null; // Return null if registration fails
    }

    // Method to login an existing user
    public String loginUserWithFeedback(String username, String password) {
        String sql = "SELECT password_hash FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    if (BCrypt.checkpw(password, storedHash)) {
                        return "Login successful";
                    } else {
                        return "Incorrect password";
                    }
                } else {
                    return "Username does not exist";
                }
            }
        } catch (SQLException e) {
            System.err.println("Login failed: " + e.getMessage());
        }
        return "Error occurred during login";
    }

    // Check if a username exists
    public boolean checkIfUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Return true if the username exists
            }
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        return false;
    }

    // Check if an email exists
    public boolean checkIfEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Return true if the email exists
            }
        } catch (SQLException e) {
            System.err.println("Error checking email: " + e.getMessage());
        }
        return false;
    }

    // Retrieve user details by username
    public User getUserByUsername(String username) {
        String query = "SELECT user_id, firstname, lastname, email, username FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
        return null;
    }

    // Optional: Transaction management for registering a user (if needed for atomic
    // operations)
    public boolean registerUserWithTransaction(String username, String email, String password, String firstname,
            String lastname) {
        String insertQuery = "INSERT INTO users (username, email, password_hash, firstname, lastname) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Check if username or email exists
            if (checkIfUsernameExists(username) || checkIfEmailExists(email)) {
                System.out.println("Username or email already exists.");
                conn.rollback(); // Rollback transaction
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, hashedPassword);
                stmt.setString(4, firstname);
                stmt.setString(5, lastname);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit(); // Commit transaction
                    return true;
                } else {
                    conn.rollback(); // Rollback transaction
                }
            }
        } catch (SQLException e) {
            System.err.println("Transaction failed: " + e.getMessage());
        }
        return false;
    }
}
