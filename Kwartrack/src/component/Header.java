package component;

import swing.PanelTransparent;
import java.awt.event.ActionListener;
import db.SessionManager;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import db.DatabaseConnection;
import db.UserDAO;
import main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.SessionManager;


public class Header extends PanelTransparent {

    public Header() {
        initComponents();
        setTransparent(0.5f);
        displayUserName();  // Set the username when the Header is initialized
    }
    
    private void displayUserName() {
        try {
            // Get the logged-in user's ID from the SessionManager
            int userId = SessionManager.getInstance().getUserId();
            
            // Fetch the username from the database using the userId
            String query = "SELECT username FROM users WHERE user_id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Set the username in the lbUserName label
                        String username = rs.getString("username");
                        lbUserName.setText(username);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log or handle error appropriately
        }
    }

    public void addMenuEvent(ActionListener event) {
        cmdMenu.addActionListener(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMenu = new swing.Button2();
        pic = new swing.ImageAvatar();
        lbUserName = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        mailBadge = new swing.ButtonBadges();

        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        cmdMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMenuActionPerformed(evt);
            }
        });

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile.png"))); // NOI18N

        lbUserName.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(127, 127, 127));
        lbUserName.setText("User Name");

        lbRole.setForeground(new java.awt.Color(127, 127, 127));
        lbRole.setText("User");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        mailBadge.setForeground(new java.awt.Color(255, 255, 255));
        mailBadge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/powersmall.png"))); // NOI18N
        mailBadge.setBorderPainted(false);
        mailBadge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailBadgeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(mailBadge, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUserName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRole, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRole))
                    .addComponent(cmdMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(mailBadge, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdMenuActionPerformed

    private void mailBadgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailBadgeActionPerformed
    SessionManager.getInstance().clearSession(); // Clear session data
    new Main().setVisible(true); // Redirect to login screen
    // Get the parent JFrame and dispose of it
    
    //connect to message.java later to confirm logout.
    java.awt.Window topLevelWindow = SwingUtilities.getWindowAncestor(this);
    if (topLevelWindow instanceof JFrame) {
        ((JFrame) topLevelWindow).dispose(); // Close the JFrame (Dashboard)
    }

    }//GEN-LAST:event_mailBadgeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button2 cmdMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbUserName;
    private swing.ButtonBadges mailBadge;
    private swing.ImageAvatar pic;
    // End of variables declaration//GEN-END:variables
}
