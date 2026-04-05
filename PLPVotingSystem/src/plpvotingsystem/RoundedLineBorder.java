package plpvotingsystem;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.border.AbstractBorder;

public class RoundedLineBorder extends AbstractBorder {
    private java.awt.Color color;
    private int radius;

    public RoundedLineBorder(java.awt.Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new java.awt.BasicStroke(2)); // 2-pixel thickness
        
        // The +1 and -3 math ensures the border draws perfectly inside the bounds without clipping
        g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
    }
}