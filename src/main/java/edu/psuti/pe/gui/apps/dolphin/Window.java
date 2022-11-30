package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.window.WindowPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Окно программы Dolphin (проводник файловой системы)
public class Window extends WindowPanel {
    // Панель инструментов программы
    private ToolbarPanel toolbarPanel = new ToolbarPanel();
    // Главная панель
    private JPanel mainPanel = new JPanel();
    // Панель с точками входа в файловую систему
    // todo entryPointPanel вместо со списком своих айтемов просится в отдельный класс
    // todo сделать там MouseListener, чтобы снимать выделение айтема нажатием на область
    private JPanel entryPointsPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(175, 178, 180));
            g2d.drawLine(199, 0, 1, getHeight());
        }
    };
    // Надпись "Точки входа"
    private JPanel epLblPanel = new JPanel();
    private JLabel epLblLabel = new JLabel();
    // Список с точками входа
    private ArrayList<EntryPointItemPanel> entryPointItemPanels = new ArrayList<>(8);
    boolean selectedItemExistenceFlag = false;
    // todo viewport списка файлов

    public Window(String appIconResource, String appTitle, int width, int height) {
        super(appIconResource, appTitle, width, height);

        setupMainPanel();
        setupEntryPointPanel();

        getContentPanel().add(toolbarPanel);
        getContentPanel().add(mainPanel);
    }

    public boolean isThereSelectedItem() { return selectedItemExistenceFlag; }
    public void setSelectedItemExistenceFlag(boolean bool) { selectedItemExistenceFlag = bool; }

    private void setupMainPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.setBackground(new Color(239, 240, 241));

        mainPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        mainPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        mainPanel.add(entryPointsPanel);
    }

    private void setupEntryPointPanel() {
        entryPointsPanel.setLayout(new BoxLayout(entryPointsPanel, BoxLayout.PAGE_AXIS));
        entryPointsPanel.setOpaque(false);
        entryPointsPanel.setBackground(Color.pink);

        entryPointsPanel.setMinimumSize(new Dimension(200, Integer.MAX_VALUE));
        entryPointsPanel.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));
        entryPointsPanel.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
        entryPointsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Настройка надписи "Точки входа"
        epLblPanel.setLayout(new BoxLayout(epLblPanel, BoxLayout.LINE_AXIS));
        epLblPanel.setOpaque(false);
        epLblPanel.setBackground(Color.blue);
        epLblPanel.setMinimumSize(new Dimension(190, 26));
        epLblPanel.setPreferredSize(new Dimension(190, 26));
        epLblPanel.setMaximumSize(new Dimension(190, 26));

        epLblLabel = new CustomTextLabel("Точки входа",
                new Font("Noto Sans Regular", Font.PLAIN, 13),
                "#747679", false, 0,
                JLabel.TOP, JLabel.LEFT);
        epLblPanel.add(epLblLabel);

        // Добавление дочерних элементов панели с точками входа
        entryPointsPanel.add(epLblPanel);
        entryPointsPanel.add(Box.createVerticalStrut(2));
        fillEntryPointPanel(entryPointItemPanels);
    }

    private void fillEntryPointPanel(List<EntryPointItemPanel> items) {
        items.add(new EntryPointItemPanel("Домашняя папка", "dolphin/home.svg",
                "Home directory", this));
        items.add(new EntryPointItemPanel("Рабочий стол", "dolphin/desktop.svg",
                "Desktop directory", this));
        items.add(new EntryPointItemPanel("Документы", "dolphin/docs.svg",
                "Documents directory", this));
        items.add(new EntryPointItemPanel("Загрузки", "dolphin/downloads.svg",
                "Downloads directory", this));
        items.add(new EntryPointItemPanel("Музыка", "dolphin/music.svg",
                "Music directory", this));
        items.add(new EntryPointItemPanel("Изображения", "dolphin/images.svg",
                "Images directory", this));
        items.add(new EntryPointItemPanel("Видеофайлы", "dolphin/video.svg",
                "Video directory", this));
        items.add(new EntryPointItemPanel("Корзина", "dolphin/trash.svg",
                "Resycle bin directory", this));

        for (EntryPointItemPanel item : items) {
            entryPointsPanel.add(item);
        }
    }

    public void resetSelectionOnAllItems() {
        for (EntryPointItemPanel item : entryPointItemPanels) {
            item.setOpaque(false);
            item.repaint();
            item.isSelected = false;
        }
    }
}
