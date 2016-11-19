package edu.drexel.cs430.renderengine.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Angel on 11/19/2016.
 */
public class SwingDisplay extends JPanel {

    BufferedImage image;

    public SwingDisplay() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame("Image Render");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(500, 500));
            frame.setSize(new Dimension(500, 500));
            setPreferredSize(new Dimension(500, 500));
            setSize(new Dimension(500, 500));
            frame.add(this);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            image = (BufferedImage) createImage(500, 500);
        });
    }

    public void setImageArray(boolean[][] imageArray) {
        while (image == null) try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int y = 0; y < imageArray.length; y++) {
            for (int x = 0; x < imageArray.length; x++) {
                if (imageArray[y][x]) {
                    image.setRGB(x, imageArray.length - y - 1, 0);
                }
            }
        }
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }
}
