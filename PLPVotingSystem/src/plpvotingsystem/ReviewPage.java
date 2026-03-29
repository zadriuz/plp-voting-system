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
    String studentNo, presidentChoice;
    
    public ReviewPage(String studentNumber, String candidateChoice) {
        initComponents();
        
        studentNo = studentNumber;
        presidentChoice = candidateChoice;
        
        lblStudentDisplay.setText(studentNo);
        lblPresidentDisplay.setText(presidentChoice);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStudentDisplay = new javax.swing.JLabel();
        lblPresidentDisplay = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblStudentDisplay.setText("Student ID: ");

        lblPresidentDisplay.setText("President: ");

        btnConfirm.setText("Confirm Vote");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

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
                            .addComponent(lblPresidentDisplay)))
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
                .addGap(57, 57, 57)
                .addComponent(btnConfirm)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        Connection conn = database.DBConnection.DBConnect();
        
        try{
            String query1 = "SELECT candidate_id FROM tblCandidates WHERE full_name = ? AND position_id = 1";
            PreparedStatement pst1 = conn.prepareStatement(query1);
            pst1.setString(1, presidentChoice);
            ResultSet rs1 = pst1.executeQuery();
            
            if(rs1.next()){
                int candidateID = rs1.getInt("candidate_id");
                
                String query2 = "INSERT INTO tblVotes(candidate_id)VALUES(?)";
                PreparedStatement pst2 = conn.prepareStatement(query2);
                pst2.setInt(1, candidateID);
                pst2.executeUpdate();
            }
            
            String query3 = "UPDATE tblVoters SET has_voted = 1 WHERE student_number = ?";
            PreparedStatement pst3 = conn.prepareStatement(query3);
            pst3.setString(1, studentNo);
            pst3.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Vote submitted successfully");
            this.dispose();
            new plpvotingsystem.LoginForm().setVisible(true);
            
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
                new ReviewPage("TestUser", "TestPresident").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel lblPresidentDisplay;
    private javax.swing.JLabel lblStudentDisplay;
    // End of variables declaration//GEN-END:variables
}
