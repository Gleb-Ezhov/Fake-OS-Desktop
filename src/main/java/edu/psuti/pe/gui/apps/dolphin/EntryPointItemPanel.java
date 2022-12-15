package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

public class EntryPointItemPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    private JLabel iconLabel = new JLabel();
    private JPanel textPanel = new JPanel();
    private CustomTextLabel textLabel;

    public EntryPointItemType type;
    public boolean isSelected = false;

    public EntryPointItemPanel(String text, String imageResource,
                               String description, EntryPointItemType type,
                               EntryPointsPanel entryPointsPanel, FilesViewportPanel filesViewportPanel) {
        this.type = type;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        addMouseListener(new EntryPointItemMouseListener(entryPointsPanel, filesViewportPanel));
        setBackground(Color.gray);
        setOpaque(false);

        setMinimumSize(new Dimension(190, 26));
        setPreferredSize(new Dimension(190, 26));
        setMaximumSize(new Dimension(190, 26));
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        setupIconLabel(imageResource, description);
        setupTextPanel(text);

        add(iconLabel);
        add(textPanel);
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

        textPanel.setMinimumSize(new Dimension(160, 18));
        textPanel.setPreferredSize(new Dimension(160, 18));
        textPanel.setMaximumSize(new Dimension(160, 18));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        textLabel = new CustomTextLabel(text,
                new Font("Noto Sans Regular", Font.PLAIN, 13),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);

        textPanel.add(textLabel);
    }
}
