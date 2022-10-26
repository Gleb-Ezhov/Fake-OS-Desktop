package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

/**
 * Класс, описывающий отдельную плитку на сетке рабочего стола: её внешний вид и работу в качестве цели дропа.
 */
public class GridTile extends JPanel {
    private DropTarget dropTarget;
    private DropHandler dropHandler;

    public GridTile() {
        super();
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (dropTarget == null) {
            dropHandler = new DropHandler();
            dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
            System.out.println("Drop Target for tile has been setted up.");
        }
    }

    @Override
    public void removeNotify() {
        if (dropTarget != null) {
            dropTarget.removeDropTargetListener(dropHandler);
            dropHandler = null;
            System.out.println("Drop Target for tile was disposed.");
        }

        dropTarget = null;
        super.removeNotify();
    }
}
