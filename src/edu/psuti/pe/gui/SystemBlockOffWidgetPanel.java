package edu.psuti.pe.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Панель виджета блокировки/выключения системы
public class SystemBlockOffWidgetPanel implements MouseListener {
    private JPanel panel = new JPanel();
    private JPanel blockPnl = new JPanel();
    private JPanel offPnl = new JPanel();

    public SystemBlockOffWidgetPanel() {
        setupMainPanel();
        setupBlockPanel();
        setupOffPanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(23, 45));
        panel.setPreferredSize(new Dimension(23, 45));
        panel.setMaximumSize(new Dimension(23, 45));
        panel.setOpaque(false);
        panel.setBorder(new LineBorder(new Color(0,0,0)));
    }

    // todo сделать иконки для панелей блока/выключения + зарефакторить этот класс

    private void setupBlockPanel() {
        blockPnl.setBackground(new Color(255, 255, 255));
        blockPnl.setOpaque(true);
        panel.add(blockPnl);
    }

    private void setupOffPanel() {
//        offPnl.setBackground(Color.BLUE);
        offPnl.setOpaque(false);

        offPnl.addMouseListener(this);
        panel.add(offPnl);
    }

    // МЕТОДЫ, РЕАЛИЗУЮЩИЕ ИНТЕРФЕЙС MouseListener

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
