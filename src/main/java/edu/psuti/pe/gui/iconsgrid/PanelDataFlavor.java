package edu.psuti.pe.gui.iconsgrid;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;

/**
 * Признак данных иконки приложения для перемещения drag'n'drop'ом.
 * Небольшой класс для изоляции статической общеиспользуемой переменной.
 */
public class PanelDataFlavor extends DataFlavor {
    public static final PanelDataFlavor SHARED_INSTANCE = new PanelDataFlavor();

    public PanelDataFlavor() {
        super(JPanel.class, null);
    }
}
