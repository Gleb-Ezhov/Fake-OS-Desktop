package com.fosdapp.gui.taskbar;

import com.fosdapp.gui.window.WindowCreator;
import com.fosdapp.gui.helper.CustomTextLabel;
import com.fosdapp.gui.helper.ImageHelper;
import com.fosdapp.gui.helper.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenuItemPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    WindowCreator windowCreator = WindowCreator.getInstance();

    private JLabel iconLabel;
    private JPanel textPanel = new JPanel();
    private CustomTextLabel textLabel;

    private String appName;
    private String imageResource;
    private TaskBarPanel taskBarPanel;

    public StartMenuItemPanel(String appName, String imageResource, TaskBarPanel taskBarPanel) {
        this.appName = appName;
        this.imageResource = imageResource;
        this.taskBarPanel = taskBarPanel;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        addMouseListener(new StartMenuItemMouseListener());
        setBackground(Color.gray);
        setOpaque(false);

        setMinimumSize(new Dimension(280, 45));
        setPreferredSize(new Dimension(280, 45));
        setMaximumSize(new Dimension(280, 45));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        setupIconLabel(imageResource, appName);
        setupTextPanel(appName);

        add(Box.createHorizontalStrut(6));
        add(iconLabel);
        add(textPanel);

        // Без этого хака видно как лэйаут смещается при первом добавлении границы
        setBorder(new RoundedBorder(new Color(120, 183, 223, 255), 8, new Insets(0, 0, 0, 0)));
        setBorder(null);
    }

    private void setupIconLabel(String imageResource, String description) {
        if (imageResource.endsWith(".jpg")) {
            ImageIcon icon = (ImageIcon)imageHelper.createImageIcon(imageResource, description);
            Image image = icon.getImage();
            Image newImg = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            iconLabel = new JLabel(new ImageIcon(newImg));
        } else {
            iconLabel = new JLabel(imageHelper.createImageIconFromSvg(imageResource,
                    description, 35, 35));
        }
        iconLabel.setOpaque(false);
        iconLabel.setBackground(Color.green);
    }

    private void setupTextPanel(String text) {
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.setBackground(Color.yellow);
        textPanel.setOpaque(false);

        textPanel.setMinimumSize(new Dimension(224, 35));
        textPanel.setPreferredSize(new Dimension(224, 35));
        textPanel.setMaximumSize(new Dimension(224, 35));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        textLabel = new CustomTextLabel(text,
                new Font("Noto Sans Regular", Font.PLAIN, 15),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(textLabel);
        textPanel.add(Box.createVerticalGlue());
    }

    private class StartMenuItemMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(120, 183, 223, 180));
            thisComponent.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            windowCreator.createWindow(imageResource, appName);
            mouseEntered(e);
            taskBarPanel.deactivateStartMenuPanel();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBorder(new RoundedBorder(new Color(120, 183, 223, 255), 8, new Insets(0, 0, 0, 0)));
            thisComponent.setBackground(new Color(120, 183, 223, 100));
            thisComponent.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setBorder(null);
            thisComponent.setOpaque(false);
            thisComponent.repaint();
        }
    }
}
