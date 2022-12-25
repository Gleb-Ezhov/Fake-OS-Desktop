package edu.psuti.pe.gui.apps.devicemanager;

import edu.psuti.pe.gui.helper.CustomTextLabel;

import javax.swing.*;
import java.awt.*;

// Панель для блока с надписью "Устройства"
public class DevicesLabelPanel extends JPanel {
    // Панель с надписью "Устройства"
    private JPanel textPanel = new JPanel();
    private JLabel textLabel;
    // Панель узкой серой полосы
    private JPanel grayLinePanel = new JPanel();

    public DevicesLabelPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(true);
        setBackground(new Color(222, 224, 226));
        setMinimumSize(new Dimension(254, 41));
        setPreferredSize(new Dimension(254, 41));
        setMaximumSize(new Dimension(254, 41));

        // Настройка панели с надписью "Устройства"
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setOpaque(false);
        textPanel.setMinimumSize(new Dimension(254, 40));
        textPanel.setPreferredSize(new Dimension(254, 40));
        textPanel.setMaximumSize(new Dimension(254, 40));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        textLabel = new CustomTextLabel("Устройства",
                new Font("Noto Sans Regular", Font.PLAIN, 16),
                "black", false, 0,
                JLabel.TOP, JLabel.LEFT);
        textLabel.setBackground(Color.red);
        textLabel.setOpaque(false);
        textPanel.add(textLabel);

        // Настройка серой полоски
        grayLinePanel.setBackground(new Color(175, 178, 180));
        grayLinePanel.setOpaque(true);
        grayLinePanel.setMinimumSize(new Dimension(254, 1));
        grayLinePanel.setPreferredSize(new Dimension(254, 1));
        grayLinePanel.setMaximumSize(new Dimension(254, 1));

        add(textPanel);
        add(grayLinePanel);
    }
}
