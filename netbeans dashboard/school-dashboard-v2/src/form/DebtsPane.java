package form;


import db.SessionManager;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;


public class DebtsPane extends javax.swing.JPanel {

    public DebtsPane() {
        initComponents();
        setOpaque(false);
        
        // Call the method to load debt data when the panel is created
        loadDebtData();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        debtsPaneAddDebtbutton = new javax.swing.JButton();
        expensePaneRemoveExpenseButton = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Debt ID", "Debtor ID", "Creditor ID", "Date Issued", "Date Due", "Amount", "Paid"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(525, 625));
        jScrollPane1.setViewportView(jTable1);

        debtsPaneAddDebtbutton.setBackground(new java.awt.Color(204, 204, 204));
        debtsPaneAddDebtbutton.setText("Add Debt");
        debtsPaneAddDebtbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debtsPaneAddDebtbuttonActionPerformed(evt);
            }
        });

        expensePaneRemoveExpenseButton.setBackground(new java.awt.Color(204, 204, 204));
        expensePaneRemoveExpenseButton.setText("Remove Debt");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(debtsPaneAddDebtbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensePaneRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(debtsPaneAddDebtbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expensePaneRemoveExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
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
private void loadDebtData() {
    // Get the logged-in user's ID from SessionManager
    int userId = 1; //SessionManager.getInstance().getUserId(); //hardcode for testing
    
    // SQL query to fetch debts where either debtor_id or creditor_id is the logged-in user's ID
    // Joining the `debts` table with the `users` table to get usernames
    String query = "SELECT debt_id, debts.debtor_id, debts.creditor_id, date_issued, date_due, amount, is_paid, " +
                   "debtor.username AS debtor_username, creditor.username AS creditor_username " +
                   "FROM debts " +
                   "JOIN users AS debtor ON debts.debtor_id = debtor.user_id " +
                   "JOIN users AS creditor ON debts.creditor_id = creditor.user_id " +
                   "WHERE debts.debtor_id = ? OR debts.creditor_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the user ID as both the debtor_id and creditor_id in the query
        stmt.setInt(1, userId);
        stmt.setInt(2, userId);
        
        // Execute the query and get the result set
        try (ResultSet rs = stmt.executeQuery()) {
            // Get the table model
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            
            // Clear any existing rows in the table
            model.setRowCount(0);

            // Process the result set and populate the table
            while (rs.next()) {
                // Fetch the data for each debt record
                int debtId = rs.getInt("debt_id");
                int debtorId = rs.getInt("debtor_id");
                int creditorId = rs.getInt("creditor_id");
                java.sql.Date dateIssued = rs.getDate("date_issued");
                java.sql.Date dateDue = rs.getDate("date_due");
                double amount = rs.getDouble("amount");
                boolean isPaid = rs.getBoolean("is_paid");
                
                // Get the debtor and creditor usernames
                String debtorUsername = rs.getString("debtor_username");
                String creditorUsername = rs.getString("creditor_username");

                // Create an array with the data for each row
                Object[] row = {
                    debtId, 
                    debtorUsername,  // Use debtor's username instead of debtor_id
                    creditorUsername, // Use creditor's username instead of creditor_id
                    dateIssued, 
                    dateDue, 
                    amount, 
                    isPaid
                };
                
                // Add the row to the table model
                model.addRow(row);
            }

        }
        
    } catch (SQLException e) {
        // Show error message if there is an issue with the database
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading debt data: " + e.getMessage(), 
                                                  "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}



    private void debtsPaneAddDebtbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debtsPaneAddDebtbuttonActionPerformed
        // TODO add your handling code here:
        addDebtWindow addDebt = new addDebtWindow();
        addDebt.setVisible(true);
        addDebt.pack();
        addDebt.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addDebt.setLocationRelativeTo(null);
    }//GEN-LAST:event_debtsPaneAddDebtbuttonActionPerformed

    private void expensePaneRemoveExpenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expensePaneRemoveExpenseButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expensePaneRemoveExpenseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton debtsPaneAddDebtbutton;
    private javax.swing.JButton expensePaneRemoveExpenseButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
