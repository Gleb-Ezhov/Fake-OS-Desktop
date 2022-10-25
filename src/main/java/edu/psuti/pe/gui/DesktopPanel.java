package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.FilledLayeredPane;
import edu.psuti.pe.gui.taskbar.TaskBarPanel;

import javax.swing.*;
import java.awt.*;

// Панель рабочего стола. Отвечает за многослойное управление сеткой иконок программ и их открытыми окнами.
public class DesktopPanel extends JPanel {
    /**
     * Кастомная многослойная панель, которая автоматически растягивается на всю DesktopPanel.
     * Именно она послойно содержит все элементы рабочего стола.
     */
    private FilledLayeredPane mainLayeredPane = new FilledLayeredPane();
    // Панель задач
    private TaskBarPanel taskBarPanel = new TaskBarPanel();

    private final int rows = 9;  // 9 строк
    private final int cols = 17; // 17 иконок в строке

    // Сетка иконок программ
    // todo: Создать отдельный класс для сетки иконок с панелью, содержащей сетку наверху и заполнитель под панель задач внизу
    private JPanel iconsGridPanel = new JPanel(new GridLayout(rows, cols));
    // Пространство для окон
    private JPanel workspacePanel = new JPanel(null); // absolute positioning

    public DesktopPanel() {
        // Настройка корневой панели JPanel
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);

        // Настройка сетки значков-ярлыков и рабочего пространства
        iconsGridPanel.setOpaque(false);
        iconsGridPanel.setBackground(Color.YELLOW);
        //fillLayout();
        workspacePanel.setOpaque(false);
        workspacePanel.setBackground(Color.cyan);

        addChildrenComponents();
    }

    private void addChildrenComponents() {
        mainLayeredPane.add(iconsGridPanel, new Integer(0));          // 1-ый слой: сетка иконок
        mainLayeredPane.add(workspacePanel, new Integer(1));          // 2-ой слой: рабочее пространство
        mainLayeredPane.add(taskBarPanel.getPanel(), new Integer(2)); // 3-ий слой: панель задач
        add(mainLayeredPane);
    }

    public JComponent getPanel() { return this; }

    public JPanel getWorkspacePanel() { return workspacePanel; }

    private void fillLayout() {
        int count = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; j++) {
                ++count;
                iconsGridPanel.add(new JButton("app " + count));
            }
        }
    }
}
