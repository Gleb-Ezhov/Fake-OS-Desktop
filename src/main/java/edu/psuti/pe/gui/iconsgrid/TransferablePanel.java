package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Класс-обёртка над переносимой панелью JPanel, которая определяет признаки переносимых
 * вместе с панелью данных.
 */
class TransferablePanel implements Transferable {

    private DataFlavor[] flavors = new DataFlavor[]{ PanelDataFlavor.SHARED_INSTANCE };
    private JPanel panel;

    public TransferablePanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        // Okay, for this example, this is overkill, but makes it easier
        // to add new flavor support by subclassing
        boolean supported = false;

        for (DataFlavor mine : getTransferDataFlavors()) {
            if (mine.equals(flavor)) {
                supported = true;
                break;
            }
        }
        return supported;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor))
            return panel;
        else
            throw new UnsupportedFlavorException(flavor);
    }
}
