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
    // todo viewport списка файлов

    public Window(String appIconResource, String appTitle, int width, int height) {
        super(appIconResource, appTitle, width, height);

        setupMainPanel();

        getContentPanel().add(toolbarPanel);
        getContentPanel().add(mainPanel);
    }

    private void setupMainPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.setBackground(new Color(239, 240, 241));

        mainPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        mainPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        mainPanel.add(entryPointsPanel);
    }
}
