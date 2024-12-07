package form;

import dialog.Message;
import main.Dashboard;
import model.ModelCard;
import model.ModelStudent;
import swing.icon.GoogleMaterialDesignIcons;
import swing.icon.IconFontSwing;
import swing.noticeboard.ModelNoticeBoard;
import java.awt.Color;
import javax.swing.Icon;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;
import db.SessionManager;
import javax.swing.table.DefaultTableModel;



public class Form_Home extends javax.swing.JPanel {

    public Form_Home() {
        initComponents();
        table1.fixTable(jScrollPane1);
        setOpaque(false);
        initData();
        refreshTableData();  // Refresh the table with data
    }

    private void initData() {
        initCardData();
        initNoticeBoard();
        initTableData();
    }

    private void initTableData() {
//        EventAction eventAction = new EventAction() {
//            @Override
//            public void delete(ModelStudent student) {
//                showMessage("Delete Student : " + student.getName());
//            }
//
//            @Override
//            public void update(ModelStudent student) {
//                showMessage("Update Student : " + student.getName());
//            }
//        };
        table1.addRow(new ModelStudent("bayad", "84/6/13", "asd", 999).toRowTable());
        table1.addRow(new ModelStudent("bayad2", "56032095", "zxc", 999).toRowTable());
        table1.addRow(new ModelStudent("bayat3", "12/78/15", "qwe", 999).toRowTable());
        table1.addRow(new ModelStudent("bading3", "56/23/96", "fgh", 999).toRowTable());
        table1.addRow(new ModelStudent("igasng2", "48/89/32", "rty", 999).toRowTable());

        
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile2.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile2.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile2.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile1.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile2.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile2.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile2.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
//        table1.addRow(new ModelStudent(new ImageIcon(getClass().getResource("/icon/profile2.jpg")), "Bora", "Male", "C#", 300).toRowTable(eventAction));
    }
    
     
private void refreshTableData() {
    try {
        // Get the database connection from DatabaseConnection
        Connection connection = DatabaseConnection.getConnection();

        // Query to get transactions for a specific user along with transaction type and category
        int userId = 1; // SessionManager.getInstance().getUserId(); hardcode for testing, replace with the actual user ID
        String query = "SELECT t.transaction_id, t.type, t.amount, t.date, t.category_id, u.username " +
                       "FROM transactions t " +
                       "JOIN users u ON t.user_id = u.user_id " +  // Join with users to get the username
                       "WHERE t.user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);

        // Execute the query
        ResultSet rs = statement.executeQuery();

        // Get the table model and clear existing rows
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        // Loop through the ResultSet and add rows to the table
        while (rs.next()) {
            int transactionId = rs.getInt("transaction_id");
            String type = rs.getString("type");  // Type of transaction (income, debt, expense)
            double amount = rs.getDouble("amount");
            Date date = rs.getDate("date");
            String username = rs.getString("username");

            // Get the transaction category name if required (can use the existing method)
            String category = null;

            if ("expense".equals(type)) {
                category = getCategoryById(rs.getInt("category_id"));  // Only fetch category for expenses
            }

            // Add a row to the table with username, type (Income/Debt/Expense), category (if applicable), etc.
            model.addRow(new Object[] { transactionId, username, type, category, date, amount });
        }

        // Close the resources
        rs.close();
        statement.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private String getCategoryById(int categoryId) {
    String categoryName = "Unknown";  // Default value
    try {
        // Query to get category name by categoryId
        String query = "SELECT name FROM categories WHERE category_id = ?";
        PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(query);
        statement.setInt(1, categoryId);

        // Execute the query
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            categoryName = rs.getString("name");
        }

        // Close the resources
        rs.close();
        statement.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return categoryName;
}

    private void initCardData() {
        int userId = 1; // Replace with SessionManager.getInstance().getUserId() for dynamic user ID

    // Query for Total Balance (Income - Expense)
    double totalBalance = getTotalBalance(userId);
    
    // Query for Expense This Week
    double expenseThisWeek = getExpenseThisWeek(userId);
    
    // Set the Total Balance card data
    Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
    card1.setData(new ModelCard("Total Balance", totalBalance, icon1));
    
    // Set the Expense This Week card data
    Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
    card2.setData(new ModelCard("Expense This Week", expenseThisWeek, icon2));

        //Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        //card1.setData(new ModelCard("Total Balance", 5100, icon1));
        //Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        //card2.setData(new ModelCard("Expense This Week", 2000, icon2));
        //Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_BASKET, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        //card3.setData(new ModelCard("Expense", 3000, icon3));
//        Icon icon4 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BUSINESS_CENTER, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
//        card4.setData(new ModelCard("Other Income", 550, 95, icon4));
    }
    
    private double getTotalBalance(int userId) {
    double totalBalance = 0.0;
    try (Connection connection = DatabaseConnection.getConnection()) {
        // Query to get total income and total expense for the user
        String incomeQuery = "SELECT SUM(amount) FROM incomes WHERE user_id = ?";
        PreparedStatement incomeStmt = connection.prepareStatement(incomeQuery);
        incomeStmt.setInt(1, userId);
        ResultSet incomeRs = incomeStmt.executeQuery();
        if (incomeRs.next()) {
            totalBalance += incomeRs.getDouble(1);  // Total Income
        }
        
        String expenseQuery = "SELECT SUM(amount) FROM expenses WHERE user_id = ?";
        PreparedStatement expenseStmt = connection.prepareStatement(expenseQuery);
        expenseStmt.setInt(1, userId);
        ResultSet expenseRs = expenseStmt.executeQuery();
        if (expenseRs.next()) {
            totalBalance -= expenseRs.getDouble(1);  // Subtract Total Expense
        }
        
        incomeRs.close();
        expenseRs.close();
        incomeStmt.close();
        expenseStmt.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return totalBalance;
}

private double getExpenseThisWeek(int userId) {
    double expenseThisWeek = 0.0;
    try (Connection connection = DatabaseConnection.getConnection()) {
        // Query to get the total expense for this week
        String query = "SELECT SUM(amount) FROM expenses WHERE user_id = ? AND YEARWEEK(date, 1) = YEARWEEK(CURDATE(), 1)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            expenseThisWeek = rs.getDouble(1);
        }
        
        rs.close();
        stmt.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return expenseThisWeek;
}

    private void initNoticeBoard() {
//        noticeBoard.addDate("04/10/2021");
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(94, 49, 238), "Hidemode", "Now", "Sets the hide mode for the component. If the hide mode has been specified in the This hide mode can be overridden by the component constraint."));
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(218, 49, 238), "Tag", "2h ago", "Tags the component with metadata name that can be used by the layout engine. The tag can be used to explain for the layout manager what the components is showing, such as an OK or Cancel button."));
//        noticeBoard.addDate("03/10/2021");
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(32, 171, 43), "Further Reading", "12:30 PM", "There are more information to digest regarding MigLayout. The resources are all available at www.migcomponents.com"));
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(50, 93, 215), "Span", "10:30 AM", "Spans the current cell (merges) over a number of cells. Practically this means that this cell and the count number of cells will be treated as one cell and the component can use the space that all these cells have."));
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(27, 188, 204), "Skip ", "9:00 AM", "Skips a number of cells in the flow. This is used to jump over a number of cells before the next free cell is looked for. The skipping is done before this component is put in a cell and thus this cells is affected by it. \"count\" defaults to 1 if not specified."));
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(238, 46, 57), "Push", "7:15 AM", "Makes the row and/or column that the component is residing in grow with \"weight\". This can be used instead of having a \"grow\" keyword in the column/row constraints."));
        noticeBoard.scrollToTop();
    }

    private void showMessage(String message) {
        Message obj = new Message(Dashboard.getFrames()[0], true);
        obj.showMessage(message);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new component.Card();
        jLabel1 = new javax.swing.JLabel();
        card2 = new component.Card();
        panelTransparent1 = new swing.PanelTransparent();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new swing.table.Table();
        dashboardBalanceButton = new javax.swing.JButton();
        dashboardExpenseButton = new javax.swing.JButton();
        dashboardDebtButton = new javax.swing.JButton();
        panelTransparent2 = new swing.PanelTransparent();
        noticeBoard = new swing.noticeboard.NoticeBoard();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        card1.setColorGradient(new java.awt.Color(211, 28, 215));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Dashboard / Home");

        card2.setBackground(new java.awt.Color(10, 30, 214));
        card2.setColorGradient(new java.awt.Color(72, 111, 252));

        panelTransparent1.setTransparent(0.5F);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "User ID", "Type", "Category", "Date", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        dashboardBalanceButton.setBackground(new java.awt.Color(204, 204, 204));
        dashboardBalanceButton.setText("Deposit Balance");
        dashboardBalanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBalanceButtonActionPerformed(evt);
            }
        });

        dashboardExpenseButton.setBackground(new java.awt.Color(204, 204, 204));
        dashboardExpenseButton.setText("Add Expense");
        dashboardExpenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardExpenseButtonActionPerformed(evt);
            }
        });

        dashboardDebtButton.setBackground(new java.awt.Color(204, 204, 204));
        dashboardDebtButton.setText("Add Debt");
        dashboardDebtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardDebtButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTransparent1Layout = new javax.swing.GroupLayout(panelTransparent1);
        panelTransparent1.setLayout(panelTransparent1Layout);
        panelTransparent1Layout.setHorizontalGroup(
            panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelTransparent1Layout.createSequentialGroup()
                        .addComponent(dashboardBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dashboardExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dashboardDebtButton, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTransparent1Layout.setVerticalGroup(
            panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransparent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboardBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboardExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboardDebtButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        panelTransparent2.setTransparent(0.5F);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(105, 105, 105));
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setText("Financial Health Score");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setOpaque(true);

        javax.swing.GroupLayout panelTransparent2Layout = new javax.swing.GroupLayout(panelTransparent2);
        panelTransparent2.setLayout(panelTransparent2Layout);
        panelTransparent2Layout.setHorizontalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTransparent2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 416, Short.MAX_VALUE))
                    .addComponent(noticeBoard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransparent2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(132, 132, 132))
        );
        panelTransparent2Layout.setVerticalGroup(
            panelTransparent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransparent2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(noticeBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(479, 479, 479))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelTransparent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTransparent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTransparent2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTransparent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardBalanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBalanceButtonActionPerformed
         //TODO add your handling code here:
         
        depositBalance depositBalanceWindow = new depositBalance();
        depositBalanceWindow.setVisible(true);
        depositBalanceWindow.pack();
        depositBalanceWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        depositBalanceWindow.setLocationRelativeTo(null);
        // Refresh the table data after adding the expense
        depositBalanceWindow.addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            refreshTableData();  // Refresh table data when the add expense window is closed
            initCardData();
        }
    });
    }//GEN-LAST:event_dashboardBalanceButtonActionPerformed

    private void dashboardExpenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardExpenseButtonActionPerformed
        // TODO add your handling code here:
        addExpense addExpenseWindow = new addExpense();
        addExpenseWindow.setVisible(true);
        addExpenseWindow.pack();
        addExpenseWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addExpenseWindow.setLocationRelativeTo(null);
        // Refresh the table data after adding the expense
        addExpenseWindow.addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            refreshTableData();  // Refresh table data when the add expense window is closed
            initCardData();
        }
    });
    }//GEN-LAST:event_dashboardExpenseButtonActionPerformed

    private void dashboardDebtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardDebtButtonActionPerformed
        // TODO add your handling code here:
        addDebtWindow addDebtWindow = new addDebtWindow();
        addDebtWindow.setVisible(true);
        addDebtWindow.pack();
        addDebtWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addDebtWindow.setLocationRelativeTo(null);
        // Refresh the table data after adding the debt
    addDebtWindow.addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            refreshTableData();  // Refresh table data when the add debt window is closed
            initCardData();
        }
    }); 
    }//GEN-LAST:event_dashboardDebtButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Card card1;
    private component.Card card2;
    private javax.swing.JButton dashboardBalanceButton;
    private javax.swing.JButton dashboardDebtButton;
    private javax.swing.JButton dashboardExpenseButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.noticeboard.NoticeBoard noticeBoard;
    private swing.PanelTransparent panelTransparent1;
    private swing.PanelTransparent panelTransparent2;
    private swing.table.Table table1;
    // End of variables declaration//GEN-END:variables
}
