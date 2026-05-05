/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManagePartylistsForm extends javax.swing.JFrame {
    
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
    
    public ManagePartylistsForm(String adminName) {
        initComponents();
        
        loadPartylistsTable();
        
        this.currentAdmin = adminName;
        
        this.setLocationRelativeTo(null);
        this.setIconImage(plpIcon);
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));
    }

    private void loadPartylistsTable(){
        javax.swing.table.DefaultTableModel model = (DefaultTableModel)tblPartyData.getModel();
        model.setRowCount(0);
        
        try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM tblPartylists ORDER BY CASE WHEN party_name = 'Independent' THEN 1 ELSE 0 END, party_name ASC")){
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String partyList = rs.getString("party_name");
                String partyDesc = rs.getString("party_description");
                
                Object[] partylistData = new Object[2];
                
                partylistData[0] = partyList;
                partylistData[1] = partyDesc;
                
                model.addRow(partylistData);
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
        jPanel1 = new javax.swing.JPanel();
        btnBack2Dashboard = new plpvotingsystem.RoundedButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPartyData = new javax.swing.JTable();
        btnEditParty = new plpvotingsystem.RoundedButton();
        btnRefresh = new plpvotingsystem.RoundedButton();
        btnAddParty = new plpvotingsystem.RoundedButton();
        btnRemoveParty = new plpvotingsystem.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Partylist Information");
        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(1018, 437));
        setResizable(false);

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 60, 20, 60));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actions");

        tblPartyData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Partylist Name", "Partylist Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPartyData);

        btnEditParty.setBackground(new java.awt.Color(250, 192, 45));
        btnEditParty.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditParty.setForeground(new java.awt.Color(0, 0, 0));
        btnEditParty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/editParty.png"))); // NOI18N
        btnEditParty.setText("Edit Partylist");
        btnEditParty.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditParty.setIconTextGap(10);
        btnEditParty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPartyActionPerformed(evt);
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

        btnAddParty.setBackground(new java.awt.Color(0, 255, 102));
        btnAddParty.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddParty.setForeground(new java.awt.Color(0, 0, 0));
        btnAddParty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/addParty.png"))); // NOI18N
        btnAddParty.setText("Register Partylist");
        btnAddParty.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddParty.setIconTextGap(10);
        btnAddParty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPartyActionPerformed(evt);
            }
        });

        btnRemoveParty.setBackground(new java.awt.Color(255, 102, 102));
        btnRemoveParty.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemoveParty.setForeground(new java.awt.Color(0, 0, 0));
        btnRemoveParty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/removeParty.png"))); // NOI18N
        btnRemoveParty.setText("Remove Partylist");
        btnRemoveParty.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoveParty.setIconTextGap(10);
        btnRemoveParty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemovePartyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditParty, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddParty, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveParty, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack2Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddParty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditParty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoveParty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack2Dashboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPartyActionPerformed
        plpvotingsystem.AddPartylistForm addForm = new plpvotingsystem.AddPartylistForm();
        
        addForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // 3. Refresh the grid the second they finish adding the candidate
                loadPartylistsTable();
            }
        });
        
        addForm.setVisible(true);
    }//GEN-LAST:event_btnAddPartyActionPerformed

    private void btnRemovePartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemovePartyActionPerformed
        int selectedRow = tblPartyData.getSelectedRow();
        
        if(selectedRow < 0){
            showCustomMessage("Selection Error", "Please select a partylist to remove.", new java.awt.Color(220, 53, 69));
            return;
        } 
        
        String selectedParty = tblPartyData.getValueAt(selectedRow, 0).toString();
        
        if (selectedParty.equals("Independent")){
            showCustomMessage("Error", "Only partylists can be modified.", new java.awt.Color(220, 53, 69));
            return;
        }
        
        // Check if candidates are attached before asking to delete
        try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM tblCandidates WHERE party_list = ?")){
            pst.setString(1, selectedParty);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                int returnedValue = rs.getInt(1);
                if (returnedValue > 0){
                    showCustomMessage("Cannot Remove", "There are " + returnedValue + " candidates currently running under this party.<br>Reassign or remove them first.", new java.awt.Color(220, 53, 69));
                    return;
                }
                
                // If no candidates are attached, summon the Custom Deletion Dialog!
                showCustomDeletionDialog("Are you sure you want to remove the partylist:<br>" + selectedParty + "?", () -> {
                    try(Connection conn2 = database.DBConnection.DBConnect(); PreparedStatement pst2 = conn2.prepareStatement("DELETE FROM tblPartylists WHERE party_name = ?")){
                        pst2.setString(1, selectedParty);
                        pst2.executeUpdate();
                        
                        showCustomMessage("Success", "Successfully removed partylist.", new java.awt.Color(55, 140, 59));
                        loadPartylistsTable();
                    }catch(java.sql.SQLException e){
                        showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
                    }
                });
            }
        } catch (java.sql.SQLException e){
            showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
        }
    }//GEN-LAST:event_btnRemovePartyActionPerformed

    private void btnBack2DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2DashboardActionPerformed
        this.dispose();
        new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnBack2DashboardActionPerformed

    private void btnEditPartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPartyActionPerformed
       
        int selectedRow = tblPartyData.getSelectedRow();

        if (selectedRow == -1) {
            showCustomMessage("Selection Error", "Select a partylist first to edit by clicking on its cell.", new java.awt.Color(220, 53, 69));
            return;
        } 
        
        String selectedParty = tblPartyData.getValueAt(selectedRow, 0).toString();
        
        if (selectedParty.equals("Independent")){
            showCustomMessage("Independency Message", "Only partylists can be modified!", new java.awt.Color(220, 53, 69));
            return;
        }

        plpvotingsystem.AddPartylistForm editForm = new plpvotingsystem.AddPartylistForm(selectedParty);
        editForm.setLocationRelativeTo(null);
        editForm.setIconImage(plpIcon);

        editForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadPartylistsTable();
            }
        });

        editForm.setVisible(true);
    }//GEN-LAST:event_btnEditPartyActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadPartylistsTable();
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
            java.util.logging.Logger.getLogger(ManagePartylistsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePartylistsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePartylistsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePartylistsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagePartylistsForm("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddParty;
    private javax.swing.JButton btnBack2Dashboard;
    private javax.swing.JButton btnEditParty;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemoveParty;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPartyData;
    // End of variables declaration//GEN-END:variables
}
