package org.example;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private BufferedImage BufferedImage;
    private Dimension Dimension;
    JImageDisplay(int width, int height) {
        this.BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        this.Dimension = new Dimension(width, height);
        setPreferredSize(Dimension);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.BufferedImage, 0, 0, this.BufferedImage.getWidth(), this.BufferedImage.getHeight(), null);
    }
    public void clearImage(){
        this.BufferedImage.setRGB(0, 0, this.BufferedImage.getWidth(), this.BufferedImage.getHeight(), new int[0], 0, 0);
    }
    public void drawPixel(int x, int y, int rgbColor) {
        this.BufferedImage.setRGB(x, y, rgbColor);
    }
    public BufferedImage getBufferedImage() {
        return this.BufferedImage;
    }
}
