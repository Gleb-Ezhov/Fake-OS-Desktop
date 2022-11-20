package edu.psuti.pe.gui.helper;

import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;

public class RoundedBorder implements Border, Serializable {
    private Color color;
    private int radius;
    private Insets insets;

    public RoundedBorder(Color color, int radius, Insets insets) {
        this.color = color;
        this.radius = radius;
        this.insets = insets;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        // Вставки в панель, создаваемые этой границей
        return insets;
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
