package plpvotingsystem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class RoundedButton extends JButton {

    private int radius = 17;
    private boolean isDialogButton = false; // THE TOGGLE SWITCH

    public RoundedButton() {
        this("");
    }

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false); 
        setFocusPainted(false); 
        setBorderPainted(false); 
        setOpaque(false);
        
        // Bold text for all buttons globally
        setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            private java.awt.Color originalColor;

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (isEnabled()) {
                    originalColor = getBackground();
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
                    setBackground(originalColor); 
                }
            }
        });
    }

    // Call this ONLY for buttons inside popups/dialogs!
    public void setDialogButton(boolean isDialog) {
        this.isDialogButton = isDialog;
        if (isDialog) {
            // Binds the "Enter" key exclusively to THIS button, ONLY when it has focus
            this.getInputMap(javax.swing.JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0), "Enter");
            this.getActionMap().put("Enter", new javax.swing.AbstractAction() {
                public void actionPerformed(java.awt.event.ActionEvent ae) {
                    doClick(); // Clicks whichever button you currently tabbed to
                }
            });
        }
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
        
        // ONLY draw the focus ring if this is specifically marked as a dialog button!
        if (this.isDialogButton && hasFocus()) {
            g2.setColor(java.awt.Color.DARK_GRAY);
            g2.setStroke(new java.awt.BasicStroke(2.0f));
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, radius, radius);
        }
        
        super.paintComponent(g); // paints the text on top
    }
}