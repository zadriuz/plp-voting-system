/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class AddPartylistForm extends javax.swing.JFrame {
    String currentParty = "";

    java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");

    public AddPartylistForm() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setDefaultButton(btnSubmit);
        this.setLocationRelativeTo(null);
        this.setIconImage(plpIcon);
    }

    public AddPartylistForm(String targetParty) {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setDefaultButton(btnSubmit);
        this.setLocationRelativeTo(null);
        this.setIconImage(plpIcon);

        this.currentParty = targetParty;

        lblTitle.setText("Edit Partylist");
        btnSubmit.setText("Update Partylist");
        txtPartyName.setEnabled(false); // Lock the Primary Key!
        txtPartyName.setText(targetParty);

        loadExistingData();
    }

    private void loadExistingData() {
        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM tblPartylists WHERE party_name = ?")) {
            pst.setString(1, this.currentParty);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String partyDesc = rs.getString("party_description");

                txtPartyDescription.setText(partyDesc);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        txtPartyName = new javax.swing.JTextField();
        lblRegister = new javax.swing.JLabel();
        lblRegister1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPartyDescription = new javax.swing.JTextArea();
        btnSubmit = new plpvotingsystem.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Register Partylist");

        lblRegister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRegister.setForeground(new java.awt.Color(0, 0, 0));
        lblRegister.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRegister.setText("Partylist Name:");

        lblRegister1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRegister1.setForeground(new java.awt.Color(0, 0, 0));
        lblRegister1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRegister1.setText("Partylist Description:");

        txtPartyDescription.setColumns(20);
        txtPartyDescription.setRows(5);
        jScrollPane1.setViewportView(txtPartyDescription);

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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPartyName)
                                .addComponent(lblRegister1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblRegister)
                .addGap(5, 5, 5)
                .addComponent(txtPartyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lblRegister1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSubmit)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String partylistName = txtPartyName.getText();
        String partyDesc = txtPartyDescription.getText();

        if (partylistName.isEmpty() || partyDesc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }

        if (currentParty.isEmpty()) {
            try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("INSERT INTO tblPartylists(party_name, party_description)VALUES(?, ?)")) {
                pst.setString(1, partylistName);
                pst.setString(2, partyDesc);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Partylist successfully registered!");

                txtPartyName.setText("");
                txtPartyDescription.setText("");
            } catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            }
        } else {
            try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("UPDATE tblPartylists SET party_description = ? WHERE party_name = ?")){
                pst.setString(1, partyDesc);
                pst.setString(2, txtPartyName.getText());
                
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Successfully updated partylist!");
                this.dispose();
            }catch(java.sql.SQLException e){
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
                return;
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
            java.util.logging.Logger.getLogger(AddPartylistForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPartylistForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPartylistForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPartylistForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPartylistForm("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JLabel lblRegister1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txtPartyDescription;
    private javax.swing.JTextField txtPartyName;
    // End of variables declaration//GEN-END:variables
}
