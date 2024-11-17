package component;

import db.User;
import db.UserDAO;
import model.ModelLogin;
import swing.Button;
import swing.MyPasswordField;
import swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public User getUser() {
        return user;
    }

    private User user;

    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
        login.setVisible(false);
        register.setVisible(true);
    }

    private Runnable onSwitchToLogin;

    public void setOnSwitchToLogin(Runnable onSwitchToLogin) {
        this.onSwitchToLogin = onSwitchToLogin;
    }

    public void switchToLogin() {
        // Simulate the transition to the login panel
        if (onSwitchToLogin != null) {
            onSwitchToLogin.run();
        }
    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    private void initRegister(ActionListener eventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]25[]push"));

        // Title Label
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", Font.BOLD, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);

        // First Name Field
        MyTextField txtFirstName = new MyTextField();
        txtFirstName.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtFirstName.setHint("First Name");
        txtFirstName.setToolTipText("Enter your first name");
        register.add(txtFirstName, "w 60%");

        // Last Name Field
        MyTextField txtLastName = new MyTextField();
        txtLastName.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtLastName.setHint("Last Name");
        txtLastName.setToolTipText("Enter your last name");
        register.add(txtLastName, "w 60%");

        // Email Field
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        txtEmail.setToolTipText("Enter a valid email address (e.g., user@example.com)");
        register.add(txtEmail, "w 60%");

        // Username Field
        MyTextField txtUsername = new MyTextField();
        txtUsername.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUsername.setHint("Username");
        txtUsername.setToolTipText("Choose a unique username (minimum 4 characters)");
        register.add(txtUsername, "w 60%");

        // Password Field
        MyPasswordField txtPassword = new MyPasswordField();
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPassword.setHint("Password");
        txtPassword.setToolTipText("Password must be at least 8 characters, include an uppercase letter, and a number");
        register.add(txtPassword, "w 60%");

        // Confirm Password Field
        MyPasswordField txtConfirmPassword = new MyPasswordField();
        txtConfirmPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtConfirmPassword.setHint("Confirm Password");
        txtConfirmPassword.setToolTipText("Re-enter your password to confirm");
        register.add(txtConfirmPassword, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventRegister);
        cmd.setText("SIGN UP");
        cmd.setFont(new Font("sansserif", Font.BOLD, 12));
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String firstName = txtFirstName.getText().trim();
                String lastName = txtLastName.getText().trim();
                String email = txtEmail.getText().trim();
                String username = txtUsername.getText().trim();
                char[] password = txtPassword.getPassword();
                char[] confirmPassword = txtConfirmPassword.getPassword();
                // Input Validation
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty()
                        || password == null || password.length == 0 || confirmPassword == null
                        || confirmPassword.length == 0) {
                    javax.swing.JOptionPane.showMessageDialog(register, "All fields are required!", "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    javax.swing.JOptionPane.showMessageDialog(register, 
                        "Please enter a valid email address (e.g., user@example.com).", 
                        "Invalid Email", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!Arrays.equals(password, confirmPassword)) {
                    javax.swing.JOptionPane.showMessageDialog(register, "Passwords do not match!", "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Register user
                UserDAO userDAO = new UserDAO();
                user = userDAO.registerUser(username, email, password, firstName, lastName);

                if (user != null) {
                    javax.swing.JOptionPane.showMessageDialog(register,
                            "Account created successfully! Welcome, " + user.getFirstName() + "!", "Success",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    // showRegister(false);
                    // Trigger the transition to the login panel
                    switchToLogin();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(register, "Registration failed. Please try again.",
                            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                // Clear the password array for security
                java.util.Arrays.fill(password, '\0');
                // Clear the password array for security
                java.util.Arrays.fill(confirmPassword, '\0');
            }
        });
    }

    private void initLogin(ActionListener eventLogin) {
        // Set layout
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        // Title Label
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", Font.BOLD, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);

        // Username Field
        MyTextField txtUsername = new MyTextField();
        txtUsername.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUsername.setHint("Username");
        txtUsername.setToolTipText("Enter your username");
        login.add(txtUsername, "w 60%");

        // Password Field
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        txtPass.setToolTipText("Enter your password");
        login.add(txtPass, "w 60%");

        // Forgot Password Button
        JButton cmdForget = new JButton("Forgot your password?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", Font.PLAIN, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdForget.setToolTipText("Click here if you forgot your password");
        cmdForget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                javax.swing.JOptionPane.showMessageDialog(login,
                        "Password recovery link sent to your registered email!", "Forgot Password",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        login.add(cmdForget);

        // Sign In Button
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        cmd.setToolTipText("Click to sign in to your account");
        login.add(cmd, "w 40%, h 40");

        // Event Listener for Sign In Button
        cmd.addActionListener(eventLogin);
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = txtUsername.getText().trim();
                char[] passwordArray = txtPass.getPassword();

                // Input Validation
                if (username.isEmpty() || passwordArray == null || passwordArray.length == 0) {
                    javax.swing.JOptionPane.showMessageDialog(login,
                            "Both fields are required!",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (username.length() < 4) {
                    javax.swing.JOptionPane.showMessageDialog(login,
                            "Username must be at least 4 characters long!",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check Login Credentials Using UserDAO
                UserDAO userDAO = new UserDAO();
                String feedback = userDAO.loginUserWithFeedback(username, passwordArray);

                switch (feedback) {
                    case "Login successful":
                        javax.swing.JOptionPane.showMessageDialog(login,
                                feedback,
                                "Success",
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);

                        // Save login data for future use
                        // dataLogin = new ModelLogin(username, password);

                        // Launch the Dashboard
                        // launchDashboard(username);
                        break;

                    case "Incorrect password":
                    case "Username does not exist":
                        javax.swing.JOptionPane.showMessageDialog(login,
                                feedback,
                                "Error",
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                        break;

                    default:
                        javax.swing.JOptionPane.showMessageDialog(login,
                                "An unexpected error occurred. Please try again.",
                                "Error",
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                }

                // Clear the password array for security
                java.util.Arrays.fill(passwordArray, '\0');
            }
        });
    }

    private void launchDashboard(String username) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Dispose the current login window
            javax.swing.SwingUtilities.getWindowAncestor(login).dispose();

            // Launch the dashboard
            //Dashboard dashboard = new Dashboard(username); // Assuming you have a Dashboard class
            //dashboard.setVisible(true);
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE));
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE));
        registerLayout.setVerticalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
