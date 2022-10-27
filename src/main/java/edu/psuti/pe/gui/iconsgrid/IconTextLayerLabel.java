package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.*;

/**
 * Кастомный лейбл для отдельного слоя, форматирующий текст нужным образом
 * в формате HTML и отрисовывающий текст с включённым антиэлиасингом.
 */
public class IconTextLayerLabel extends JLabel {
    public IconTextLayerLabel(String text, String textColor) {
        setupLabel(this, text, textColor);
    }

    private void setupLabel(JLabel label, String text, String textColor) {
        label.setOpaque(false);
        label.setBackground(Color.orange);
        label.setVerticalAlignment(JLabel.TOP);
        label.setMinimumSize(new Dimension(80, 37));
        label.setPreferredSize(new Dimension(80, 37));
        label.setMaximumSize(new Dimension(80, 37));

        label.setFont(new Font("Noto Sans Regular", Font.PLAIN, 13));
        String labelText = String.format(
            "<html>" +
                "<div style='width:%dpx; text-align:center; color:%s;'>" +
                    "%s" +
                "</div>" +
            "</html>",
            65, textColor, text);
        label.setText(labelText);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g2D);
    }
}
