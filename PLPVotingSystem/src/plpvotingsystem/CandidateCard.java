/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package plpvotingsystem;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PLP
 */
public class CandidateCard extends plpvotingsystem.RoundedPanel {

    public CandidateCard(String name, String party, String creds, String plat, String imagePath) {
        initComponents();

        this.removeAll();

        this.setLayout(new java.awt.BorderLayout());
        this.setBackground(java.awt.Color.WHITE);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblFullName.setText(name);
        lblPosition.setText(party);
        txtAreaCredentials.setText(creds);
        txtAreaPlatform.setText(plat);

        // 1. Instantiate your brand new custom component!
        plpvotingsystem.CircularImageComponent profilePic = new plpvotingsystem.CircularImageComponent();

        // 2. Set the image dynamically based on the database path!
        if (imagePath != null && !imagePath.isEmpty() && !imagePath.equals("Path:")) {
            profilePic.setImage(new ImageIcon(imagePath));
        } else {
            // Fallback for Abstain or missing photos
            profilePic.setImage(new ImageIcon("SystemIcons\\independentIcon.png"));
        }

// 3. Lock its size so it forms a perfect circle (e.g., 80x80 pixels)
        profilePic.setPreferredSize(new java.awt.Dimension(80, 80));
        profilePic.setMaximumSize(new java.awt.Dimension(80, 80));
        profilePic.setMinimumSize(new java.awt.Dimension(80, 80));

// 4. Add the new profilePic to the left side INSTEAD of picturePlaceholder
        // 1. Create a transparent wrapper panel to protect the circle from stretching
        JPanel picWrapper = new JPanel(new java.awt.BorderLayout());
        picWrapper.setOpaque(false);

// 2. Pin your circular image to the absolute NORTH (top) of the wrapper
        picWrapper.add(profilePic, java.awt.BorderLayout.NORTH);

// 3. Add the protective wrapper to the West side of the card
        this.add(picWrapper, java.awt.BorderLayout.WEST);

        JPanel contentBox = new JPanel();
        contentBox.setLayout(new javax.swing.BoxLayout(contentBox, javax.swing.BoxLayout.Y_AXIS));
        contentBox.setOpaque(false);
        // 5. Move the 20-pixel gap to the content box so it doesn't mess with our custom drawing area
        contentBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0));

        // 1. Style the Name
        lblFullName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));

// 2. Create and style the Custom Pill Badge
        plpvotingsystem.PillBadgeLabel customPositionLabel = new plpvotingsystem.PillBadgeLabel(party);
        customPositionLabel.setForeground(java.awt.Color.WHITE);
        customPositionLabel.setBackground(new java.awt.Color(46, 125, 50));
        customPositionLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        customPositionLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 12, 4, 12));
        customPositionLabel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

// 3. Style the Credentials Text Area
        txtAreaCredentials.setLineWrap(true);
        txtAreaCredentials.setWrapStyleWord(true);
        txtAreaCredentials.setOpaque(false);
        txtAreaCredentials.setEditable(false);
        txtAreaCredentials.setBorder(null);
        txtAreaCredentials.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        txtAreaCredentials.setForeground(java.awt.Color.DARK_GRAY);

// 4. Create and style the Platform Label
        JLabel lblPlataporma = new JLabel("Plataporma:");
        lblPlataporma.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblPlataporma.setForeground(java.awt.Color.BLACK);

// 5. Style the Platform Text Area
        txtAreaPlatform.setLineWrap(true);
        txtAreaPlatform.setWrapStyleWord(true);
        txtAreaPlatform.setOpaque(false);
        txtAreaPlatform.setEditable(false);
        txtAreaPlatform.setBorder(null);
        txtAreaPlatform.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        txtAreaPlatform.setForeground(java.awt.Color.DARK_GRAY);

// 6. Stack them into the content box
        lblFullName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        txtAreaCredentials.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        lblPlataporma.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        txtAreaPlatform.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        contentBox.add(lblFullName);
        contentBox.add(javax.swing.Box.createVerticalStrut(5));
        contentBox.add(customPositionLabel);
        contentBox.add(javax.swing.Box.createVerticalStrut(15));
        contentBox.add(txtAreaCredentials);
        contentBox.add(javax.swing.Box.createVerticalStrut(15));
        contentBox.add(lblPlataporma);
        contentBox.add(javax.swing.Box.createVerticalStrut(5));
        contentBox.add(txtAreaPlatform);

// 7. Add the finished content box to the center of the master card
        this.add(contentBox, java.awt.BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picturePlaceholder = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaCredentials = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaPlatform = new javax.swing.JTextArea();

        picturePlaceholder.setText("jLabel1");

        lblFullName.setText("jLabel1");

        lblPosition.setBackground(new java.awt.Color(46, 125, 50));
        lblPosition.setForeground(new java.awt.Color(255, 255, 255));
        lblPosition.setText("jLabel1");

        txtAreaCredentials.setColumns(20);
        txtAreaCredentials.setRows(5);
        jScrollPane1.setViewportView(txtAreaCredentials);

        txtAreaPlatform.setColumns(20);
        txtAreaPlatform.setRows(5);
        jScrollPane2.setViewportView(txtAreaPlatform);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(picturePlaceholder)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPosition)
                            .addComponent(lblFullName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(picturePlaceholder)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFullName)
                        .addGap(30, 30, 30)
                        .addComponent(lblPosition))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel picturePlaceholder;
    private javax.swing.JTextArea txtAreaCredentials;
    private javax.swing.JTextArea txtAreaPlatform;
    // End of variables declaration//GEN-END:variables
}
