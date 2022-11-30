package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

// Панель виджета edit paste системы
public class EditPasteWidgetPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel panel = new JPanel();
    private JPanel editPastePanel = new JPanel();
    private JLabel editPasteLabel = new JLabel();

    public EditPasteWidgetPanel()
    {   setupMainPanel();
        setupEditPastePanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(23, 23));
        panel.setPreferredSize(new Dimension(23, 23));
        panel.setMaximumSize(new Dimension(23, 23));
        panel.setOpaque(false);
        panel.add(editPastePanel);
    }

    private void setupEditPastePanel() {
        editPastePanel.setOpaque(false);
        editPastePanel.addMouseListener(new TaskBarMouseListener());
        editPastePanel.setLayout(new BoxLayout(editPastePanel, BoxLayout.PAGE_AXIS));

        editPastePanel.setMinimumSize(new Dimension(23, 23));
        editPastePanel.setPreferredSize(new Dimension(23, 23));
        editPastePanel.setMaximumSize(new Dimension(23, 23));

        editPasteLabel.setIcon(imageHelper.createImageIconFromSvg("edit-paste.svg", "Edit Paste Button",
                23, 23));

        editPastePanel.add(editPasteLabel);
    }

    // Класс-слушатель событий для виджета edit paste
    class EditPasteMouseListener extends TaskBarMouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            System.exit(0);
        }
    }
}
