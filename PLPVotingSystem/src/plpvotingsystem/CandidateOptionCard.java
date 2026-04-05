/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plpvotingsystem;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CandidateOptionCard extends plpvotingsystem.RoundedPanel {

    // Store these at the class level so we can use them later for a "View Details" popup!
    private String candidateCreds;
    private String candidatePlatform;

    // The Upgraded Constructor!
    public CandidateOptionCard(String name, String party, javax.swing.ButtonGroup group, boolean isSelected, String creds, String platform, String imagePath) {

        this.candidateCreds = creds;
        this.candidatePlatform = platform;

        this.setLayout(new java.awt.BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JRadioButton btnCandidate = new JRadioButton();
        btnCandidate.setActionCommand(name);
        btnCandidate.setSelected(isSelected);
        group.add(btnCandidate);

        plpvotingsystem.CircularImageComponent imgCandidate = new plpvotingsystem.CircularImageComponent();

        // THE IMAGE BOUNCER: Check if they actually have a photo path. If not (like "Abstain"), use a default!
        if (imagePath != null && !imagePath.isEmpty() && !imagePath.equals("Path:")) {
            imgCandidate.setImage(new ImageIcon(imagePath));
        } else {
            // Fallback for Abstain or if the admin forgot an image
            imgCandidate.setImage(new ImageIcon("SystemIcons\\independentIcon.png"));
        }

        imgCandidate.setPreferredSize(new java.awt.Dimension(50, 50));
        imgCandidate.setMaximumSize(new java.awt.Dimension(50, 50));
        imgCandidate.setMinimumSize(new java.awt.Dimension(50, 50));

        btnCandidate.setOpaque(false);

        JPanel pnlContainer = new JPanel(new java.awt.FlowLayout());
        pnlContainer.add(btnCandidate);
        pnlContainer.add(javax.swing.Box.createHorizontalStrut(5));
        pnlContainer.add(imgCandidate);
        pnlContainer.setOpaque(false);

        this.add(pnlContainer, java.awt.BorderLayout.WEST);

        JPanel pnlText = new JPanel();
        pnlText.setLayout(new javax.swing.BoxLayout(pnlText, javax.swing.BoxLayout.Y_AXIS));
        pnlText.setOpaque(false);
        pnlText.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JLabel candidateName = new JLabel();
        candidateName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        candidateName.setForeground(Color.DARK_GRAY);

        JLabel partylistName = new JLabel();
        partylistName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        candidateName.setText(name);
        partylistName.setText(party);

        candidateName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        partylistName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        pnlText.add(candidateName);
        pnlText.add(javax.swing.Box.createVerticalStrut(5));
        pnlText.add(partylistName);

        this.add(pnlText, java.awt.BorderLayout.CENTER);

        // Listen for when the radio button gets selected or deselected
        btnCandidate.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    // Apply a 2-pixel green border with rounded corners. 
                    // We reduce the empty padding by 2 pixels (13 and 18) so the card doesn't jump in size!
                    CandidateOptionCard.this.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            // Use your brand new custom rounded border with a 20-pixel radius!
                            new plpvotingsystem.RoundedLineBorder(new java.awt.Color(46, 125, 50), 20),
                            javax.swing.BorderFactory.createEmptyBorder(13, 18, 13, 18)
                    ));
                } else {
                    // Revert to the standard invisible padding when deselected
                    CandidateOptionCard.this.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));
                }
            }
        });

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCandidate.setSelected(true);
            }
        });

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
}
