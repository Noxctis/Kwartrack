package form;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import db.SessionManager;
import java.util.Vector;
import javax.swing.JOptionPane;
import chart.ModelChart;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class BalanceChartPane extends javax.swing.JPanel {

    public BalanceChartPane() {
        initComponents();
        setOpaque(false);
        loadIncomeData();
        //loadExpenses();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BalanceLineChart = new chart.LineChart();
        BalanceChartRemoveBalanceButton = new javax.swing.JButton();
        BalanceChartAddBalanceButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1100, 700));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel2.setBackground(new java.awt.Color(221, 224, 228));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Your Income Trend");

        BalanceLineChart.setToolTipText("");
        BalanceLineChart.setFocusTraversalPolicyProvider(true);
        BalanceLineChart.setFocusable(false);
        BalanceLineChart.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N

        BalanceChartRemoveBalanceButton.setBackground(new java.awt.Color(204, 204, 204));
        BalanceChartRemoveBalanceButton.setText("Remove Balance");
        BalanceChartRemoveBalanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalanceChartRemoveBalanceButtonActionPerformed(evt);
            }
        });

        BalanceChartAddBalanceButton.setBackground(new java.awt.Color(204, 204, 204));
        BalanceChartAddBalanceButton.setText("Deposit Balance");
        BalanceChartAddBalanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalanceChartAddBalanceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(BalanceLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BalanceChartAddBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BalanceChartRemoveBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BalanceLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BalanceChartAddBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BalanceChartRemoveBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
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

    private void BalanceChartRemoveBalanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalanceChartRemoveBalanceButtonActionPerformed
        // TODO add your handling code here:
        RemoveWindow removeWindow = new RemoveWindow(); // Pass the reference to ExpensesPane
        removeWindow.setVisible(true);
        removeWindow.pack();
        removeWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        removeWindow.setLocationRelativeTo(null);
    }//GEN-LAST:event_BalanceChartRemoveBalanceButtonActionPerformed

    private void BalanceChartAddBalanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalanceChartAddBalanceButtonActionPerformed
        //TODO add your handling code here:

        depositBalance depositBalanceWindow = new depositBalance(); // rmoved this
        depositBalanceWindow.setVisible(true);
        depositBalanceWindow.pack();
        depositBalanceWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        depositBalanceWindow.setLocationRelativeTo(null);

    }//GEN-LAST:event_BalanceChartAddBalanceButtonActionPerformed
    
    private void loadIncomeData() {
    int userId = SessionManager.getInstance().getUserId(); // Get user ID from the session

    // SQL query to get individual income records for the last 6 months from the incomes table
    String query = "SELECT date, amount " +
                   "FROM incomes " +
                   "WHERE user_id = ? " +
                   "AND date >= CURDATE() - INTERVAL 6 MONTH " +
                   "ORDER BY date ASC"; // Order by date ascending

    List<ModelChart> chartData = new ArrayList<>();
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, userId); // Set user ID in query
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                java.sql.Date date = rs.getDate("date");
                double amount = rs.getDouble("amount");

                // Convert the date to month-year format
                String label = new java.text.SimpleDateFormat("MMM yyyy").format(date);

                // Add the income data for this specific record
                ModelChart chartModel = new ModelChart(label, new double[]{amount});
                chartData.add(chartModel);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Add the income data to the chart
    addIncomeDataToChart(chartData);
}



// Method to add individual income data to the chart
private void addIncomeDataToChart(List<ModelChart> chartData) {
    // Clear previous data
    BalanceLineChart.clear();

    // Add legend for income
    BalanceLineChart.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));

    // Add each income data as a separate point
    for (ModelChart data : chartData) {
        BalanceLineChart.addData(data);
    }

    // Start the animation (chart display)
    BalanceLineChart.start();
}


// Convert month number to month name
private String getMonthName(int month) {
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    return months[month - 1];
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
    private javax.swing.JButton BalanceChartAddBalanceButton;
    private javax.swing.JButton BalanceChartRemoveBalanceButton;
    private chart.LineChart BalanceLineChart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
