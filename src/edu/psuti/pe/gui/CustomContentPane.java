package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Класс кастомной панели содержимого для корневого контейнера FrameFakeOSDesktop.
 * Реализует интерфейс ComponentListener, чтобы использовать самого себя в качестве слушателя событий.
 */
public class CustomContentPane extends JPanel implements ComponentListener {
    private static CustomContentPane customContentPane;

    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private Image backgroundImage;

    private CustomContentPane() {
        backgroundImage = imageHelper.getSubImageFromBufferedImage("wallpapers1920x1080.png", 0, 1000, 2570, 1080);
        addComponentListener(this);
    }

    public static CustomContentPane getInstance() {
        if (customContentPane == null) {
            customContentPane = new CustomContentPane();
            return customContentPane;
        } else {
            return customContentPane;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    // МЕТОДЫ, РЕАЛИЗУЮЩИЕ ИНТЕРФЕЙС ComponentListener

    public void componentHidden(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Hidden");
    }

    public void componentMoved(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Moved");
    }

    public void componentResized(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Resized ");
        System.out.println("Content Pane new size is " + getWidth() + " by " + getHeight());
        // Resize background image to fit content pane
        backgroundImage = imageHelper.getSubImageFromBufferedImage("wallpapers1920x1080.png", 0, 1000, getWidth(), getHeight());
        // Строку ниже можно будет использовать для скейла изображения при необходимости.
        // Во время скейла нужно соблюдать пропорции, чтобы избежать искажений.
        //backgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    }

    public void componentShown(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Shown");
    }
}
