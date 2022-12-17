package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

public class AppTabPanel extends JPanel {
    private ImageHelper imageHelper = ImageHelper.getInstance();

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

    public AppTabPanel(String appIconResource, String appTitle) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
        setBackground(Color.GREEN);

        setMinimumSize(new Dimension(100, 45));
        setPreferredSize(new Dimension(200, 45));
        setMaximumSize(new Dimension(200, 45));

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
        iconPanel.setOpaque(true);
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
        appNameLabel.setOpaque(true);

        // Клей сверху и снизу, чтобы выровнить лейбл
        namePanel.add(Box.createVerticalGlue());
        namePanel.add(appNameLabel);
        namePanel.add(Box.createVerticalGlue());
    }

    private void setupTabPanel() {
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.LINE_AXIS));
        tabPanel.setOpaque(true);
        tabPanel.setBackground(Color.cyan);

        tabPanel.setMinimumSize(new Dimension(100, 42));
        tabPanel.setPreferredSize(new Dimension(200, 42));
        tabPanel.setMaximumSize(new Dimension(200, 42));

        tabPanel.add(iconPanel);
        tabPanel.add(namePanel);
    }
}
