package edu.psuti.pe.gui.apps.devicemanager;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

public class MenuItemPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    // Панель для иконки и текста
    private JPanel iconAndTextPanel = new JPanel();
    private JLabel iconLabel = new JLabel();
    private JPanel textPanel = new JPanel();
    private CustomTextLabel textLabel;
    // Панель узкой серой полосы
    private JPanel grayLinePanel = new JPanel();

    public MenuItemType type;
    public boolean isSelected = false;

    public MenuItemPanel(String text, String imageResource,
                               String description, MenuItemType type,
                               MenuItemsPanel menuItemsPanel, InformationPanel informationPanel) {
        this.type = type;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addMouseListener(new MenuItemMouseListener(menuItemsPanel, informationPanel));
        setBackground(Color.gray);
        setOpaque(false);

        setMinimumSize(new Dimension(254, 30));
        setPreferredSize(new Dimension(254, 30));
        setMaximumSize(new Dimension(254, 30));

        setupIconLabel(imageResource, description);
        setupTextPanel(text);
        setupIconAndTextPanel();
        setupGrayLinePanel();

        add(iconAndTextPanel);
        add(grayLinePanel);
    }

    private void setupIconLabel(String imageResource, String description) {
        iconLabel.setIcon(imageHelper.createImageIconFromSvg(imageResource,
                description, 22, 18));
        iconLabel.setOpaque(false);
        iconLabel.setBackground(Color.green);
    }

    private void setupTextPanel(String text) {
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.setBackground(Color.yellow);
        textPanel.setOpaque(false);

        textPanel.setMinimumSize(new Dimension(160, 20));
        textPanel.setPreferredSize(new Dimension(160, 20));
        textPanel.setMaximumSize(new Dimension(160, 20));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        textLabel = new CustomTextLabel(text,
                new Font("Noto Sans Regular", Font.PLAIN, 13),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(textLabel);
        textPanel.add(Box.createVerticalGlue());
    }

    private void setupIconAndTextPanel() {
        iconAndTextPanel.setLayout(new BoxLayout(iconAndTextPanel, BoxLayout.LINE_AXIS));
        iconAndTextPanel.setBackground(Color.gray);
        iconAndTextPanel.setOpaque(false);

        iconAndTextPanel.setMinimumSize(new Dimension(224, 29));
        iconAndTextPanel.setPreferredSize(new Dimension(224, 29));
        iconAndTextPanel.setMaximumSize(new Dimension(224, 29));
        iconAndTextPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        iconAndTextPanel.add(iconLabel);
        iconAndTextPanel.add(textPanel);
    }

    private void setupGrayLinePanel() {
        grayLinePanel.setBackground(new Color(232, 231, 230));
        grayLinePanel.setOpaque(true);
        grayLinePanel.setMinimumSize(new Dimension(244, 1));
        grayLinePanel.setPreferredSize(new Dimension(244, 1));
        grayLinePanel.setMaximumSize(new Dimension(244, 1));
        grayLinePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
    }
}
