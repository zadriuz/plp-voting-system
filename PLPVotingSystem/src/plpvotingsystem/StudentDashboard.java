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
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author PLP
 */
public class StudentDashboard extends javax.swing.JFrame {

    String loggedInStudent;

    public StudentDashboard(String studentNo) {
        initComponents();

        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);

        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        pnlMainContent.setLayout(new javax.swing.BoxLayout(pnlMainContent, javax.swing.BoxLayout.Y_AXIS));
        pnlGraphs.setLayout(new java.awt.GridLayout(1, 2, 50, 0));

        // 1. Configure the raw panel to lay items out horizontally from left to right
        pnlParties.setLayout(new java.awt.GridLayout(0, 3, 20, 20));
        pnlParties.setBackground(new java.awt.Color(245, 245, 245));

        lblGreeting1.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        lblGreeting1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        btnVoteNow.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnVoteNow.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        btnVoteNow.setPreferredSize(new java.awt.Dimension(200, 45));
        btnVoteNow.setMaximumSize(new java.awt.Dimension(200, 45));
        btnVoteNow.setIcon(plpvotingsystem.ImageUtil.getSharpIcon("SystemIcons\\voteIcon.png", 24, 24));
        btnVoteNow.setIconTextGap(20);

        pnlMainContent.remove(lblGreeting);
        pnlMainContent.remove(btnVoteNow);

        JPanel greetingWrapper = new JPanel();
        greetingWrapper.setLayout(new java.awt.BorderLayout());
        greetingWrapper.setBackground(new java.awt.Color(245, 245, 245));

        greetingWrapper.add(lblGreeting, java.awt.BorderLayout.WEST);
        greetingWrapper.add(btnVoteNow, java.awt.BorderLayout.EAST);

        pnlMainContent.add(greetingWrapper, 0);

        // Add the Live Standings Title
        pnlMainContent.add(lblGreeting1);

        // Add the Graphs Panel
        pnlMainContent.add(pnlGraphs);

        // Add the Parties Title
        pnlMainContent.add(lblGreeting2);

        // Add the SCROLL PANE (which holds pnlParties inside it)
        pnlMainContent.add(pnlParties);

        lblGreeting2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        lblGreeting2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 0, 10, 0));

        loadLiveResults();
        loadCandidateDirectory();

        this.loggedInStudent = studentNo;
        lblGreeting.setText("Hello, Student " + this.loggedInStudent);

        // --- THE MASTER WRAPPER ---
        // 1. Yank the main content out of the frame
        this.getContentPane().remove(pnlMainContent);
        
        // 2. THE ANTI-BLEED SHIELD: A local class that implements Scrollable to lock the width!
        class ScrollablePanel extends JPanel implements javax.swing.Scrollable {
            public ScrollablePanel() { setLayout(new java.awt.BorderLayout()); }
            public java.awt.Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
            public int getScrollableUnitIncrement(java.awt.Rectangle r, int o, int d) { return 16; }
            public int getScrollableBlockIncrement(java.awt.Rectangle r, int o, int d) { return 16; }
            public boolean getScrollableTracksViewportWidth() { return true; } // The magic lock!
            public boolean getScrollableTracksViewportHeight() { return false; } // Let it scroll vertically!
        }
        
        ScrollablePanel antiBleedPanel = new ScrollablePanel();
        antiBleedPanel.setBackground(new java.awt.Color(245, 245, 245));
        antiBleedPanel.add(pnlMainContent, java.awt.BorderLayout.CENTER);

        // 3. Wrap the shielded panel in the scroll pane
        javax.swing.JScrollPane scpMain = new javax.swing.JScrollPane(antiBleedPanel);
        scpMain.setBorder(null);
        scpMain.getViewport().setBackground(new java.awt.Color(245, 245, 245));

        // 4. Apply your custom UI
        scpMain.getVerticalScrollBar().setUI(new plpvotingsystem.ModernScrollBarUI());
        scpMain.getVerticalScrollBar().setUnitIncrement(16);

        // 5. Enforce scrolling rules
        scpMain.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scpMain.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // 6. Put the scroll pane back into the frame!
        this.getContentPane().add(scpMain, java.awt.BorderLayout.CENTER);
    }

    private void loadLiveResults() {
        try (
                Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT c.full_name, c.party_list, COUNT(v.candidate_id) AS total_votes, c.position_id, t.pos_total FROM tblCandidates c LEFT JOIN tblVotes v ON c.candidate_id = v.candidate_id JOIN (SELECT c2.position_id, COUNT(v2.candidate_id) as pos_total FROM tblCandidates c2 LEFT JOIN tblVotes v2 ON c2.candidate_id = v2.candidate_id WHERE c2.full_name != 'Abstain' GROUP BY c2.position_id) t ON c.position_id = t.position_id WHERE c.full_name != 'Abstain' GROUP BY c.candidate_id, c.full_name, c.party_list, c.position_id, t.pos_total ORDER BY c.position_id ASC, total_votes DESC")) {
            ResultSet rs = pst.executeQuery();

            int currentCategory = 0;
            JPanel leftCol = new JPanel();
            JPanel rightCol = new JPanel();
            leftCol.setLayout(new javax.swing.BoxLayout(leftCol, javax.swing.BoxLayout.Y_AXIS));
            rightCol.setLayout(new javax.swing.BoxLayout(rightCol, javax.swing.BoxLayout.Y_AXIS));

            leftCol.setBackground(new java.awt.Color(245, 245, 245));
            rightCol.setBackground(new java.awt.Color(245, 245, 245));

            pnlGraphs.add(leftCol);
            pnlGraphs.add(rightCol);

            while (rs.next()) {
                String fullName = rs.getString("full_name");
                String partyList = rs.getString("party_list"); // 1. Grab the party
                int totalVotes = rs.getInt("total_votes");
                int positionID = rs.getInt("position_id");
                int positionTotal = rs.getInt("pos_total");

                if (positionID != currentCategory) {
                    String positionTitle = "";
                    if (positionID == 1) {
                        positionTitle = "President";
                    } else if (positionID == 2) {
                        positionTitle = "Vice President";
                    } else if (positionID == 3) {
                        positionTitle = "Secretary";
                    } else if (positionID == 4) {
                        positionTitle = "Treasurer";
                    } else if (positionID == 5) {
                        positionTitle = "Auditor";
                    } else if (positionID == 6) {
                        positionTitle = "P.R.O";
                    }

                    JLabel positionLabel = new JLabel(positionTitle);
                    positionLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

                    if (positionID <= 3) {
                        if (positionID != 1) { // Prevents gap at the very top of the Left column
                            leftCol.add(javax.swing.Box.createVerticalStrut(15));
                        }
                        leftCol.add(positionLabel);
                    } else {
                        if (positionID != 4) { // Prevents gap at the very top of the Right column
                            rightCol.add(javax.swing.Box.createVerticalStrut(25));
                        }
                        rightCol.add(positionLabel);
                    }

                    currentCategory = positionID;
                }

                JLabel candidateVotes = new JLabel(fullName + " (" + partyList + ") - " + totalVotes + " votes");
                candidateVotes.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 25));

                javax.swing.UIManager.put("ProgressBar.selectionBackground", java.awt.Color.BLACK);
                javax.swing.UIManager.put("ProgressBar.selectionForeground", java.awt.Color.BLACK);

                JProgressBar voteBar = new JProgressBar();

                if (positionTotal == 0) {
                    voteBar.setMaximum(1);
                } else {
                    voteBar.setMaximum(positionTotal);
                }

                voteBar.setValue(totalVotes);
                voteBar.setStringPainted(true);
                voteBar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
                voteBar.setForeground(new java.awt.Color(250, 192, 45));
                voteBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
                voteBar.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 25));

                if (positionID <= 3) {
                    leftCol.add(candidateVotes);
                    leftCol.add(javax.swing.Box.createVerticalStrut(2));
                    leftCol.add(voteBar);
                    leftCol.add(javax.swing.Box.createVerticalStrut(8));
                } else {
                    rightCol.add(candidateVotes);
                    rightCol.add(javax.swing.Box.createVerticalStrut(2));
                    rightCol.add(voteBar);
                    rightCol.add(javax.swing.Box.createVerticalStrut(8));
                }
            }
            pnlGraphs.repaint();
            pnlGraphs.revalidate();
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }
    }

    private void loadCandidateDirectory() {
        try (
                Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT party_name, party_description FROM tblPartylists ORDER BY CASE WHEN party_name = 'Independent' THEN 1 ELSE 0 END, party_name ASC")) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String partyName = rs.getString("party_name");
                String partyDesc = rs.getString("party_description");

                JLabel partyListInfo = new JLabel("<html><b>" + partyName + "</b><br>" + partyDesc + "</html>");

                plpvotingsystem.RoundedButton viewCandidates = new plpvotingsystem.RoundedButton("View Candidates");
                viewCandidates.setIcon(plpvotingsystem.ImageUtil.getSharpIcon("SystemIcons\\rightArrow.png", 20, 20));
                viewCandidates.setBackground(new java.awt.Color(250, 192, 45));
                viewCandidates.setForeground(java.awt.Color.BLACK);
                viewCandidates.setHorizontalAlignment(SwingConstants.CENTER);
                viewCandidates.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 40));
                viewCandidates.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                viewCandidates.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
                viewCandidates.setPreferredSize(new java.awt.Dimension(300, 40));
                viewCandidates.setIconTextGap(10);
                viewCandidates.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                viewCandidates.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

                viewCandidates.addActionListener(
                        new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        StudentDashboard.this.dispose();
                        new plpvotingsystem.PartyCandidatesForm(partyName, loggedInStudent).setVisible(true);
                    }
                }
                );

                /*
                    addActionListener is an object of JButton, it expects an ActionListener() object.
                    java.awt.event.ActionListener() demands an actionPerformed method (built-in method) with parameter java.awt.event.ActionEvent
                
                 */
                javax.swing.ImageIcon partyIcon = null;

                if (partyName.equalsIgnoreCase("Independent")) {
                    partyIcon = plpvotingsystem.ImageUtil.getSharpIcon("SystemIcons\\independentIcon.png", 40, 40);
                } else {
                    partyIcon = plpvotingsystem.ImageUtil.getSharpIcon("SystemIcons\\partylistIcon.png", 40, 40);
                }

                JLabel lblIcon = new JLabel(partyIcon);

                plpvotingsystem.RoundedPanel partyCard = new plpvotingsystem.RoundedPanel();
                JPanel contentWrapper = new JPanel();
                contentWrapper.setLayout(new javax.swing.BoxLayout(contentWrapper, javax.swing.BoxLayout.Y_AXIS));

                contentWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
                contentWrapper.setOpaque(false);

                partyCard.setLayout(new javax.swing.BoxLayout(partyCard, javax.swing.BoxLayout.Y_AXIS));

                JPanel topRow = new JPanel();
                topRow.setLayout(new javax.swing.BoxLayout(topRow, javax.swing.BoxLayout.X_AXIS));
                topRow.setOpaque(false);

                JPanel textWrapper = new JPanel();
                textWrapper.setLayout(new javax.swing.BoxLayout(textWrapper, javax.swing.BoxLayout.Y_AXIS));
                textWrapper.setOpaque(false);

                textWrapper.add(partyListInfo);

                topRow.add(lblIcon);
                topRow.add(javax.swing.Box.createHorizontalStrut(15));
                topRow.add(textWrapper);

                partyCard.setBackground(java.awt.Color.WHITE);

                contentWrapper.add(topRow);
                contentWrapper.add(javax.swing.Box.createVerticalStrut(20));
                contentWrapper.add(viewCandidates);

                partyCard.add(contentWrapper);

                pnlParties.add(partyCard);

            }
            pnlParties.repaint();
            pnlParties.revalidate();
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalHeader2 = new plpvotingsystem.GlobalHeader();
        pnlMainContent = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        lblGreeting1 = new javax.swing.JLabel();
        pnlGraphs = new javax.swing.JPanel();
        lblGreeting2 = new javax.swing.JLabel();
        pnlParties = new javax.swing.JPanel();
        btnVoteNow = new plpvotingsystem.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 20, 100));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));
        getContentPane().add(globalHeader2, java.awt.BorderLayout.NORTH);

        pnlMainContent.setBackground(new java.awt.Color(245, 245, 245));
        pnlMainContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 80, 30, 80));
        pnlMainContent.setLayout(new javax.swing.BoxLayout(pnlMainContent, javax.swing.BoxLayout.LINE_AXIS));

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblGreeting.setText("Hello, Student");
        pnlMainContent.add(lblGreeting);

        lblGreeting1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGreeting1.setText("Live Election Standings");
        pnlMainContent.add(lblGreeting1);

        pnlGraphs.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout pnlGraphsLayout = new javax.swing.GroupLayout(pnlGraphs);
        pnlGraphs.setLayout(pnlGraphsLayout);
        pnlGraphsLayout.setHorizontalGroup(
            pnlGraphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );
        pnlGraphsLayout.setVerticalGroup(
            pnlGraphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        pnlMainContent.add(pnlGraphs);

        lblGreeting2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGreeting2.setText("Get to Know the Candidates");
        pnlMainContent.add(lblGreeting2);

        pnlParties.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout pnlPartiesLayout = new javax.swing.GroupLayout(pnlParties);
        pnlParties.setLayout(pnlPartiesLayout);
        pnlPartiesLayout.setHorizontalGroup(
            pnlPartiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPartiesLayout.setVerticalGroup(
            pnlPartiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        pnlMainContent.add(pnlParties);

        btnVoteNow.setBackground(new java.awt.Color(250, 192, 45));
        btnVoteNow.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVoteNow.setForeground(new java.awt.Color(0, 0, 0));
        btnVoteNow.setText("Vote Now");
        btnVoteNow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoteNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoteNowActionPerformed(evt);
            }
        });
        pnlMainContent.add(btnVoteNow);

        getContentPane().add(pnlMainContent, java.awt.BorderLayout.CENTER);

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
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblGreeting1;
    private javax.swing.JLabel lblGreeting2;
    private javax.swing.JPanel pnlGraphs;
    private javax.swing.JPanel pnlMainContent;
    private javax.swing.JPanel pnlParties;
    // End of variables declaration//GEN-END:variables
}
