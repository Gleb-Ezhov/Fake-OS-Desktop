package edu.psuti.pe.gui.apps.dolphin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class EntryPointItemMouseListener implements MouseListener {
    private Window dolphinWindow;

    public EntryPointItemMouseListener(Window dolphinWindow) {
        this.dolphinWindow = dolphinWindow;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (dolphinWindow.isThereSelectedItem()) {
            dolphinWindow.resetSelectionOnAllItems();
        }

        EntryPointItemPanel thisComponent = (EntryPointItemPanel) e.getComponent();
        thisComponent.setOpaque(true);
        thisComponent.setBackground(new Color(122, 180, 220));
        thisComponent.repaint();

        thisComponent.isSelected = true;
        dolphinWindow.setSelectedItemExistenceFlag(true);
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
