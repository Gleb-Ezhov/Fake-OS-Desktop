package edu.psuti.pe.gui.apps.devicemanager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuItemMouseListener implements MouseListener {
    private MenuItemsPanel parentPanel;
    private InformationPanel informationPanel;
    private HardwareInfo hardwareInfo = new HardwareInfo();

    public MenuItemMouseListener(MenuItemsPanel menuItemsPanel, InformationPanel informationPanel) {
        this.parentPanel = menuItemsPanel;
        this.informationPanel = informationPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (parentPanel.isThereSelectedItem()) {
            parentPanel.resetSelectionOnAllItems();
        }

        MenuItemPanel thisComponent = (MenuItemPanel) e.getComponent();
        thisComponent.setOpaque(true);
        thisComponent.setBackground(new Color(122, 180, 220));
        thisComponent.repaint();

        thisComponent.isSelected = true;
        parentPanel.setSelectedItemExistenceFlag(true);

        switch (thisComponent.type) {
            case OS:
                informationPanel.updateInformation(hardwareInfo.osInfo, MenuItemType.OS);
                break;

            case CPU:
                informationPanel.updateInformation(hardwareInfo.cpuInfo, MenuItemType.CPU);
                break;

            case RAM:
                informationPanel.updateInformation(hardwareInfo.ramInfo, MenuItemType.RAM);
                break;

            case HDD:
                informationPanel.updateInformation(hardwareInfo.hddInfo, MenuItemType.HDD);
                break;

            case NETWORK:
                informationPanel.updateInformation(hardwareInfo.networkInfo, MenuItemType.NETWORK);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MenuItemPanel thisComponent = (MenuItemPanel) e.getComponent();
        if (!thisComponent.isSelected) {
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(208, 221, 230));
            thisComponent.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MenuItemPanel thisComponent = (MenuItemPanel) e.getComponent();
        if (!thisComponent.isSelected) {
            thisComponent.setOpaque(false);
            thisComponent.repaint();
        }
    }
}
