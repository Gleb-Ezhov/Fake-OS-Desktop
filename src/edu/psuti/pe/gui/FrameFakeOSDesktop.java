package edu.psuti.pe.gui;

import javax.swing.*;
import java.awt.*;

public class FrameFakeOSDesktop extends JFrame {
    //private JPanel contentPane = new JPanel();
    private CustomContentPane contentPane = CustomContentPane.getInstance();
    private DesktopPanel desktopPanel = new DesktopPanel();
    private TaskBarPanel taskBarPanel = new TaskBarPanel();

    public FrameFakeOSDesktop() {
        System.out.println("Initializing objects...");
        setCustomContentPane();
        addChildrenComponents();
    }

    /**
     * Установка в качестве панели содержимого JPanel объекта, чтобы работать
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
        setLocationRelativeTo(null); // centers a frame onscreen

        // Display the window.
        pack();
        setVisible(true);
    }
}
