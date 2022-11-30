package edu.psuti.pe.gui.apps.dolphin;

import edu.psuti.pe.gui.helper.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToolbarButtonMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        thisComponent.setOpaque(true);
        thisComponent.setBackground(new Color(189, 209, 225));
        thisComponent.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        thisComponent.setOpaque(false);
        thisComponent.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        thisComponent.setBorder(new RoundedBorder(new Color(122, 180, 220), 6, new Insets(5, 5, 5, 5)));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        thisComponent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
