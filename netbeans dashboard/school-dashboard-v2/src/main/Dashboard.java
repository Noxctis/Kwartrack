package main;

import component.Header;
import component.Menu;
import event.EventMenuSelected;
import event.EventShowPopupMenu;
import form.DebtsPane;
import form.ExpensesPane;
import form.BalancePane;
import form.Form_Home;
import form.DebtsChartPane;
import form.ExpensesChartPane;
import form.BalanceChartPane;
import form.MainForm;
import swing.MenuItem;
import swing.PopupMenu;
import swing.icon.GoogleMaterialDesignIcons;
import swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import swingacrylic.SwingAcrylic;
import db.SessionManager;
import form.BalanceChartPane;
import form.DebtsChartPane;
import form.ExpensesChartPane;

public class Dashboard extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;

    public Dashboard() {
        initComponents();
        init();
    }

    private void init() {
        // Check if the session is valid
    /*if (!SessionManager.getInstance().isSessionValid()) {
        javax.swing.JOptionPane.showMessageDialog(null,
                "Your session has expired. Please log in again.",
                "Session Expired",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        //new Login().setVisible(true); // Redirect to login screen
        this.dispose(); // Close the current dashboard
        return; // Stop further initialization
    }*/
        layout = new MigLayout("fill", "10[]10[100%, fill]10", "10[fill, top]10"); // original code
        //layout = new MigLayout("fill", "10[200!]10[800!, fill]10", "10[fill, top]10");

        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (menuIndex == 0) {
                    if (subMenuIndex == 0) {
                        main.showForm(new Form_Home());
                    } else if (subMenuIndex == 1) {
                        main.showForm(new ExpensesPane());
                    } else if (subMenuIndex == 2) {
                        main.showForm(new DebtsPane());
                    } else if (subMenuIndex == 3) {
                        main.showForm(new BalancePane());
                    }
                } else if (menuIndex == 1) {
                    if (subMenuIndex == 0) {
                        main.showForm(new ExpensesChartPane());
                    } else if (subMenuIndex == 1) {
                        main.showForm(new DebtsChartPane());
                    } else if (subMenuIndex == 2) {
                        main.showForm(new BalanceChartPane());
                    }
                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Dashboard.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Dashboard.this.getX() + 62;
                int y = Dashboard.this.getY() + com.getY() + 95;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Start with this form
        main.showForm(new Form_Home());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new swing.PanelTransparent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingAcrylic.prepareSwing();
                Dashboard frame = new Dashboard();
                frame.setVisible(true);
                SwingAcrylic.processFrame(frame);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.PanelTransparent bg;
    // End of variables declaration//GEN-END:variables
}
