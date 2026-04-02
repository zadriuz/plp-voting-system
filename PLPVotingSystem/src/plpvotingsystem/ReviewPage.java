/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ReviewPage extends javax.swing.JFrame {
    String studentNo, presChoice, vpChoice, secChoice, treasChoice, audiChoice, proChoice;
    
    String[] candidateChoices;
    
    public ReviewPage(String studentNumber, String[] candidateChoices) {
        initComponents();
        
        this.candidateChoices = candidateChoices;
        this.studentNo = studentNumber;
        
        this.presChoice = candidateChoices[0];
        this.vpChoice = candidateChoices[1];
        this.secChoice = candidateChoices[2];
        this.treasChoice = candidateChoices[3];
        this.audiChoice = candidateChoices[4];
        this.proChoice = candidateChoices[5];
        
        lblStudentDisplay.setText(this.studentNo);
        lblPresidentDisplay.setText(this.presChoice);
        lblVPresidentDisplay.setText(this.vpChoice);
        lblSecretaryDisplay.setText(this.secChoice);
        lblTreasurerDisplay.setText(this.treasChoice);
        lblAuditorDisplay.setText(this.audiChoice);
        lblPRODisplay.setText(this.proChoice);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStudentDisplay = new javax.swing.JLabel();
        lblPresidentDisplay = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        lblVPresidentDisplay = new javax.swing.JLabel();
        lblSecretaryDisplay = new javax.swing.JLabel();
        lblTreasurerDisplay = new javax.swing.JLabel();
        lblAuditorDisplay = new javax.swing.JLabel();
        lblPRODisplay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblStudentDisplay.setText("Student ID: ");

        lblPresidentDisplay.setText("President: ");

        btnConfirm.setText("Confirm Vote");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        lblVPresidentDisplay.setText("Vice President:");

        lblSecretaryDisplay.setText("Secretary:");

        lblTreasurerDisplay.setText("Treasurer:");

        lblAuditorDisplay.setText("Auditor:");

        lblPRODisplay.setText("PRO:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStudentDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPresidentDisplay)
                            .addComponent(lblVPresidentDisplay)
                            .addComponent(lblSecretaryDisplay)
                            .addComponent(lblTreasurerDisplay)
                            .addComponent(lblAuditorDisplay)
                            .addComponent(lblPRODisplay)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnConfirm)))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblStudentDisplay)
                .addGap(33, 33, 33)
                .addComponent(lblPresidentDisplay)
                .addGap(33, 33, 33)
                .addComponent(lblVPresidentDisplay)
                .addGap(33, 33, 33)
                .addComponent(lblSecretaryDisplay)
                .addGap(33, 33, 33)
                .addComponent(lblTreasurerDisplay)
                .addGap(46, 46, 46)
                .addComponent(lblAuditorDisplay)
                .addGap(41, 41, 41)
                .addComponent(lblPRODisplay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(btnConfirm)
                .addGap(144, 144, 144))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed

        try(
            Connection conn = database.DBConnection.DBConnect();
            PreparedStatement pst1 = conn.prepareStatement("SELECT candidate_id FROM tblCandidates WHERE full_name = ? AND position_id = ?");
            PreparedStatement pst2 = conn.prepareStatement("INSERT INTO tblVotes(candidate_id) VALUES (?)");
            PreparedStatement pst3 = conn.prepareStatement("UPDATE tblVoters SET has_voted = 1 WHERE student_number = ?")
        ){
            int[] selectedIDs = new int[6];
            
            for(int i = 0; i < candidateChoices.length; i++){
                pst1.setString(1, candidateChoices[i]);
                pst1.setInt(2, i + 1);
                ResultSet rs1 = pst1.executeQuery();
                
                if(rs1.next()){
                    selectedIDs[i] = rs1.getInt("candidate_id");
                }
            }
            
            for(int i = 0; i < selectedIDs.length; i++){
                pst2.setInt(1, selectedIDs[i]);
                pst2.addBatch();
            }
            pst2.executeBatch();
            
            pst3.setString(1, studentNo);
            pst3.executeUpdate();
        }catch(java.sql.SQLException e){
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            return;
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(ReviewPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String[] candidateChoices = new String[6];
                new ReviewPage("TestUser", candidateChoices).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel lblAuditorDisplay;
    private javax.swing.JLabel lblPRODisplay;
    private javax.swing.JLabel lblPresidentDisplay;
    private javax.swing.JLabel lblSecretaryDisplay;
    private javax.swing.JLabel lblStudentDisplay;
    private javax.swing.JLabel lblTreasurerDisplay;
    private javax.swing.JLabel lblVPresidentDisplay;
    // End of variables declaration//GEN-END:variables
}
