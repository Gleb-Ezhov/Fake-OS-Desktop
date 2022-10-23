package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Панель виджета блокировки/выключения системы
public class SystemBlockShutdownWidgetPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel panel = new JPanel();
    private JPanel blockPanel = new JPanel();
    private JLabel blockLabel = new JLabel();
    private JPanel shutdownPanel = new JPanel();
    private JLabel shutdownLabel = new JLabel();

    public SystemBlockShutdownWidgetPanel() {
        setupMainPanel();
        setupBlockPanel();
        setupShutdownPanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(23, 45));
        panel.setPreferredSize(new Dimension(23, 45));
        panel.setMaximumSize(new Dimension(23, 45));
        panel.setOpaque(false);
        panel.add(blockPanel);
        panel.add(shutdownPanel);
    }

    private void setupBlockPanel() {
        blockPanel.setOpaque(false);
        blockPanel.addMouseListener(new BlockPanelMouseListener());
        blockPanel.setLayout(new BoxLayout(blockPanel, BoxLayout.PAGE_AXIS));

        blockPanel.setMinimumSize(new Dimension(23, 23));
        blockPanel.setPreferredSize(new Dimension(23, 23));
        blockPanel.setMaximumSize(new Dimension(23, 23));

        blockLabel.setIcon(imageHelper.createImageIconFromSvg("system-lock-screen.svg", "Lock Screen Button",
                23, 23));

        blockPanel.add(blockLabel);
    }

    private void setupShutdownPanel() {
        shutdownPanel.setOpaque(false);
        shutdownPanel.addMouseListener(new ShutdownPanelMouseListener());
        shutdownPanel.setLayout(new BoxLayout(shutdownPanel, BoxLayout.PAGE_AXIS));

        shutdownPanel.setMinimumSize(new Dimension(23, 22));
        shutdownPanel.setPreferredSize(new Dimension(23, 22));
        shutdownPanel.setMaximumSize(new Dimension(23, 22));

        shutdownLabel.setIcon(imageHelper.createImageIconFromSvg("system-shutdown.svg", "System Shutdown Button",
                23, 23));

        shutdownPanel.add(shutdownLabel);
    }

    // todo сделать общий TaskBarMouseListener, который будет подсвечивать элементы, а событие нажатия можно переопределять в подклассе (цель - избавиться от повторяющегося кода в слушателях)
    // КЛАССЫ-СЛУШАТЕЛИ СОБЫТИЙ ДЛЯ ПАНЕЛЕЙ БЛКИРОВКИ ЭКРАНА И ВЫКЛЮЧЕНИЯ

    class BlockPanelMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(144, 150, 156));
            thisComponent.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(184, 192, 200));
            thisComponent.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(184, 192, 200));
            thisComponent.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(false);
            thisComponent.repaint();
        }
    }

    class ShutdownPanelMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(144, 150, 156));
            thisComponent.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(184, 192, 200));
            thisComponent.repaint();
            System.exit(0);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(new Color(184, 192, 200));
            thisComponent.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(false);
            thisComponent.repaint();
        }
    }
}
