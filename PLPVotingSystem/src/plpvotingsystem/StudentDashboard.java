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

    int hasVoted;

    public StudentDashboard(String studentNo, int hasVoted) {
        initComponents();

        // FIX: Assign these IMMEDIATELY so the rest of the code can use them!
        this.loggedInStudent = studentNo;
        this.hasVoted = hasVoted;

        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);

        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        pnlMainContent.setLayout(new javax.swing.BoxLayout(pnlMainContent, javax.swing.BoxLayout.Y_AXIS));

        // 1. Configure the raw panel to lay items out horizontally from left to right
        pnlParties.setLayout(new java.awt.GridLayout(0, 3, 20, 20));
        pnlParties.setBackground(new java.awt.Color(245, 245, 245));

        // SEPARATE VOTE BUTTON BY ESTABLISHING A CONNECTION
        // Create the icon ONCE
        javax.swing.Icon voteIcon = plpvotingsystem.ImageUtil.getSharpIcon("SystemIcons\\voteIcon.png", 24, 24);
        btnVoteNow.setIcon(voteIcon);
        btnVoteNow.setDisabledIcon(voteIcon); // <-- THE FIX: Tell Java to keep using the icon even when locked!

        if (hasVoted == 1) {
            btnVoteNow.setEnabled(false);
            btnVoteNow.setBackground(java.awt.Color.GRAY);
            btnVoteNow.setText("Already Voted");
        } else {
            btnVoteNow.setEnabled(true);
            btnVoteNow.setBackground(new java.awt.Color(250, 192, 45));
        }

        btnVoteNow.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnVoteNow.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        btnVoteNow.setPreferredSize(new java.awt.Dimension(200, 45));
        btnVoteNow.setMaximumSize(new java.awt.Dimension(200, 45));

        btnVoteNow.setIconTextGap(20);

        pnlMainContent.remove(lblGreeting);
        pnlMainContent.remove(btnVoteNow);
        
        // --- KILL THE GHOST NETBEANS COMPONENTS ---
        try {
            pnlMainContent.remove(lblTurnoutTitle);
            pnlMainContent.remove(barTurnout);
            pnlMainContent.remove(lblTurnoutStats);
        } catch(Exception e) { /* Ignored if already gone */ }

        JPanel greetingWrapper = new JPanel();
        greetingWrapper.setLayout(new java.awt.BorderLayout());
        greetingWrapper.setBackground(new java.awt.Color(245, 245, 245));

        greetingWrapper.add(lblGreeting, java.awt.BorderLayout.WEST);
        greetingWrapper.add(btnVoteNow, java.awt.BorderLayout.EAST);

        pnlMainContent.add(greetingWrapper, 0);
        pnlMainContent.add(javax.swing.Box.createVerticalStrut(25)); 

        // --- THE 2-COLUMN GRID ---
        JPanel topGrid = new JPanel(new java.awt.GridLayout(1, 2, 40, 0));
        topGrid.setOpaque(false);
        topGrid.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 350)); // Made taller for the extra text

        // ==========================================
        // LEFT COLUMN: PASSPORT & GUIDELINES
        // ==========================================
        JPanel leftCol = new JPanel();
        leftCol.setLayout(new javax.swing.BoxLayout(leftCol, javax.swing.BoxLayout.Y_AXIS));
        leftCol.setOpaque(false);

        plpvotingsystem.RoundedPanel pnlPassport = new plpvotingsystem.RoundedPanel();
        pnlPassport.setBackground(java.awt.Color.WHITE);
        pnlPassport.setLayout(new java.awt.BorderLayout(40, 0)); 
        pnlPassport.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));
        
        JLabel lblPassIcon = new JLabel(plpvotingsystem.ImageUtil.getSharpIcon("SystemIcons\\manageVoter.png", 50, 50));
        pnlPassport.add(lblPassIcon, java.awt.BorderLayout.WEST);
        
        JLabel lblPassportData = new JLabel("Fetching database records..."); 
        lblPassportData.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16)); 
        pnlPassport.add(lblPassportData, java.awt.BorderLayout.CENTER);
        
        leftCol.add(pnlPassport);
        leftCol.add(javax.swing.Box.createVerticalStrut(20)); 

        // 2. The Voting Guidelines (Added more rules!)
        // 2. The Voting Guidelines (Expanded!)
        plpvotingsystem.RoundedPanel pnlGuidelines = new plpvotingsystem.RoundedPanel();
        pnlGuidelines.setBackground(new java.awt.Color(235, 245, 255));
        pnlGuidelines.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25));
        pnlGuidelines.setLayout(new java.awt.BorderLayout());
        
        JLabel lblGuideText = new JLabel("<html><b>Voting Guidelines:</b><br><br>"
                + "1. Review candidate platforms carefully before proceeding.<br><br>"
                + "2. Verify your Voter Profile details above before casting your vote.<br><br>"
                + "3. You may abstain from voting for specific positions.<br><br>"
                + "4. You may only submit your ballot once. No revisions allowed.<br><br>"
                + "5. Do not close the application while your ballot is processing.</html>");
        lblGuideText.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14)); 
        pnlGuidelines.add(lblGuideText, java.awt.BorderLayout.CENTER);
        
        leftCol.add(pnlGuidelines);

        // ==========================================
        // RIGHT COLUMN: CIRCULAR TURNOUT TRACKER 
        // ==========================================
        plpvotingsystem.RoundedPanel rightCol = new plpvotingsystem.RoundedPanel();
        rightCol.setBackground(java.awt.Color.WHITE); 
        rightCol.setLayout(new java.awt.BorderLayout());
        rightCol.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblTurnoutTitle2 = new JLabel("Live Campus Turnout", SwingConstants.CENTER);
        lblTurnoutTitle2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblTurnoutTitle2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Pushes the circle down
        rightCol.add(lblTurnoutTitle2, java.awt.BorderLayout.NORTH);
        
        CircularTurnoutBar circularBar = new CircularTurnoutBar();
        rightCol.add(circularBar, java.awt.BorderLayout.CENTER);

        // NEW: Added the description beneath the circle!
        JLabel lblTurnoutDesc = new JLabel("<html><div style='text-align: center;'>Voting concludes when the election period expires<br>or 100% voter turnout is achieved.</div></html>", SwingConstants.CENTER);
        lblTurnoutDesc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 13));
        lblTurnoutDesc.setForeground(java.awt.Color.GRAY);
        rightCol.add(lblTurnoutDesc, java.awt.BorderLayout.SOUTH);

        // --- ASSEMBLE THE GRID ---
        topGrid.add(leftCol);
        topGrid.add(rightCol);
        pnlMainContent.add(topGrid);
        pnlMainContent.add(javax.swing.Box.createVerticalStrut(10)); 

        // Add Candidates section
        lblGreeting2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        lblGreeting2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 0, 15, 0));
        pnlMainContent.add(lblGreeting2);
        pnlMainContent.add(pnlParties);
        pnlMainContent.add(javax.swing.Box.createVerticalStrut(20));

        // --- LOAD THE DATA ---
        loadStudentPassportData(lblPassportData);
        loadTurnoutStats(circularBar);
        loadCandidateDirectory();

       
        lblGreeting.setText("Hello, Student " + this.loggedInStudent);

        // --- THE MASTER WRAPPER ---
        // 1. Yank the main content out of the frame
        this.getContentPane().remove(pnlMainContent);

        // 2. THE ANTI-BLEED SHIELD: A local class that implements Scrollable to lock the width!
        class ScrollablePanel extends JPanel implements javax.swing.Scrollable {

            public ScrollablePanel() {
                setLayout(new java.awt.BorderLayout());
            }

            public java.awt.Dimension getPreferredScrollableViewportSize() {
                return getPreferredSize();
            }

            public int getScrollableUnitIncrement(java.awt.Rectangle r, int o, int d) {
                return 16;
            }

            public int getScrollableBlockIncrement(java.awt.Rectangle r, int o, int d) {
                return 16;
            }

            public boolean getScrollableTracksViewportWidth() {
                return true;
            } // The magic lock!

            public boolean getScrollableTracksViewportHeight() {
                return false;
            } // Let it scroll vertically!
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
                        new plpvotingsystem.PartyCandidatesForm(partyName, loggedInStudent, hasVoted).setVisible(true);
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

    private void loadTurnoutStats(CircularTurnoutBar circularBar) {
        try (Connection conn = database.DBConnection.DBConnect();
             PreparedStatement pstTotal = conn.prepareStatement("SELECT COUNT(*) AS total FROM tblVoters");
             PreparedStatement pstVoted = conn.prepareStatement("SELECT COUNT(*) AS voted FROM tblVoters WHERE has_voted = 1")) {

            int totalStudents = 0;
            int votedStudents = 0;

            ResultSet rsTotal = pstTotal.executeQuery();
            if (rsTotal.next()) { totalStudents = rsTotal.getInt("total"); }

            ResultSet rsVoted = pstVoted.executeQuery();
            if (rsVoted.next()) { votedStudents = rsVoted.getInt("voted"); }

            if (totalStudents > 0) {
                int percent = (int) (((double) votedStudents / totalStudents) * 100);
                circularBar.updateProgress(percent, votedStudents + " / " + totalStudents + " Voted");
            } else {
                circularBar.updateProgress(0, "No Voters");
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentPassportData(JLabel passportLabel) {
        try (Connection conn = database.DBConnection.DBConnect();
             PreparedStatement pst = conn.prepareStatement("SELECT course, year_level, section FROM tblVoters WHERE student_number = ?")) {
            
            pst.setString(1, this.loggedInStudent);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // If the student exists, show their data!
                String course = rs.getString("course");
                String year = rs.getString("year_level");
                String section = rs.getString("section");
                passportLabel.setText("<html><b>Student ID:</b> " + this.loggedInStudent + "<br><br><b>Program:</b> " + course + " " + year + "-" + section + "<br><br><b>Status:</b> " + (hasVoted == 1 ? "<font color='green'>Verified & Voted</font>" : "<font color='red'>Pending Ballot</font>") + "</html>");
            } else {
                // THE FIX: If the student IS NOT in the database, warn the user!
                passportLabel.setText("<html><font color='red'><b>DATABASE ERROR:</b></font><br>Student ID '" + this.loggedInStudent + "' is not registered in tblVoters.<br>Please register this account via the Admin Panel first.</html>");
            }
        } catch (java.sql.SQLException e) {
            passportLabel.setText("<html><font color='red'><b>SQL Error:</b></font> Cannot connect to database.</html>");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalHeader2 = new plpvotingsystem.GlobalHeader();
        pnlMainContent = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        lblTurnoutTitle = new javax.swing.JLabel();
        barTurnout = new javax.swing.JProgressBar();
        lblTurnoutStats = new javax.swing.JLabel();
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

        lblTurnoutTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTurnoutTitle.setText("Live Campus Turnout");
        pnlMainContent.add(lblTurnoutTitle);
        pnlMainContent.add(barTurnout);

        lblTurnoutStats.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTurnoutStats.setText("... / ... Students Voted");
        pnlMainContent.add(lblTurnoutStats);

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
                new StudentDashboard("TestStudent", 1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barTurnout;
    private javax.swing.JButton btnVoteNow;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblGreeting2;
    private javax.swing.JLabel lblTurnoutStats;
    private javax.swing.JLabel lblTurnoutTitle;
    private javax.swing.JPanel pnlMainContent;
    private javax.swing.JPanel pnlParties;
    // End of variables declaration//GEN-END:variables
}

// --- CUSTOM CIRCULAR PROGRESS BAR CLASS ---
class CircularTurnoutBar extends javax.swing.JPanel {
    private int progress = 0;
    private String text = "0%";

    public CircularTurnoutBar() {
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(200, 200));
    }

    public void updateProgress(int percent, String stats) {
        this.progress = percent;
        this.text = stats;
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        // Draw Background Ring (Gray)
        g2.setColor(new java.awt.Color(220, 220, 220));
        g2.setStroke(new java.awt.BasicStroke(20, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
        g2.drawArc(x + 15, y + 15, size - 30, size - 30, 0, 360);

        // Draw Foreground Ring (PLP Green)
        g2.setColor(new java.awt.Color(55, 140, 59));
        int angle = -(int) (360 * (progress / 100.0));
        g2.drawArc(x + 15, y + 15, size - 30, size - 30, 90, angle);

        // Draw Percentage in the middle
        g2.setColor(java.awt.Color.BLACK);
        g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 36));
        String percentText = progress + "%";
        java.awt.FontMetrics fm = g2.getFontMetrics();
        g2.drawString(percentText, x + (size - fm.stringWidth(percentText)) / 2, y + (size - fm.getHeight()) / 2 + fm.getAscent() - 10);
        
        // Draw subtitle inside the ring
        g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        g2.setColor(java.awt.Color.DARK_GRAY);
        java.awt.FontMetrics fm2 = g2.getFontMetrics();
        g2.drawString(text, x + (size - fm2.stringWidth(text)) / 2, y + (size - fm.getHeight()) / 2 + fm.getAscent() + 20);
    }
}
