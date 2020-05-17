package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer {
    private int dispSize;
    private JImageDisplay display;
    private FractalGenerator generator;
    private Rectangle2D.Double planePart;
    private JComboBox cBox;

    JFrame frame;
    JButton resetButton;
    JButton saveButton;
    JLabel label;

    FractalExplorer(int dispSize) {
        this.dispSize = dispSize;
        this.generator = new BurningShip();
        this.planePart = new Rectangle2D.Double(0, 0,0,0);
    }
    public void createAndShowGUI() {
        frame = new JFrame("Фрактал");
        display = new JImageDisplay(dispSize, dispSize);
        resetButton = new JButton("Сброс");
        saveButton = new JButton("Сохранить");
        resetButton.setActionCommand("reset");
        saveButton.setActionCommand("save");
        label = new JLabel("Фрактал: ");
        cBox = new JComboBox();
        cBox.addItem(new Mandelbrot());
        cBox.addItem(new Tricorn());
        cBox.addItem(new BurningShip());
        JPanel panel = new JPanel();
        JPanel btnPanel = new JPanel();
        panel.add(label);
        panel.add(cBox);
        btnPanel.add(resetButton);
        btnPanel.add(saveButton);


        ActionHandler aHandler = new ActionHandler();
        MouseHandler mHandler = new MouseHandler();
        resetButton.addActionListener(aHandler);
        saveButton.addActionListener(aHandler);
        display.addMouseListener(mHandler);

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(display, java.awt.BorderLayout.CENTER);
        frame.add(btnPanel, java.awt.BorderLayout.SOUTH);
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
            if (e.getActionCommand() == "reset") {
                generator = (FractalGenerator) cBox.getSelectedItem();
                generator.getInitialRange(planePart);
                drawFractal();
            }
            else if (e.getActionCommand() == "save") {
                JFileChooser chooser = new javax.swing.JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int res = chooser.showSaveDialog(display);

                if (res == JFileChooser.APPROVE_OPTION) {
                    try {
                        javax.imageio.ImageIO.write(display.BufferedImage,
                                "png", chooser.getSelectedFile());
                    } catch (Exception e1) {
                        javax.swing.JOptionPane.showMessageDialog(display,
                                e1.getMessage(), "Ошибка сохранения ихображения",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    return;
                }
            }

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
