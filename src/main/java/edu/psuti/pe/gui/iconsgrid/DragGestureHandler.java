package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;

/**
 * Обработчик жеста перетаскивания. Реализует методы слушателя возникновения такого жеста,
 * а также слушателя объекта-источника перетаскивания.
 */
public class DragGestureHandler implements DragGestureListener, DragSourceListener, Serializable {
    private Container parent;
    private JPanel child;

    public DragGestureHandler(JPanel child) { this.child = child; }

    public JPanel getChildPanel() { return child; }

    public void setParent(Container parent) { this.parent = parent; }

    public Container getParent() { return parent; }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        // When the drag begins, we need to grab a reference to the
        // parent container so we can return it if the drop is rejected
        Container parent = child.getParent();
        System.out.println("parent = " + parent.hashCode());
        setParent(parent);

        // Create our transferable wrapper
        System.out.println("Drag " + getChildPanel().hashCode());
        Transferable transferableObj = new TransferablePanel(child);

        // Start the "drag" process...
        DragSource ds = dge.getDragSource();
        ds.startDrag(dge, Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR), transferableObj, this);

        // Remove the panel from the parent.  If we don't do this, it can cause serialization issues.
        // We could overcome this by allowing the drop target to remove the component, but that's
        // an argument for another day
        parent.remove(child);

        // Update the display
        parent.invalidate();
        parent.repaint();
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) { }

    @Override
    public void dragOver(DragSourceDragEvent dsde) { }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) { }

    @Override
    public void dragExit(DragSourceEvent dse) { }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        // If the drop was not successful, we need to return the component back to it's previous parent
        if (!dsde.getDropSuccess()) {
            getParent().add(child);
        } else {
            getChildPanel().remove(child);
        }
        getParent().invalidate();
        getParent().repaint();
    }
}
