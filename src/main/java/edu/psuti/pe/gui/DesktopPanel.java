package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.FilledLayeredPane;
import edu.psuti.pe.gui.iconsgrid.IconsGridPanel;
import edu.psuti.pe.gui.taskbar.TaskBarPanel;
import edu.psuti.pe.gui.window.WindowsManager;

import javax.swing.*;
import java.awt.*;

// Панель рабочего стола. Отвечает за управление сеткой иконок программ и их открытыми окнами на разных слоях.
public class DesktopPanel extends JPanel {
    /**
     * Кастомная многослойная панель, которая автоматически растягивается на всю DesktopPanel.
     * Именно она послойно содержит все элементы рабочего стола.
     */
    private FilledLayeredPane mainLayeredPane = new FilledLayeredPane();
    // Панель задач
    private TaskBarPanel taskBarPanel = new TaskBarPanel();
    // Сетка иконок программ
    private IconsGridPanel iconsGridPanel = new IconsGridPanel();
    // Многослойное пространство для окон с абсолютным позиционированием
    private JLayeredPane workspacePanel = new JLayeredPane() {
        // получение размеров переопределено, чтобы workspace был над панелью задач (на 45px меньше по высоте)
        @Override
        public Rectangle getBounds() {
            Rectangle bounds = super.getBounds();
            bounds.setSize((int)bounds.getWidth(), (int)bounds.getHeight() - 45);
            return bounds;
        }
    };

    public DesktopPanel() {
        // Настройка корневой панели JPanel
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);

        // Настройка сетки значков-ярлыков и рабочего пространства
        iconsGridPanel.setOpaque(false);
        iconsGridPanel.setBackground(Color.YELLOW);
        workspacePanel.setOpaque(false);
        workspacePanel.setBackground(Color.cyan);
        WindowsManager.getInstance(workspacePanel); // менеджер окон получает workspace

        addChildrenComponents();
    }

    private void addChildrenComponents() {
        mainLayeredPane.add(iconsGridPanel, new Integer(0));          // 1-ый слой: сетка иконок
        mainLayeredPane.add(workspacePanel, new Integer(1));          // 2-ой слой: рабочее пространство
        mainLayeredPane.add(taskBarPanel.getPanel(), new Integer(2)); // 3-ий слой: панель задач
        add(mainLayeredPane);
    }

    public JComponent getPanel() { return this; }

    public JLayeredPane getWorkspacePanel() { return workspacePanel; }
}
