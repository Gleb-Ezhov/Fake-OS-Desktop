package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.ImageHelper;
import edu.psuti.pe.gui.helper.RoundedBorder;

import javax.swing.*;
import java.awt.*;

// Панель инструментов программы Dolphin
public class ToolbarPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    // Кнопка "Назад"
    private JPanel goPreviousBtnPanel = new JPanel();
    private JLabel goPreviousBtnLabel = new JLabel();
    // Кнопка "Вперёд"
    private JPanel goNextBtnPanel = new JPanel();
    private JLabel goNextBtnLabel = new JLabel();
    // Полоска-разделитель
    private JPanel delimiterPanel = new JPanel();
    // Кнопка "Значки"
    private JPanel iconsBtnPanel = new JPanel();
    private JLabel iconsBtnLabel = new JLabel();
    // Кнопка "Столбцы"
    private JPanel columnsBtnPanel = new JPanel();
    private JLabel columnsBtnLabel = new JLabel();
    // Кнопка "Таблица"
    private JPanel tableBtnPanel = new JPanel();
    private JLabel tableBtnLabel = new JLabel();
    // Кнопка "Разделить область просмотра на две панели"
    private JPanel splitViewportBtnPanel = new JPanel();
    private JLabel splitViewportBtnLabel = new JLabel();
    // Кнопка "Поиск файлов и папок"
    private JPanel searchFilesBtnPanel = new JPanel();
    private JLabel searchFilesBtnLabel = new JLabel();
    // Кнопка "Открыть меню"
    private JPanel toolbarMenuBtnPanel = new JPanel();
    private JLabel toolbarMenuBtnLabel = new JLabel();

    public ToolbarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setOpaque(true);
        setBackground(new Color(222, 224, 226));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 45));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 45));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Настройка/инициализация дочерних элементов
        setupAllButtons();
        setupDelimiterPanel();

        // Добавление всех дочерних элементов
        add(goPreviousBtnPanel);
        add(Box.createHorizontalStrut(2));
        add(goNextBtnPanel);
        add(Box.createHorizontalStrut(4));
        add(delimiterPanel);
        add(Box.createHorizontalStrut(4));
        add(iconsBtnPanel);
        add(Box.createHorizontalStrut(2));
        add(columnsBtnPanel);
        add(Box.createHorizontalStrut(2));
        add(tableBtnPanel);
        add(Box.createHorizontalStrut(15));
        add(Box.createHorizontalGlue());
        add(Box.createHorizontalStrut(4));
        add(splitViewportBtnPanel);
        add(Box.createHorizontalStrut(2));
        add(searchFilesBtnPanel);
        add(Box.createHorizontalStrut(2));
        add(toolbarMenuBtnPanel);
    }

    private void setupAllButtons() {
        setupToolbarButton(goPreviousBtnPanel, goPreviousBtnLabel, "go-previous.svg", "Go Previous");
        setupToolbarButton(goNextBtnPanel, goNextBtnLabel, "go-next.svg", "Go Next");
        setupToolbarButton(iconsBtnPanel, iconsBtnLabel, "view-list-icons.svg", "Icons view of files");
        setupToolbarButton(columnsBtnPanel, columnsBtnLabel, "view-list-details.svg", "Columns view of files");
        setupToolbarButton(tableBtnPanel, tableBtnLabel, "view-list-tree.svg", "Table view of files");
        setupToolbarButton(splitViewportBtnPanel, splitViewportBtnLabel, "view-right-new.svg", "Split viewport into two panes");
        setupToolbarButton(searchFilesBtnPanel, searchFilesBtnLabel, "edit-find.svg", "Search for files and folders");
        setupToolbarButton(toolbarMenuBtnPanel, toolbarMenuBtnLabel, "application-menu.svg", "Open toolbar menu");
    }

    private void setupToolbarButton(JPanel panel, JLabel label, String imageName, String imageDescription) {
        panel.setOpaque(false);
        panel.addMouseListener(new ToolbarButtonMouseListener());
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.setMinimumSize(new Dimension(34, 34));
        panel.setPreferredSize(new Dimension(34, 34));
        panel.setMaximumSize(new Dimension(34, 34));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        label.setIcon(imageHelper.createImageIconFromSvg(imageName,
                imageDescription, 24, 24));

        panel.add(label);
    }

    private void setupDelimiterPanel() {
        delimiterPanel.setOpaque(true);
        delimiterPanel.setBackground(new Color(175, 178, 180));
        delimiterPanel.setMinimumSize(new Dimension(1, 34));
        delimiterPanel.setPreferredSize(new Dimension(1, 34));
        delimiterPanel.setMaximumSize(new Dimension(1, 34));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(175, 178, 180));
        g2d.drawLine(0, 44, getWidth(), 44);
    }
}
