package edu.psuti.pe.gui.helper;

import javax.swing.*;
import java.awt.*;

// Настраиваемый лейбл с текстом
public class CustomTextLabel extends JLabel {

    /**
     * Кастомный Лейбл с отформатированным текстом.
     * @param text Строка текста.
     * @param font Экземпляр Font с нужным шрифтом.
     * @param color Цвет текста в текстовом виде (e.g. "black" or "#A3F917").
     * @param isWrapUpEnabled Оборачивание текста. Если true, то текст переходит
     *                        к новой строке, если он достиг границы для переноса.
     * @param wrapUpWidth Граница для переноса текста в px.
     */
    public CustomTextLabel(String text, Font font, String color,
                           boolean isWrapUpEnabled, int wrapUpWidth) {
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

    /**
     * Кастомный Лейбл с отформатированным текстом.
     * @param text Строка текста.
     * @param font Экземпляр Font с нужным шрифтом.
     * @param color Цвет текста в текстовом виде (e.g. "black" or "#A3F917").
     * @param isWrapUpEnabled Оборачивание текста. Если true, то текст переходит
     *                        к новой строке, если он достиг границы для переноса.
     * @param wrapUpWidth Граница для переноса текста в px.
     * @param verticalAlignment Выравнивание текста в данном лейбле
     *                          по веритикали. Например: JLabel.CENTER .
     * @param horizontalAlignment Выравнивание текста по горизонтали.
     */
    public CustomTextLabel(String text, Font font, String color,
                           boolean isWrapUpEnabled, int wrapUpWidth,
                           int verticalAlignment,
                           int horizontalAlignment) {
        setOpaque(false);
        setBackground(Color.orange);
        setVerticalAlignment(verticalAlignment);
        setHorizontalAlignment(horizontalAlignment);

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
