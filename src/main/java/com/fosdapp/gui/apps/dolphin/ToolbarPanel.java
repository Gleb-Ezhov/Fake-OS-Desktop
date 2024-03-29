package com.fosdapp.gui.apps.dolphin;

import com.fosdapp.gui.helper.CustomTextLabel;
import com.fosdapp.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

// Панель инструментов программы Dolphin
public class ToolbarPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    // Кнопка "Назад"
    private ToolbarButtonPanel goPreviousBtnPanel;
    // Кнопка "Вперёд"
    private ToolbarButtonPanel goNextBtnPanel;
    // Полоска-разделитель
    private JPanel delimiterPanel = new JPanel();
    // Кнопка "Значки"
    private ToolbarButtonPanel iconsBtnPanel;
    // Кнопка "Столбцы"
    private ToolbarButtonPanel columnsBtnPanel;
    // Кнопка "Таблица"
    private ToolbarButtonPanel tableBtnPanel;
    // Текст пути
    private JPanel pathPanel = new JPanel();
    private CustomTextLabel pathLabel;
    // Кнопка "Разделить область просмотра на две панели"
    private ToolbarButtonPanel splitViewportBtnPanel;
    // Кнопка "Поиск файлов и папок"
    private ToolbarButtonPanel searchFilesBtnPanel;
    // Кнопка "Открыть меню"
    private ToolbarButtonPanel toolbarMenuBtnPanel;

    public String currentPath;

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
        setupPathPanel();

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
        add(pathPanel);
        add(Box.createHorizontalGlue());
        add(Box.createHorizontalStrut(4));
        add(splitViewportBtnPanel);
        add(Box.createHorizontalStrut(2));
        add(searchFilesBtnPanel);
        add(Box.createHorizontalStrut(2));
        add(toolbarMenuBtnPanel);
    }

    public void updatePath(String path) {
        currentPath = path;
        pathPanel.remove(pathLabel);
        pathLabel = new CustomTextLabel(path, new Font("Noto Sans Regular", Font.PLAIN, 14),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);
        pathPanel.add(pathLabel);
        pathPanel.validate();
    }

    private void setupAllButtons() {
        goPreviousBtnPanel = new ToolbarButtonPanel("go-previous.svg", "Go Previous", ToolbarButtonType.PREVIOUS, this);
        goNextBtnPanel = new ToolbarButtonPanel("go-next.svg", "Go Next", ToolbarButtonType.NEXT, this);
        iconsBtnPanel = new ToolbarButtonPanel("view-list-icons.svg", "Icons view of files", ToolbarButtonType.ICONS, this);
        columnsBtnPanel = new ToolbarButtonPanel("view-list-details.svg", "Columns view of files", ToolbarButtonType.COLUMNS, this);
        tableBtnPanel = new ToolbarButtonPanel("view-list-tree.svg", "Table view of files", ToolbarButtonType.TABLE, this);
        splitViewportBtnPanel = new ToolbarButtonPanel("view-right-new.svg", "Split viewport into two panes", ToolbarButtonType.SPLIT, this);
        searchFilesBtnPanel = new ToolbarButtonPanel("edit-find.svg", "Search for files and folders", ToolbarButtonType.SEARCH, this);
        toolbarMenuBtnPanel = new ToolbarButtonPanel("application-menu.svg", "Open toolbar menu", ToolbarButtonType.MENU, this);
    }

    private void setupDelimiterPanel() {
        delimiterPanel.setOpaque(true);
        delimiterPanel.setBackground(new Color(175, 178, 180));
        delimiterPanel.setMinimumSize(new Dimension(1, 34));
        delimiterPanel.setPreferredSize(new Dimension(1, 34));
        delimiterPanel.setMaximumSize(new Dimension(1, 34));
    }

    private void setupPathPanel() {
        pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.LINE_AXIS));
        pathPanel.setOpaque(false);
        pathPanel.setBackground(Color.green);

        pathLabel = new CustomTextLabel("placeholder text", new Font("Noto Sans Regular", Font.PLAIN, 14),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);
        pathLabel.setOpaque(false);
        pathLabel.setBackground(Color.lightGray);

        pathPanel.add(pathLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(175, 178, 180));
        g2d.drawLine(0, 44, getWidth(), 44);
    }
}
