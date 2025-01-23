package form;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import db.SessionManager;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ExpensesPane extends javax.swing.JPanel {

    public ExpensesPane() {
        initComponents();
        setOpaque(false);
        loadExpenses();
        addTableModelListener();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        expensePaneTable = new javax.swing.JTable();
        expensePaneAddExpenseButton = new javax.swing.JButton();
        expensePaneRemoveExpenseButton = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(221, 224, 228));

        jLabel5.setText(".");

        expensePaneTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Expense ID", "Category", "Date", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(expensePaneTable);

        expensePaneAddExpenseButton.setBackground(new java.awt.Color(204, 204, 204));
        expensePaneAddExpenseButton.setText("Add Expense");
        expensePaneAddExpenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expensePaneAddExpenseButtonActionPerformed(evt);
            }
        });

        expensePaneRemoveExpenseButton.setBackground(new java.awt.Color(204, 204, 204));
        expensePaneRemoveExpenseButton.setText("Remove Expense");
        expensePaneRemoveExpenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expensePaneRemoveExpenseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(1169, 1169, 1169))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(expensePaneAddExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expensePaneRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(expensePaneAddExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(expensePaneRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
                        .addContainerGap())))
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
    
    // Method to add the TableModelListener to the table
    private void addTableModelListener() {
        expensePaneTable.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            // Get updated data based on the cell change
            Object updatedValue = expensePaneTable.getValueAt(row, column);
            int expenseId = (int) expensePaneTable.getValueAt(row, 0); // Expense ID is in the first column

            // Check which column was edited and update accordingly
            if (column == 3) { // Date column (index 3)
                // Date was updated
                java.sql.Date updatedDate = java.sql.Date.valueOf(updatedValue.toString());
                updateExpenseDate(expenseId, updatedDate);
            } else if (column == 4) { // Amount column (index 4)
                // Amount was updated
                double updatedAmount = Double.parseDouble(updatedValue.toString());
                updateExpenseAmount(expenseId, updatedAmount);
            }
        });
    }

    // Method to update the expense date in the database
    private void updateExpenseDate(int expenseId, java.sql.Date updatedDate) {
        String updateDateQuery = "UPDATE expenses SET date = ? WHERE expense_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateDateQuery)) {

            stmt.setDate(1, updatedDate);
            stmt.setInt(2, expenseId);

            stmt.executeUpdate(); // Update the date in the database

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating expense date: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to update the expense amount in the database
    private void updateExpenseAmount(int expenseId, double updatedAmount) {
        String updateAmountQuery = "UPDATE expenses SET amount = ? WHERE expense_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateAmountQuery)) {

            stmt.setDouble(1, updatedAmount);
            stmt.setInt(2, expenseId);

            stmt.executeUpdate(); // Update the amount in the database

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating expense amount: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void loadExpenses() {
        // Get user ID from the session
        int userId = 1;//SessionManager.getInstance().getUserId(); hardcode for testing

        // SQL query to get expenses, including username and category name
        String query = "SELECT e.expense_id, u.username, c.name AS category, e.date, e.amount " +
                       "FROM expenses e " +
                       "JOIN users u ON e.user_id = u.user_id " +
                       "JOIN categories c ON e.category_id = c.category_id " +
                       "WHERE e.user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId); // Set user ID as parameter
            ResultSet rs = stmt.executeQuery();

            // Create a vector to hold the data for the table
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("expense_id"));
                row.add(rs.getString("username"));
                row.add(rs.getString("category"));
                row.add(rs.getDate("date"));
                row.add(rs.getDouble("amount"));
                data.add(row);
            }

            // Define column names
            Vector<String> columns = new Vector<>();
            columns.add("Expense ID");
            columns.add("Username");
            columns.add("Category");
            columns.add("Date");
            columns.add("Amount");

            // Set the table model
            expensePaneTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching expenses: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void expensePaneAddExpenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expensePaneAddExpenseButtonActionPerformed
        // TODO add your handling code here:
        addExpense addExpenseWindow = new addExpense(this); // Pass the reference to ExpensesPane
        addExpenseWindow.setVisible(true);
        addExpenseWindow.pack();
        addExpenseWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addExpenseWindow.setLocationRelativeTo(null);
    }//GEN-LAST:event_expensePaneAddExpenseButtonActionPerformed

    private void expensePaneRemoveExpenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expensePaneRemoveExpenseButtonActionPerformed
        // TODO add your handling code here:
        //new RemoveWindow().setVisible(true);
        RemoveWindow removeWindow = new RemoveWindow(this); // Pass the reference to ExpensesPane
        removeWindow.setVisible(true);
        removeWindow.pack();
        removeWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        removeWindow.setLocationRelativeTo(null);
    }//GEN-LAST:event_expensePaneRemoveExpenseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton expensePaneAddExpenseButton;
    private javax.swing.JButton expensePaneRemoveExpenseButton;
    private javax.swing.JTable expensePaneTable;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
