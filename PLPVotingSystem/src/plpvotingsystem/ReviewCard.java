package plpvotingsystem;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReviewCard extends plpvotingsystem.RoundedPanel {

    public ReviewCard(String position, String name, String party, String imagePath) {
        this.setLayout(new java.awt.BorderLayout());
        this.setBackground(java.awt.Color.WHITE);
        this.setOpaque(false);
        this.setBorder(javax.swing.BorderFactory.createCompoundBorder(new plpvotingsystem.RoundedLineBorder(new java.awt.Color(220, 220, 220), 15), javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)));

        plpvotingsystem.CircularImageComponent imgCandidate = new plpvotingsystem.CircularImageComponent();
        imgCandidate.setPreferredSize(new java.awt.Dimension(50, 50));
        imgCandidate.setMinimumSize(new java.awt.Dimension(50, 50));
        imgCandidate.setMaximumSize(new java.awt.Dimension(50, 50));
        
        // KILLS THE BLEEDING SCROLL ARTIFACT:
        imgCandidate.setOpaque(false); 
        
        // Fetches and scales the dynamic image securely
        javax.swing.ImageIcon sharpIcon = plpvotingsystem.ImageUtil.getSharpIcon(imagePath, 50, 50);
        imgCandidate.setImage(sharpIcon);

        JPanel pnlContainer = new JPanel(new java.awt.FlowLayout());
        pnlContainer.add(imgCandidate);
        pnlContainer.setOpaque(false);

        this.add(pnlContainer, java.awt.BorderLayout.WEST);

        JPanel pnlTransp = new JPanel();
        pnlTransp.setLayout(new javax.swing.BoxLayout(pnlTransp, javax.swing.BoxLayout.Y_AXIS));
        pnlTransp.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 0));
        pnlTransp.setOpaque(false);

        plpvotingsystem.PillBadgeLabel positionBadge = new plpvotingsystem.PillBadgeLabel(position);
        positionBadge.setBackground(new java.awt.Color(55, 140, 59));
        positionBadge.setForeground(java.awt.Color.WHITE);
        positionBadge.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        positionBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 2, 10));
        positionBadge.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        JLabel candidateName = new JLabel();
        candidateName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        candidateName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        JLabel candidateParty = new JLabel();
        candidateParty.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        candidateParty.setForeground(Color.GRAY);
        candidateParty.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        candidateName.setText(name);
        candidateParty.setText(party);

        pnlTransp.add(positionBadge);
        pnlTransp.add(javax.swing.Box.createVerticalStrut(2));
        pnlTransp.add(candidateName);
        pnlTransp.add(javax.swing.Box.createVerticalStrut(0));
        pnlTransp.add(candidateParty);

        this.add(pnlTransp, java.awt.BorderLayout.CENTER);
    }
}