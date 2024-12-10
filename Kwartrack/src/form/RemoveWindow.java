/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;
import db.DatabaseConnection;
import db.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.SessionManager;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */
public class RemoveWindow extends javax.swing.JFrame {

    private DebtsPane debtsPane;  // Reference to ExpensesPane for refreshing the table
    private BalancePane balancePane;  // Reference to ExpensesPane for refreshing the table
    private ExpensesPane expensesPane;  // Reference to ExpensesPane for refreshing the table
    
    public RemoveWindow(DebtsPane debtsPane){
        this.debtsPane = debtsPane;  // Store the reference
        initComponents();
    }
    
    public RemoveWindow(BalancePane balancePane){
        this.balancePane = balancePane;   // Store the reference
        initComponents();
    }
    
    public RemoveWindow(ExpensesPane expensesPane){
        this.expensesPane = expensesPane;  // Store the reference
        initComponents();
    }
    /**
     * Creates new form RemoveWindow
     */
    public RemoveWindow() {
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
        RemoveButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        RemoveIdField = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(410, 290));

        jPanel2.setBackground(new java.awt.Color(221, 224, 228));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Source");

        RemoveButton.setText("Remove");
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("ID");

        RemoveIdField.setColumns(20);
        RemoveIdField.setRows(5);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debts", "Expenses", "Balance"}));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(RemoveButton))
                            .addComponent(RemoveIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(67, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoveButton)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoveIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
        // Get the selected type from JComboBox
    String selectedType = (String) jComboBox1.getSelectedItem();
    // Get the Remove ID (Expense ID, Income ID, or Debt ID)
    String removeId = RemoveIdField.getText().trim();

    if (removeId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a valid ID to remove.");
        return; // Exit method if no ID is entered
    }

    // Convert the ID to an integer (assuming the ID is a valid number)
    try {
        int id = Integer.parseInt(removeId);

        // Get the current user's ID (this should come from session or login context)
        int currentUserId = SessionManager.getInstance().getUserId(); // Assuming you have a method to get the logged-in user ID

        // Create a connection to the database
        try (Connection connection = DatabaseConnection.getConnection()) { // Use try-with-resources for automatic closing
            String query = "";
            switch (selectedType) {
                case "Debts":
                    // SQL query to remove a debt only if the current user is the debtor
                    query = "DELETE FROM debts WHERE debt_id = ? AND debtor_id = ?";
                    break;
                case "Expenses":
                    // SQL query to remove an expense only if the current user is the owner
                    query = "DELETE FROM expenses WHERE expense_id = ? AND user_id = ?";
                    break;
                case "Balance":
                    // SQL query to remove an income only if the current user is the owner
                    query = "DELETE FROM incomes WHERE income_id = ? AND user_id = ?";
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid selection.");
                    return;
            }

            // Prepare and execute the query
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id); // Set the ID to be deleted
                statement.setInt(2, currentUserId); // Ensure the current user is the owner of the record
                int rowsAffected = statement.executeUpdate();

                // Check if the delete was successful
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully removed " + selectedType.toLowerCase() + " with ID " + id);
                    if (expensesPane != null) {
                        expensesPane.loadExpenses();
                    }
                    if (balancePane != null) {
                    balancePane.refreshIncomeTable(); // Refresh table data to show the newly added income
                    }
                     if (debtsPane != null) {
                        debtsPane.loadDebtData();
                    }
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "You do not have permission to remove this " + selectedType.toLowerCase() + " or the record does not exist.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error while deleting: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid numerical ID.");
    }
    }//GEN-LAST:event_RemoveButtonActionPerformed

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
//            java.util.logging.Logger.getLogger(RemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RemoveWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RemoveWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RemoveButton;
    private javax.swing.JTextArea RemoveIdField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
