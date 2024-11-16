package component;

import swing.CapsuleGradientButton;
import model.ModelLogin;
import model.ModelUser;
import swing.MyPasswordField;
import swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public ModelLogin getDataLogin() {
        return dataLogin;
    }

    public ModelUser getUser() {
        return user;
    }

    private ModelUser user;
    private ModelLogin dataLogin;

    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
        login.setVisible(false);
        register.setVisible(true);
    }

private void initRegister(ActionListener eventRegister) {
    register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]25[]push"));

    // Title Label
    JLabel label = new JLabel("Create Account");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(7, 164, 121));
    register.add(label);

    // First Name Field
    MyTextField txtFirstName = new MyTextField();
    txtFirstName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
    txtFirstName.setHint("First Name");
    register.add(txtFirstName, "w 60%");

    // Last Name Field
    MyTextField txtLastName = new MyTextField();
    txtLastName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
    txtLastName.setHint("Last Name");
    register.add(txtLastName, "w 60%");

    // Email Field
    MyTextField txtEmail = new MyTextField();
    txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/mail.png")));
    txtEmail.setHint("Email");
    register.add(txtEmail, "w 60%");

    // Username Field
    MyTextField txtUsername = new MyTextField();
    txtUsername.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
    txtUsername.setHint("Username");
    register.add(txtUsername, "w 60%");

    // Password Field
    MyPasswordField txtPassword = new MyPasswordField();
    txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
    txtPassword.setHint("Password");
    register.add(txtPassword, "w 60%");

    // Sign Up Button
    CapsuleGradientButton cmd = new CapsuleGradientButton("SIGN UP", new Color(7, 164, 121), new Color(0, 128, 255));
    cmd.setForeground(new Color(250, 250, 250));
    register.add(cmd, "w 40%, h 40");
    cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
    // Event Listener for the "SIGN UP" Button
    cmd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            String email = txtEmail.getText().trim();
            String username = txtUsername.getText().trim();
            String password = String.valueOf(txtPassword.getPassword());

            // Input validation
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(register, "All fields are required!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                javax.swing.JOptionPane.showMessageDialog(register, "Invalid email address!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
                javax.swing.JOptionPane.showMessageDialog(register, "Password must be at least 8 characters long, contain an uppercase letter, and a number.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Register user in the database
            UserDAO userDAO = new UserDAO();
            User newUser = userDAO.registerUser(username, email, password, firstName, lastName);

            if (newUser != null) {
                javax.swing.JOptionPane.showMessageDialog(register, "Account created successfully! User ID: " + newUser.getUserID(), "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(register, "Registration failed. Please try again.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    });
}


    private void initLogin(ActionListener eventLogin) {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        CapsuleGradientButton cmd = new CapsuleGradientButton("SIGN UP", new Color(7, 164, 121), new Color(0, 128, 255));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventLogin);
        cmd.setText("SIGN IN");
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                dataLogin = new ModelLogin(email, password);
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
