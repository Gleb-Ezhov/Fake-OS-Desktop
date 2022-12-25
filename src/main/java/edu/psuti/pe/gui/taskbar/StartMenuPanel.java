package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

public class StartMenuPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    private JPanel userInfoPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(197, 200, 201));
            g2d.drawLine(0, 49, 300, 49);
        }
    };
    private JLabel iconLabel;
    private JPanel textPanel = new JPanel();
    private CustomTextLabel textLabel;

    private TaskBarPanel parent;

    public StartMenuPanel(TaskBarPanel taskBarPanel) {
        this.parent = taskBarPanel;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(new Color(240, 240, 241));
        setOpaque(true);

        setMinimumSize(new Dimension(300, 515));
        setPreferredSize(new Dimension(300, 515));
        setMaximumSize(new Dimension(300, 515));
        setAlignmentX(LEFT_ALIGNMENT);

        setupUserInfoPanel();

        add(userInfoPanel);
        add(Box.createVerticalStrut(10));
        addMenuItems();
        add(Box.createVerticalStrut(10));
    }

    private void setupUserInfoPanel() {
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.LINE_AXIS));
        userInfoPanel.setBackground(new Color(227, 229, 230));
        userInfoPanel.setOpaque(true);

        userInfoPanel.setMinimumSize(new Dimension(300, 50));
        userInfoPanel.setPreferredSize(new Dimension(300, 50));
        userInfoPanel.setMaximumSize(new Dimension(300, 50));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        iconLabel = new JLabel(imageHelper.createImageIconFromSvg("user-leonardo-da-vinci.svg", "Current User Avatar Image",
                35, 35));

        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.setBackground(Color.yellow);
        textPanel.setOpaque(false);

        textPanel.setMinimumSize(new Dimension(220, 49));
        textPanel.setPreferredSize(new Dimension(220, 49));
        textPanel.setMaximumSize(new Dimension(220, 49));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        textLabel = new CustomTextLabel("Super User",
                new Font("Noto Sans Regular", Font.PLAIN, 15),
                "black", false, 0,
                JLabel.CENTER, JLabel.LEFT);

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(textLabel);
        textPanel.add(Box.createVerticalGlue());

        userInfoPanel.add(iconLabel);
        userInfoPanel.add(textPanel);
    }

    private void addMenuItems() {
        add(new StartMenuItemPanel("Dolphin", "dolphin.svg", parent));
        add(new StartMenuItemPanel("Konsole", "konsole.svg", parent));
        add(new StartMenuItemPanel("Командная строка", "cmd.jpg", parent));
        add(new StartMenuItemPanel("Диспетчер устройств", "hwinfo.svg", parent));
    }
}
