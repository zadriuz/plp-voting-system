/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

/**
 *
 * @author PLP
 */
public class StudentDashboard extends javax.swing.JFrame {
    String loggedInStudent;
    public StudentDashboard(String studentNo) {
        initComponents();
        
        pnlMainContent.setLayout(new javax.swing.BoxLayout(pnlMainContent, javax.swing.BoxLayout.Y_AXIS));
        pnlGraphs.setLayout(new javax.swing.BoxLayout(pnlGraphs, javax.swing.BoxLayout.Y_AXIS));
        pnlParties.setLayout(new javax.swing.BoxLayout(pnlParties, javax.swing.BoxLayout.Y_AXIS));
        
        loadLiveResults();
        loadCandidateDirectory();
        
        this.loggedInStudent = studentNo;
        lblGreeting.setText("Hello, Student " + this.loggedInStudent);
    }

    private void loadLiveResults(){
        try (
            Connection conn = database.DBConnection.DBConnect();
            PreparedStatement pst = conn.prepareStatement("SELECT full_name, COUNT(tblVotes.candidate_id) AS total_votes, tblCandidates.position_id FROM tblCandidates LEFT JOIN tblVotes ON tblCandidates.candidate_id = tblVotes.candidate_id WHERE tblCandidates.full_name != 'Abstain' GROUP BY tblCandidates.candidate_id ORDER BY tblCandidates.position_id ASC, total_votes DESC")
        ){
            ResultSet rs = pst.executeQuery();
        
            int currentCategory = 0;
            
            while(rs.next()){
                String fullName = rs.getString("full_name");
                int totalVotes = rs.getInt("total_votes");
                int positionID = rs.getInt("position_id");
                
                if(positionID != currentCategory){
                    String positionTitle = "";
                    if(positionID == 1){
                        positionTitle = "President";
                    } else if (positionID == 2){
                        positionTitle = "Vice President";
                    } else if (positionID == 3){
                        positionTitle = "Secretary";
                    } else if (positionID == 4){
                        positionTitle = "Treasurer";
                    } else if (positionID == 5){
                        positionTitle = "Auditor";
                    } else if (positionID == 6){
                        positionTitle = "P.R.O";
                    }
                    JLabel positionLabel = new JLabel(positionTitle);
                    positionLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    pnlGraphs.add(positionLabel);
                    currentCategory = positionID;
                }

                JLabel candidateVotes = new JLabel(fullName + " - " + totalVotes + " votes");

                JProgressBar voteBar = new JProgressBar(0, 100);
                voteBar.setValue(totalVotes);
                voteBar.setStringPainted(true);

                pnlGraphs.add(candidateVotes);
                pnlGraphs.add(voteBar);
            }
            pnlGraphs.repaint();
            pnlGraphs.revalidate();
        }catch(java.sql.SQLException e){
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }
    }
    
    private void loadCandidateDirectory(){
        try(
            Connection conn = database.DBConnection.DBConnect();
            PreparedStatement pst = conn.prepareStatement("SELECT party_name, party_description FROM tblPartylists")
        ){
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String partyName = rs.getString("party_name");
                String partyDesc = rs.getString("party_description");
                
                JLabel partyListInfo = new JLabel("<html><b>" + partyName + "</b><br>" + partyDesc + "</html>");
                
                JButton viewCandidates = new JButton("View Candidates");
                viewCandidates.addActionListener(
                    new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        StudentDashboard.this.dispose();
                        new plpvotingsystem.PartyCandidatesForm(partyName, loggedInStudent).setVisible(true);
                    }
                    }
                );
                
                /*
                    addActionListener is an object of JButton, it expects an ActionListener() object.
                    java.awt.event.ActionListener() demands an actionPerformed method (built-in method) with parameter java.awt.event.ActionEvent
                
                */
                pnlParties.add(partyListInfo);
                pnlParties.add(viewCandidates);
                pnlParties.add(javax.swing.Box.createVerticalStrut(15));
            }
            pnlParties.repaint();
            pnlParties.revalidate();
        }catch(java.sql.SQLException e){
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnlMainContent = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        lblGreeting1 = new javax.swing.JLabel();
        pnlGraphs = new javax.swing.JPanel();
        lblGreeting2 = new javax.swing.JLabel();
        pnlParties = new javax.swing.JPanel();
        btnVoteNow = new javax.swing.JButton();
        globalHeader2 = new plpvotingsystem.GlobalHeader();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblGreeting.setText("Hello, Student");

        lblGreeting1.setText("Live Election Standings");

        javax.swing.GroupLayout pnlGraphsLayout = new javax.swing.GroupLayout(pnlGraphs);
        pnlGraphs.setLayout(pnlGraphsLayout);
        pnlGraphsLayout.setHorizontalGroup(
            pnlGraphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );
        pnlGraphsLayout.setVerticalGroup(
            pnlGraphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblGreeting2.setText("GET TO KNOW THE CANDIDATES");

        javax.swing.GroupLayout pnlPartiesLayout = new javax.swing.GroupLayout(pnlParties);
        pnlParties.setLayout(pnlPartiesLayout);
        pnlPartiesLayout.setHorizontalGroup(
            pnlPartiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPartiesLayout.setVerticalGroup(
            pnlPartiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnVoteNow.setText("VOTE NOW");
        btnVoteNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoteNowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainContentLayout = new javax.swing.GroupLayout(pnlMainContent);
        pnlMainContent.setLayout(pnlMainContentLayout);
        pnlMainContentLayout.setHorizontalGroup(
            pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGreeting2)
                    .addComponent(lblGreeting1)
                    .addComponent(pnlGraphs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlParties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(btnVoteNow)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        pnlMainContentLayout.setVerticalGroup(
            pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblGreeting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGreeting1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlGraphs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGreeting2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlParties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoteNow)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(pnlMainContent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoteNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteNowActionPerformed
        this.dispose();
        new plpvotingsystem.VotingForm(loggedInStudent).setVisible(true);
    }//GEN-LAST:event_btnVoteNowActionPerformed

           
    
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
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDashboard("TestStudent").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoteNow;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblGreeting1;
    private javax.swing.JLabel lblGreeting2;
    private javax.swing.JPanel pnlGraphs;
    private javax.swing.JPanel pnlMainContent;
    private javax.swing.JPanel pnlParties;
    // End of variables declaration//GEN-END:variables
}
