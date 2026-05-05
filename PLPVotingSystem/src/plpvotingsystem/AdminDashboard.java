/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class AdminDashboard extends javax.swing.JFrame {
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
        btnYes.setText("Yes");
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
    
    
    private void showCustomMessage(String title, String message, java.awt.Color themeColor) {
        javax.swing.JDialog msgDialog = new javax.swing.JDialog(this, title, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        msgDialog.setSize(400, 200);
        msgDialog.setLocationRelativeTo(this);
        msgDialog.setUndecorated(true);

        plpvotingsystem.RoundedPanel pnlContent = new plpvotingsystem.RoundedPanel();
        pnlContent.setBackground(java.awt.Color.WHITE);
        pnlContent.setBorder(javax.swing.BorderFactory.createLineBorder(themeColor, 3));
        
        // THE FIX: BorderLayout for perfect spacing
        pnlContent.setLayout(new java.awt.BorderLayout());

        javax.swing.JLabel lblMessage = new javax.swing.JLabel("<html><div style='text-align: center; width: 280px;'>" + message.replace("\n", "<br>") + "</div></html>");
        lblMessage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setVerticalAlignment(javax.swing.SwingConstants.CENTER); // Dead center vertically

        javax.swing.JPanel btnPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Bottom padding
        
        plpvotingsystem.RoundedButton btnOk = new plpvotingsystem.RoundedButton();
        btnOk.setText("OK");
        btnOk.setBackground(themeColor);
        btnOk.setForeground(java.awt.Color.WHITE);
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.setPreferredSize(new java.awt.Dimension(120, 40));
        
        // THE FOCUS CUE: Dark outline so the user knows they can hit Enter
        btnOk.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY, 2, true));
        
        btnOk.addActionListener(e -> msgDialog.dispose());

        btnPanel.add(btnOk);

        pnlContent.add(lblMessage, java.awt.BorderLayout.CENTER); // Fills middle
        pnlContent.add(btnPanel, java.awt.BorderLayout.SOUTH);    // Anchors bottom

        msgDialog.add(pnlContent);

        // Bind Enter key AND force visual focus
        msgDialog.getRootPane().setDefaultButton(btnOk);
        msgDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent e) {
                btnOk.requestFocusInWindow();
            }
        });

        msgDialog.setVisible(true);
    }

    String currentAdmin = "";

    public AdminDashboard(String adminName) {
        initComponents();
        
        try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM tblElectionStatus")){
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                String electionStatus = rs.getString("current_phase").trim();
                
                if(electionStatus.equalsIgnoreCase("OPEN")){
                    btnManageCandidate.setEnabled(false);
                    btnManagePosition.setEnabled(false);
                    
                    btnManageCandidate.setBackground(new java.awt.Color(200, 200, 200));
                    btnManagePosition.setBackground(new java.awt.Color(200, 200, 200));
                } else{
                    btnManageCandidate.setEnabled(true);
                    btnManagePosition.setEnabled(true);
                    
                    btnManageCandidate.setBackground(new java.awt.Color(0,255,102));
                    btnManagePosition.setBackground(new java.awt.Color(250,192,45));
                }
            }
        }catch(java.sql.SQLException e){
            showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
            return;
        }

        this.currentAdmin = adminName;

        this.setLocationRelativeTo(null);
        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);

        this.getContentPane().setBackground(new java.awt.Color(245, 245, 245));

        lblGreeting.setText("Hello, " + adminName);

        checkElectionStatus();
    }

    private void checkElectionStatus() {
        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT current_phase FROM tblElectionStatus WHERE status_id = 1")) {

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String currentPhase = rs.getString("current_phase");

                if (currentPhase.equalsIgnoreCase("CLOSED")) {
                    btnConcludeElect.setText("See Election Results");
                    btnConcludeElect.setBackground(new java.awt.Color(55, 140, 59)); // PLP Green
                    btnConcludeElect.setForeground(java.awt.Color.BLACK);
                }
            }
        } catch (java.sql.SQLException e) {
            showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalHeader2 = new plpvotingsystem.GlobalHeader();
        jPanel1 = new javax.swing.JPanel();
        btnManageCandidate = new plpvotingsystem.RoundedButton();
        btnManageVoter = new plpvotingsystem.RoundedButton();
        jLabel2 = new javax.swing.JLabel();
        btnConcludeElect = new plpvotingsystem.RoundedButton();
        lblGreeting = new javax.swing.JLabel();
        btnManageParty = new plpvotingsystem.RoundedButton();
        btnManagePosition = new plpvotingsystem.RoundedButton();
        btnVerifyIntegrity = new plpvotingsystem.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setBackground(new java.awt.Color(245, 245, 245));
        setResizable(false);

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 60, 20, 60));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 80, 1, 80));

        btnManageCandidate.setBackground(new java.awt.Color(0, 255, 102));
        btnManageCandidate.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnManageCandidate.setForeground(new java.awt.Color(0, 0, 0));
        btnManageCandidate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/manageCandidate.png"))); // NOI18N
        btnManageCandidate.setText("Manage Candidate Information");
        btnManageCandidate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManageCandidate.setIconTextGap(10);
        btnManageCandidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageCandidateActionPerformed(evt);
            }
        });

        btnManageVoter.setBackground(new java.awt.Color(250, 192, 45));
        btnManageVoter.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnManageVoter.setForeground(new java.awt.Color(0, 0, 0));
        btnManageVoter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/manageVoter.png"))); // NOI18N
        btnManageVoter.setText("Manage Voter Information");
        btnManageVoter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManageVoter.setIconTextGap(10);
        btnManageVoter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageVoterActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin Only Actions");

        btnConcludeElect.setBackground(new java.awt.Color(255, 102, 102));
        btnConcludeElect.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnConcludeElect.setForeground(new java.awt.Color(0, 0, 0));
        btnConcludeElect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/voteIcon.png"))); // NOI18N
        btnConcludeElect.setText("Conclude Elections");
        btnConcludeElect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConcludeElect.setIconTextGap(15);
        btnConcludeElect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcludeElectActionPerformed(evt);
            }
        });

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGreeting.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGreeting.setText("Hello");

        btnManageParty.setBackground(new java.awt.Color(0, 255, 102));
        btnManageParty.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnManageParty.setForeground(new java.awt.Color(0, 0, 0));
        btnManageParty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/manageParty.png"))); // NOI18N
        btnManageParty.setText("Manage Partylist Information");
        btnManageParty.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManageParty.setIconTextGap(10);
        btnManageParty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagePartyActionPerformed(evt);
            }
        });

        btnManagePosition.setBackground(new java.awt.Color(250, 192, 45));
        btnManagePosition.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnManagePosition.setForeground(new java.awt.Color(0, 0, 0));
        btnManagePosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/managePosition.png"))); // NOI18N
        btnManagePosition.setText("Manage Position Information");
        btnManagePosition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManagePosition.setIconTextGap(10);
        btnManagePosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagePositionActionPerformed(evt);
            }
        });

        btnVerifyIntegrity.setBackground(new java.awt.Color(250, 192, 45));
        btnVerifyIntegrity.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnVerifyIntegrity.setForeground(new java.awt.Color(0, 0, 0));
        btnVerifyIntegrity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/verifyDB.png"))); // NOI18N
        btnVerifyIntegrity.setText("Verify Database Integrity");
        btnVerifyIntegrity.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerifyIntegrity.setIconTextGap(10);
        btnVerifyIntegrity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyIntegrityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnManageCandidate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnManageVoter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnManageParty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnManagePosition))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnConcludeElect, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerifyIntegrity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGreeting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnManageCandidate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageVoter, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageParty, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManagePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcludeElect, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerifyIntegrity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConcludeElectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcludeElectActionPerformed
        // Traffic Cop: If it's green and says See Results, teleport and abort!
        if (btnConcludeElect.getText().equals("See Election Results")) {
            this.dispose();
            new plpvotingsystem.ElectionResultsForm(this.currentAdmin).setVisible(true);
            return;
        }
        
        // UI REPLACEMENT: Use the custom deletion style for the warning prompt
        showCustomDeletionDialog("Are you sure you want to CONCLUDE the elections?<br>This action cannot be undone.", () -> {
            try (Connection conn = database.DBConnection.DBConnect(); 
                 PreparedStatement pst = conn.prepareStatement("UPDATE tblElectionStatus SET current_phase = ? WHERE status_id = 1")) {
                
                pst.setString(1, "CLOSED");
                pst.executeUpdate();

                // UI REPLACEMENT: Use custom success message
                showCustomMessage("Election Concluded", "Election has now been concluded.", new java.awt.Color(55, 140, 59));

                this.dispose();
                new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);

            } catch (java.sql.SQLException e) {
                showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
            }
        });
    }//GEN-LAST:event_btnConcludeElectActionPerformed

    private void btnManageCandidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageCandidateActionPerformed
        this.dispose();
        new plpvotingsystem.ManageCandidatesForm(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnManageCandidateActionPerformed

    private void btnManageVoterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageVoterActionPerformed
        this.dispose();
        new plpvotingsystem.ManageStudentsForm(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnManageVoterActionPerformed

    private void btnManagePartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagePartyActionPerformed
        this.dispose();
        new plpvotingsystem.ManagePartylistsForm(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnManagePartyActionPerformed

    private void btnManagePositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagePositionActionPerformed
        this.dispose();
        new plpvotingsystem.ManagePositionsForm(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnManagePositionActionPerformed

    private void btnVerifyIntegrityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyIntegrityActionPerformed
        try (Connection conn = database.DBConnection.DBConnect(); 
             PreparedStatement pst = conn.prepareStatement("SELECT candidate_id, vote_hash FROM tblVotes ORDER BY vote_id ASC")) {
            
            ResultSet rs = pst.executeQuery();
            String previousHash = "GENESIS_BLOCK";
            int rowCount = 0;
            boolean isCorrupted = false;
            int corruptedRow = 0;

            while (rs.next()) {
                rowCount++;
                int candidateId = rs.getInt("candidate_id");
                String storedHash = rs.getString("vote_hash");
                
                String rawData = candidateId + previousHash;
                String expectedHash = SecurityUtil.generateHash(rawData); 

                if (!expectedHash.equals(storedHash)) {
                    isCorrupted = true;
                    corruptedRow = rowCount;
                    break; 
                }
                
                previousHash = storedHash;
            }

            if (isCorrupted) {
                showCustomMessage("Integrity Audit Failed", 
                    "CRITICAL SECURITY ALERT: Database tampering detected!<br><br>The cryptographic chain was broken at Vote #" + corruptedRow + ".<br>An admin has illegally modified the database.", 
                    new java.awt.Color(220, 53, 69));
            } else if (rowCount == 0) {
                 showCustomMessage("Integrity Audit", "The ballot box is currently empty. No votes to verify.", new java.awt.Color(250, 192, 45));
            } else {
                showCustomMessage("Integrity Audit Passed", 
                    "Database Integrity Verified.<br><br>Successfully audited " + rowCount + " votes.<br>The cryptographic chain is completely intact.", 
                    new java.awt.Color(55, 140, 59));
            }

        } catch (Exception e) {
            showCustomMessage("Audit Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
        }
    }//GEN-LAST:event_btnVerifyIntegrityActionPerformed

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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConcludeElect;
    private javax.swing.JButton btnManageCandidate;
    private javax.swing.JButton btnManageParty;
    private javax.swing.JButton btnManagePosition;
    private javax.swing.JButton btnManageVoter;
    private javax.swing.JButton btnVerifyIntegrity;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGreeting;
    // End of variables declaration//GEN-END:variables
}
