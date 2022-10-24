package edu.psuti.pe.gui;

import edu.psuti.pe.gui.taskbar.TaskBarPanel;

import javax.swing.*;
import java.awt.*;

// Контейнер верхнего уровня (корневой) для окна программы
public class FrameFakeOSDesktop extends JFrame {
    private CustomContentPane contentPane = CustomContentPane.getInstance();
    private DesktopPanel desktopPanel = new DesktopPanel();
    private TaskBarPanel taskBarPanel = new TaskBarPanel();

    // Устройство для вывода изображения на дисплей (для включения fullscreen режима)
    private static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    public FrameFakeOSDesktop() {
        System.out.println("Initializing objects...");
        setCustomContentPane();
        addChildrenComponents();
    }

    /**
     * Установка в качестве панели содержимого кастомного JPanel объекта, чтобы работать
     * с ней как с JComponent типом, а не Container. Используется BoxLayout способ компоновки.
     */
    private void setCustomContentPane() {
        // BoxLayout.PAGE_AXIS включает режим компоновки элементов "сверху вниз"
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        setContentPane(contentPane);
    }

    private void addChildrenComponents() {
        contentPane.add(desktopPanel.getPanel());
        contentPane.add(taskBarPanel.getPanel());
    }

    public void createAndShowGUI() {
        //Create and set up the window.
        setTitle("desktop prototype");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1280, 720));

        // Display the window.
        pack();
        //device.setFullScreenWindow(this);
        setVisible(true);
    }
}
