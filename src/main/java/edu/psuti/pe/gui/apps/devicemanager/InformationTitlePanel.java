package edu.psuti.pe.gui.apps.devicemanager;

import edu.psuti.pe.gui.helper.CustomTextLabel;

import javax.swing.*;
import java.awt.*;

// Панель для блока с надписью текущего пункта меню
public class InformationTitlePanel extends JPanel {
    // Панель с надписью
    private JPanel textPanel = new JPanel();
    private JLabel textLabel;
    // Панель узкой серой полосы
    private JPanel grayLinePanel = new JPanel();

    public InformationTitlePanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(true);
        setBackground(new Color(222, 224, 226));
        setMinimumSize(new Dimension(254, 41));
        setPreferredSize(new Dimension(254, 41));
        setMaximumSize(new Dimension(254, 41));

        // Настройка панели с надписью "Устройства"
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setOpaque(false);
        textPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
        textPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        textPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        textLabel = new CustomTextLabel("Диспетчер задач",
                new Font("Noto Sans Regular", Font.PLAIN, 18),
                "black", false, 0,
                JLabel.TOP, JLabel.LEFT);
        textLabel.setBackground(Color.red);
        textLabel.setOpaque(false);
        textPanel.add(textLabel);

        // Настройка серой полоски
        grayLinePanel.setBackground(new Color(175, 178, 180));
        grayLinePanel.setOpaque(true);
        grayLinePanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 1));
        grayLinePanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
        grayLinePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        add(textPanel);
        add(grayLinePanel);
    }

    public void updateTitle(String text) {
        textPanel.remove(0);
        textLabel = new CustomTextLabel(text,
                new Font("Noto Sans Regular", Font.PLAIN, 18),
                "black", false, 0,
                JLabel.TOP, JLabel.LEFT);
        textPanel.add(textLabel);
        textPanel.validate();
    }
}
