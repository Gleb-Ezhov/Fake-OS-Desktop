package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

// Панель виджета виджета корзины
public class TrashBinWidgetPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel panel = new JPanel();
    private JPanel trashBinPanel = new JPanel();
    private JLabel trashBinLabel = new JLabel();

    public TrashBinWidgetPanel()
    {   setupMainPanel();
        setupTrashBinPanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(23, 23));
        panel.setPreferredSize(new Dimension(23, 23));
        panel.setMaximumSize(new Dimension(23, 23));
        panel.setOpaque(false);
        panel.add(trashBinPanel);
    }

    private void setupTrashBinPanel() {
        trashBinPanel.setOpaque(false);
        trashBinPanel.addMouseListener(new TaskBarMouseListener());
        trashBinPanel.setLayout(new BoxLayout(trashBinPanel, BoxLayout.PAGE_AXIS));

        trashBinPanel.setMinimumSize(new Dimension(23, 23));
        trashBinPanel.setPreferredSize(new Dimension(23, 23));
        trashBinPanel.setMaximumSize(new Dimension(23, 23));

        trashBinLabel.setIcon(imageHelper.createImageIconFromSvg("user-trash.svg", "Trash Bin Button",
                23, 23));

        trashBinPanel.add(trashBinLabel);
    }

    // Класс-слушатель событий для виджета корзины
    class TrashBinPanelMouseListener extends TaskBarMouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            System.exit(0);
        }
    }
}
