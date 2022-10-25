package edu.psuti.pe.gui.helper;

import javax.swing.*;
import java.awt.*;

public class FilledLayeredPane extends JLayeredPane {
    /**
     * Layout each of the components in this JLayeredPane so that they all fill
     * the entire extents of the layered pane -- from (0,0) to (getWidth(), getHeight())
     */
    @Override
    public void doLayout() {
        System.out.println("Doing FilledLayeredPane layouting");
        super.doLayout();
        // Synchronizing on getTreeLock, because I see other layouts doing that.
        // see BorderLayout::layoutContainer(Container)
        synchronized (getTreeLock()) {
            int w = getWidth();
            int h = getHeight();
            for (Component c : getComponents()) {
                // Код ниже показывает как можно изменять некоторые компоненты с изменением размеров.
                // Например, размер шрифта может изменяться относительно новых размеров слоя.
                // В данном случае это касается только JLayeredPane.PALETTE_LAYER слоя.
//                if (getLayer(c) == JLayeredPane.PALETTE_LAYER) {
//                    c.setFont(new Font("Serif", Font.PLAIN, (int) (getHeight() * 0.8)));
//                }
                c.setBounds(0, 0, w, h);
            }
        }
    }
}
