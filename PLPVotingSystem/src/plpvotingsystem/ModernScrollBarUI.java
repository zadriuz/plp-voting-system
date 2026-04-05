package plpvotingsystem;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class ModernScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
    
    @Override
    protected JButton createDecreaseButton(int orientation){
        JButton btnDecrease = new JButton();
        // Force the button to disappear
        btnDecrease.setPreferredSize(new java.awt.Dimension(0, 0));
        return btnDecrease;
    }
    
    @Override
    protected JButton createIncreaseButton(int orientation){
        JButton btnIncrease = new JButton();
        // Force the button to disappear
        btnIncrease.setPreferredSize(new java.awt.Dimension(0, 0));
        return btnIncrease;
    } 
    
    @Override
    protected void configureScrollBarColors(){
        this.thumbColor = new java.awt.Color(200, 200, 200);
        this.trackColor = new java.awt.Color(245, 245, 245); // Added the missing 'new' keyword!
    }
    
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds){
        // Left completely empty so the background track is invisible
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(thumbColor);
        
        // Draw the sleek thumb. The +2 and -4 math creates a 2-pixel margin so it doesn't touch the walls.
        // The 10, 10 at the end curves the corners perfectly.
        g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2, thumbBounds.width - 4, thumbBounds.height - 4, 10, 10);
    }
}