/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PartyCandidatesForm extends javax.swing.JFrame {

    String currentParty, studentNo;
    int hasVoted = 0;

    public PartyCandidatesForm(String currentParty, String studentNo, int hasVoted) {
        initComponents();
        this.currentParty = currentParty;
        this.studentNo = studentNo;
        this.hasVoted = hasVoted;

        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);

        pnlCandidatesList.setLayout(new javax.swing.BoxLayout(pnlCandidatesList, javax.swing.BoxLayout.Y_AXIS));

        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        this.getContentPane().setLayout(new java.awt.BorderLayout());
        this.getContentPane().removeAll();
        plpvotingsystem.GlobalHeader header = new plpvotingsystem.GlobalHeader();
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 20, 100));

        header.setPreferredSize(new java.awt.Dimension(1000, 110));
        this.getContentPane().add(header, java.awt.BorderLayout.NORTH);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));
        pnlMain.setBackground(new java.awt.Color(245, 245, 245));
        pnlMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 80, 30, 80));

        // 1. Style the Title and Description
        lblPartyName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 32));
        lblPartyDesc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        lblPartyDesc.setForeground(java.awt.Color.DARK_GRAY);

// 2. Turn the Back Button into a clean, clickable text link
        btnDashboard.setText("← Back to Dashboard");
        btnDashboard.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 17));
        btnDashboard.setForeground(new java.awt.Color(34, 139, 34)); // Forest Green
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDashboard.setBorder(null);

// 3. Clean up the Scroll Pane
        jScrollPane1.setBorder(null); // Removes the ugly default border
        jScrollPane1.getViewport().setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling

        jScrollPane1.getVerticalScrollBar().setUI(new plpvotingsystem.ModernScrollBarUI());

        pnlCandidatesList.setBackground(new java.awt.Color(245, 245, 245));
        pnlCandidatesList.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 15));

        btnDashboard.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        lblPartyName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        lblPartyDesc.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        jScrollPane1.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // 1. Create a transparent wrapper for the title row
        JPanel titleRow = new JPanel();
        titleRow.setLayout(new java.awt.BorderLayout());
        titleRow.setOpaque(false);
        titleRow.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 50));

// 2. Anchor the title to the Left
        titleRow.add(lblPartyName, java.awt.BorderLayout.WEST);

// 3. Build a brand new, custom Vote button (Ignoring the NetBeans one)
        plpvotingsystem.RoundedButton customBtnVote = new plpvotingsystem.RoundedButton("Vote Now");
        customBtnVote.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        customBtnVote.setBackground(new java.awt.Color(250, 192, 45));
        customBtnVote.setForeground(java.awt.Color.BLACK);
        
        // Grab the icon and apply it to both states!
        ImageIcon voteIcon = new ImageIcon("SystemIcons\\voteIcon.png");
        customBtnVote.setIcon(voteIcon);
        customBtnVote.setDisabledIcon(voteIcon); // <-- THE FIX

        customBtnVote.setIconTextGap(20);
        customBtnVote.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        customBtnVote.setPreferredSize(new java.awt.Dimension(200, 45));
        customBtnVote.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        customBtnVote.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        if (this.hasVoted == 1) {
            customBtnVote.setEnabled(false);
            customBtnVote.setBackground(java.awt.Color.GRAY);
            customBtnVote.setText("Already Voted");
        }

// Wire up the click action
        customBtnVote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PartyCandidatesForm.this.dispose();
                new plpvotingsystem.VotingForm(studentNo).setVisible(true);
            }
        });

// 4. Anchor the button to the Right
        titleRow.add(customBtnVote, java.awt.BorderLayout.EAST);

// 5. Force every single layer to strictly align to the left edge
        btnDashboard.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        titleRow.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        lblPartyDesc.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        jScrollPane1.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

// 6. Assemble the main panel (WITH the scroll pane this time!)
        pnlMain.add(btnDashboard);
        pnlMain.add(javax.swing.Box.createVerticalStrut(20));
        pnlMain.add(titleRow);
        pnlMain.add(javax.swing.Box.createVerticalStrut(10));
        pnlMain.add(lblPartyDesc);
        pnlMain.add(javax.swing.Box.createVerticalStrut(30)); // Breathing room
        pnlMain.add(jScrollPane1); // <-- The missing container!

        this.getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        loadPartyMembers();

        // Force the scroll pane back to the absolute top after everything loads
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jScrollPane1.getVerticalScrollBar().setValue(0);
            }
        });
    }

    private void loadPartyMembers() {

        // The VIP list: Stack all your connections and statements here
        try (
                Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst1 = conn.prepareStatement("SELECT party_description FROM tblPartylists WHERE party_name = ?"); PreparedStatement pst2 = conn.prepareStatement("SELECT * FROM tblCandidates WHERE party_list = ?")) {
            // Execute Query 1: Get Party Info
            pst1.setString(1, currentParty);
            ResultSet rs1 = pst1.executeQuery();

            // Changed to an 'if' because a party only has ONE description
            if (rs1.next()) {
                String partyDesc = rs1.getString("party_description");
                lblPartyName.setText(currentParty);
                lblPartyDesc.setText(partyDesc);
            }

            // Execute Query 2: Get Candidates
            pst2.setString(1, currentParty);
            ResultSet rs2 = pst2.executeQuery();

            while (rs2.next()) {
                String fullName = rs2.getString("full_name");
                int positionID = rs2.getInt("position_id");
                String credentials = rs2.getString("credentials");
                String platform = rs2.getString("plataporma");
                String imgPath = rs2.getString("image_path");

                String currentPosition = "";

                if (positionID == 1) {
                    currentPosition = "President";
                } else if (positionID == 2) {
                    currentPosition = "Vice President";
                } else if (positionID == 3) {
                    currentPosition = "Secretary";
                } else if (positionID == 4) {
                    currentPosition = "Treasurer";
                } else if (positionID == 5) {
                    currentPosition = "Auditor";
                } else if (positionID == 6) {
                    currentPosition = "P.R.O.";
                }

                // 1. Create the card (Notice I updated currentParty to currentPosition so your green pill actually works!)
                CandidateCard myCard = new plpvotingsystem.CandidateCard(fullName, currentPosition, credentials, platform, imgPath);

// 2. Force it to stretch wide instead of shrinking
                myCard.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
                myCard.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

// 3. Add directly to the list with a 20-pixel gap below it
                pnlCandidatesList.add(myCard);
                pnlCandidatesList.add(javax.swing.Box.createVerticalStrut(20));
            }

            pnlCandidatesList.repaint();
            pnlCandidatesList.revalidate();

        } catch (java.sql.SQLException e) {
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
        setUndecorated(true);
        setResizable(false);

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
        new plpvotingsystem.StudentDashboard(studentNo, hasVoted).setVisible(true);
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
                new PartyCandidatesForm("TestParty", "StudentNo", 0).setVisible(true);
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
