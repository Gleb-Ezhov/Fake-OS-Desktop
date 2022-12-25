package edu.psuti.pe.gui.apps.about;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

public class AboutEntryPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    
    private JLabel iconLabel;
    private JPanel textPanel = new JPanel();
    private CustomTextLabel textLabel;

    public AboutEntryPanel(String imageResource, String text) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(Color.gray);
        setOpaque(false);

        setMinimumSize(new Dimension(350, 60));
        setPreferredSize(new Dimension(350, 60));
        setMaximumSize(new Dimension(350, 60));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        setupIconLabel(imageResource, imageResource);
        setupTextPanel(text);

        add(Box.createHorizontalStrut(6));
        add(iconLabel);
        add(textPanel);
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

        textPanel.setMinimumSize(new Dimension(280, 50));
        textPanel.setPreferredSize(new Dimension(280, 50));
        textPanel.setMaximumSize(new Dimension(280, 50));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        textLabel = new CustomTextLabel(text,
                new Font("Noto Sans Regular", Font.PLAIN, 15),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(textLabel);
        textPanel.add(Box.createVerticalGlue());
    }
}
