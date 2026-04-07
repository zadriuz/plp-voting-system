package plpvotingsystem;

import java.awt.*;
import javax.swing.*;

public class VotingStepper extends plpvotingsystem.RoundedPanel {

    private int currentStep = 1;
    java.awt.Image checkIcon;
    private JLabel lblStepCount;
    
    // --- DYNAMIC LISTS ---
    java.util.List<String> positionNames;

    // We now require the VotingStepper to be given the list of positions when created!
    public VotingStepper(java.util.List<String> dynamicPositionNames) {
        this.positionNames = dynamicPositionNames;
        
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JPanel topText = new JPanel(new BorderLayout());
        topText.setOpaque(false);

        checkIcon = new ImageIcon("SystemIcons\\checkMark.png").getImage();

        JLabel lblTitle = new JLabel("Voting Progress");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // Dynamically set the "of X" text
        lblStepCount = new JLabel("Step 1 of " + positionNames.size());
        lblStepCount.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblStepCount.setForeground(Color.GRAY);

        topText.add(lblTitle, BorderLayout.WEST);
        topText.add(lblStepCount, BorderLayout.EAST);
        this.add(topText, BorderLayout.NORTH);

        // The Vector Graphic Canvas
        JComponent graphic = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_RENDER_QUALITY);

                int width = getWidth();
                int centerY = getHeight() / 2 - 10;
                int padding = 40;
                
                // SAFETY NET: If there's only 1 position, don't divide by zero!
                int totalCircles = positionNames.size();
                int spacing = (totalCircles > 1) ? (width - (padding * 2)) / (totalCircles - 1) : 0;

                // 1. Draw Connecting Lines first so they sit UNDER the circles
                for (int i = 0; i < totalCircles - 1; i++) {
                    if (i < currentStep - 1) {
                        g2.setColor(new Color(46, 125, 50)); // Green completed line
                        g2.setStroke(new BasicStroke(4));
                    } else {
                        g2.setColor(new Color(220, 220, 220)); // Gray pending line
                        g2.setStroke(new BasicStroke(2));
                    }
                    g2.drawLine(padding + (i * spacing), centerY, padding + ((i + 1) * spacing), centerY);
                }

                // 2. Draw Circles and Text dynamically
                for (int i = 0; i < totalCircles; i++) {
                    int cx = padding + (i * spacing);
                    int r = 16;

                    // Fill the circles
                    if (i < currentStep) {
                        g2.setColor(new Color(46, 125, 50));
                        g2.fillOval(cx - r, centerY - r, r * 2, r * 2);
                        g2.setColor(Color.WHITE);
                    } else {
                        g2.setColor(new Color(240, 240, 240));
                        g2.fillOval(cx - r, centerY - r, r * 2, r * 2);
                        g2.setColor(Color.GRAY);
                    }

                    g2.setFont(new Font("Segoe UI", Font.BOLD, 14));

                    // Draw Checkmarks or Numbers
                    if (i < currentStep - 1) {
                        g2.drawImage(checkIcon, cx - 8, centerY - 8, 16, 16, null);
                    } else {
                        String text = String.valueOf(i + 1);
                        int tx = cx - (g2.getFontMetrics().stringWidth(text) / 2);
                        g2.drawString(text, tx, centerY + 5);
                    }

                    // Draw Dynamic Labels (President, VP Internal, etc.)
                    g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                    g2.setColor(i == currentStep - 1 ? Color.BLACK : Color.GRAY);
                    String labelText = positionNames.get(i);
                    int lx = cx - (g2.getFontMetrics().stringWidth(labelText) / 2);
                    g2.drawString(labelText, lx, centerY + r + 25);
                }
            }
        };

        // Force the canvas to have an actual physical size!
        graphic.setPreferredSize(new Dimension(800, 80));
        graphic.setMinimumSize(new Dimension(800, 80));

        // Force the main stepper panel to stay tall
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, 130));
        this.setMinimumSize(new Dimension(1000, 130));

        this.add(graphic, BorderLayout.CENTER);
    }

    public void setStep(int step) {
        this.currentStep = step;
        lblStepCount.setText("Step " + step + " of " + positionNames.size());
        repaint(); // Triggers paintComponent to redraw everything!
    }
}