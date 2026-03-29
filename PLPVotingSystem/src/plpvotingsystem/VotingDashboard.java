/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.JOptionPane;

public class VotingDashboard extends javax.swing.JFrame {
    String loggedInStudent;
    
    public VotingDashboard(String studentNumber) {
        initComponents();
        loggedInStudent = studentNumber;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupPres = new javax.swing.ButtonGroup();
        btnGroupVP = new javax.swing.ButtonGroup();
        btnGroupSec = new javax.swing.ButtonGroup();
        btnGroupTreasurer = new javax.swing.ButtonGroup();
        btnGroupAuditor = new javax.swing.ButtonGroup();
        btnGroupPRO = new javax.swing.ButtonGroup();
        lblPresidentDisplay = new javax.swing.JLabel();
        btnCandidateA = new javax.swing.JRadioButton();
        btnCandidateB = new javax.swing.JRadioButton();
        btnSubmit = new javax.swing.JButton();
        btnAbstain_P = new javax.swing.JRadioButton();
        btnCandidateC = new javax.swing.JRadioButton();
        lblVPDisplay = new javax.swing.JLabel();
        btnCandidateD = new javax.swing.JRadioButton();
        btnAbstain_VP = new javax.swing.JRadioButton();
        lblSecretaryDisplay = new javax.swing.JLabel();
        btnCandidateE = new javax.swing.JRadioButton();
        btnCandidateF = new javax.swing.JRadioButton();
        btnAbstain_Sec = new javax.swing.JRadioButton();
        lblTreasurerDisplay = new javax.swing.JLabel();
        lblAuditorDisplay = new javax.swing.JLabel();
        lblPRODisplay = new javax.swing.JLabel();
        btnCandidateA1 = new javax.swing.JRadioButton();
        btnCandidateB1 = new javax.swing.JRadioButton();
        btnAbstain_P1 = new javax.swing.JRadioButton();
        btnCandidateA2 = new javax.swing.JRadioButton();
        btnCandidateB2 = new javax.swing.JRadioButton();
        btnAbstain_P2 = new javax.swing.JRadioButton();
        btnAbstain_P3 = new javax.swing.JRadioButton();
        btnCandidateA3 = new javax.swing.JRadioButton();
        btnCandidateB3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPresidentDisplay.setText("President");
        lblPresidentDisplay.setToolTipText("");

        btnGroupPres.add(btnCandidateA);
        btnCandidateA.setText("Candidate A");

        btnGroupPres.add(btnCandidateB);
        btnCandidateB.setText("Candidate B");

        btnSubmit.setText("Submit Vote");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnGroupPres.add(btnAbstain_P);
        btnAbstain_P.setText("Abstain");
        btnAbstain_P.setToolTipText("Choose this if you wish to withhold your vote for this position.");

        btnGroupVP.add(btnCandidateC);
        btnCandidateC.setText("Candidate C");

        lblVPDisplay.setText("Vice President");
        lblVPDisplay.setToolTipText("");

        btnGroupVP.add(btnCandidateD);
        btnCandidateD.setText("Candidate D");

        btnGroupVP.add(btnAbstain_VP);
        btnAbstain_VP.setText("Abstain");
        btnAbstain_VP.setToolTipText("Choose this if you wish to withhold your vote for this position.");

        lblSecretaryDisplay.setText("Secretary");
        lblSecretaryDisplay.setToolTipText("");

        btnGroupSec.add(btnCandidateE);
        btnCandidateE.setText("Candidate E");

        btnGroupSec.add(btnCandidateF);
        btnCandidateF.setText("Candidate F");

        btnGroupSec.add(btnAbstain_Sec);
        btnAbstain_Sec.setText("Abstain");
        btnAbstain_Sec.setToolTipText("Choose this if you wish to withhold your vote for this position.");

        lblTreasurerDisplay.setText("Treasurer");
        lblTreasurerDisplay.setToolTipText("");

        lblAuditorDisplay.setText("Auditor");
        lblAuditorDisplay.setToolTipText("");

        lblPRODisplay.setText("P.R.O.");
        lblPRODisplay.setToolTipText("");

        btnGroupPres.add(btnCandidateA1);
        btnCandidateA1.setText("Candidate A");

        btnGroupPres.add(btnCandidateB1);
        btnCandidateB1.setText("Candidate B");

        btnGroupPres.add(btnAbstain_P1);
        btnAbstain_P1.setText("Abstain");
        btnAbstain_P1.setToolTipText("Choose this if you wish to withhold your vote for this position.");

        btnGroupPres.add(btnCandidateA2);
        btnCandidateA2.setText("Candidate A");

        btnGroupPres.add(btnCandidateB2);
        btnCandidateB2.setText("Candidate B");

        btnGroupPres.add(btnAbstain_P2);
        btnAbstain_P2.setText("Abstain");
        btnAbstain_P2.setToolTipText("Choose this if you wish to withhold your vote for this position.");

        btnGroupPres.add(btnAbstain_P3);
        btnAbstain_P3.setText("Abstain");
        btnAbstain_P3.setToolTipText("Choose this if you wish to withhold your vote for this position.");

        btnGroupPres.add(btnCandidateA3);
        btnCandidateA3.setText("Candidate A");

        btnGroupPres.add(btnCandidateB3);
        btnCandidateB3.setText("Candidate B");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblPresidentDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCandidateB, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCandidateA, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbstain_P, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCandidateC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCandidateD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbstain_VP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCandidateE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCandidateF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbstain_Sec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSecretaryDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVPDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(211, 211, 211)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAuditorDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTreasurerDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPRODisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCandidateB1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCandidateA1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbstain_P1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCandidateB2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCandidateA2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbstain_P2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCandidateB3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCandidateA3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbstain_P3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(240, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSubmit)
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPresidentDisplay)
                            .addComponent(lblTreasurerDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCandidateA)
                                .addGap(18, 18, 18)
                                .addComponent(btnCandidateB)
                                .addGap(18, 18, 18)
                                .addComponent(btnAbstain_P))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCandidateA1)
                                .addGap(18, 18, 18)
                                .addComponent(btnCandidateB1)
                                .addGap(18, 18, 18)
                                .addComponent(btnAbstain_P1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVPDisplay)
                            .addComponent(lblAuditorDisplay))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCandidateC)
                                .addGap(18, 18, 18)
                                .addComponent(btnCandidateD)
                                .addGap(18, 18, 18)
                                .addComponent(btnAbstain_VP))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCandidateA2)
                                .addGap(18, 18, 18)
                                .addComponent(btnCandidateB2)
                                .addGap(18, 18, 18)
                                .addComponent(btnAbstain_P2)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSecretaryDisplay)
                            .addComponent(lblPRODisplay))
                        .addGap(27, 27, 27)
                        .addComponent(btnCandidateE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCandidateF)
                        .addGap(18, 18, 18)
                        .addComponent(btnAbstain_Sec))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCandidateA3)
                        .addGap(18, 18, 18)
                        .addComponent(btnCandidateB3)
                        .addGap(18, 18, 18)
                        .addComponent(btnAbstain_P3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(btnSubmit)
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String presidentChoice = "";
        
        if(btnCandidateA.isSelected()){
            presidentChoice = btnCandidateA.getText();
        } else if(btnCandidateB.isSelected()){
            presidentChoice = btnCandidateB.getText();
        } else if(btnAbstain_P.isSelected()){
            presidentChoice = btnAbstain_P.getText();
        } else{
            JOptionPane.showMessageDialog(this, "Blank President Choice");
            return;
        }
        
        String vpChoice = "";
        
        if(btnCandidateC.isSelected()){
            vpChoice = btnCandidateC.getText();
        } else if(btnCandidateD.isSelected()){
            vpChoice = btnCandidateD.getText();
        } else if(btnAbstain_VP.isSelected()){
            vpChoice = "Abstain";
        } else{
            JOptionPane.showMessageDialog(this, "Blank Vice President Choice");
            return;
        }
        
        String secretaryChoice = "";
        
        if(btnCandidateE.isSelected()){
            secretaryChoice = btnCandidateE.getText();
        } else if(btnCandidateF.isSelected()){
            secretaryChoice = btnCandidateF.getText();
        } else if(btnAbstain_Sec.isSelected()){
            secretaryChoice = "Abstain";
        } else{
            JOptionPane.showMessageDialog(this, "Blank Secretary Choice");
            return;
        }
       
        this.dispose();
        new plpvotingsystem.ReviewPage(loggedInStudent, presidentChoice, vpChoice, secretaryChoice).setVisible(true);
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
            java.util.logging.Logger.getLogger(VotingDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VotingDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VotingDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VotingDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VotingDashboard("TestUser").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnAbstain_P;
    private javax.swing.JRadioButton btnAbstain_P1;
    private javax.swing.JRadioButton btnAbstain_P2;
    private javax.swing.JRadioButton btnAbstain_P3;
    private javax.swing.JRadioButton btnAbstain_Sec;
    private javax.swing.JRadioButton btnAbstain_VP;
    private javax.swing.JRadioButton btnCandidateA;
    private javax.swing.JRadioButton btnCandidateA1;
    private javax.swing.JRadioButton btnCandidateA2;
    private javax.swing.JRadioButton btnCandidateA3;
    private javax.swing.JRadioButton btnCandidateB;
    private javax.swing.JRadioButton btnCandidateB1;
    private javax.swing.JRadioButton btnCandidateB2;
    private javax.swing.JRadioButton btnCandidateB3;
    private javax.swing.JRadioButton btnCandidateC;
    private javax.swing.JRadioButton btnCandidateD;
    private javax.swing.JRadioButton btnCandidateE;
    private javax.swing.JRadioButton btnCandidateF;
    private javax.swing.ButtonGroup btnGroupAuditor;
    private javax.swing.ButtonGroup btnGroupPRO;
    private javax.swing.ButtonGroup btnGroupPres;
    private javax.swing.ButtonGroup btnGroupSec;
    private javax.swing.ButtonGroup btnGroupTreasurer;
    private javax.swing.ButtonGroup btnGroupVP;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblAuditorDisplay;
    private javax.swing.JLabel lblPRODisplay;
    private javax.swing.JLabel lblPresidentDisplay;
    private javax.swing.JLabel lblSecretaryDisplay;
    private javax.swing.JLabel lblTreasurerDisplay;
    private javax.swing.JLabel lblVPDisplay;
    // End of variables declaration//GEN-END:variables
}
