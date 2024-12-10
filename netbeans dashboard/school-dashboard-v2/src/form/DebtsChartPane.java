package form;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import db.SessionManager;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JOptionPane;
import chart.ModelChart;
import java.util.ArrayList;
import java.util.List;

public class DebtsChartPane extends javax.swing.JPanel {

    public DebtsChartPane() {
        initComponents();
        setOpaque(false);
        loadDebtsData();
        //loadExpenses();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DebtsLineChart = new chart.LineChart();
        DebtsChartAddDebtbutton = new javax.swing.JButton();
        DebtsChartRemoveExpenseButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1100, 700));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel2.setBackground(new java.awt.Color(221, 224, 228));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Your Debts Overview");

        DebtsLineChart.setToolTipText("");
        DebtsLineChart.setFocusTraversalPolicyProvider(true);
        DebtsLineChart.setFocusable(false);
        DebtsLineChart.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        DebtsChartAddDebtbutton.setBackground(new java.awt.Color(204, 204, 204));
        DebtsChartAddDebtbutton.setText("Add Debt");
        DebtsChartAddDebtbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebtsChartAddDebtbuttonActionPerformed(evt);
            }
        });

        DebtsChartRemoveExpenseButton.setBackground(new java.awt.Color(204, 204, 204));
        DebtsChartRemoveExpenseButton.setText("Remove Debt");
        DebtsChartRemoveExpenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebtsChartRemoveExpenseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(DebtsLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DebtsChartAddDebtbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DebtsChartRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DebtsLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(DebtsChartAddDebtbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DebtsChartRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DebtsChartAddDebtbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebtsChartAddDebtbuttonActionPerformed
        // TODO add your handling code here:
        addDebtWindow addDebt = new addDebtWindow();
        addDebt.setVisible(true);
        addDebt.pack();
        addDebt.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addDebt.setLocationRelativeTo(null);
    }//GEN-LAST:event_DebtsChartAddDebtbuttonActionPerformed

    private void DebtsChartRemoveExpenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebtsChartRemoveExpenseButtonActionPerformed
        // TODO add your handling code here:
        RemoveWindow removeWindow = new RemoveWindow(); // Pass the reference to ExpensesPane
        removeWindow.setVisible(true);
        removeWindow.pack();
        removeWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        removeWindow.setLocationRelativeTo(null);
    }//GEN-LAST:event_DebtsChartRemoveExpenseButtonActionPerformed
    
    private void loadDebtsData() {
    int userId = 1; // Replace with SessionManager.getInstance().getUserId()

    // SQL query to get debt data for the last 6 months
    String query = "SELECT d.date_issued, d.amount, u1.username AS debtor_username, u2.username AS creditor_username " +
                   "FROM debts d " +
                   "JOIN users u1 ON d.debtor_id = u1.user_id " +
                   "JOIN users u2 ON d.creditor_id = u2.user_id " +
                   "WHERE d.debtor_id = ? OR d.creditor_id = ? " +
                   "AND d.date_issued >= CURDATE() - INTERVAL 6 MONTH " +  // Last 6 months
                   "ORDER BY d.date_issued ASC";  // Order by date

    List<ModelChart> chartData = new ArrayList<>();
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, userId); // Set user ID as parameter
        stmt.setInt(2, userId); // Set user ID as parameter for both debtor and creditor
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                java.sql.Date dateIssued = rs.getDate("date_issued");
                double amount = rs.getDouble("amount");
                String label = new java.text.SimpleDateFormat("MMM yyyy").format(dateIssued); // Format as "Month Year"

                // Add the debt data for this specific record
                ModelChart chartModel = new ModelChart(label, new double[]{amount});
                chartData.add(chartModel);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Add the debt data to the chart
    addDebtDataToChart(chartData);
}

    private void addDebtDataToChart(List<ModelChart> chartData) {
    // Clear any previous data
    DebtsLineChart.clear();

    // Add legend for debts
    DebtsLineChart.addLegend("Debts", new Color(12, 84, 175), new Color(0, 108, 247));
    
    // Add the fetched data to the chart
    for (ModelChart data : chartData) {
        DebtsLineChart.addData(data);
    }

    // Start the animation to display the data on the chart
    DebtsLineChart.start();
}

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
    private javax.swing.JButton DebtsChartAddDebtbutton;
    private javax.swing.JButton DebtsChartRemoveExpenseButton;
    private chart.LineChart DebtsLineChart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
