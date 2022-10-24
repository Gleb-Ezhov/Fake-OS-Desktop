package edu.psuti.pe.gui.taskbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TaskBarMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

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
