package model;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepositBalance {
    private int userId;
    private int categoryId; // Optional, can be null
    private double amount;
    private String date;

    // Constructor
    public DepositBalance(int userId, int categoryId, double amount, String date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Expected: YYYY-MM-DD");
        }

        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Expected: YYYY-MM-DD");
        }
        this.date = date;
    }

    // Utility method to validate the date format (YYYY-MM-DD)
    private boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Save deposit to the database
    public boolean saveToDatabase() {
        String insertQuery = "INSERT INTO incomes (user_id, category_id, amount, date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setInt(1, this.userId);
            if (this.categoryId > 0) {
                stmt.setInt(2, this.categoryId);
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.setDouble(3, this.amount);
            stmt.setString(4, this.date);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return "DepositBalance{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
