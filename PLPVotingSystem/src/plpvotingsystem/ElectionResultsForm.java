/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author PLP
 */
public class ElectionResultsForm extends javax.swing.JFrame {

    String currentAdmin = "";

    public ElectionResultsForm(String adminName) {
        initComponents();

        this.getContentPane().setLayout(new java.awt.BorderLayout());

        plpvotingsystem.GlobalHeader header = new plpvotingsystem.GlobalHeader();
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 20, 100));
        header.setPreferredSize(new java.awt.Dimension(1000, 110));
        this.getContentPane().add(header, java.awt.BorderLayout.NORTH);

        this.setLocationRelativeTo(null);
        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        this.getContentPane().setBackground(new java.awt.Color(245, 245, 245));

        pnlGraphs.setLayout(new java.awt.GridLayout(0, 2, 50, 0));

        pnlMainContent.setBackground(new java.awt.Color(245, 245, 245));
        pnlGraphs.setBackground(new java.awt.Color(245, 245, 245));

        pnlWinners.setLayout(new java.awt.GridLayout(0, 3, 50, 30));
        pnlWinners.setBackground(new java.awt.Color(245, 245, 245));
        pnlWinners.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));

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

        loadLiveResults();
        generateWinnersSummary();
    }

    
    
    private void loadLiveResults() {
        pnlGraphs.removeAll();
        // Use the infinite row trick for the graphs! 2 columns, infinite rows.
        pnlGraphs.setLayout(new java.awt.GridLayout(0, 2, 50, 30));

        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pstPositions = conn.prepareStatement("SELECT position_id, position_name FROM tblPositions ORDER BY position_id ASC")) {

            ResultSet rsPositions = pstPositions.executeQuery();

            while (rsPositions.next()) {
                int currentPosId = rsPositions.getInt("position_id");
                String posName = rsPositions.getString("position_name");

                // --- 1. CREATE A MINI-PANEL FOR THIS SPECIFIC POSITION ---
                JPanel categoryPanel = new JPanel();
                categoryPanel.setLayout(new javax.swing.BoxLayout(categoryPanel, javax.swing.BoxLayout.Y_AXIS));
                categoryPanel.setBackground(new java.awt.Color(245, 245, 245));

                // Add the Position Title to the top of the mini-panel
                JLabel positionLabel = new JLabel(posName);
                positionLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
                positionLabel.setForeground(new java.awt.Color(55, 140, 59)); // PLP Green
                categoryPanel.add(positionLabel);
                categoryPanel.add(javax.swing.Box.createVerticalStrut(10));

                // --- 2. FETCH CANDIDATES & VOTES FOR THIS POSITION ---
                // This query is massive but it calculates total votes AND the total votes cast for the position in one go!
                String candidateQuery = "SELECT c.full_name, c.party_list, COUNT(v.candidate_id) AS total_votes, "
                        + "(SELECT COUNT(*) FROM tblVotes v2 JOIN tblCandidates c2 ON v2.candidate_id = c2.candidate_id WHERE c2.position_id = ?) as pos_total "
                        + "FROM tblCandidates c LEFT JOIN tblVotes v ON c.candidate_id = v.candidate_id "
                        + "WHERE c.position_id = ? "
                        + "GROUP BY c.candidate_id, c.full_name, c.party_list "
                        + "ORDER BY total_votes DESC";

                try (PreparedStatement pstCandidates = conn.prepareStatement(candidateQuery)) {

                    pstCandidates.setInt(1, currentPosId);
                    pstCandidates.setInt(2, currentPosId);
                    ResultSet rsCandidates = pstCandidates.executeQuery();

                    boolean hasCandidates = false;

                    // --- 3. BUILD THE PROGRESS BARS ---
                    while (rsCandidates.next()) {
                        hasCandidates = true;
                        String fullName = rsCandidates.getString("full_name");
                        String partyList = rsCandidates.getString("party_list");
                        int totalVotes = rsCandidates.getInt("total_votes");
                        int positionTotal = rsCandidates.getInt("pos_total");

                        String displayString = fullName.equals("Abstain")
                                ? fullName + " - " + totalVotes + " votes"
                                : fullName + " (" + partyList + ") - " + totalVotes + " votes";

                        JLabel candidateVotes = new JLabel(displayString);
                        candidateVotes.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 25));

                        JProgressBar voteBar = new JProgressBar();
                        voteBar.setMaximum(positionTotal == 0 ? 1 : positionTotal); // Prevent divide by zero error
                        voteBar.setValue(totalVotes);
                        voteBar.setStringPainted(true);
                        voteBar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
                        voteBar.setForeground(new java.awt.Color(250, 192, 45)); // PLP Yellow
                        voteBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());

                        // --- THE FIX: Force the text to be black regardless of the juice level ---
                        javax.swing.UIManager.put("ProgressBar.selectionForeground", java.awt.Color.BLACK);
                        javax.swing.UIManager.put("ProgressBar.selectionBackground", java.awt.Color.BLACK);

                        voteBar.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 25));

                        // Snap the bar into the category panel
                        categoryPanel.add(candidateVotes);
                        categoryPanel.add(javax.swing.Box.createVerticalStrut(2));
                        categoryPanel.add(voteBar);
                        categoryPanel.add(javax.swing.Box.createVerticalStrut(8));
                    }

                    // If nobody is running, show a ghost town message
                    if (!hasCandidates) {
                        JLabel lblNoCandidates = new JLabel("No candidates registered.");
                        lblNoCandidates.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 12));
                        lblNoCandidates.setForeground(java.awt.Color.GRAY);
                        categoryPanel.add(lblNoCandidates);
                    }
                }
                // --- 4. SNAP THE FINISHED CATEGORY PANEL INTO THE MAIN GRAPH GRID ---
                pnlGraphs.add(categoryPanel);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }

        // Refresh UI
        pnlGraphs.repaint();
        pnlGraphs.revalidate();
    }

    private void generateWinnersSummary() {
        // 1. Clear the panel just in case this is called during a refresh
        pnlWinners.removeAll();

        // 2. Open ONE main connection to the database
        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pstPositions = conn.prepareStatement("SELECT position_id, position_name FROM tblPositions ORDER BY position_id ASC")) {

            ResultSet rsPositions = pstPositions.executeQuery();

            // 3. Loop through however many positions actually exist in the database
            while (rsPositions.next()) {
                int dynamicPosId = rsPositions.getInt("position_id");
                String positionTitle = rsPositions.getString("position_name");

                // 4. Run the Winner query for THIS specific dynamic position
                try (PreparedStatement pstWinner = conn.prepareStatement("SELECT c.full_name, c.party_list, COUNT(v.candidate_id) as vote_count FROM tblCandidates c LEFT JOIN tblVotes v ON c.candidate_id = v.candidate_id WHERE c.position_id = ? AND c.full_name != 'Abstain' GROUP BY c.candidate_id, c.full_name, c.party_list ORDER BY vote_count DESC LIMIT 1")) {

                    pstWinner.setInt(1, dynamicPosId);
                    ResultSet rsWinner = pstWinner.executeQuery();

                    if (rsWinner.next()) {
                        // --- WE HAVE A WINNER: Build the Standard Card ---
                        String fullName = rsWinner.getString("full_name");
                        String partyList = rsWinner.getString("party_list");
                        int voteCount = rsWinner.getInt("vote_count");

                        plpvotingsystem.RoundedPanel partyCard = new plpvotingsystem.RoundedPanel();
                        partyCard.setLayout(new javax.swing.BoxLayout(partyCard, javax.swing.BoxLayout.Y_AXIS));
                        partyCard.setBackground(java.awt.Color.WHITE);

                        JPanel contentWrapper = new JPanel();
                        contentWrapper.setLayout(new javax.swing.BoxLayout(contentWrapper, javax.swing.BoxLayout.Y_AXIS));
                        contentWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
                        contentWrapper.setOpaque(false);

                        JLabel lblPosition = new JLabel(positionTitle);
                        lblPosition.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
                        lblPosition.setForeground(new java.awt.Color(55, 140, 59)); // PLP Green

                        JLabel lblWinnerName = new JLabel(fullName);
                        lblWinnerName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));

                        JLabel lblParty = new JLabel(partyList);
                        lblParty.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
                        lblParty.setForeground(java.awt.Color.GRAY);

                        JLabel lblVotes = new JLabel("Total Votes: " + voteCount);
                        lblVotes.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));

                        contentWrapper.add(lblPosition);
                        contentWrapper.add(javax.swing.Box.createVerticalStrut(10));
                        contentWrapper.add(lblWinnerName);
                        contentWrapper.add(lblParty);
                        contentWrapper.add(javax.swing.Box.createVerticalStrut(15));
                        contentWrapper.add(lblVotes);

                        partyCard.add(contentWrapper);
                        pnlWinners.add(partyCard);

                    } else {
                        // --- GHOST TOWN: Nobody ran for this position ---
                        plpvotingsystem.RoundedPanel emptyCard = new plpvotingsystem.RoundedPanel();
                        emptyCard.setLayout(new javax.swing.BoxLayout(emptyCard, javax.swing.BoxLayout.Y_AXIS));
                        emptyCard.setBackground(new java.awt.Color(220, 220, 220)); // Gray background

                        JPanel emptyWrapper = new JPanel();
                        emptyWrapper.setLayout(new javax.swing.BoxLayout(emptyWrapper, javax.swing.BoxLayout.Y_AXIS));
                        emptyWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
                        emptyWrapper.setOpaque(false);

                        JLabel lblPos = new JLabel(positionTitle);
                        lblPos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
                        lblPos.setForeground(java.awt.Color.DARK_GRAY);

                        JLabel lblVacant = new JLabel("VACANT");
                        lblVacant.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
                        lblVacant.setForeground(java.awt.Color.GRAY);

                        emptyWrapper.add(lblPos);
                        emptyWrapper.add(javax.swing.Box.createVerticalStrut(10));
                        emptyWrapper.add(lblVacant);

                        emptyCard.add(emptyWrapper);
                        pnlWinners.add(emptyCard);
                    }
                }
            }

        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }

        // 5. Tell Java to repaint the UI with the dynamically generated cards
        pnlWinners.repaint();
        pnlWinners.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMainContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlGraphs = new javax.swing.JPanel();
        btnBack2Dashboard = new plpvotingsystem.RoundedButton();
        jLabel2 = new javax.swing.JLabel();
        pnlWinners = new javax.swing.JPanel();
        btnReopen = new plpvotingsystem.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Election Results");
        setUndecorated(true);
        setResizable(false);

        pnlMainContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 80, 30, 80));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Official Election Results");

        javax.swing.GroupLayout pnlGraphsLayout = new javax.swing.GroupLayout(pnlGraphs);
        pnlGraphs.setLayout(pnlGraphsLayout);
        pnlGraphsLayout.setHorizontalGroup(
            pnlGraphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlGraphsLayout.setVerticalGroup(
            pnlGraphsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        btnBack2Dashboard.setBackground(new java.awt.Color(204, 204, 204));
        btnBack2Dashboard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack2Dashboard.setForeground(new java.awt.Color(0, 0, 0));
        btnBack2Dashboard.setText("TP to Dashboard");
        btnBack2Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack2Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2DashboardActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Elected Officers");

        pnlWinners.setForeground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout pnlWinnersLayout = new javax.swing.GroupLayout(pnlWinners);
        pnlWinners.setLayout(pnlWinnersLayout);
        pnlWinnersLayout.setHorizontalGroup(
            pnlWinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlWinnersLayout.setVerticalGroup(
            pnlWinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );

        btnReopen.setBackground(new java.awt.Color(0, 255, 102));
        btnReopen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReopen.setForeground(new java.awt.Color(0, 0, 0));
        btnReopen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/voteIcon.png"))); // NOI18N
        btnReopen.setText("Reopen Elections");
        btnReopen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReopen.setIconTextGap(15);
        btnReopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReopenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainContentLayout = new javax.swing.GroupLayout(pnlMainContent);
        pnlMainContent.setLayout(pnlMainContentLayout);
        pnlMainContentLayout.setHorizontalGroup(
            pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addGroup(pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainContentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack2Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReopen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlGraphs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlWinners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlMainContentLayout.setVerticalGroup(
            pnlMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(btnBack2Dashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlGraphs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlWinners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(btnReopen)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMainContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(pnlMainContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBack2DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2DashboardActionPerformed
        this.dispose();
        new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnBack2DashboardActionPerformed

    private void btnReopenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReopenActionPerformed
        int adminChoice = JOptionPane.showConfirmDialog(this, "Are you sure you want to REOPEN the elections? Students will be able to log in and vote again.", "Reopen Elections", JOptionPane.WARNING_MESSAGE);

        if (adminChoice == JOptionPane.YES_OPTION) {
            try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("UPDATE tblElectionStatus SET current_phase = 'OPEN' WHERE status_id = 1")) {

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "The election is now OPEN.");

                // Teleport back to the dashboard so it refreshes the buttons
                this.dispose();
                new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);

            } catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnReopenActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(ElectionResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ElectionResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ElectionResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ElectionResultsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ElectionResultsForm("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack2Dashboard;
    private javax.swing.JButton btnReopen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlGraphs;
    private javax.swing.JPanel pnlMainContent;
    private javax.swing.JPanel pnlWinners;
    // End of variables declaration//GEN-END:variables
}
