package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.window.WindowPanel;

import javax.swing.*;
import java.awt.*;

// Окно программы Dolphin (проводник файловой системы)
public class Window extends WindowPanel {
    // Панель инструментов программы
    private ToolbarPanel toolbarPanel = new ToolbarPanel();
    // Главная панель
    private JPanel mainPanel = new JPanel();
    // Панель с точками входа в файловую систему
    private EntryPointsPanel entryPointsPanel = new EntryPointsPanel();
    // Панель с окном просмотра списка файлов
    private FilesViewportPanel filesViewportPanel = new FilesViewportPanel();

    public Window(String appIconResource, String appTitle, int width, int height) {
        super(appIconResource, appTitle, width, height);

        setupMainPanel();

        getContentPanel().add(toolbarPanel);
        getContentPanel().add(mainPanel);
    }

    private void setupMainPanel() {
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(239, 240, 241));

        mainPanel.setMinimumSize(new Dimension(1000, 200));
        mainPanel.setPreferredSize(new Dimension(1000, 200));
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        GridBagConstraints entryPointsConstraints = new GridBagConstraints();
        entryPointsConstraints.gridx = 0;
        entryPointsConstraints.gridy = 0;
        entryPointsConstraints.fill = GridBagConstraints.VERTICAL;

        GridBagConstraints filesViewportConstraints = new GridBagConstraints();
        // даже небольшой вес будет отнимать всё оставшееся пространство для панелей
        filesViewportConstraints.weightx = 0.5;
        filesViewportConstraints.weighty = 0.5;
        filesViewportConstraints.fill = GridBagConstraints.BOTH;
        filesViewportConstraints.gridx = 2;
        filesViewportConstraints.gridy = 0;

        GridBagConstraints strutConstraints = new GridBagConstraints();
        strutConstraints.gridx = 1;
        strutConstraints.gridy = 0;

        mainPanel.add(entryPointsPanel, entryPointsConstraints);
        mainPanel.add(Box.createHorizontalStrut(1), strutConstraints);
        mainPanel.add(filesViewportPanel, filesViewportConstraints);
        strutConstraints.gridx = 3;
        mainPanel.add(Box.createHorizontalStrut(1), strutConstraints);
    }
}
