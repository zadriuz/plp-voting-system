/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PLP
 */
public class ManageCandidatesForm extends javax.swing.JFrame {
    
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

    int candidateID;
    java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");

    String currentAdmin = "";

    public ManageCandidatesForm(String adminName) {
        initComponents();

        this.setLocationRelativeTo(null);

        this.setIconImage(plpIcon);

        this.currentAdmin = adminName;

        this.getContentPane().setBackground(new java.awt.Color(245, 245, 245));

        loadCandidateTable();
    }

    private void loadCandidateTable() {
        javax.swing.table.DefaultTableModel model = (DefaultTableModel) tblCandidateData.getModel();
        model.setRowCount(0);

        try (
                Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT candidate_id, full_name, party_list, position_id, credentials, plataporma FROM tblCandidates WHERE full_name != 'Abstain' ORDER BY position_id ASC")) {

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                this.candidateID = rs.getInt("candidate_id");
                String fullName = rs.getString("full_name");
                String partyList = rs.getString("party_list");
                int positionID = rs.getInt("position_id");
                String credentials = rs.getString("credentials");
                String plataporma = rs.getString("plataporma");

                Object[] candidateInformation = new Object[6];
                candidateInformation[0] = candidateID;
                candidateInformation[1] = fullName;
                candidateInformation[2] = partyList;
                candidateInformation[3] = positionID;
                candidateInformation[4] = credentials;
                candidateInformation[5] = plataporma;

                model.addRow(candidateInformation);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCandidateData = new javax.swing.JTable();
        btnAddCandidate = new plpvotingsystem.RoundedButton();
        globalHeader2 = new plpvotingsystem.GlobalHeader();
        btnEditCandidate = new plpvotingsystem.RoundedButton();
        btnDeleteCandidate = new plpvotingsystem.RoundedButton();
        btnRefresh = new plpvotingsystem.RoundedButton();
        jLabel2 = new javax.swing.JLabel();
        btnBack2Dashboard = new plpvotingsystem.RoundedButton();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblFilterBy = new javax.swing.JLabel();
        cmbFilterBy = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Candidates");
        setResizable(false);

        tblCandidateData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Candidate ID", "Full Name", "Party List", "Position ID", "Credentials", "Platform"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCandidateData);

        btnAddCandidate.setBackground(new java.awt.Color(0, 255, 102));
        btnAddCandidate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddCandidate.setForeground(new java.awt.Color(0, 0, 0));
        btnAddCandidate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/addUser.png"))); // NOI18N
        btnAddCandidate.setText("Register Candidate");
        btnAddCandidate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCandidate.setIconTextGap(10);
        btnAddCandidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCandidateActionPerformed(evt);
            }
        });

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 60, 20, 60));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));

        btnEditCandidate.setBackground(new java.awt.Color(250, 192, 45));
        btnEditCandidate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditCandidate.setForeground(new java.awt.Color(0, 0, 0));
        btnEditCandidate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/editUser.png"))); // NOI18N
        btnEditCandidate.setText("Edit Candidate");
        btnEditCandidate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditCandidate.setIconTextGap(10);
        btnEditCandidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCandidateActionPerformed(evt);
            }
        });

        btnDeleteCandidate.setBackground(new java.awt.Color(255, 102, 102));
        btnDeleteCandidate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteCandidate.setForeground(new java.awt.Color(0, 0, 0));
        btnDeleteCandidate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/removeUser.png"))); // NOI18N
        btnDeleteCandidate.setText("Remove Candidate");
        btnDeleteCandidate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCandidate.setIconTextGap(10);
        btnDeleteCandidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCandidateActionPerformed(evt);
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actions");

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

        lblSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(0, 0, 0));
        lblSearch.setText("Search:");

        txtSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblFilterBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFilterBy.setForeground(new java.awt.Color(0, 0, 0));
        lblFilterBy.setText("Filter By:");

        cmbFilterBy.setBackground(new java.awt.Color(255, 255, 255));
        cmbFilterBy.setForeground(new java.awt.Color(0, 0, 0));
        cmbFilterBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Party List", "Position ID" }));
        cmbFilterBy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbFilterBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterByActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblSearch)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFilterBy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFilterBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddCandidate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditCandidate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack2Dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteCandidate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch)
                    .addComponent(lblFilterBy)
                    .addComponent(cmbFilterBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddCandidate)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditCandidate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteCandidate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack2Dashboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadCandidateTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDeleteCandidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCandidateActionPerformed
        int[] selectedRows = tblCandidateData.getSelectedRows();

        if (selectedRows.length == 0) {
            showCustomMessage("Selection Error", "Select a candidate first to remove by clicking on their cell.", new java.awt.Color(220, 53, 69));
            return;
        }

        showCustomDeletionDialog("Are you sure you want to remove the " + selectedRows.length + " selected candidate(s)?", () -> {
            try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("DELETE FROM tblCandidates WHERE candidate_id = ?")) {

                for (int i = 0; i < selectedRows.length; i++) {
                    int modelRow = tblCandidateData.convertRowIndexToModel(selectedRows[i]);
                    String data = tblCandidateData.getModel().getValueAt(modelRow, 0).toString();
                    pst.setInt(1, Integer.parseInt(data));
                    pst.addBatch();
                }

                pst.executeBatch();
                showCustomMessage("Success", "Successfully deleted " + selectedRows.length + " record(s).", new java.awt.Color(55, 140, 59));
                loadCandidateTable();

            } catch (java.sql.SQLException e) {
                showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
            }
        });
    }//GEN-LAST:event_btnDeleteCandidateActionPerformed

    private void btnEditCandidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCandidateActionPerformed
        int selectedRow = tblCandidateData.getSelectedRow();

        if (selectedRow == -1) {
            showCustomMessage("Selection Error", "Select a candidate first to edit by clicking on their cell.", new java.awt.Color(220, 53, 69));
            return;
        } 
        
        int modelRow = tblCandidateData.convertRowIndexToModel(selectedRow);
        String data = tblCandidateData.getModel().getValueAt(modelRow, 0).toString();
        int selectedID = Integer.parseInt(data);

        plpvotingsystem.AddCandidateForm editForm = new plpvotingsystem.AddCandidateForm(selectedID);
        editForm.setLocationRelativeTo(null);
        editForm.setIconImage(plpIcon);

        editForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadCandidateTable();
            }
        });

        editForm.setVisible(true);
    }//GEN-LAST:event_btnEditCandidateActionPerformed

    private void btnAddCandidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCandidateActionPerformed
        // 1. Trigger the Chameleon Form in "Create" mode (using the blank constructor)
        plpvotingsystem.AddCandidateForm addForm = new plpvotingsystem.AddCandidateForm();

        // 2. Attach a spy to wait for the form to close
        addForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // 3. Refresh the grid the second they finish adding the candidate
                loadCandidateTable();
            }
        });

        // 4. Show the form to the admin
        addForm.setVisible(true);
    }//GEN-LAST:event_btnAddCandidateActionPerformed

    private void btnBack2DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2DashboardActionPerformed
        this.dispose();
        new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnBack2DashboardActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String searchText = txtSearch.getText();
        String filterBy = cmbFilterBy.getSelectedItem().toString();

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblCandidateData.getModel();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> trs = new javax.swing.table.TableRowSorter<>(model);
        tblCandidateData.setRowSorter(trs);

        if (searchText.trim().length() == 0) {
            trs.setRowFilter(null);
        } else {
            // Check the combobox for Candidates!
            if (filterBy.equals("Party List")) {
                trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + searchText, 2)); // Column 2
            } else if (filterBy.equals("Position ID")) {
                trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + searchText, 3)); // Column 3
            } else {
                // "None" selected - search everything! (Omit the column index parameter)
                trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + searchText));
            }
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cmbFilterByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterByActionPerformed
        txtSearchKeyReleased(null);
    }//GEN-LAST:event_cmbFilterByActionPerformed

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
            java.util.logging.Logger.getLogger(ManageCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageCandidatesForm("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCandidate;
    private javax.swing.JButton btnBack2Dashboard;
    private javax.swing.JButton btnDeleteCandidate;
    private javax.swing.JButton btnEditCandidate;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cmbFilterBy;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFilterBy;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblCandidateData;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
