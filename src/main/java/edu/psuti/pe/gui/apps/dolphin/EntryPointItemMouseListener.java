package edu.psuti.pe.gui.apps.dolphin;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;

public class EntryPointItemMouseListener implements MouseListener {
    private EntryPointsPanel parentPanel;
    private FilesViewportPanel filesViewportPanel;

    public EntryPointItemMouseListener(EntryPointsPanel entryPointsPanel, FilesViewportPanel filesViewportPanel) {
        this.parentPanel = entryPointsPanel;
        this.filesViewportPanel = filesViewportPanel;
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

        // Не открываются директории, в которых содержатся ссылки (из них не достаётся FileInfo).
        switch (thisComponent.type) {
            case HOME:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/"));
                break;
            case DESKTOP:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/Рабочий стол/"));
                break;
            case DOCS:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/Документы/"));
                break;
            case DOWNLOADS:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/Загрузки/"));
                break;
            case MUSIC:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/Музыка/"));
                break;
            case IMAGES:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/Изображения/"));
                break;
            case VIDEO:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/Видео/"));
                break;
            case TRASH:
                filesViewportPanel.updateList(Paths.get(System.getProperty("user.home") + "/.local/share/Trash/files"));
                break;
        }
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
