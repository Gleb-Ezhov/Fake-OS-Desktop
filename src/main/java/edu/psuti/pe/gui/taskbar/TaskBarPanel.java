package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.ImageHelper;
import edu.psuti.pe.gui.window.WindowsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TaskBarPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private final WindowsManager windowsManager = WindowsManager.getInstance(null);

    // Главная панель для слоя с панелью задач
    private JPanel mainPanel = new JPanel();
    // Панель "Меню запуска приложений"
    private StartMenuPanel startMenuPanel = new StartMenuPanel(this);
    JPanel helperPanel; // вспомогательная панель для выравнивания "Меню запуска приложений"
    // Непосредственно панель задач
    private JPanel taskBarPanel = new JPanel();
    // Панель для кнопки запуска "Меню приложений"
    private JPanel startButtonPanel = new JPanel();
    private JLabel startButtonLabel = new JLabel();
    // Кнопка "Параметры системы"
    private JPanel systemSettingsBtnPanel = new JPanel();
    private JLabel systemSettingsBtnLabel = new JLabel();
    // Кнопка "Корзина"
    private JPanel trashBinBtnPanel = new JPanel();
    private JLabel trashBinBtnLabel = new JLabel();
    // Виджет блокировки/выключения системы
    SystemBlockShutdownWidgetPanel blockOffWidget = new SystemBlockShutdownWidgetPanel();
    // Виджет "Состояние и уведомления"
    StatusAndNotificationsWidgetPanel sttsAndNtfctnsWidget = new StatusAndNotificationsWidgetPanel();
    // Виджет "Цифровые часы"
    DateTimeWidgetPanel dateTimeWidgetPanel = new DateTimeWidgetPanel();

    boolean isStartMenuActivated = false;

    public TaskBarPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setOpaque(false);
        mainPanel.setBackground(Color.GREEN);

        // BoxLayout.LINE_AXIS включает режим компоновки элементов "слева направо"
        taskBarPanel.setLayout(new BoxLayout(taskBarPanel, BoxLayout.LINE_AXIS));
        taskBarPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 45));
        taskBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        taskBarPanel.setOpaque(true);
        taskBarPanel.setBackground(new Color(214, 223, 232));

        // Настройка/инциализация дочерних элементов панели задач
        setupStartButtonPanel();
        setupSystemSettingsBtnPanel();
        setupTrashBinBtnPanel();
        helperPanel = new JPanel(); // Панель, помогающая разместить меню по левому краю
        helperPanel.setLayout(new BoxLayout(helperPanel, BoxLayout.LINE_AXIS));
        helperPanel.setOpaque(false);
        helperPanel.add(startMenuPanel);
        helperPanel.add(Box.createHorizontalGlue());

        // Добавление всех дочерних элементов в панель задач
        taskBarPanel.add(startButtonPanel);
        taskBarPanel.add(Box.createHorizontalGlue()); // горизонтальный "наполнитель-клей"
        taskBarPanel.add(systemSettingsBtnPanel);
        taskBarPanel.add(trashBinBtnPanel);
        taskBarPanel.add(sttsAndNtfctnsWidget);
        taskBarPanel.add(dateTimeWidgetPanel);
        taskBarPanel.add(blockOffWidget.getPanel());

        // Набивка главной панели заполнителем и добавление вниз контейнера панели задач
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(taskBarPanel);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public void activateStartMenuPanel() {
        mainPanel.add(helperPanel, 1);
        mainPanel.validate();
        mainPanel.repaint();
        isStartMenuActivated = true;
    }

    public void deactivateStartMenuPanel() {
        mainPanel.remove(helperPanel);
        mainPanel.validate();
        mainPanel.repaint();
        isStartMenuActivated = false;
    }

    public void addAppTabPanel(JPanel appTabPanel) {
        int indexToInsertNewTab = windowsManager.getWindowsCount() + 1;
        taskBarPanel.add(appTabPanel, indexToInsertNewTab);
        taskBarPanel.validate();
    }

    public void removeAppTabPanel(JPanel appTabPanel) {
        taskBarPanel.remove(appTabPanel);
        taskBarPanel.validate();
    }

    private void setupStartButtonPanel() {
        startButtonPanel.setOpaque(false);
        startButtonPanel.addMouseListener(new TaskBarMouseListener());
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel, BoxLayout.PAGE_AXIS));
        startButtonPanel.addMouseListener(new StartButtonPanelMouseListener());

        startButtonPanel.setMinimumSize(new Dimension(45, 45));
        startButtonPanel.setPreferredSize(new Dimension(45, 45));
        startButtonPanel.setMaximumSize(new Dimension(45, 45));
        startButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        startButtonLabel.setIcon(imageHelper.createImageIconFromSvg("start.svgz", "Start Application Launcher Menu Button",
                35, 35));

        startButtonPanel.add(startButtonLabel);
    }

    private void setupSystemSettingsBtnPanel() {
        systemSettingsBtnPanel.setOpaque(false);
        systemSettingsBtnPanel.addMouseListener(new TaskBarMouseListener());
        systemSettingsBtnPanel.setLayout(new BoxLayout(systemSettingsBtnPanel, BoxLayout.PAGE_AXIS));

        systemSettingsBtnPanel.setMinimumSize(new Dimension(45, 45));
        systemSettingsBtnPanel.setPreferredSize(new Dimension(45, 45));
        systemSettingsBtnPanel.setMaximumSize(new Dimension(45, 45));
        systemSettingsBtnPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        systemSettingsBtnLabel.setIcon(imageHelper.createImageIconFromSvg("systemsettings.svg", "System settings button",
                35, 35));

        systemSettingsBtnPanel.add(systemSettingsBtnLabel);
    }

    private void setupTrashBinBtnPanel() {
        trashBinBtnPanel.setOpaque(false);
        trashBinBtnPanel.addMouseListener(new TaskBarMouseListener());
        trashBinBtnPanel.setLayout(new BoxLayout(trashBinBtnPanel, BoxLayout.PAGE_AXIS));

        trashBinBtnPanel.setMinimumSize(new Dimension(45, 45));
        trashBinBtnPanel.setPreferredSize(new Dimension(45, 45));
        trashBinBtnPanel.setMaximumSize(new Dimension(45, 45));
        trashBinBtnPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        trashBinBtnLabel.setIcon(imageHelper.createImageIconFromSvg("user-trash.svg", "Trash bin button",
                35, 35));

        trashBinBtnPanel.add(trashBinBtnLabel);
    }

    class StartButtonPanelMouseListener extends TaskBarMouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (isStartMenuActivated) {
                deactivateStartMenuPanel();
            } else {
                activateStartMenuPanel();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
        }
    }
}
