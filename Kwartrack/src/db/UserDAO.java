package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    // Register a new user
    public User registerUser(String username, String email, char[] password, String firstname, String lastname) {

        if (checkIfUsernameExists(username)) {
            LOGGER.warning("Username already exists: " + username);
            javax.swing.JOptionPane.showMessageDialog(null, // Use 'null' to center the dialog on screen
                    "Username already exists. Please choose another username.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (checkIfEmailExists(email)) {
            LOGGER.warning("Email already exists: " + email);
            javax.swing.JOptionPane.showMessageDialog(null, // Use 'null' to center the dialog on screen
                    "Email already exists. Please choose another email.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!isPasswordStrong(password)) {
            LOGGER.warning("Password does not meet strength requirements.");
            javax.swing.JOptionPane.showMessageDialog(null, // Use 'null' to center the dialog on screen
                    "Password does not meet strength requirements.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String insertQuery = "INSERT INTO users (username, email, password_hash, firstname, lastname) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Convert char[] password to String (temporarily)
            String passwordString = new String(password);
            String hashedPassword = BCrypt.hashpw(passwordString, BCrypt.gensalt());
            // Clear password string immediately after use
            java.util.Arrays.fill(password, '\0');

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, firstname);
            stmt.setString(5, lastname);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userID = generatedKeys.getInt(1);
                        return new User(userID, firstname, lastname, email, username);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Registration failed: " + e.getMessage());
        } finally {
            // Clear the password array after use for security
            java.util.Arrays.fill(password, '\0');
        }
        return null;
    }

    // Check password strength
    private boolean isPasswordStrong(char[] password) {
        if (password.length < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasDigit = false;

        for (char c : password) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasUppercase && hasDigit) {
                break; // Stop early if both conditions are met
            }
        }

        return hasUppercase && hasDigit;
    }

        // Check if a username exists
        public boolean checkIfUsernameExists(String username) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
    
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)) {
    
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            } catch (SQLException e) {
                LOGGER.severe("Error checking username: " + e.getMessage());
            }
            return false;
        }

    // Login user with feedback and session token generation
    public String loginUserWithFeedback(String username, char[] password) {
        String sql = "SELECT user_id, password_hash FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    int userId = rs.getInt("user_id");
                    String passwordString = new String(password);

                    if (BCrypt.checkpw(passwordString, storedHash)) {
                        // Generate session token
                        String sessionToken = generateSessionToken();
                        long expirationTime = System.currentTimeMillis() + (15 * 60 * 1000); // 15 minutes

                        // Save session token to the database
                        saveSessionToken(userId, sessionToken, expirationTime);

                        return "Login successful";
                    } else {
                        return "Incorrect password";
                    }
                } else {
                    return "Username does not exist";
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Login failed: " + e.getMessage());
        }
        return "Error occurred during login";
    }

    // Generate a secure session token (UUID with timestamp)
    private String generateSessionToken() {
        return UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
    }

    // Save session token to database
    public void saveSessionToken(int userId, String sessionToken, long expirationTime) throws SQLException {
        String sql = "INSERT INTO user_sessions (user_id, session_token, expires_at) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, sessionToken);
            stmt.setTimestamp(3, new java.sql.Timestamp(expirationTime));
            stmt.executeUpdate();
        }
    }

    // Retrieve session token for a user
    public String getSessionToken(int userId) throws SQLException {
        String sql = "SELECT session_token FROM user_sessions WHERE user_id = ? AND expires_at > NOW()";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("session_token");
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error retrieving session token: " + e.getMessage());
        }
        return null; // No valid session token found
    }

    // Check if an email exists
    public boolean checkIfEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error checking email: " + e.getMessage());
        }
        return false;
    }

    // Retrieve user by username
    public User getUserByUsername(String username) {
        String query = "SELECT user_id, firstname, lastname, email, username FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("email"),
                            rs.getString("username"));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error retrieving user: " + e.getMessage());
        }
        return null;
    }
}
