package model;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDebt {
    private int debtorId; // The user who owes the debt
    private int creditorId; // The user to whom the debt is owed
    private double amount;
    private String dateIssued;
    private String dateDue;
    private boolean isPaid;

    // Constructor
    public AddDebt(int debtorId, int creditorId, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        this.debtorId = debtorId;
        this.creditorId = creditorId;
        this.amount = amount;
        this.dateIssued = java.time.LocalDate.now().toString(); // Set current date as dateIssued
        this.dateDue = null; // Default to null
        this.isPaid = false; // Default to unpaid
    }

    // Utility method to validate the date format (YYYY-MM-DD)
    private boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Save debt to the database
    public boolean saveToDatabase() {
        String insertQuery = "INSERT INTO debts (debtor_id, creditor_id, amount, date_issued, is_paid) " +
                             "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setInt(1, this.debtorId);
            stmt.setInt(2, this.creditorId);
            stmt.setDouble(3, this.amount);
            stmt.setString(4, this.dateIssued);
            stmt.setBoolean(5, this.isPaid);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return "AddDebt{" +
                "debtorId=" + debtorId +
                ", creditorId=" + creditorId +
                ", amount=" + amount +
                ", dateIssued='" + dateIssued + '\'' +
                ", dateDue='" + dateDue + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}