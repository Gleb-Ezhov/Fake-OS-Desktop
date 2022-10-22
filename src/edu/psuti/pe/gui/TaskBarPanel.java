package edu.psuti.pe.gui;

import javax.swing.*;
import java.awt.*;

public class TaskBarPanel {
    private JPanel panel = new JPanel();

    public TaskBarPanel() {
        // BoxLayout.LINE_AXIS включает режим компоновки элементов "слева направо"
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 45));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        panel.setOpaque(true);
        panel.setBackground(new Color(214, 223, 232));

        Button tempStartButton = new Button(" ");
        tempStartButton.setBackground(Color.WHITE);
        tempStartButton.setMinimumSize(new Dimension(45, 45));
        tempStartButton.setMaximumSize(new Dimension(45, 45));
        tempStartButton.setPreferredSize(new Dimension(45, 45));

        SystemBlockOffWidgetPanel blockOffWidget = new SystemBlockOffWidgetPanel();

        panel.add(tempStartButton);
        panel.add(Box.createHorizontalGlue());
        panel.add(blockOffWidget.getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }
}
