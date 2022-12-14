package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class FilesViewportPanel extends JPanel {
    private JPanel viewportPanel = new JPanel(); // вьюпорт со списком файлов
    private JPanel viewportStatePanel = new JPanel(); // панель снизу для краткой информации

    public FilesViewportPanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBackground(Color.green);

        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));
        setMaximumSize(new Dimension(200, 200));

        // Настройка и добавление дочерних панелей
        GridBagConstraints strutConstraints = new GridBagConstraints();
        strutConstraints.gridx = 0;
        strutConstraints.gridy = 0;

        add(Box.createVerticalStrut(1), strutConstraints);
        setupViewportPanel();
        strutConstraints.gridy = 2;
        add(Box.createVerticalStrut(1), strutConstraints);
        setupViewportStatePanel();
    }

    private void setupViewportPanel() {
        viewportPanel.setLayout(new BoxLayout(viewportPanel, BoxLayout.LINE_AXIS));
        viewportPanel.setOpaque(true);
        viewportPanel.setBackground(Color.white);

        viewportPanel.setMinimumSize(new Dimension(100, 100));
        viewportPanel.setPreferredSize(new Dimension(100, 100));
        viewportPanel.setMaximumSize(new Dimension(100, 100));
        viewportPanel.setBorder(new RoundedBorder(new Color(175, 178, 180), 6, new Insets(0, 0, 0, 0)));

        GridBagConstraints viewportConstraints = new GridBagConstraints();
        viewportConstraints.gridx = 0;
        viewportConstraints.gridy = 1;
        viewportConstraints.weightx = 0.5;
        viewportConstraints.weighty = 0.5;
        viewportConstraints.fill = GridBagConstraints.BOTH;
        add(viewportPanel, viewportConstraints);
    }

    private void setupViewportStatePanel() {
        viewportStatePanel.setLayout(new BoxLayout(viewportStatePanel, BoxLayout.LINE_AXIS));
        viewportStatePanel.setOpaque(true);
        viewportStatePanel.setBackground(Color.orange);

        viewportStatePanel.setMinimumSize(new Dimension(200, 25));
        viewportStatePanel.setPreferredSize(new Dimension(200, 25));
        viewportStatePanel.setMaximumSize(new Dimension(200, 25));

        GridBagConstraints stateConstraints = new GridBagConstraints();
        stateConstraints.gridx = 0;
        stateConstraints.gridy = 3;
        stateConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(viewportStatePanel, stateConstraints);
    }
}
