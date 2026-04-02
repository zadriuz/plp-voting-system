/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package plpvotingsystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

public class RoundedBorder implements Border{
    private int radius;
    private Color color;
    
    public RoundedBorder(int radius, Color color){
        this.radius = radius;
        this.color = color;
    }
    
    @Override
    public Insets getBorderInsets(Component c){
        // add padding inside text field to avoid hitting curved walls
        return new Insets(8,15,8,15);
    }
    
    @Override
    public boolean isBorderOpaque(){
        return true;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(this.color); // paints whatever color is passed onto it
        // Shifts the start point down/right by 1px, and shrinks the total width/height to keep it inside the bounds
        g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
    }
}
