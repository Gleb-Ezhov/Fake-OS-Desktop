/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */

package edu.psuti.pe.gui.helper;

import javax.swing.*;
import java.awt.*;

/**
 * The "missing icon" is a white box with a black border and a red x.
 * It's used to display something when there are issues loading an
 * icon from an external location.
 *
 * Иконка-заглушка, которую можно применять, если картинки для нужной
 * иконки пока нет и она возвращает null.
 *
 * @author Collin Fagan
 * @date 7/25/2007
 */
public class MissingIcon implements Icon {
    private int width = 32;
    private int height = 32;

    private BasicStroke stroke = new BasicStroke(4);

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x + 1 ,y + 1,width - 2 ,height - 2);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 1 ,y + 1,width - 2 ,height - 2);

        g2d.setColor(Color.RED);

        g2d.setStroke(stroke);
        g2d.drawLine(x + 10, y + 10, x + width - 10, y + height - 10);
        g2d.drawLine(x + 10, y + height - 10, x + width - 10, y + 10);

        g2d.dispose();
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
}
