package form;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import db.SessionManager;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ExpensesChartPane extends javax.swing.JPanel {

    public ExpensesChartPane() {
        initComponents();
        setOpaque(false);
        //loadExpenses();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(221, 224, 228));

        jLabel1.setText("asdasdasd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(2289, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(553, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
//    public void loadExpenses() {
//        // Get user ID from the session
//        int userId = 1;//SessionManager.getInstance().getUserId(); hardcode for testing
//
//        // SQL query to get expenses, including username and category name
//        String query = "SELECT e.expense_id, u.username, c.name AS category, e.date, e.amount " +
//                       "FROM expenses e " +
//                       "JOIN users u ON e.user_id = u.user_id " +
//                       "JOIN categories c ON e.category_id = c.category_id " +
//                       "WHERE e.user_id = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setInt(1, userId); // Set user ID as parameter
//            ResultSet rs = stmt.executeQuery();
//
//            // Create a vector to hold the data for the table
//            Vector<Vector<Object>> data = new Vector<>();
//            while (rs.next()) {
//                Vector<Object> row = new Vector<>();
//                row.add(rs.getInt("expense_id"));
//                row.add(rs.getString("username"));
//                row.add(rs.getString("category"));
//                row.add(rs.getDate("date"));
//                row.add(rs.getDouble("amount"));
//                data.add(row);
//            }
//
//            // Define column names
//            Vector<String> columns = new Vector<>();
//            columns.add("Expense ID");
//            columns.add("Username");
//            columns.add("Category");
//            columns.add("Date");
//            columns.add("Amount");
//
//            // Set the table model
//            expensePaneTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error fetching expenses: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
