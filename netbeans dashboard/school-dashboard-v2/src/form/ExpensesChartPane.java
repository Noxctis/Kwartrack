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
        BalanceLineChart = new chart.LineChart();
        expenseChartAddExpenseButton = new javax.swing.JButton();
        expenseChartRemoveExpenseButton = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1100, 700));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel2.setBackground(new java.awt.Color(221, 224, 228));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Your Expense Trend");

        BalanceLineChart.setToolTipText("");
        BalanceLineChart.setFocusTraversalPolicyProvider(true);
        BalanceLineChart.setFocusable(false);
        BalanceLineChart.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        expenseChartAddExpenseButton.setBackground(new java.awt.Color(204, 204, 204));
        expenseChartAddExpenseButton.setText("Add Expense");
        expenseChartAddExpenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expenseChartAddExpenseButtonActionPerformed(evt);
            }
        });

        expenseChartRemoveExpenseButton.setBackground(new java.awt.Color(204, 204, 204));
        expenseChartRemoveExpenseButton.setText("Remove Expense");
        expenseChartRemoveExpenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expenseChartRemoveExpenseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BalanceLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(expenseChartAddExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expenseChartRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BalanceLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(expenseChartAddExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expenseChartRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void expenseChartAddExpenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expenseChartAddExpenseButtonActionPerformed
        // TODO add your handling code here:
        addExpense addExpenseWindow = new addExpense(); // Pass the reference to ExpensesPane
        addExpenseWindow.setVisible(true);
        addExpenseWindow.pack();
        addExpenseWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addExpenseWindow.setLocationRelativeTo(null);
    }//GEN-LAST:event_expenseChartAddExpenseButtonActionPerformed

    private void expenseChartRemoveExpenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expenseChartRemoveExpenseButtonActionPerformed
        // TODO add your handling code here:
        //new RemoveWindow().setVisible(true);
        RemoveWindow removeWindow = new RemoveWindow(); // Pass the reference to ExpensesPane
        removeWindow.setVisible(true);
        removeWindow.pack();
        removeWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        removeWindow.setLocationRelativeTo(null);
    }//GEN-LAST:event_expenseChartRemoveExpenseButtonActionPerformed
    
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
    private chart.LineChart BalanceLineChart;
    private javax.swing.JButton expenseChartAddExpenseButton;
    private javax.swing.JButton expenseChartRemoveExpenseButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
