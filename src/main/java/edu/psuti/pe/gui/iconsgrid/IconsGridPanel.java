package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.*;

public class IconsGridPanel extends JPanel {
    private final int rows = 9;  // 9 строк
    private final int cols = 17; // 17 иконок в строке
    // Панель самой решётки
    private JPanel grid = new JPanel(new GridLayout(rows, cols));

    public IconsGridPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
        grid.setOpaque(false);

        add(grid);
        add(Box.createRigidArea(new Dimension(Integer.MAX_VALUE, 45)));

        fillLayout();
    }

    private void fillLayout() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; j++) {
                GridTile tile = new GridTile();

                if (i == 0) {
                    AppIconPanel icon = new AppIconPanel("start.svgz", "Placeholder App Icon");
                    tile.add(icon);
                }

                grid.add(tile);
            }
        }
    }
}
