package form;

import db.DatabaseConnection;
import db.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.SessionManager;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;

public class BalancePane extends javax.swing.JPanel {

    public BalancePane() {
        initComponents();
        setOpaque(false);
        refreshIncomeTable();  // Refresh the income table when the panel is created
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DebtsPaneTable = new javax.swing.JTable();
        BalancePaneAddBalanceButton = new javax.swing.JButton();
        BalancePaneRemoveBalanceButton = new javax.swing.JButton();

        DebtsPaneTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Income ID", "User ID", "Source", "Date", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        DebtsPaneTable.setPreferredSize(new java.awt.Dimension(525, 625));
        jScrollPane1.setViewportView(DebtsPaneTable);

        BalancePaneAddBalanceButton.setBackground(new java.awt.Color(204, 204, 204));
        BalancePaneAddBalanceButton.setText("Deposit Balance");
        BalancePaneAddBalanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalancePaneAddBalanceButtonActionPerformed(evt);
            }
        });

        BalancePaneRemoveBalanceButton.setBackground(new java.awt.Color(204, 204, 204));
        BalancePaneRemoveBalanceButton.setText("Remove Balance");
        BalancePaneRemoveBalanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BalancePaneRemoveBalanceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BalancePaneAddBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(910, 910, 910)
                        .addComponent(BalancePaneRemoveBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(BalancePaneAddBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BalancePaneRemoveBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
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

    // Function to refresh income table data
    public void refreshIncomeTable() {
    try {
        // Get the database connection from DatabaseConnection
        Connection connection = DatabaseConnection.getConnection();

        // Query to get incomes for a specific user along with username
        int userId = 1; //SessionManager.getInstance().getUserId(); // Replace with actual user ID from SessionManager
        String query = "SELECT i.income_id, i.source, i.date, i.amount, u.username " +
                       "FROM incomes i " +
                       "JOIN users u ON i.user_id = u.user_id " +
                       "WHERE i.user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        
        // Execute the query
        ResultSet rs = statement.executeQuery();
        
        // Get the table model and clear existing rows
        DefaultTableModel model = (DefaultTableModel) DebtsPaneTable.getModel();
        model.setRowCount(0);

        // Loop through the ResultSet and add rows to the table
        while (rs.next()) {
            int incomeId = rs.getInt("income_id");
            String source = rs.getString("source");
            Date date = rs.getDate("date");
            double amount = rs.getDouble("amount");
            String username = rs.getString("username");  // Retrieve the username from the query
            model.addRow(new Object[] { incomeId, username, source, date, amount });
        }

        // Close the resources
        rs.close();
        statement.close();
        
    } catch (SQLException e) {
        e.printStackTrace();  // Log or handle error appropriately
    }
}
    
    private void BalancePaneAddBalanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalancePaneAddBalanceButtonActionPerformed
        //TODO add your handling code here:

        depositBalance depositBalanceWindow = new depositBalance(this);
        depositBalanceWindow.setVisible(true);
        depositBalanceWindow.pack();
        depositBalanceWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        depositBalanceWindow.setLocationRelativeTo(null);
        
       
    }//GEN-LAST:event_BalancePaneAddBalanceButtonActionPerformed

    private void BalancePaneRemoveBalanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BalancePaneRemoveBalanceButtonActionPerformed
        // TODO add your handling code here:
        RemoveWindow removeWindow = new RemoveWindow(this); // Pass the reference to ExpensesPane
        removeWindow.setVisible(true);
        removeWindow.pack();
        removeWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        removeWindow.setLocationRelativeTo(null);
    }//GEN-LAST:event_BalancePaneRemoveBalanceButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BalancePaneAddBalanceButton;
    private javax.swing.JButton BalancePaneRemoveBalanceButton;
    private javax.swing.JTable DebtsPaneTable;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
