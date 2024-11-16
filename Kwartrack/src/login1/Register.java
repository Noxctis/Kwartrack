/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package login1;

import db.UserDAO;

/**
 *
 * @author user
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    public Register() {
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

        jLabel6 = new javax.swing.JLabel();
        username1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        username3 = new javax.swing.JTextField();
        out1 = new javax.swing.JTextField();
        textArea1 = new java.awt.TextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        registerPasswordLabel = new javax.swing.JLabel();
        registerUserField = new javax.swing.JTextField();
        registerUserLabel = new javax.swing.JLabel();
        registerPasswordField = new javax.swing.JPasswordField();
        registerLoginButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        registerFirstLabel = new javax.swing.JLabel();
        registerFirstField = new javax.swing.JTextField();
        registerLastLabel = new javax.swing.JLabel();
        registerLastField = new javax.swing.JTextField();
        registerRegisterButton = new javax.swing.JButton();
        registerErrorMessage = new javax.swing.JLabel();
        registerEmailField = new javax.swing.JTextField();
        registerEmailLabel = new javax.swing.JLabel();

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Username");

        username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username1ActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Username");

        username3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username3ActionPerformed(evt);
            }
        });

        out1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                out1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTER");
        setPreferredSize(new java.awt.Dimension(516, 525));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 500));

        registerPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerPasswordLabel.setText("Password");

        registerUserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerUserFieldActionPerformed(evt);
            }
        });

        registerUserLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerUserLabel.setText("Username");

        registerLoginButton.setText("LOG IN");
        registerLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerLoginButtonActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("I already have an account");

        registerFirstLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerFirstLabel.setText("First Name");

        registerFirstField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerFirstFieldActionPerformed(evt);
            }
        });

        registerLastLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerLastLabel.setText("Last Name");

        registerLastField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerLastFieldActionPerformed(evt);
            }
        });

        registerRegisterButton.setText("REGISTER");
        registerRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerRegisterButtonActionPerformed(evt);
            }
        });

        registerErrorMessage.setBackground(new java.awt.Color(0, 0, 0));
        registerErrorMessage.setForeground(new java.awt.Color(255, 51, 51));
        registerErrorMessage.setText("     ");

        registerEmailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerEmailFieldActionPerformed(evt);
            }
        });

        registerEmailLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerEmailLabel.setText("Email");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerLastField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerFirstField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerFirstLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerLastLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(registerLoginButton))
                            .addComponent(registerPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(registerRegisterButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(registerErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(registerErrorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerFirstLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerFirstField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(registerLastLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerLastField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerUserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerEmailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registerRegisterButton)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(registerLoginButton))
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 500, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 74, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerUserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerUserFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_registerUserFieldActionPerformed

    private void registerLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerLoginButtonActionPerformed
        // TODO add your handling code here:
        Logn loginFrame = new Logn();
        loginFrame.setVisible(true);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_registerLoginButtonActionPerformed

    private void username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username1ActionPerformed

    private void registerFirstFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerFirstFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerFirstFieldActionPerformed

    private void username3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username3ActionPerformed

    private void registerLastFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerLastFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerLastFieldActionPerformed

    private void registerRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerRegisterButtonActionPerformed
        // TODO add your handling code here:
        // Step 1: Get input values
        String firstName = registerFirstField.getText().trim();
        String lastName = registerLastField.getText().trim();
        String regUsername = registerUserField.getText().trim();
        String regEmail = registerEmailField.getText().trim();
        char[] regPasswordArray = registerPasswordField.getPassword();
        String regPassword = new String(regPasswordArray).trim();

        // Step 2: Validate inputs
        if (firstName.isEmpty() || lastName.isEmpty() || regUsername.isEmpty() || regEmail.isEmpty() || regPassword.isEmpty()) {
            registerErrorMessage.setText("All fields are required.");
            return;
        }

        if (!regEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            registerErrorMessage.setText("Invalid email format.");
            return;
        }

        if (regPassword.length() < 8 || !regPassword.matches(".*[A-Z].*") || !regPassword.matches(".*[0-9].*")) {
            registerErrorMessage.setText("Password must be at least 8 characters long, contain a number, and an uppercase letter.");
            return;
        }

        // Step 3: Register the user
        UserDAO userDAO = new UserDAO();
        boolean registrationSuccess = userDAO.registerUser(regUsername, regEmail, regPassword, firstName, lastName);

        // Step 4: Feedback
        if (registrationSuccess) {
            registerErrorMessage.setText("Registration successful!");
            Logn loginFrame = new Logn();
            loginFrame.setVisible(true);
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            this.dispose();
        } else {
            registerErrorMessage.setText("Registration failed. Username or email may already exist.");
        }
    }//GEN-LAST:event_registerRegisterButtonActionPerformed

    private void out1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_out1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_out1ActionPerformed

    private void registerEmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerEmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerEmailFieldActionPerformed

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField out1;
    private javax.swing.JTextField registerEmailField;
    private javax.swing.JLabel registerEmailLabel;
    private javax.swing.JLabel registerErrorMessage;
    private javax.swing.JTextField registerFirstField;
    private javax.swing.JLabel registerFirstLabel;
    private javax.swing.JTextField registerLastField;
    private javax.swing.JLabel registerLastLabel;
    private javax.swing.JButton registerLoginButton;
    private javax.swing.JPasswordField registerPasswordField;
    private javax.swing.JLabel registerPasswordLabel;
    private javax.swing.JButton registerRegisterButton;
    private javax.swing.JTextField registerUserField;
    private javax.swing.JLabel registerUserLabel;
    private java.awt.TextArea textArea1;
    private javax.swing.JTextField username1;
    private javax.swing.JTextField username3;
    // End of variables declaration//GEN-END:variables
}
