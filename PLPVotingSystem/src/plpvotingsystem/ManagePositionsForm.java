/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ManagePositionsForm extends javax.swing.JFrame {
    
    private void showCustomMessage(String title, String message, java.awt.Color themeColor) {
        javax.swing.JDialog msgDialog = new javax.swing.JDialog(this, title, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        msgDialog.setSize(400, 200);
        msgDialog.setLocationRelativeTo(this);
        msgDialog.setUndecorated(true);

        plpvotingsystem.RoundedPanel pnlContent = new plpvotingsystem.RoundedPanel();
        pnlContent.setBackground(java.awt.Color.WHITE);
        pnlContent.setBorder(javax.swing.BorderFactory.createLineBorder(themeColor, 3));
        
        pnlContent.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        javax.swing.JLabel lblMessage = new javax.swing.JLabel("<html><center style='width: 280px;'>" + message.replace("\n", "<br>") + "</center></html>");
        lblMessage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.JPanel btnPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        
        plpvotingsystem.RoundedButton btnOk = new plpvotingsystem.RoundedButton();
        btnOk.setText("OK");
        btnOk.setBackground(themeColor);
        btnOk.setForeground(java.awt.Color.WHITE);
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.setPreferredSize(new java.awt.Dimension(120, 40));
        btnOk.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY, 2, true));
        btnOk.addActionListener(e -> msgDialog.dispose());

        btnPanel.add(btnOk);

        // Assembly
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(0, 0, 20, 0); // Exactly 20px gap below the text
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        pnlContent.add(lblMessage, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        pnlContent.add(btnPanel, gbc);

        msgDialog.add(pnlContent);

        msgDialog.getRootPane().setDefaultButton(btnOk);
        msgDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent e) {
                btnOk.requestFocusInWindow();
            }
        });

        msgDialog.setVisible(true);
    }
    
    /**
     * UI REPLACEMENT 3: Replaces the standard gray deletion prompt with the application's custom style.
     * @param message The confirmation message to display (e.g., "Are you sure you want to delete...").
     * @param onConfirm Action to be executed if user clicks 'Yes'. This is a function that takes no parameters.
     */
    private void showCustomDeletionDialog(String message, Runnable onConfirm) {
        javax.swing.JDialog confirmDialog = new javax.swing.JDialog(this, "Confirm Deletion", java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        confirmDialog.setSize(400, 200); 
        confirmDialog.setLocationRelativeTo(this); 
        confirmDialog.setUndecorated(true); 

        plpvotingsystem.RoundedPanel pnlContent = new plpvotingsystem.RoundedPanel();
        pnlContent.setBackground(java.awt.Color.WHITE);
        pnlContent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 53, 69), 3));
        
        pnlContent.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        javax.swing.JLabel lblMessage = new javax.swing.JLabel("<html><center style='width: 280px;'>" + message.replace("\n", "<br>") + "</center></html>");
        lblMessage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.JPanel buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);

        plpvotingsystem.RoundedButton btnYes = new plpvotingsystem.RoundedButton();
        btnYes.setText("Yes, Delete");
        btnYes.setBackground(new java.awt.Color(220, 53, 69)); 
        btnYes.setForeground(java.awt.Color.WHITE);
        btnYes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnYes.setPreferredSize(new java.awt.Dimension(130, 40));
        btnYes.setDialogButton(true); 
        btnYes.addActionListener(e -> {
            confirmDialog.dispose();
            onConfirm.run(); 
        });

        plpvotingsystem.RoundedButton btnNo = new plpvotingsystem.RoundedButton();
        btnNo.setText("Cancel");
        btnNo.setBackground(new java.awt.Color(200, 200, 200));
        btnNo.setForeground(java.awt.Color.BLACK);
        btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNo.setPreferredSize(new java.awt.Dimension(100, 40));
        btnNo.setDialogButton(true); 
        btnNo.addActionListener(e -> confirmDialog.dispose()); 

        buttonPanel.add(btnYes);
        buttonPanel.add(btnNo);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(0, 0, 20, 0); 
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        pnlContent.add(lblMessage, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        pnlContent.add(buttonPanel, gbc);

        confirmDialog.add(pnlContent);
        
        confirmDialog.getRootPane().registerKeyboardAction(e -> confirmDialog.dispose(), javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        confirmDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent e) {
                btnYes.requestFocusInWindow();
            }
        });

        confirmDialog.setVisible(true);
    }

    String currentAdmin = "";

    java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");

    public ManagePositionsForm(String adminName) {
        initComponents();
        this.currentAdmin = adminName;
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));
        this.setIconImage(plpIcon);
        
        loadPositionsTable();
    }

    private void loadPositionsTable(){
        javax.swing.table.DefaultTableModel model = (DefaultTableModel) tblPositionsData.getModel();
        model.setRowCount(0);
        
        try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM tblPositions ORDER BY position_id ASC")){
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                int positionID = rs.getInt("position_id");
                String position = rs.getString("position_name");
                int maxPos = rs.getInt("max_votes");
                
                Object[] posInformation = new Object[3];
                
                posInformation[0] = positionID;
                posInformation[1] = position;
                posInformation[2] = maxPos;
                
                model.addRow(posInformation);
            }
        }catch(java.sql.SQLException e){
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalHeader2 = new plpvotingsystem.GlobalHeader();
        jLabel2 = new javax.swing.JLabel();
        btnAddPosition = new plpvotingsystem.RoundedButton();
        btnEditPosition = new plpvotingsystem.RoundedButton();
        btnRemovePosition = new plpvotingsystem.RoundedButton();
        btnBack2Dashboard = new plpvotingsystem.RoundedButton();
        btnRefresh = new plpvotingsystem.RoundedButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPositionsData = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 245, 245));
        setResizable(false);

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 60, 20, 60));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actions");

        btnAddPosition.setBackground(new java.awt.Color(0, 255, 102));
        btnAddPosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddPosition.setForeground(new java.awt.Color(0, 0, 0));
        btnAddPosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/addPosition.png"))); // NOI18N
        btnAddPosition.setText("Register Position");
        btnAddPosition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPosition.setIconTextGap(10);
        btnAddPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPositionActionPerformed(evt);
            }
        });

        btnEditPosition.setBackground(new java.awt.Color(250, 192, 45));
        btnEditPosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditPosition.setForeground(new java.awt.Color(0, 0, 0));
        btnEditPosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/editPosition.png"))); // NOI18N
        btnEditPosition.setText("Edit Position");
        btnEditPosition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditPosition.setIconTextGap(10);
        btnEditPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPositionActionPerformed(evt);
            }
        });

        btnRemovePosition.setBackground(new java.awt.Color(255, 102, 102));
        btnRemovePosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemovePosition.setForeground(new java.awt.Color(0, 0, 0));
        btnRemovePosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/deletePosition.png"))); // NOI18N
        btnRemovePosition.setText("Remove Position");
        btnRemovePosition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemovePosition.setIconTextGap(10);
        btnRemovePosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemovePositionActionPerformed(evt);
            }
        });

        btnBack2Dashboard.setBackground(new java.awt.Color(204, 204, 204));
        btnBack2Dashboard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack2Dashboard.setForeground(new java.awt.Color(0, 0, 0));
        btnBack2Dashboard.setText("TP to Admin Dashboard");
        btnBack2Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack2Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2DashboardActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(204, 204, 204));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(0, 0, 0));
        btnRefresh.setText("Refresh Table");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tblPositionsData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Position ID", "Position Name", "Max Positions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPositionsData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemovePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack2Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAddPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddPosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditPosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemovePosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack2Dashboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPositionActionPerformed
        plpvotingsystem.AddPositionForm addForm = new plpvotingsystem.AddPositionForm();

        addForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // 3. Refresh the grid the second they finish adding the candidate
                loadPositionsTable();
            }
        });

        addForm.setVisible(true);
    }//GEN-LAST:event_btnAddPositionActionPerformed

    private void btnEditPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPositionActionPerformed
int selectedRow = tblPositionsData.getSelectedRow();

        if (selectedRow == -1) {
            showCustomMessage("Selection Error", "Select a position first to edit by clicking on its cell.", new java.awt.Color(220, 53, 69));
            return;
        } 
        
        int posID = Integer.parseInt(tblPositionsData.getValueAt(selectedRow, 0).toString());
        String posName = tblPositionsData.getValueAt(selectedRow, 1).toString();
        int maxPos = Integer.parseInt(tblPositionsData.getValueAt(selectedRow, 2).toString());

        plpvotingsystem.AddPositionForm editForm = new plpvotingsystem.AddPositionForm(posID, posName, maxPos);
        editForm.setLocationRelativeTo(null);
        editForm.setIconImage(plpIcon);

        editForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadPositionsTable();
            }
        });

        editForm.setVisible(true);
    }//GEN-LAST:event_btnEditPositionActionPerformed

    private void btnRemovePositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemovePositionActionPerformed
        int selectedRow = tblPositionsData.getSelectedRow();
        
        if(selectedRow < 0){
            showCustomMessage("Selection Error", "Please select a position to remove.", new java.awt.Color(220, 53, 69));
            return;
        } 
        
        String selectedID = tblPositionsData.getValueAt(selectedRow, 0).toString();
        int selectID = Integer.parseInt(selectedID);
        
        try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM tblCandidates WHERE position_id = ?")){
            pst.setInt(1, selectID);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){  
                int count = rs.getInt(1);
                
                if(count > 0){
                    showCustomMessage("Remove Position Disabled", "Cannot remove a position when candidates are currently running for it.", new java.awt.Color(220, 53, 69));
                    return;
                } 
                
                showCustomDeletionDialog("Are you sure you want to remove this position?", () -> {
                    try(Connection conn2 = database.DBConnection.DBConnect(); PreparedStatement pst2 = conn2.prepareStatement("DELETE FROM tblPositions WHERE position_id = ?")){
                        pst2.setInt(1, selectID);
                        pst2.executeUpdate();
                        
                        showCustomMessage("Success", "Successfully removed position.", new java.awt.Color(55, 140, 59));
                        loadPositionsTable();
                        
                    }catch(java.sql.SQLException e){
                        showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
                    }
                });
            }
        }catch(java.sql.SQLException e){
            showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
        }
    }//GEN-LAST:event_btnRemovePositionActionPerformed

    private void btnBack2DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2DashboardActionPerformed
        this.dispose();
        new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnBack2DashboardActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadPositionsTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagePositionsForm("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPosition;
    private javax.swing.JButton btnBack2Dashboard;
    private javax.swing.JButton btnEditPosition;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemovePosition;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPositionsData;
    // End of variables declaration//GEN-END:variables
}
