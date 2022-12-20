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
        // Сетка заполняется GridTile'ами
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; j++) {
                GridTile tile = new GridTile();
                gridPanel.add(tile);
            }
        }

        // В соответствующие тайлы добавляются ярлыки стандартных приложений
        AppIconPanel iconStart = new AppIconPanel("start.svgz", "Placeholder App Icon");
        AppIconPanel iconDolphin = new AppIconPanel("dolphin.svg", "Dolphin");
        AppIconPanel iconKonsole = new AppIconPanel("konsole.svg", "Konsole");
        ((Container)gridPanel.getComponent(cols * 0)).add(iconStart);
        ((Container)gridPanel.getComponent(cols * 1)).add(iconDolphin);
        ((Container)gridPanel.getComponent(cols * 2)).add(iconKonsole);
    }
}
