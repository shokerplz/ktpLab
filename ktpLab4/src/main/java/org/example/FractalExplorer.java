package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int dispSize;
    private JImageDisplay display;
    private FractalGenerator generator;
    private Rectangle2D.Double planePart;

    JFrame frame;
    JButton resetButton;
    JLabel label;

    FractalExplorer(int dispSize) {
        this.dispSize = dispSize;
        this.generator = new Mandelbrot();
        this.planePart = new Rectangle2D.Double(0, 0,0,0);
    }
    public void createAndShowGUI() {
        frame = new JFrame("Фрактал");
        display = new JImageDisplay(dispSize, dispSize);
        resetButton = new JButton("Сброс");
        label = new JLabel("Фрактал: ");

        ActionHandler aHandler = new ActionHandler();
        MouseHandler mHandler = new MouseHandler();
        resetButton.addActionListener(aHandler);
        display.addMouseListener(mHandler);

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(display, java.awt.BorderLayout.CENTER);
        frame.add(resetButton, java.awt.BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        for (int i = 0; i < dispSize; i++) {
            for (int j = 0; j < dispSize; j++) {
                double xCoord = FractalGenerator.getCoord(this.planePart.x, this.planePart.x + this.planePart.width, dispSize, i);
                double yCoord = FractalGenerator.getCoord(this.planePart.y, this.planePart.y + this.planePart.width, dispSize, j);
                double numIters = this.generator.numIterations(xCoord, yCoord);

                if (numIters == -1) {
                    this.display.drawPixel(i, j, 0);
                }
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    this.display.drawPixel(i, j, rgbColor);
                }
            }
        }
        this.display.repaint();
    }


    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            generator.getInitialRange(planePart);
            drawFractal();
        }
    }
    public class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            double xCoord = FractalGenerator.getCoord(planePart.x, planePart.x + planePart.width, dispSize, e.getX());
            double yCoord = FractalGenerator.getCoord(planePart.y, planePart.y + planePart.height, dispSize, e.getY());
            generator.recenterAndZoomRange(planePart, xCoord, yCoord, .5);
            drawFractal();
        }
    }
    public static void main(String[] args) {
        FractalExplorer explorer = new FractalExplorer(500);
        explorer.createAndShowGUI();
        explorer.drawFractal();
    }
}
