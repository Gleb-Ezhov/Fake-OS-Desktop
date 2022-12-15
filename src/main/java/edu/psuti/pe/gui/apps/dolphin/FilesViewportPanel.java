package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class FilesViewportPanel extends JPanel {
    private JScrollPane scrollViewportPane = new JScrollPane(); // панель прокурутки для вьюпорта с файлами
    private JPanel viewportPanel = new JPanel(); // вьюпорт со списком файлов
    private JPanel viewportStatePanel = new JPanel(); // панель снизу для краткой информации

    // Список с текущими элементами
    private ArrayList<FilesViewportItemPanel> filesViewportItems = new ArrayList<>(100);
    boolean selectedItemExistenceFlag = false;

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

    public boolean isThereSelectedItem() { return selectedItemExistenceFlag; }
    public void setSelectedItemExistenceFlag(boolean bool) { selectedItemExistenceFlag = bool; }

    public void resetSelectionOnAllItems() {
        for (FilesViewportItemPanel item : filesViewportItems) {
            item.setOpaque(false);
            item.repaint();
            item.isSelected = false;
        }
    }


    public void addItemsToViewport(Collection<FilesViewportItemPanel> items) {
        for (FilesViewportItemPanel item : items) {
            viewportPanel.add(item);
        }
        viewportPanel.validate();
        viewportPanel.repaint();
    }

    private void setupViewportPanel() {
        scrollViewportPane.setMinimumSize(new Dimension(100, 100));
        scrollViewportPane.setPreferredSize(new Dimension(100, 100));
        scrollViewportPane.setViewportView(viewportPanel); // вьюпорт с файлами становится вьюпортом для панели прокрутки

        viewportPanel.setLayout(new BoxLayout(viewportPanel, BoxLayout.PAGE_AXIS));
        viewportPanel.setOpaque(true);
        viewportPanel.setBackground(Color.white);
        // Размеры для ViewportPanel не проставлялись, чтобы он ресайзился отностельно своих элементов.
        // Ниже закгруглённая граница, как в Dolphin, но она сейчас не нужна, т.к. ScrollPane сам создал границу.
        //viewportPanel.setBorder(new RoundedBorder(new Color(175, 178, 180), 6, new Insets(0, 0, 0, 0)));

        GridBagConstraints viewportConstraints = new GridBagConstraints();
        viewportConstraints.gridx = 0;
        viewportConstraints.gridy = 1;
        viewportConstraints.weightx = 0.5;
        viewportConstraints.weighty = 0.5;
        viewportConstraints.fill = GridBagConstraints.BOTH;
        add(scrollViewportPane, viewportConstraints); // в основную панель добавляется именно панель прокурутки

        // тест вьюпорта
        viewportTest1();
    }

    public void viewportTest1() {
        for (int i = 0; i < 100; ++i) {
            if (i % 2 == 0) {
                filesViewportItems.add(new FilesViewportItemPanel(String.valueOf(i), false, this));
            } else {
                filesViewportItems.add(new FilesViewportItemPanel(String.valueOf(i), true, this));
            }
        }
        addItemsToViewport(filesViewportItems);
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
