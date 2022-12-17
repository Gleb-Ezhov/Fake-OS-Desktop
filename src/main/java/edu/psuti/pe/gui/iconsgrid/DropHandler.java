package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;

/**
 * Слушатель за объектом, принимающим дроп.
 */
public class DropHandler implements DropTargetListener, Serializable {

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        // Determine if we can actually process the contents coming in.
        // You could try and inspect the transferable as well, but there is an issue on the MacOS under some circumstances
        // where it does not actually bundle the data until you accept the drop.
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        } else {
            dtde.rejectDrag();
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) { }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) { }

    @Override
    public void dragExit(DropTargetEvent dte) { }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        boolean success = false;

        // Basically, we want to unwrap the present...
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {
            Transferable transferable = dtde.getTransferable();

            try {
                Object data = transferable.getTransferData(PanelDataFlavor.SHARED_INSTANCE);

                // JAVA 18 pattern variable: если data есть экземпляр JPanel, то она присваивается переменной panel типа JPanel
//                if (data instanceof JPanel panel) {
                if (data instanceof JPanel) {
                    JPanel panel = (JPanel)data;

                    DropTargetContext dtc = dtde.getDropTargetContext();
                    Component dropTargetComponent = dtc.getComponent();

                    if (dropTargetComponent instanceof JComponent) {
                        // Если у данной цели дропа уже есть дочерний компонент, то дроп отменяется
                        if (isDropTargetHasChild(dropTargetComponent)) {
                            dtde.rejectDrop();
                            dtde.dropComplete(false);
                            return;
                        }

                        // Иначе дроп продолжается
                        ((JComponent)dropTargetComponent).add(panel);

                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);

                        dropTargetComponent.invalidate();
                        dropTargetComponent.repaint();
                    } else {
                        dtde.rejectDrop();
                    }
                } else {
                    dtde.rejectDrop();
                }
            } catch (Exception exp) {
                success = false;
                dtde.rejectDrop();
                exp.printStackTrace();
            }
        } else {
            dtde.rejectDrop();
        }

        dtde.dropComplete(success);
    }

    private boolean isDropTargetHasChild(Component dropTargetComponent) {
        try {
            ((JPanel)dropTargetComponent).getComponent(0);
        } catch (ArrayIndexOutOfBoundsException exc) {
            return false;
        }
        return true;
    }
}
