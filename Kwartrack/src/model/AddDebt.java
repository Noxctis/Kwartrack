package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DatabaseConnection;

public class AddDebt {
    private int debtorId;
    private int creditorId;
    private double debtAmount;
    private String dueDate;

    public AddDebt(int debtorId, int creditorId, double debtAmount, String dueDate) {
        this.debtorId = debtorId;
        this.creditorId = creditorId;
        this.debtAmount = debtAmount;
        this.dueDate = dueDate;
    }

    public boolean saveToDatabase() {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            // Get a valid database connection
            connection = DatabaseConnection.getConnection();

            // SQL query to insert a new debt record
            String sql = "INSERT INTO debts (debtor_id, creditor_id, debt_amount, due_date) VALUES (?, ?, ?, ?)";

            // Prepare the statement
            ps = connection.prepareStatement(sql);
            ps.setInt(1, debtorId);       // Set debtor's ID
            ps.setInt(2, creditorId);     // Set creditor's ID
            ps.setDouble(3, debtAmount);  // Set debt amount
            ps.setString(4, dueDate);     // Set due date

            // Execute the query
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Return true if at least one row was inserted

        } catch (SQLException e) {
            System.err.println("Error saving debt to the database: " + e.getMessage());
            return false; // Return false if an error occurred

        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
