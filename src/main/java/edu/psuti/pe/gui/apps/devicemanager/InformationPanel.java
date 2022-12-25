package edu.psuti.pe.gui.apps.devicemanager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InformationPanel extends JPanel {
    private InformationTitlePanel informationTitlePanel = new InformationTitlePanel();
    private JScrollPane scrollPane = new JScrollPane();
    private JTextArea textArea = new JTextArea() {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2D = (Graphics2D)g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            super.paintComponent(g2D);
        }
    };

    public InformationPanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBackground(Color.green);

        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));
        setMaximumSize(new Dimension(200, 200));

        // Настройка и добавление дочерних панелей
        setupInformationTitlePanel();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(Box.createVerticalStrut(1), constraints);
        setupScrollableTextArea();
        constraints.gridy = 3;
        add(Box.createVerticalStrut(1), constraints);
    }

    private void setupInformationTitlePanel() {
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(informationTitlePanel, titleConstraints);
    }

    private void setupScrollableTextArea() {
        scrollPane.setMinimumSize(new Dimension(100, 100));
        scrollPane.setPreferredSize(new Dimension(100, 100));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // увеличение скорости прокрутки
        scrollPane.setViewportView(textArea);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0)); // убирается декоративная граница у панели прокрутки

        textArea.setBackground(new Color(247, 247, 247));
        textArea.setForeground(Color.black);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Noto Sans Regular", Font.PLAIN, 13));
        textArea.setText("   Выберите пункт меню.");

        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();
        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 2;
        scrollPaneConstraints.weightx = 0.5;
        scrollPaneConstraints.weighty = 0.5;
        scrollPaneConstraints.fill = GridBagConstraints.BOTH;
        add(scrollPane, scrollPaneConstraints); // в основную панель добавляется именно панель прокурутки
    }

    public void updateInformation(String text, MenuItemType selectedMenuItemType) {
        textArea.setText(text);

        switch (selectedMenuItemType) {
            case OS:
                informationTitlePanel.updateTitle("Операционная система");
                break;

            case CPU:
                informationTitlePanel.updateTitle("Центральный процессор");
                break;

            case RAM:
                informationTitlePanel.updateTitle("Оперативная память");
                break;

            case HDD:
                informationTitlePanel.updateTitle("Твердотельный накопитель");
                break;

            case NETWORK:
                informationTitlePanel.updateTitle("Сетевой интерфейс");
                break;
        }
    }
}
