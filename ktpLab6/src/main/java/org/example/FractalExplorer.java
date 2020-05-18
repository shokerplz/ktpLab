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
    private int rowsRemain;

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
        enableUI(false);
        this.rowsRemain = dispSize;
        for (int i = 0; i < dispSize; i++) {
            FractalWorker line = new FractalWorker(i);
            line.execute();
        }
    }

    private void enableUI(boolean val) {
        saveButton.setEnabled(val);
        resetButton.setEnabled(val);
        cBox.setEnabled(val);
    }

    private class FractalWorker extends SwingWorker<Object, Object> {
        private int yCoord;
        private int[] colorSpace;
        FractalWorker(int yCoord) {
            this.yCoord = yCoord;
        }
        protected Object doInBackground() {
            this.colorSpace = new int[dispSize];
            double yCoord = FractalGenerator.getCoord(planePart.y, planePart.y + planePart.width, dispSize, this.yCoord);
            for (int j = 0; j < dispSize; j++) {
                double xCoord = FractalGenerator.getCoord(planePart.x, planePart.x + planePart.width, dispSize, j);
                double numIters = generator.numIterations(xCoord, yCoord);
                if (numIters == -1) {
                    colorSpace[j] = 0;
                } else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    colorSpace[j] = rgbColor;
                }
            }
            return null;
        }
        public void done() {
            for (int i = 0; i < dispSize; i++) {
                display.drawPixel(i, yCoord, colorSpace[i]);
            }
            display.repaint(0, 0, yCoord, dispSize, 1);
            rowsRemain -= 1;
            if (rowsRemain == 0) {
                enableUI(true);
            }
        }
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
            if (rowsRemain != 0) {
                return;
            }
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
