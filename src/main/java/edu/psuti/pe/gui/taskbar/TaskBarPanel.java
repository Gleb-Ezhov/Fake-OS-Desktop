package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TaskBarPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    // Главная панель
    private JPanel panel = new JPanel();
    // Панель для кнопки запуска "Меню приложений"
    private JPanel startButtonPanel = new JPanel();
    private JLabel startButtonLabel = new JLabel();

    public TaskBarPanel() {
        // BoxLayout.LINE_AXIS включает режим компоновки элементов "слева направо"
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 45));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        panel.setOpaque(true);
        panel.setBackground(new Color(214, 223, 232));

        // Настройка/инциализация дочерних элементов панели задач
        setupStartButtonPanel();
        SystemBlockShutdownWidgetPanel blockOffWidget = new SystemBlockShutdownWidgetPanel();

        // Добавление всех дочерних элементов
        panel.add(startButtonPanel);
        panel.add(Box.createHorizontalGlue()); // горизонтальный "наполнитель-клей"
        panel.add(blockOffWidget.getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    private void setupStartButtonPanel() {
        startButtonPanel.setOpaque(false);
        startButtonPanel.addMouseListener(new TaskBarMouseListener());
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel, BoxLayout.PAGE_AXIS));

        startButtonPanel.setMinimumSize(new Dimension(45, 45));
        startButtonPanel.setPreferredSize(new Dimension(45, 45));
        startButtonPanel.setMaximumSize(new Dimension(45, 45));
        startButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        startButtonLabel.setIcon(imageHelper.createImageIconFromSvg("start.svgz", "Start Application Launcher Menu Button",
                35, 35));

        startButtonPanel.add(startButtonLabel);
    }
}
