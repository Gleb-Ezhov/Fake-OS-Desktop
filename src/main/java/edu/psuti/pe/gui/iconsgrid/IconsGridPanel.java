package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.*;

public class IconsGridPanel extends JPanel {
    private final int rows = 9;  // 9 строк
    private final int cols = 17; // 17 иконок в строке
    // Панель самой решётки
    private JPanel gridPanel = new JPanel(new GridLayout(rows, cols));

    public IconsGridPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        gridPanel.setOpaque(false);

        add(gridPanel);
        add(Box.createRigidArea(new Dimension(Integer.MAX_VALUE, 45)));

        fillLayout();
    }

    private void fillLayout() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; j++) {
                GridTile tile = new GridTile();

                if (i == 0 && (j == 0 || j == 1)) {
                    AppIconPanel icon = new AppIconPanel("start.svgz", "Placeholder App Icon");
                    tile.add(icon);
                }

                gridPanel.add(tile);
            }
        }
    }
}
