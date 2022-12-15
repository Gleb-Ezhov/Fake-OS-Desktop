package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

// Элемента из списка выведенных файлов
public class FilesViewportItemPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    // Значок
    private JPanel iconPanel = new JPanel();
    private JLabel iconLabel = new JLabel();
    // Текст
    private JPanel textPanel = new JPanel();
    private CustomTextLabel textLabel;

    public boolean isFile;
    public String name;

    public boolean isSelected = false;

    public FilesViewportItemPanel(String name, boolean isFile, FilesViewportPanel filesViewport) {
        this.name = name;
        this.isFile = isFile;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        addMouseListener(new FilesViewportItemMouseListener(filesViewport));
        setBackground(Color.gray);
        setOpaque(false);

        setMinimumSize(new Dimension(400, 30));
        setPreferredSize(new Dimension(400, 30));
        setMaximumSize(new Dimension(400, 30));
        setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        setupIconPanel();
        setupTextPanel();

        add(iconPanel);
        add(textPanel);
    }

    private void setupIconPanel() {
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.PAGE_AXIS));
        iconPanel.setBackground(Color.red);
        iconPanel.setOpaque(false);

        iconPanel.setMinimumSize(new Dimension(28, 26));
        iconPanel.setPreferredSize(new Dimension(28, 26));
        iconPanel.setMaximumSize(new Dimension(28, 26));

        if (isFile) {
            iconLabel.setIcon(imageHelper.createImageIconFromSvg("dolphin/file.svg",
                    name, 28, 24));
        } else {
            iconLabel.setIcon(imageHelper.createImageIconFromSvg("dolphin/folder.svg",
                    name, 28, 24));
        }

        iconPanel.add(iconLabel);
    }

    private void setupTextPanel() {
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.setBackground(Color.yellow);
        textPanel.setOpaque(false);

        textPanel.setMinimumSize(new Dimension(364, 26));
        textPanel.setPreferredSize(new Dimension(364, 26));
        textPanel.setMaximumSize(new Dimension(364, 26));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        textLabel = new CustomTextLabel(name,
                new Font("Noto Sans Regular", Font.PLAIN, 14),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);
        textLabel.setBackground(Color.MAGENTA);
        textLabel.setOpaque(false);

        // Клей сверху и снизу, чтобы выровнить лейбл
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(textLabel);
        textPanel.add(Box.createVerticalGlue());
    }
}
