/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plpvotingsystem;

import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;

/**
 *
 * @author PLP
 */
public class CircularImageComponent extends JComponent {

    private Image profileImage;

    public void setImage(javax.swing.ImageIcon icon) {
        this.profileImage = icon.getImage();
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        if (profileImage == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

// Forces high-quality image scaling to kill the blur!
        g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_RENDER_QUALITY);

        // 1. Force a perfect circle based on the smallest dimension to guarantee it never ovals
        int size = Math.min(getWidth(), getHeight());
        java.awt.geom.Area circle = new java.awt.geom.Area(new java.awt.geom.Ellipse2D.Double(0, 0, size, size));
        g2.setClip(circle);

        // 2. Add internal padding so the image shrinks safely away from the "blade" of the clipping mask
        int padding = 8; // 8 pixels of breathing room on all sides
        g2.drawImage(profileImage, padding, padding, size - (padding * 2), size - (padding * 2), this);
    }
}
