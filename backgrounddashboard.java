package Apps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class backgrounddashboard extends JPanel {
    private Image image;
    
    public backgrounddashboard() {
        try {
            // Coba load image dengan path yang berbeda
            java.net.URL imageURL = getClass().getResource("/image/dashboard.png");
            if (imageURL != null) {
                image = new ImageIcon(imageURL).getImage();
            } else {
                // Coba path alternatif
                imageURL = getClass().getResource("/Image/loginbg.png");
                if (imageURL != null) {
                    image = new ImageIcon(imageURL).getImage();
                } else {
                    System.out.println("Image not found: backgroundlog.jpg");
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (image != null) {
            Graphics2D gd = (Graphics2D) grphcs.create();
            gd.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            gd.dispose();
        }
    }
}