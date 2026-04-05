/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plpvotingsystem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class RoundedButton extends JButton {

    private int radius = 17;

    public RoundedButton() {
        this("");
    }

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false); // stops windows rec taking over
        setFocusPainted(false); // stops ugly dotted line when clicked
        setBorderPainted(false); // since we are drawing the shape, we disable the native rectangular border
        setOpaque(false);

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            private java.awt.Color originalColor;

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (isEnabled()) {
                    originalColor = getBackground();
                    // Darken the button slightly on hover
                    setBackground(new java.awt.Color(
                            Math.max(originalColor.getRed() - 20, 0),
                            Math.max(originalColor.getGreen() - 20, 0),
                            Math.max(originalColor.getBlue() - 20, 0)
                    ));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (isEnabled() && originalColor != null) {
                    setBackground(originalColor); // Put the original color back
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g); // paints the text on top
    }
}
