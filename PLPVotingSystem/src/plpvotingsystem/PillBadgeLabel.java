package plpvotingsystem;

import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PillBadgeLabel extends JLabel {

    public PillBadgeLabel(String text) {
        super(text);
        setOpaque(false); // We must set this to false so Swing doesn't paint a square background over our custom pill!
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Paint the perfect pill background
        g2.setColor(getBackground());
        int arc = getHeight(); 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // 2. Let the standard JLabel draw the text right on top of our new shape
        super.paintComponent(g);
    }
}