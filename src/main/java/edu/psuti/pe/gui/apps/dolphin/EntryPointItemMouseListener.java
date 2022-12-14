package edu.psuti.pe.gui.apps.dolphin;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EntryPointItemMouseListener implements MouseListener {
    private EntryPointsPanel parentPanel;

    public EntryPointItemMouseListener(EntryPointsPanel entryPointsPanel) {
        this.parentPanel = entryPointsPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (parentPanel.isThereSelectedItem()) {
            parentPanel.resetSelectionOnAllItems();
        }

        EntryPointItemPanel thisComponent = (EntryPointItemPanel) e.getComponent();
        thisComponent.setOpaque(true);
        thisComponent.setBackground(new Color(122, 180, 220));
        thisComponent.repaint();

        thisComponent.isSelected = true;
        parentPanel.setSelectedItemExistenceFlag(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        EntryPointItemPanel thisComponent = (EntryPointItemPanel) e.getComponent();
        if (!thisComponent.isSelected) {
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(208, 221, 230));
            thisComponent.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        EntryPointItemPanel thisComponent = (EntryPointItemPanel) e.getComponent();
        if (!thisComponent.isSelected) {
            thisComponent.setOpaque(false);
            thisComponent.repaint();
        }
    }
}
