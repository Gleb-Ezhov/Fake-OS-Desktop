package edu.psuti.pe.gui.helper;

import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;

public class RoundedBorder implements Border, Serializable {
    private Color color;
    private int radius;

    public RoundedBorder(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        // Данная реализация Border не создаёт вставок в панель и не смещает её содержимое
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(color);
        g2d.drawRoundRect(x, y,width - 1, height - 1, radius, radius);
    }
}
