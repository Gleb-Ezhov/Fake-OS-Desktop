package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ImageIconHelper;

import javax.swing.*;
import java.awt.*;

public class CustomContentPane extends JPanel {
    private final ImageIconHelper imageIconHelper = ImageIconHelper.getInstance();
    private static CustomContentPane customContentPane;

    private CustomContentPane() {}

    public static CustomContentPane getInstance() {
        if (customContentPane == null) {
            customContentPane = new CustomContentPane();
            return customContentPane;
        } else {
            return customContentPane;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        Image backgroundImage = imageIconHelper.createImageIcon("wallpapers1920x1080.png", "wallpapers").getImage();
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
