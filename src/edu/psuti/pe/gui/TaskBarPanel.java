package edu.psuti.pe.gui;

import javax.swing.*;
import java.awt.*;

public class TaskBarPanel {
    private JPanel panel = new JPanel();

    public TaskBarPanel() {
        // BoxLayout.PAGE_AXIS включает режим компоновки элементов "слева направо"
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setPreferredSize(new Dimension(1280, 45));
        panel.setMaximumSize(new Dimension(1280, 45));
        panel.setOpaque(true);
        panel.setBackground(new Color(214, 223, 232));
        Button tempStartButton = new Button(" ");
        tempStartButton.setBackground(Color.WHITE);
        panel.add(tempStartButton);
        panel.add(Box.createRigidArea(new Dimension(1235, 45)));
    }

    public JPanel getPanel() {
        return panel;
    }
}
