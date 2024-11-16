/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login.src.login1;

/**
 *
 * @author user
 */
public class Logn extends javax.swing.JFrame {

    /**
     * Creates new form Logn
     */
    public Logn() {
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
        blackBG = new javax.swing.JPanel();
        kwartrack = new javax.swing.JLabel();
        Finance = new javax.swing.JLabel();
        loginPasswordLabel = new javax.swing.JLabel();
        loginUsernameField = new javax.swing.JTextField();
        loginUsernameLabel = new javax.swing.JLabel();
        loginPasswordField = new javax.swing.JPasswordField();
        loginRegisterButton = new javax.swing.JButton();
        loginLoginButton = new javax.swing.JButton();
        KwartrackLogo = new javax.swing.JLabel();
        loginErrorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setPreferredSize(new java.awt.Dimension(516, 535));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.setLayout(null);

        blackBG.setBackground(new java.awt.Color(0, 0, 0));

        kwartrack.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        kwartrack.setForeground(new java.awt.Color(255, 255, 255));
        kwartrack.setText("KWARTRACK");

        Finance.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        Finance.setForeground(new java.awt.Color(255, 255, 255));
        Finance.setText("FINANCE");

        loginPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginPasswordLabel.setText("Password");

        loginUsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUsernameFieldActionPerformed(evt);
            }
        });

        loginUsernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginUsernameLabel.setText("Username");

        loginRegisterButton.setText("REGISTER");
        loginRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginRegisterButtonActionPerformed(evt);
            }
        });

        loginLoginButton.setText("LOG IN");
        loginLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginLoginButtonActionPerformed(evt);
            }
        });

        KwartrackLogo.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\Kwartrack UI\\Kwartrack Logo (smaller).png")); // NOI18N
        KwartrackLogo.setText("jLabel5");

        loginErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        loginErrorMessage.setText(" ");
        loginErrorMessage.setToolTipText("");

        javax.swing.GroupLayout blackBGLayout = new javax.swing.GroupLayout(blackBG);
        blackBG.setLayout(blackBGLayout);
        blackBGLayout.setHorizontalGroup(
            blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blackBGLayout.createSequentialGroup()
                .addGroup(blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(blackBGLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(KwartrackLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(blackBGLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(loginLoginButton)
                        .addGap(44, 44, 44)
                        .addComponent(loginRegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(blackBGLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(Finance, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kwartrack)
                            .addComponent(loginErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(blackBGLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(loginUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        blackBGLayout.setVerticalGroup(
            blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blackBGLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(KwartrackLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kwartrack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Finance)
                .addGap(105, 105, 105)
                .addComponent(loginErrorMessage)
                .addGap(26, 26, 26)
                .addGroup(blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLoginButton)
                    .addComponent(loginRegisterButton))
                .addGap(36, 36, 36))
            .addGroup(blackBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(blackBGLayout.createSequentialGroup()
                    .addContainerGap(290, Short.MAX_VALUE)
                    .addComponent(loginUsernameLabel)
                    .addGap(4, 4, 4)
                    .addComponent(loginUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(loginPasswordLabel)
                    .addGap(4, 4, 4)
                    .addComponent(loginPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 108, Short.MAX_VALUE)))
        );

        jPanel1.add(blackBG);
        blackBG.setBounds(0, 0, 500, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginUsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUsernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginUsernameFieldActionPerformed

    private void loginRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginRegisterButtonActionPerformed
        // TODO add your handling code here:
        Register RegisterFrame = new Register();
        RegisterFrame.setVisible(true);
        RegisterFrame.pack();
        RegisterFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_loginRegisterButtonActionPerformed

    private void loginLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginLoginButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginLoginButtonActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Finance;
    private javax.swing.JLabel KwartrackLogo;
    private javax.swing.JPanel blackBG;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel kwartrack;
    private javax.swing.JLabel loginErrorMessage;
    private javax.swing.JButton loginLoginButton;
    private javax.swing.JPasswordField loginPasswordField;
    private javax.swing.JLabel loginPasswordLabel;
    private javax.swing.JButton loginRegisterButton;
    private javax.swing.JTextField loginUsernameField;
    private javax.swing.JLabel loginUsernameLabel;
    // End of variables declaration//GEN-END:variables
}
