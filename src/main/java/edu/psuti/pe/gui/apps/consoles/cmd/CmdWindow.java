package edu.psuti.pe.gui.apps.consoles.cmd;

import edu.psuti.pe.gui.apps.consoles.cmd.ConsoleTextArea;
import edu.psuti.pe.gui.window.WindowPanel;

import javax.swing.*;
import java.awt.*;

public class CmdWindow extends WindowPanel {
    // Главная панель с GridBagLayout компоновкой для всех дочерних элементов
    private JPanel mainPanel = new JPanel();
    // Текстовая область терминала
    private ConsoleTextArea consoleArea = new ConsoleTextArea();

    public CmdWindow(String appIconResource, String appTitle, int width, int height) {
        super(appIconResource, appTitle, width, height);
        setupMainPanel();
        getContentPanel().add(mainPanel);
    }

    private void setupMainPanel() {
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(222, 224, 226));

        mainPanel.setMinimumSize(new Dimension(100, 100));
        mainPanel.setPreferredSize(new Dimension(200, 200));
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        GridBagConstraints consoleConstraints = new GridBagConstraints();
        // даже небольшой вес будет отнимать всё оставшееся пространство для панелей
        consoleConstraints.weightx = 0.5;
        consoleConstraints.weighty = 0.5;
        consoleConstraints.fill = GridBagConstraints.BOTH;
        consoleConstraints.gridx = 0;
        consoleConstraints.gridy = 0;

        mainPanel.add(consoleArea, consoleConstraints);
    }
}
