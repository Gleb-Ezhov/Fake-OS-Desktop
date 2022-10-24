package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ImageHelper;
import edu.psuti.pe.gui.taskbar.TaskBarPanel;

import javax.swing.*;
import java.awt.*;

// Контейнер верхнего уровня (корневой) для окна программы
public class FrameFakeOSDesktop extends JFrame {
    ImageHelper imageHelper = ImageHelper.getInstance();
    private CustomContentPane contentPane = CustomContentPane.getInstance();
    private DesktopPanel desktopPanel = new DesktopPanel();
    private TaskBarPanel taskBarPanel = new TaskBarPanel();

    // Устройство для вывода изображения на дисплей (для включения fullscreen режима)
    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    public FrameFakeOSDesktop() {
        System.out.println("Initializing objects...");
        setCustomContentPane();
        addChildrenComponents();
        //experimentingWithMovablePanels();
    }

    private void experimentingWithMovablePanels() {
        JPanel movablePanel = new JPanel();
        movablePanel.setOpaque(true);
        movablePanel.setBackground(Color.RED);
        movablePanel.setLayout(new BoxLayout(movablePanel, BoxLayout.PAGE_AXIS));
        movablePanel.setBounds(10, 10, 200, 400);
        desktopPanel.getPanel().add(movablePanel);

        // передвигатель
        ComponentMover componentMover = new ComponentMover();
        componentMover.registerComponent(movablePanel);

        // размероизменятель
        ComponentResizer cr = new ComponentResizer();
        cr.setSnapSize(new Dimension(10, 10));
        cr.registerComponent(movablePanel);
    }

    /**
     * Установка в качестве панели содержимого кастомного JPanel объекта, чтобы работать
     * с ней как с JComponent типом, а не Container. Используется BoxLayout способ компоновки.
     */
    private void setCustomContentPane() {
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
