/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddPositionForm extends javax.swing.JFrame {
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
        btnOk.setDialogButton(true); 
        btnOk.addActionListener(e -> msgDialog.dispose());

        btnPanel.add(btnOk);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(0, 0, 20, 0); 
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        pnlContent.add(lblMessage, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        pnlContent.add(btnPanel, gbc);

        msgDialog.add(pnlContent);

        msgDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent e) {
                btnOk.requestFocusInWindow();
            }
        });

        msgDialog.setVisible(true);
    }

    int currentPositionId = -1; // -1 means we are ADDING a new position
     java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");

    public AddPositionForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        lblTitle.setText("Register Position");;
        
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));
        this.setIconImage(plpIcon);
    }

    // Constructor 2: Used when EDITING an existing position
    public AddPositionForm(int posId, String posName, int maxPos) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        lblTitle.setText("Edit Position");
        btnSubmit.setText("Update");
        txtPosName.setEnabled(false); // Do not let them change the name, only max votes! (explained below)

        this.currentPositionId = posId;
        txtPosName.setText(posName);
        spnMaxPos.setValue(maxPos);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblPositionName = new javax.swing.JLabel();
        txtPosName = new javax.swing.JTextField();
        lblMaxPos = new javax.swing.JLabel();
        btnSubmit = new plpvotingsystem.RoundedButton();
        spnMaxPos = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register Position");
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Register Position");

        lblPositionName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPositionName.setForeground(new java.awt.Color(0, 0, 0));
        lblPositionName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPositionName.setText("Position Name:");

        lblMaxPos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaxPos.setForeground(new java.awt.Color(0, 0, 0));
        lblMaxPos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaxPos.setText("Max Positions");

        btnSubmit.setBackground(new java.awt.Color(0, 255, 102));
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(0, 0, 0));
        btnSubmit.setText("Save");
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(lblPositionName, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMaxPos)
                                .addGap(18, 18, 18)
                                .addComponent(spnMaxPos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPosName))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblPositionName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPosName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxPos)
                    .addComponent(spnMaxPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSubmit)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String positionName = txtPosName.getText().trim();
        int maxVotes = (int) spnMaxPos.getValue();

        if (positionName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a position name.");
            return;
        }

        if (maxVotes < 1) {
            JOptionPane.showMessageDialog(this, "Max votes must be at least 1.");
            return;
        }

        if (this.currentPositionId == -1) {
            // --- ADD MODE ---
            try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("INSERT INTO tblPositions (position_name, max_votes) VALUES (?, ?)")) {

                pst.setString(1, positionName);
                pst.setInt(2, maxVotes);
                pst.executeUpdate();

                showCustomMessage("Success", "Successfully registered position!", new java.awt.Color(55, 140, 59));
                txtPosName.setText("");
                spnMaxPos.setValue(1); // Reset spinner

            } catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            }
        } else {
            // --- EDIT MODE ---
            try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("UPDATE tblPositions SET max_votes = ? WHERE position_id = ?")) {

                pst.setInt(1, maxVotes);
                pst.setInt(2, this.currentPositionId);
                pst.executeUpdate();

                showCustomMessage("Success", "Successfully updated maximum votes!", new java.awt.Color(55, 140, 59));
                this.dispose();

            } catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(AddPositionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPositionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPositionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPositionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPositionForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblMaxPos;
    private javax.swing.JLabel lblPositionName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JSpinner spnMaxPos;
    private javax.swing.JTextField txtPosName;
    // End of variables declaration//GEN-END:variables
}
