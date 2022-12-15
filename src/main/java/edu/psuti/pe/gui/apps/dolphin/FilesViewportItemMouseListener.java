package edu.psuti.pe.gui.apps.dolphin;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Было бы хорошо этот класс и EntryPointItemMouseListener сделать одним шаблонным классом, т.к. пока дело
// касается внешнего вида, то логика идентичная. Пока оставил так.
public class FilesViewportItemMouseListener implements MouseListener {
    private FilesViewportPanel parentPanel;

    public FilesViewportItemMouseListener(FilesViewportPanel filesViewportPanel) { this.parentPanel = filesViewportPanel; }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (parentPanel.isThereSelectedItem()) {
            parentPanel.resetSelectionOnAllItems();
        }

        FilesViewportItemPanel thisComponent = (FilesViewportItemPanel) e.getComponent();
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
        FilesViewportItemPanel thisComponent = (FilesViewportItemPanel) e.getComponent();
        if (!thisComponent.isSelected) {
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(208, 221, 230));
            thisComponent.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        FilesViewportItemPanel thisComponent = (FilesViewportItemPanel) e.getComponent();
        if (!thisComponent.isSelected) {
            thisComponent.setOpaque(false);
            thisComponent.repaint();
        }
    }
}
