package plpvotingsystem;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageUtil {
    
    // This is a global tool you can call from ANY form in your system!
    public static ImageIcon getSharpIcon(String imagePath, int width, int height) {
        try {
            // 1. Load the original raw image
            Image originalImage = new ImageIcon(imagePath).getImage();
            
            // 2. Force Java to use the high-quality smooth scaling algorithm
            Image sharpImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            
            // 3. Package it back into an ImageIcon so JLabels can use it
            return new ImageIcon(sharpImage);
        } catch (Exception e) {
            System.out.println("Could not load image: " + imagePath);
            return null;
        }
    }
}