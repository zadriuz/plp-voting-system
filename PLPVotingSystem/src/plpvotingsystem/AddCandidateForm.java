/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PLP
 */
public class AddCandidateForm extends javax.swing.JFrame {

    int currentCandidateID = 0;

    public AddCandidateForm() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setDefaultButton(btnRegister);
        loadPartyLists();
        
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));

        this.setLocationRelativeTo(null);
        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);
    }

    public AddCandidateForm(int candidateID) {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        loadPartyLists();
        this.getRootPane().setDefaultButton(btnRegister);
        this.currentCandidateID = candidateID;
        lblTitle.setText("Edit Candidate");
        btnRegister.setText("Update");

        loadExistingData();
    }

    private void loadExistingData() {
        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM tblCandidates WHERE candidate_id = ?")) {
            pst.setInt(1, this.currentCandidateID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("full_name");
                String partyList = rs.getString("party_list");
                int positionID = rs.getInt("position_id");
                String credentials = rs.getString("credentials");
                String platform = rs.getString("plataporma");
                String imagePath = rs.getString("image_path");

                String position = "";

                switch (positionID) {
                    case 1:
                        position = "President";
                        break;
                    case 2:
                        position = "Vice President";
                        break;
                    case 3:
                        position = "Secretary";
                        break;
                    case 4:
                        position = "Treasurer";
                        break;
                    case 5:
                        position = "Auditor";
                        break;
                    case 6:
                        position = "P.R.O.";
                        break;
                }

                txtFullName.setText(fullName);
                cmbCandidatePosition.setSelectedItem(position);
                cmbParty.setSelectedItem(partyList);
                lblPath.setText(imagePath);

                txtPlataporma.setText(platform);
                txtCredentials.setText(credentials);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFullName = new javax.swing.JTextField();
        cmbParty = new javax.swing.JComboBox<>();
        btnChooseImg = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        lblCandidateName = new javax.swing.JLabel();
        lblCandidatePosition = new javax.swing.JLabel();
        lblCandidateParty = new javax.swing.JLabel();
        lblCandidatePic = new javax.swing.JLabel();
        cmbCandidatePosition = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPlataporma = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCredentials = new javax.swing.JTextArea();
        lblCandidateParty1 = new javax.swing.JLabel();
        lblCandidateParty2 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        cmbParty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "United States Alliance", "" }));

        btnChooseImg.setText("Choose Image");
        btnChooseImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImgActionPerformed(evt);
            }
        });

        lblPath.setText("Path:");

        btnRegister.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        lblCandidateName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCandidateName.setText("Candidate's Full Name:");

        lblCandidatePosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCandidatePosition.setText("Candidate's Selected Position:");

        lblCandidateParty.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCandidateParty.setText("Candidate's Party:");

        lblCandidatePic.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCandidatePic.setText("Candidate's Picture:");

        cmbCandidatePosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "President", "Vice President", "Secretary", "Treasurer", "Auditor", "P.R.O." }));

        txtPlataporma.setColumns(20);
        txtPlataporma.setRows(5);
        jScrollPane1.setViewportView(txtPlataporma);

        txtCredentials.setColumns(20);
        txtCredentials.setRows(5);
        jScrollPane2.setViewportView(txtCredentials);

        lblCandidateParty1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCandidateParty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateParty1.setText("Candidate's Credentials");

        lblCandidateParty2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCandidateParty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCandidateParty2.setText("Candidate's Platform");

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Register Candidate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblCandidatePosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblCandidateName, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cmbCandidatePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(168, 168, 168)))
                            .addComponent(cmbParty, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCandidatePic)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnChooseImg))
                            .addComponent(lblPath, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(lblCandidateParty2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCandidateParty1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCandidateParty, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCandidateParty2)
                    .addComponent(lblCandidateParty1)
                    .addComponent(lblCandidateName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCandidatePosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCandidatePosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCandidateParty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCandidatePic)
                            .addComponent(btnChooseImg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPath))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegister)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImgActionPerformed
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        // Force them to only pick images!
        javax.swing.filechooser.FileNameExtensionFilter filter = new javax.swing.filechooser.FileNameExtensionFilter("Images (PNG, JPG, JPEG)", "png", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File sourceFile = fileChooser.getSelectedFile();

            // 1. Create the secure vault directory inside your project folder
            java.io.File destFolder = new java.io.File("imgCandidates");
            if (!destFolder.exists()) {
                destFolder.mkdir(); // Builds the folder if it doesn't exist yet!
            }

            // 2. Define exactly where the new file will live
            java.io.File destFile = new java.io.File(destFolder, sourceFile.getName());

            // 3. Copy the file from their PC into your project folder
            try {
                java.nio.file.Files.copy(sourceFile.toPath(), destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                // 4. Save ONLY the relative path to the label so it goes safely to the database!
                lblPath.setText("imgCandidates\\" + sourceFile.getName());

            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to copy image into system: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnChooseImgActionPerformed

    private void loadPartyLists() {
        cmbParty.removeAllItems();

        try (
                Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst1 = conn.prepareStatement("SELECT party_name FROM tblPartylists ORDER BY CASE WHEN party_name = 'Independent' THEN 1 ELSE 0 END, party_name ASC");) {
            ResultSet rs1 = pst1.executeQuery();

            while (rs1.next()) {
                String partylistName = rs1.getString("party_name");
                cmbParty.addItem(partylistName);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }
    }

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String fullName = txtFullName.getText().trim();
        String party = cmbParty.getSelectedItem().toString();
        String positionName = cmbCandidatePosition.getSelectedItem().toString();
        String imagePath = lblPath.getText();
        String plataporma = txtPlataporma.getText().trim();
        String credentials = txtCredentials.getText().trim();

        // 1. Validation Bouncer (Removed the strict image check!)
        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a candidate name.", "Missing Info", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- THE DEFAULT IMAGE FALLBACK ---
        // If they didn't pick an image, default it to the independent icon
        if (imagePath.equals("Path:") || imagePath.trim().isEmpty()) {
            imagePath = "SystemIcons\\independentIcon.png";
        }

        // 2. Convert the dropdown text into the correct position_id (1 to 6)
        // 2. Convert the dropdown directly to the position_id (Index 0 = President(1), Index 5 = P.R.O.(6))
        int positionId = cmbCandidatePosition.getSelectedIndex() + 1;

        // 3. Database Insertion
        if (currentCandidateID == 0) {
            try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("INSERT INTO tblCandidates (full_name, position_id, party_list, image_path, plataporma, credentials) VALUES (?, ?, ?, ?, ?, ?)")) {

                pst.setString(1, fullName);
                pst.setInt(2, positionId);
                pst.setString(3, party);
                // Replace single backslashes with double backslashes so Java/SQL doesn't crash reading the path
                pst.setString(4, imagePath);
                pst.setString(5, plataporma);
                pst.setString(6, credentials);

                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Candidate Successfully Added!");
                    this.dispose(); // Closes the Edit popup so they return to the Manager table!

                // Reset the form for the next candidate
                txtFullName.setText("");
                txtPlataporma.setText("");
                txtCredentials.setText("");
                lblPath.setText("Path:");

            } catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            }
        } else{
            try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("UPDATE tblCandidates SET full_name = ?, position_id = ?, party_list = ?, image_path = ?, plataporma = ?, credentials = ? WHERE candidate_id = ?")){
                pst.setString(1, fullName);
                pst.setInt(2, positionId);
                pst.setString(3, party);
                pst.setString(4, imagePath);
                pst.setString(5, plataporma);
                pst.setString(6, credentials);
                pst.setInt(7, this.currentCandidateID);
                
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Candidate Successfully Updated!");
                    this.dispose(); // Closes the Edit popup so they return to the Manager table!
                
            } catch(java.sql.SQLException e){
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

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
            java.util.logging.Logger.getLogger(AddCandidateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCandidateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCandidateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCandidateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCandidateForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseImg;
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox<String> cmbCandidatePosition;
    private javax.swing.JComboBox<String> cmbParty;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCandidateName;
    private javax.swing.JLabel lblCandidateParty;
    private javax.swing.JLabel lblCandidateParty1;
    private javax.swing.JLabel lblCandidateParty2;
    private javax.swing.JLabel lblCandidatePic;
    private javax.swing.JLabel lblCandidatePosition;
    private javax.swing.JLabel lblPath;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txtCredentials;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextArea txtPlataporma;
    // End of variables declaration//GEN-END:variables
}
