package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;
import edu.psuti.pe.gui.window.WindowPanel;
import edu.psuti.pe.gui.window.WindowsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AppTabPanel extends JPanel {
    private ImageHelper imageHelper = ImageHelper.getInstance();
    private WindowsManager windowsManager = WindowsManager.getInstance(null);

    // Полоска подсвечивающая активное окно на панели задач
    private JPanel glowingPanel = new JPanel();
    // Панель для иконки и названия программы
    private JPanel tabPanel = new JPanel();
    // Иконка программы
    private JPanel iconPanel = new JPanel();
    private JLabel iconLabel = new JLabel();
    // Название программы
    private JPanel namePanel = new JPanel();
    private CustomTextLabel appNameLabel;

    private WindowPanel associatedWindow;

    public AppTabPanel(String appIconResource, String appTitle, WindowPanel associatedWindow) {
        this.associatedWindow = associatedWindow;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addMouseListener(new AppTabPanelMouseListener());
        setOpaque(false);
        setBackground(Color.GREEN);

        setMinimumSize(new Dimension(100, 45));
        setPreferredSize(new Dimension(200, 45));
        setMaximumSize(new Dimension(200, 45));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));

        setupGlowingPanel();
        setupIconPanel(appIconResource, appTitle);
        setupNamePanel(appTitle);
        setupTabPanel();

        add(glowingPanel);
        add(tabPanel);
    }

    private void setupGlowingPanel() {
        glowingPanel.setBackground(new Color(122, 180, 220));
        glowingPanel.setOpaque(true);
        glowingPanel.setMinimumSize(new Dimension(100, 3));
        glowingPanel.setPreferredSize(new Dimension(200, 3));
        glowingPanel.setMaximumSize(new Dimension(200, 3));
    }

    private void setupIconPanel(String appIconResource, String appTitle) {
        iconPanel.setOpaque(false);
        iconPanel.setBackground(Color.orange);
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.PAGE_AXIS));

        iconPanel.setMinimumSize(new Dimension(42, 42));
        iconPanel.setPreferredSize(new Dimension(42, 42));
        iconPanel.setMaximumSize(new Dimension(42, 42));
        iconPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        iconLabel.setIcon(imageHelper.createImageIconFromSvg(appIconResource, appTitle + "tab",
                30, 30));

        iconPanel.add(iconLabel);
    }

    private void setupNamePanel(String appTitle) {
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS));
        namePanel.setBackground(Color.yellow);
        namePanel.setOpaque(false);

        namePanel.setMinimumSize(new Dimension(58, 42));
        namePanel.setPreferredSize(new Dimension(158, 42));
        namePanel.setMaximumSize(new Dimension(158, 42));
        namePanel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));

        appNameLabel = new CustomTextLabel(appTitle,
                new Font("Noto Sans Regular", Font.PLAIN, 13),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);
        appNameLabel.setBackground(Color.MAGENTA);
        appNameLabel.setOpaque(false);

        // Клей сверху и снизу, чтобы выровнить лейбл
        namePanel.add(Box.createVerticalGlue());
        namePanel.add(appNameLabel);
        namePanel.add(Box.createVerticalGlue());
    }

    private void setupTabPanel() {
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.LINE_AXIS));
        tabPanel.setOpaque(false);
        tabPanel.setBackground(Color.cyan);

        tabPanel.setMinimumSize(new Dimension(100, 42));
        tabPanel.setPreferredSize(new Dimension(200, 42));
        tabPanel.setMaximumSize(new Dimension(200, 42));

        tabPanel.add(iconPanel);
        tabPanel.add(namePanel);
    }

    public void setTabInactive() {
        glowingPanel.setBackground(new Color(144, 150, 156));
        glowingPanel.repaint();
    }

    public void setTabActive() {
        glowingPanel.setBackground(new Color(122, 180, 220));
        glowingPanel.repaint();
    }

    class AppTabPanelMouseListener extends TaskBarMouseListener {
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
            windowsManager.unhideWindow(associatedWindow);
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
