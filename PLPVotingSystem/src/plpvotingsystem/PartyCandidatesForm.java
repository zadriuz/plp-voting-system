/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PartyCandidatesForm extends javax.swing.JFrame {
    String currentParty, studentNo;

    public PartyCandidatesForm(String currentParty, String studentNo) {
        initComponents();
        this.currentParty = currentParty;
        this.studentNo = studentNo;
        
        pnlCandidatesList.setLayout(new javax.swing.BoxLayout(pnlCandidatesList, javax.swing.BoxLayout.Y_AXIS));
        
        loadPartyMembers();
    }

    private void loadPartyMembers(){
        
        // The VIP list: Stack all your connections and statements here
        try(
            Connection conn = database.DBConnection.DBConnect();
            PreparedStatement pst1 = conn.prepareStatement("SELECT party_description FROM tblPartylists WHERE party_name = ?");
            PreparedStatement pst2 = conn.prepareStatement("SELECT * FROM tblCandidates WHERE party_list = ?")
        ){
            // Execute Query 1: Get Party Info
            pst1.setString(1, currentParty);
            ResultSet rs1 = pst1.executeQuery();
            
            // Changed to an 'if' because a party only has ONE description
            if(rs1.next()){
                String partyDesc = rs1.getString("party_description");
                lblPartyName.setText(currentParty);
                lblPartyDesc.setText(partyDesc);
            }
            
            // Execute Query 2: Get Candidates
            pst2.setString(1, currentParty);
            ResultSet rs2 = pst2.executeQuery();
            
            while(rs2.next()){
                String fullName = rs2.getString("full_name");
                int positionID = rs2.getInt("position_id");
                String credentials = rs2.getString("credentials");
                String platform = rs2.getString("plataporma");
                
                String currentPosition = "";
                
                if(positionID == 1){
                    currentPosition = "President";
                } else if (positionID == 2){
                    currentPosition = "Vice President";
                } else if (positionID == 3){
                    currentPosition = "Secretary";
                } else if (positionID == 4){
                    currentPosition = "Treasurer";
                } else if (positionID == 5){
                    currentPosition = "Auditor";
                } else if (positionID == 6){
                    currentPosition = "P.R.O.";
                }
                
                CandidateCard myCard = new plpvotingsystem.CandidateCard(fullName, currentParty, credentials, platform);
                JPanel wrapper = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
                wrapper.add(myCard);
                pnlCandidatesList.add(wrapper);
            }
            
            pnlCandidatesList.repaint();
            pnlCandidatesList.revalidate();
            
        }catch(java.sql.SQLException e){
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());        
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPartyName = new javax.swing.JLabel();
        lblPartyDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlCandidatesList = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnVote = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPartyName.setText("Party Name");

        lblPartyDesc.setText("Party Description");

        javax.swing.GroupLayout pnlCandidatesListLayout = new javax.swing.GroupLayout(pnlCandidatesList);
        pnlCandidatesList.setLayout(pnlCandidatesListLayout);
        pnlCandidatesListLayout.setHorizontalGroup(
            pnlCandidatesListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );
        pnlCandidatesListLayout.setVerticalGroup(
            pnlCandidatesListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlCandidatesList);

        btnDashboard.setText("Back to Dashboard");
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        btnVote.setText("Vote Now");
        btnVote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoteActionPerformed(evt);
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
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnDashboard)
                                .addGap(37, 37, 37)
                                .addComponent(btnVote))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPartyName, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPartyDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblPartyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPartyDesc)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDashboard)
                    .addComponent(btnVote))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        this.dispose();
        new plpvotingsystem.StudentDashboard(studentNo).setVisible(true);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnVoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteActionPerformed
        this.dispose();
        new plpvotingsystem.VotingForm(studentNo).setVisible(true);
    }//GEN-LAST:event_btnVoteActionPerformed

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
            java.util.logging.Logger.getLogger(PartyCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PartyCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PartyCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PartyCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PartyCandidatesForm("TestParty", "StudentNo").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnVote;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPartyDesc;
    private javax.swing.JLabel lblPartyName;
    private javax.swing.JPanel pnlCandidatesList;
    // End of variables declaration//GEN-END:variables
}
