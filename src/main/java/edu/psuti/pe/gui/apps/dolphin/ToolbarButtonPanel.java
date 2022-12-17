package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

public class ToolbarButtonPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    private JLabel label = new JLabel();

    public ToolbarButtonType type;

    public ToolbarButtonPanel(String imageName, String imageDescription, ToolbarButtonType type, ToolbarPanel parent) {
        this.type = type;

        setOpaque(false);
        addMouseListener(new ToolbarButtonMouseListener(parent));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        setMinimumSize(new Dimension(34, 34));
        setPreferredSize(new Dimension(34, 34));
        setMaximumSize(new Dimension(34, 34));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        label.setIcon(imageHelper.createImageIconFromSvg(imageName,
                imageDescription, 24, 24));

        add(label);
    }
}
