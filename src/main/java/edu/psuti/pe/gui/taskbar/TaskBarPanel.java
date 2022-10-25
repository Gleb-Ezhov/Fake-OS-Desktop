package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

public class TaskBarPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    // Главная панель для слоя с панелью задач
    private JPanel mainPanel = new JPanel();
    // Непосредственно панель задач
    private JPanel taskBarPanel = new JPanel();
    // Панель для кнопки запуска "Меню приложений"
    private JPanel startButtonPanel = new JPanel();
    private JLabel startButtonLabel = new JLabel();
    // Виджет блокировки/выключения системы
    SystemBlockShutdownWidgetPanel blockOffWidget = new SystemBlockShutdownWidgetPanel();

    public TaskBarPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setOpaque(false);
        mainPanel.setBackground(Color.GREEN);
        //mainPanel.setBounds(0, 0, 1280, 720);

        // BoxLayout.LINE_AXIS включает режим компоновки элементов "слева направо"
        taskBarPanel.setLayout(new BoxLayout(taskBarPanel, BoxLayout.LINE_AXIS));
        taskBarPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 45));
        taskBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        taskBarPanel.setOpaque(true);
        taskBarPanel.setBackground(new Color(214, 223, 232));

        // Настройка/инциализация дочерних элементов панели задач
        setupStartButtonPanel();

        // Добавление всех дочерних элементов в панель задач
        taskBarPanel.add(startButtonPanel);
        taskBarPanel.add(Box.createHorizontalGlue()); // горизонтальный "наполнитель-клей"
        taskBarPanel.add(blockOffWidget.getPanel());

        // Набивка главной панели заполнителем и добавление вниз контейнера панели задач
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(taskBarPanel);
    }

    public JPanel getPanel() {
        return mainPanel;
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
