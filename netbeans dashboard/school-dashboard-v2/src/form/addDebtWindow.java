
package form;

import db.DatabaseConnection;
import db.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.SessionManager;

/**
 *
 * @author user
 */
public class addDebtWindow extends javax.swing.JFrame {

    /**
     * Creates new form addDebtWindow
     */
    public addDebtWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        amountTextField = new javax.swing.JTextArea();
        dueDateTextField = new javax.swing.JTextArea();
        creditorUsernameTextField = new javax.swing.JTextArea();
        addButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Debt");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(221, 224, 228));
        jPanel2.setPreferredSize(new java.awt.Dimension(410, 290));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Amount");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Debtor");

        amountTextField.setColumns(20);
        amountTextField.setRows(5);
        amountTextField.setPreferredSize(new java.awt.Dimension(234, 86));

        dueDateTextField.setColumns(20);
        dueDateTextField.setRows(5);
        dueDateTextField.setPreferredSize(new java.awt.Dimension(232, 30));

        creditorUsernameTextField.setColumns(20);
        creditorUsernameTextField.setRows(5);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Due Date");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(creditorUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(addButton))
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addButton)
                    .addComponent(creditorUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        // Get the amount owed from the user input
    String amountText = amountTextField.getText().trim();
    if (amountText.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Amount owed is required.", 
            "Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    double amount;
    try {
        amount = Double.parseDouble(amountText);
    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Please enter a valid amount.", 
            "Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Get the creditor's username
    String creditorUsername = creditorUsernameTextField.getText().trim();
    if (creditorUsername.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Creditor username is required.", 
            "Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Check if creditor exists
    int creditorId = 0;
    try {
        creditorId = UserDAO.getCreditorIdFromUsername(creditorUsername);  // Check if creditor exists
    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Creditor not found: " + e.getMessage(),
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Get the due date
    String dueDateText = dueDateTextField.getText().trim();
    java.sql.Date dateDue = null;
    
    if (!dueDateText.isEmpty()) {
        try {
            // If user input is provided, parse the date
            dateDue = java.sql.Date.valueOf(dueDateText);
        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Invalid date format. Please use yyyy-mm-dd.", 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
    } else {
        // If no due date is provided, set it to 3 days after the current date
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.DATE, 3);  // Add 3 days to the current date
        dateDue = new java.sql.Date(calendar.getTimeInMillis());  // Set the date
    }

    // Get the debtor's ID from the session
    int debtorId = 1; //SessionManager.getInstance().getUserId(); hardcode for testing

    // SQL query to insert debt
    String insertDebtQuery = "INSERT INTO debts (debtor_id, creditor_id, amount, date_issued, date_due, is_paid) "
                            + "VALUES (?, ?, ?, CURDATE(), ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(insertDebtQuery)) {

        stmt.setInt(1, debtorId);   // Set debtor_id
        stmt.setInt(2, creditorId); // Set creditor_id
        stmt.setDouble(3, amount);  // Set amount owed
        stmt.setDate(4, dateDue);   // Set due date (either provided or calculated)
        stmt.setBoolean(5, false);  // Set is_paid to false by default

        int rowsAffected = stmt.executeUpdate();  // Execute insert

        if (rowsAffected > 0) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Debt record added successfully!",
                "Success",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Failed to add debt record.",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Error while saving debt record: " + e.getMessage(),
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addButtonActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(addDebtWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(addDebtWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(addDebtWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(addDebtWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new addDebtWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextArea amountTextField;
    private javax.swing.JTextArea creditorUsernameTextField;
    private javax.swing.JTextArea dueDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
