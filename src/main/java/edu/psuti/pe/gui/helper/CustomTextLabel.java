package edu.psuti.pe.gui.helper;

import javax.swing.*;
import java.awt.*;

// Настраиваемый лейбл с текстом
public class CustomTextLabel extends JLabel {

    public CustomTextLabel(String text, Font font, String color, boolean isWrapUpEnabled, int wrapUpWidth) {
        setOpaque(false);
        setBackground(Color.orange);
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);

        setFont(font);
        if (isWrapUpEnabled) {
            String labelText = String.format(
                "<html>" +
                    "<div style='width:%dpx; text-align:center; color:%s;'>" +
                        "%s" +
                    "</div>" +
                "</html>",
                wrapUpWidth, color, text);
            setText(labelText);
        } else {
            String labelText = String.format(
                "<html>" +
                    "<div style='text-align:center; color:%s;'>" +
                        "%s" +
                    "</div>" +
                "</html>",
                color, text);
            setText(labelText);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g2D);
    }
}
