/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.awt.Color;
import javax.swing.ButtonGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class VotingForm extends javax.swing.JFrame {

    int currentPositionID = 1;
    plpvotingsystem.RoundedButton customBtnBack;
    plpvotingsystem.RoundedButton customBtnNext;
    plpvotingsystem.VotingStepper stepper;
    ButtonGroup groupCandidates = new ButtonGroup();

    String[] candidateChoices = new String[6];

    private void loadCandidates(int positionID) {
        pnlCandidates.removeAll();
        groupCandidates = new ButtonGroup();
        stepper.setStep(positionID);

        if (positionID == 1) {
            lblPositionTitle.setText("Vote for President");
        } else if (positionID == 2) {
            lblPositionTitle.setText("Vote for Vice President");
        } else if (positionID == 3) {
            lblPositionTitle.setText("Vote for Secretary");
        } else if (positionID == 4) {
            lblPositionTitle.setText("Vote for Treasurer");
        } else if (positionID == 5) {
            lblPositionTitle.setText("Vote for Auditor");
        } else if (positionID == 6) {
            lblPositionTitle.setText("Vote for P.R.O");
        }

        customBtnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customBtnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        try (
                Connection conn = database.DBConnection.DBConnect(); 
                // BUCKET LIST #6 ACHIEVED: The ORDER BY CASE statement forces 'Abstain' to be evaluated as '1' (bottom of the list) and everyone else as '0' (top of the list), then sorts alphabetically!
                PreparedStatement pst = conn.prepareStatement("SELECT full_name, party_list, credentials, plataporma, image_path FROM tblCandidates WHERE position_id = ? ORDER BY CASE WHEN full_name = 'Abstain' THEN 1 ELSE 0 END, full_name ASC")
        ) {
            pst.setInt(1, positionID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("full_name");
                
                // GHOST BUSTER: If a blank candidate accidentally sneaked into the database, ignore them completely!
                if (fullName == null || fullName.trim().isEmpty()) {
                    continue; 
                }

                String partyList = rs.getString("party_list");
                String creds = rs.getString("credentials");
                String platform = rs.getString("plataporma");
                String path = rs.getString("image_path");

                // 1. Check if this candidate was already chosen previously
                boolean isAlreadyChosen = false;
                if (candidateChoices[currentPositionID - 1] != null && candidateChoices[currentPositionID - 1].equals(fullName)) {
                    isAlreadyChosen = true;
                }

                // 2. Handle the "Abstain" party text gracefully
                String displayParty = partyList;
                if (fullName.equals("Abstain") || partyList == null) {
                    displayParty = "No Vote Cast";
                }

                // 3. Instantiate your beautiful new custom card!
                plpvotingsystem.CandidateOptionCard myCard = new plpvotingsystem.CandidateOptionCard(fullName, displayParty, groupCandidates, isAlreadyChosen, creds, platform, path);

                // 4. THE ANTI-SQUISH SHIELD: Force strict sizing so the layout manager cannot crush the card and cut off the icon!
                myCard.setPreferredSize(new java.awt.Dimension(600, 120));
                myCard.setMinimumSize(new java.awt.Dimension(200, 120));
                myCard.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 120));
                myCard.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

                // 5. Add it to the ballot
                pnlCandidates.add(myCard);
                pnlCandidates.add(javax.swing.Box.createVerticalStrut(15));
            }
            
            // CRITICAL: revalidate() MUST come before repaint() so the scroll pane knows the interior height has changed!
            pnlCandidates.revalidate();
            pnlCandidates.repaint();
            
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }

        if (positionID == 6) {
            customBtnNext.setText("Review Votes");
        } else {
            customBtnNext.setText("Next");
        }
    }

    String loggedInStudent;

    public void restoreChoices(String[] previousChoices) {
        this.candidateChoices = previousChoices;
        // Reload page 1 so the radio button automatically selects the saved candidate!
        loadCandidates(currentPositionID);
    }

    public VotingForm(String studentNumber) {
        initComponents();

        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        this.getContentPane().removeAll();

        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);

        this.setLayout(new java.awt.BorderLayout());
        plpvotingsystem.GlobalHeader header = new plpvotingsystem.GlobalHeader();
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 20, 100));
        header.setPreferredSize(new java.awt.Dimension(1000, 110));
        this.getContentPane().add(header, java.awt.BorderLayout.NORTH);

        JPanel pnlMain = new JPanel();

        pnlMain.setLayout(new javax.swing.BoxLayout(pnlMain, javax.swing.BoxLayout.Y_AXIS));
        pnlMain.setBackground(new java.awt.Color(245, 245, 245));
        pnlMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 100, 30, 100));

        stepper = new plpvotingsystem.VotingStepper();
        stepper.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // 1. Build the white wrapper card
        plpvotingsystem.RoundedPanel pnlTitleBox = new plpvotingsystem.RoundedPanel();
        pnlTitleBox.setLayout(new javax.swing.BoxLayout(pnlTitleBox, javax.swing.BoxLayout.Y_AXIS));
        pnlTitleBox.setBackground(java.awt.Color.WHITE);
        pnlTitleBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));
        pnlTitleBox.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        pnlTitleBox.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

// 2. Style the main title
        lblPositionTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 36));
        lblPositionTitle.setForeground(java.awt.Color.BLACK);

// 3. Create and style the subtitle
        JLabel lblSubtitle = new JLabel("Select one candidate");
        lblSubtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        lblSubtitle.setForeground(java.awt.Color.GRAY);

// 4. Stack the headers inside the white card
        pnlTitleBox.add(lblPositionTitle);
        pnlTitleBox.add(javax.swing.Box.createVerticalStrut(5));
        pnlTitleBox.add(lblSubtitle);

// 5. Add breathing room
        pnlTitleBox.add(javax.swing.Box.createVerticalStrut(30));

        // Configure the raw panel first
        pnlCandidates.setOpaque(false);
        pnlCandidates.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        pnlCandidates.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 20, 20));
        pnlCandidates.setLayout(new javax.swing.BoxLayout(pnlCandidates, javax.swing.BoxLayout.Y_AXIS));

        // 6. WRAP IT IN A CUSTOM SCROLL PANE!
        javax.swing.JScrollPane scpCandidates = new javax.swing.JScrollPane(pnlCandidates);
        scpCandidates.setBorder(null); // Strip ugly border
        scpCandidates.getViewport().setBackground(java.awt.Color.WHITE); // Blend with the white card
        scpCandidates.getVerticalScrollBar().setUI(new plpvotingsystem.ModernScrollBarUI()); // Your custom UI!
        scpCandidates.getVerticalScrollBar().setUnitIncrement(16); // Smooth mouse wheel

        // Strictly enforce "Only When Needed"
        scpCandidates.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scpCandidates.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scpCandidates.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // 7. Embed the SCROLL PANE into the white card, not the raw panel!
        pnlTitleBox.add(scpCandidates);

        JPanel buttonRow = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        buttonRow.setOpaque(false);

        buttonRow.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 60));
        buttonRow.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        customBtnBack = new plpvotingsystem.RoundedButton();
        customBtnBack.setBackground(new java.awt.Color(220, 220, 220)); // Solid Light Gray
        customBtnBack.setForeground(Color.BLACK);
        customBtnBack.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Remove the buggy line border

        customBtnBack.setVisible(false);

        customBtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(null);
            }
        });

        customBtnNext = new plpvotingsystem.RoundedButton();
        customBtnNext.setBackground(new java.awt.Color(250, 192, 45));
        customBtnNext.setForeground(java.awt.Color.BLACK);

        customBtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(null);
            }
        });

        // 1. Set the button text!
        customBtnBack.setText("Back");
        customBtnNext.setText("Next");

        customBtnBack.setPreferredSize(new java.awt.Dimension(130, 45));
        customBtnBack.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
// (Do the same for customBtnNext)

        customBtnNext.setPreferredSize(new java.awt.Dimension(130, 45));
        customBtnNext.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
// (Do the same for customBtnNext)

// 2. Add the buttons to the row (You missed customBtnBack!)
        buttonRow.add(customBtnBack);
        buttonRow.add(javax.swing.Box.createHorizontalStrut(20));
        buttonRow.add(customBtnNext);

        // 3. Final Assembly of the UI Stack
        pnlMain.add(stepper);
        pnlMain.add(javax.swing.Box.createVerticalStrut(30)); // Space below progress bar

        // This pnlTitleBox now contains: Title, Subtitle, AND pnlCandidates
        pnlMain.add(pnlTitleBox);

        pnlMain.add(javax.swing.Box.createVerticalGlue()); // Push buttons to the bottom
        pnlMain.add(buttonRow);

        // 4. Add the finished pnlMain to the frame ONLY ONCE
        this.getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

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
        setUndecorated(true);
        setResizable(false);

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
        if (groupCandidates.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Blank " + lblPositionTitle.getText() + " choice");
            return;
        } else {
            String chosenCandidate = groupCandidates.getSelection().getActionCommand();
            candidateChoices[currentPositionID - 1] = chosenCandidate;
        }

        if (currentPositionID < 6) {
            currentPositionID++;
            if (currentPositionID > 1) {
                customBtnBack.setVisible(true);
            }
            loadCandidates(currentPositionID);
        } else {
            this.dispose();
            new plpvotingsystem.ReviewPage(loggedInStudent, candidateChoices).setVisible(true);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        currentPositionID--;
        if (currentPositionID == 1) {
            customBtnBack.setVisible(false);
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
