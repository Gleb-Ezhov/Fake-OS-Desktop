package com.fosdapp.gui.apps.dolphin;

import com.fosdapp.gui.helper.CustomTextLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class EntryPointsPanel extends JPanel {
    // Надпись "Точки входа"
    private JPanel epLblPanel = new JPanel();
    private JLabel epLblLabel;
    // Список с точками входа
    private ArrayList<EntryPointItemPanel> entryPointItemPanels = new ArrayList<>(8);
    boolean selectedItemExistenceFlag = false;
    // FilesViewport для его дальнейшей передачи точкам входа
    FilesViewportPanel filesViewportPanel;

    public EntryPointsPanel(FilesViewportPanel filesViewportPanel) {
        this.filesViewportPanel = filesViewportPanel;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
        setBackground(Color.pink);
        addMouseListener(new EntryPointsMouseListener());

        setMinimumSize(new Dimension(200, 600));
        setPreferredSize(new Dimension(200, 600));
        setMaximumSize(new Dimension(200, 600));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

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
        add(epLblPanel);
        add(Box.createVerticalStrut(2));
        fillEntryPointPanel(entryPointItemPanels);
    }

    private void fillEntryPointPanel(List<EntryPointItemPanel> items) {
        items.add(new EntryPointItemPanel("Домашняя папка", "dolphin/home.svg",
                "Home directory", EntryPointItemType.HOME, this, filesViewportPanel));
        items.add(new EntryPointItemPanel("Рабочий стол", "dolphin/desktop.svg",
                "Desktop directory", EntryPointItemType.DESKTOP, this, filesViewportPanel));
        items.add(new EntryPointItemPanel("Документы", "dolphin/docs.svg",
                "Documents directory", EntryPointItemType.DOCS, this, filesViewportPanel));
        items.add(new EntryPointItemPanel("Загрузки", "dolphin/downloads.svg",
                "Downloads directory", EntryPointItemType.DOWNLOADS, this, filesViewportPanel));
        items.add(new EntryPointItemPanel("Музыка", "dolphin/music.svg",
                "Music directory", EntryPointItemType.MUSIC, this, filesViewportPanel));
        items.add(new EntryPointItemPanel("Изображения", "dolphin/images.svg",
                "Images directory", EntryPointItemType.IMAGES, this, filesViewportPanel));
        items.add(new EntryPointItemPanel("Видеофайлы", "dolphin/video.svg",
                "Video directory", EntryPointItemType.VIDEO, this, filesViewportPanel));
        items.add(new EntryPointItemPanel("Корзина", "dolphin/trash.svg",
                "Recycle bin directory", EntryPointItemType.TRASH, this, filesViewportPanel));

        for (EntryPointItemPanel item : items) {
            add(item);
        }
    }

    public boolean isThereSelectedItem() { return selectedItemExistenceFlag; }
    public void setSelectedItemExistenceFlag(boolean bool) { selectedItemExistenceFlag = bool; }

    public void resetSelectionOnAllItems() {
        for (EntryPointItemPanel item : entryPointItemPanels) {
            item.setOpaque(false);
            item.repaint();
            item.isSelected = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(175, 178, 180));
        g2d.drawLine(199, 0, 199, getHeight());
    }

    class EntryPointsMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            resetSelectionOnAllItems();
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
