package edu.psuti.pe.gui;

import javax.swing.*;
import java.awt.*;

public class DesktopPanel {
    private final int rows = 9;  // 9 строк
    private final int cols = 17; // 17 иконок в строке

    private JPanel panel = new JPanel(new GridLayout(rows, cols));

    public DesktopPanel() {
        panel.setOpaque(false);
        //fillLayout();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void fillLayout() {
        int count = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; j++) {
                ++count;
                panel.add(new Button("app " + count));
            }
        }
    }
}
