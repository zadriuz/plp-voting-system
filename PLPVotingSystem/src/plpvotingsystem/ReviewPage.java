/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReviewPage extends javax.swing.JFrame {

    String studentNo, presChoice, vpChoice, secChoice, treasChoice, audiChoice, proChoice;

    String[] candidateChoices;

    public ReviewPage(String studentNumber, String[] candidateChoices) {
        initComponents();

        this.getContentPane().removeAll();
        this.setLayout(new java.awt.BorderLayout());
        
        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);

        this.studentNo = studentNumber;
        this.candidateChoices = candidateChoices;

        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        plpvotingsystem.GlobalHeader header = new plpvotingsystem.GlobalHeader();
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 20, 100));
        header.setPreferredSize(new java.awt.Dimension(1000,110));
        this.getContentPane().add(header, java.awt.BorderLayout.NORTH);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));
        pnlMain.setBackground(new java.awt.Color(245, 245, 245));
        pnlMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 100, 30, 100));

        plpvotingsystem.RoundedPanel pnlWhiteCard = new plpvotingsystem.RoundedPanel();
        pnlWhiteCard.setLayout(new javax.swing.BoxLayout(pnlWhiteCard, javax.swing.BoxLayout.Y_AXIS));
        pnlWhiteCard.setOpaque(false);
        pnlWhiteCard.setBackground(Color.WHITE);
        pnlWhiteCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 50, 40, 50));
        pnlWhiteCard.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel lblReviewVote = new JLabel("Review Your Votes");
        lblReviewVote.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 36));
        JLabel lblReviewSelections = new JLabel("Please review your selections...");
        lblReviewSelections.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblReviewSelections.setForeground(Color.GRAY);

        lblReviewVote.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        lblReviewSelections.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        pnlWhiteCard.add(lblReviewVote);
        pnlWhiteCard.add(javax.swing.Box.createVerticalStrut(20));
        pnlWhiteCard.add(lblReviewSelections);

        String[] positions = {"President", "Vice President", "Secretary", "Treasurer", "Auditor", "PRO"};

        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT party_list FROM tblCandidates WHERE full_name = ? AND position_id = ?")) {

            for (int i = 0; i < 6; i++) {
                String cName = candidateChoices[i];
                String cParty = "No Vote Cast";

                if (cName != null && !cName.equals("Abstain")) {
                    pst.setString(1, cName);
                    pst.setInt(2, i + 1);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        cParty = rs.getString("party_list");
                    }
                } else if (cName == null) {
                    cName = "Abstain";
                }

                // Instantiate your fixed card!
                plpvotingsystem.ReviewCard rCard = new plpvotingsystem.ReviewCard(positions[i], cName, cParty);
                rCard.setPreferredSize(new java.awt.Dimension(800,85));
                rCard.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 85));
                rCard.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

                pnlWhiteCard.add(rCard);
                pnlWhiteCard.add(javax.swing.Box.createVerticalStrut(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1. Setup the Box Layout
        plpvotingsystem.RoundedPanel pnlNotice = new plpvotingsystem.RoundedPanel();
        pnlNotice.setLayout(new java.awt.BorderLayout(15, 0)); // 15px gap between icon and text
        pnlNotice.setBackground(new java.awt.Color(235, 245, 255));

        // 2. Fix the Border (Make thickness 2, not 15!)
        pnlNotice.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 210, 255), 2, true),
                javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // 3. Add the Icon (WEST)
        JLabel lblNoticeIcon = new JLabel(new javax.swing.ImageIcon("SystemIcons\\warningIcon.png"));
        pnlNotice.add(lblNoticeIcon, java.awt.BorderLayout.WEST);

        // 4. Build the Text Stack (CENTER)
        JPanel pnlNoticeText = new JPanel();
        pnlNoticeText.setLayout(new javax.swing.BoxLayout(pnlNoticeText, javax.swing.BoxLayout.Y_AXIS));
        pnlNoticeText.setOpaque(false);

        JLabel lblNoticeTitle = new JLabel("Important Notice");
        lblNoticeTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblNoticeTitle.setForeground(new java.awt.Color(0, 50, 150)); // Dark Blue

        JLabel warningText = new JLabel("Once you submit your vote, you cannot change it. Please review your selections carefully.");
        warningText.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        warningText.setForeground(Color.BLUE);

        pnlNoticeText.add(lblNoticeTitle);
        pnlNoticeText.add(warningText);

        // 5. Assemble
        pnlNotice.add(pnlNoticeText, java.awt.BorderLayout.CENTER);
        
        pnlNotice.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        
        pnlWhiteCard.add(pnlNotice);
        
        pnlWhiteCard.add(javax.swing.Box.createVerticalStrut(25));

        JPanel transpoPanel = new JPanel();
        transpoPanel.setLayout(new java.awt.FlowLayout(FlowLayout.RIGHT));
        transpoPanel.setOpaque(false);

        transpoPanel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        
        plpvotingsystem.RoundedButton btnEdit = new plpvotingsystem.RoundedButton();
        btnEdit.setText("Edit Votes");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setBackground(new java.awt.Color(220, 220, 220)); // Solid Light Gray
        btnEdit.setForeground(Color.BLACK);
        btnEdit.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Remove the buggy line border

        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReviewPage.this.dispose();
                plpvotingsystem.VotingForm form = new plpvotingsystem.VotingForm(studentNo);
                form.restoreChoices(candidateChoices); // Give it the memory!
                form.setVisible(true);
            }
        });

        plpvotingsystem.RoundedButton customBtnConfirm = new plpvotingsystem.RoundedButton();
        customBtnConfirm.setText("Submit Vote");
        customBtnConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customBtnConfirm.setBackground(new java.awt.Color(250, 192, 45));
        customBtnConfirm.setForeground(Color.BLACK);
        btnConfirm.hide();

        customBtnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirm.doClick(); // Now it waits for the user!
            }
        });

        btnEdit.setPreferredSize(new java.awt.Dimension(120, 40));
        btnEdit.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        customBtnConfirm.setPreferredSize(new java.awt.Dimension(120, 40));
        customBtnConfirm.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        transpoPanel.add(btnEdit);
        transpoPanel.add(customBtnConfirm);

        pnlWhiteCard.add(transpoPanel);

// Wrap the massive white card in a scroll pane...
        // Wrap the massive white card in a scroll pane
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(pnlWhiteCard);
        scrollPane.setBorder(null);
        
        scrollPane.setBackground(new java.awt.Color(245,245,245));
        scrollPane.getViewport().setBackground(new java.awt.Color(245,245,245));
        
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Remove default ugly border

        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        scrollPane.getVerticalScrollBar().setUI(new plpvotingsystem.ModernScrollBarUI()); // Use our sleek custom UI!

// Add the scroll pane to the main screen instead of the raw card
        pnlMain.add(scrollPane);

        this.getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);
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
        setUndecorated(true);
        setResizable(false);

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

        try (
                Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst1 = conn.prepareStatement("SELECT candidate_id FROM tblCandidates WHERE full_name = ? AND position_id = ?"); PreparedStatement pst2 = conn.prepareStatement("INSERT INTO tblVotes(candidate_id) VALUES (?)"); PreparedStatement pst3 = conn.prepareStatement("UPDATE tblVoters SET has_voted = 1 WHERE student_number = ?")) {
            int[] selectedIDs = new int[6];

            for (int i = 0; i < candidateChoices.length; i++) {
                pst1.setString(1, candidateChoices[i]);
                pst1.setInt(2, i + 1);
                ResultSet rs1 = pst1.executeQuery();

                if (rs1.next()) {
                    selectedIDs[i] = rs1.getInt("candidate_id");
                }
            }

            for (int i = 0; i < selectedIDs.length; i++) {
                pst2.setInt(1, selectedIDs[i]);
                pst2.addBatch();
            }
            pst2.executeBatch();

            pst3.setString(1, studentNo);
            pst3.executeUpdate();
            // Tell the user it worked!
            JOptionPane.showMessageDialog(this, "Your vote has been successfully submitted! Thank you for voting.");
            this.dispose();
            new plpvotingsystem.LoginForm().setVisible(true);
        } catch (java.sql.SQLException e) {
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
