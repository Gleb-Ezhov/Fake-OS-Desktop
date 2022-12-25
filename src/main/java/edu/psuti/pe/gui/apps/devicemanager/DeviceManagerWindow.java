package edu.psuti.pe.gui.apps.devicemanager;

import edu.psuti.pe.gui.window.WindowPanel;

import javax.swing.*;
import java.awt.*;

public class DeviceManagerWindow extends WindowPanel {
    // Главная панель
    private JPanel mainPanel = new JPanel();
    // Панель с информацией по выбранному пункту
    private InformationPanel informationPanel = new InformationPanel();
    // Панель с пунктами меню
    private MenuItemsPanel menuItemsPanel = new MenuItemsPanel(informationPanel);

    public DeviceManagerWindow(String appIconResource, String appTitle, int width, int height) {
        super(appIconResource, appTitle, width, height);

        setupMainPanel();

        getContentPanel().add(mainPanel);
    }

    private void setupMainPanel() {
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(247, 247, 247));

        mainPanel.setMinimumSize(new Dimension(300, 300));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        GridBagConstraints menuItemsConstraints = new GridBagConstraints();
        menuItemsConstraints.gridx = 0;
        menuItemsConstraints.gridy = 0;
        menuItemsConstraints.fill = GridBagConstraints.VERTICAL;

        GridBagConstraints informationConstraints = new GridBagConstraints();
        // даже небольшой вес будет отнимать всё оставшееся пространство для панелей
        informationConstraints.weightx = 0.5;
        informationConstraints.weighty = 0.5;
        informationConstraints.fill = GridBagConstraints.BOTH;
        informationConstraints.gridx = 2;
        informationConstraints.gridy = 0;

        GridBagConstraints strutConstraints = new GridBagConstraints();
        strutConstraints.gridx = 1;
        strutConstraints.gridy = 0;

        mainPanel.add(menuItemsPanel, menuItemsConstraints);
        mainPanel.add(Box.createHorizontalStrut(0), strutConstraints);
        mainPanel.add(informationPanel, informationConstraints);
        strutConstraints.gridx = 3;
        mainPanel.add(Box.createHorizontalStrut(0), strutConstraints);
    }
}
