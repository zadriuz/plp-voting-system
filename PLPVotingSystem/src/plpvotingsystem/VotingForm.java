/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.ButtonGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class VotingForm extends javax.swing.JFrame {
    int currentPositionID = 1;
    ButtonGroup groupCandidates = new ButtonGroup();
    
    String[] candidateChoices = new String[6];
    
    private void loadCandidates(int positionID){
        pnlCandidates.removeAll();
        groupCandidates = new ButtonGroup();
        
        if(positionID == 1){
            lblPositionTitle.setText("President");
        } else if (positionID == 2){
            lblPositionTitle.setText("Vice President");
        } else if (positionID == 3){
            lblPositionTitle.setText("Secretary");
        } else if (positionID == 4){
            lblPositionTitle.setText("Treasurer");
        } else if (positionID == 5){
            lblPositionTitle.setText("Auditor");
        } else if (positionID == 6){
            lblPositionTitle.setText("PRO");
        }
        
        try(
            Connection conn = database.DBConnection.DBConnect();
            PreparedStatement pst = conn.prepareStatement("SELECT full_name, party_list FROM tblCandidates WHERE position_id = ?")   
        ){
            pst.setInt(1, positionID);
            ResultSet rs = pst.executeQuery();
        
            while(rs.next()){
                String fullName = rs.getString("full_name");
                String partyList = rs.getString("party_list");
                
                String candidateDisplay = fullName; // for radio btns setText
                
                if(!fullName.equals("Abstain")){
                    candidateDisplay = fullName + " (" + partyList + ")";
                }
                
                JRadioButton currentCandidate = new JRadioButton(candidateDisplay);
                currentCandidate.setActionCommand(fullName); // give unique identity
                
                if(candidateChoices[currentPositionID - 1] != null && candidateChoices[currentPositionID - 1].equals(fullName)){
                    currentCandidate.setSelected(true);
                }
                
                groupCandidates.add(currentCandidate);
                pnlCandidates.add(currentCandidate);
            }  
                pnlCandidates.repaint();
                pnlCandidates.revalidate();     
        }catch (java.sql.SQLException e){
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }
    
    String loggedInStudent;
    
    public VotingForm(String studentNumber) {
        initComponents();
        pnlCandidates.setLayout(new javax.swing.BoxLayout(pnlCandidates, javax.swing.BoxLayout.Y_AXIS));
        btnBack.setVisible(false);
        
        loggedInStudent = studentNumber;
        loadCandidates(currentPositionID);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNext = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblPositionTitle = new javax.swing.JLabel();
        pnlCandidates = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblPositionTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblPositionTitle.setText("President");

        pnlCandidates.setLayout(new javax.swing.BoxLayout(pnlCandidates, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNext)
                .addGap(126, 126, 126))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPositionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCandidates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 467, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblPositionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCandidates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnBack))
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if(groupCandidates.getSelection() == null){
            JOptionPane.showMessageDialog(this, "Blank " + lblPositionTitle.getText() + " choice");
            return;
        }else{
            String chosenCandidate = groupCandidates.getSelection().getActionCommand();
            candidateChoices[currentPositionID - 1] = chosenCandidate;
        }
        
        if(currentPositionID < 6){ 
            currentPositionID++;
            if(currentPositionID > 1){
                btnBack.setVisible(true);
            }
            loadCandidates(currentPositionID);
        } else{
            this.dispose();
            new plpvotingsystem.ReviewPage(loggedInStudent, candidateChoices).setVisible(true);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        currentPositionID--;
        if(currentPositionID == 1){
            btnBack.setVisible(false);
        }
        loadCandidates(currentPositionID);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(VotingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VotingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VotingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VotingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VotingForm("TestUser").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNext;
    private javax.swing.JLabel lblPositionTitle;
    private javax.swing.JPanel pnlCandidates;
    // End of variables declaration//GEN-END:variables
}
