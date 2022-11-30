package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

// Панель виджета даты и времени системы
//TODO: Необходимо сделать рабочее время и дату, календарь на mouseListener

public class DateTimeWidgetPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel panel = new JPanel();
    private JPanel dateTimePanel = new JPanel();
    private JLabel dateTimeLabel = new JLabel();

    Font customFont = new Font("Noto Sans Regular", Font.PLAIN, 13);
    public DateTimeWidgetPanel()
    {   setupMainPanel();
        setupDataTimePanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(120, 23));
        panel.setPreferredSize(new Dimension(120, 23));
        panel.setMaximumSize(new Dimension(120, 23));
        panel.setOpaque(false);
        panel.add(dateTimePanel);
    }

    private void setupDataTimePanel() {
        dateTimePanel.setOpaque(false);
        dateTimePanel.addMouseListener(new TaskBarMouseListener());
        dateTimePanel.setLayout(new BoxLayout(dateTimePanel, BoxLayout.PAGE_AXIS));

        dateTimePanel.setMinimumSize(new Dimension(120, 23));
        dateTimePanel.setPreferredSize(new Dimension(120, 23));
        dateTimePanel.setMaximumSize(new Dimension(120, 23));

        dateTimeLabel.setFont(customFont);
        dateTimeLabel.setText(String.valueOf(Calendar.getInstance().getTime()));

        dateTimePanel.add(dateTimeLabel);
    }
}
