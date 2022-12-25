package edu.psuti.pe.gui.apps.devicemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsPanel extends JPanel {
    private DevicesLabelPanel devicesLabelPanel = new DevicesLabelPanel();
    // Список с точками входа
    private ArrayList<MenuItemPanel> menuItemPanels = new ArrayList<>(5);
    boolean selectedItemExistenceFlag = false;
    // InformationPanel для его дальнейшей передачи пунктам меню
    InformationPanel informationPanel;

    public MenuItemsPanel(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
        setBackground(Color.pink);
        addMouseListener(new MenuItemsPanel.MenuItemsMouseListener());
        // 255 30
        setMinimumSize(new Dimension(255, 600));
        setPreferredSize(new Dimension(255, 600));
        setMaximumSize(new Dimension(255, 600));

        // Добавление дочерних элементов панели с точками входа
        add(devicesLabelPanel);
        fillEntryPointPanel(menuItemPanels);
    }

    private void fillEntryPointPanel(List<MenuItemPanel> items) {
        items.add(new MenuItemPanel("Операционная система", "start.svgz",
                "Operating System", MenuItemType.OS, this, informationPanel));
        items.add(new MenuItemPanel("ЦП", "devicemanager/cpu.svg",
                "CPU", MenuItemType.CPU, this, informationPanel));
        items.add(new MenuItemPanel("Оперативная память", "devicemanager/media-flash.svg",
                "Random Access Memory", MenuItemType.RAM, this, informationPanel));
        items.add(new MenuItemPanel("Hard Disk Drive", "devicemanager/hdd.svg",
                "Hard Disk Drive", MenuItemType.HDD, this, informationPanel));
        items.add(new MenuItemPanel("Сетевой интерфейс", "devicemanager/network.svg",
                "Network Interface", MenuItemType.NETWORK, this, informationPanel));

        for (MenuItemPanel item : items) {
            add(item);
        }
    }

    public boolean isThereSelectedItem() { return selectedItemExistenceFlag; }
    public void setSelectedItemExistenceFlag(boolean bool) { selectedItemExistenceFlag = bool; }

    public void resetSelectionOnAllItems() {
        for (MenuItemPanel item : menuItemPanels) {
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
        g2d.drawLine(254, 0, 254, getHeight());
    }

    class MenuItemsMouseListener implements MouseListener {
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
