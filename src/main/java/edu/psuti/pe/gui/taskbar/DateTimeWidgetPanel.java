package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.CustomTextLabel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeWidgetPanel extends JPanel {
    private JPanel timePanel = new JPanel();
    private JLabel timeLabel = new JLabel();
    private JPanel datePanel = new JPanel();
    private JLabel dateLabel = new JLabel();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

    public DateTimeWidgetPanel() {
        setupMainPanel();
        setupTimePanel();
        setupDatePanel();
    }

    private void setupMainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setMinimumSize(new Dimension(100, 45));
        setPreferredSize(new Dimension(100, 45));
        setMaximumSize(new Dimension(100, 45));
        setOpaque(false);
        add(timePanel);
        add(datePanel);

        Timer timer = new Timer(10000, e -> updateDateTime());
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    private void setupTimePanel() {
        timePanel.setOpaque(false);
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.PAGE_AXIS));

        timePanel.setMinimumSize(new Dimension(100, 23));
        timePanel.setPreferredSize(new Dimension(100, 23));
        timePanel.setMaximumSize(new Dimension(100, 23));

        timePanel.add(Box.createHorizontalGlue());
        timePanel.add(timeLabel);
        timePanel.add(Box.createHorizontalGlue());
    }

    private void setupDatePanel() {
        datePanel.setOpaque(false);
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.PAGE_AXIS));

        datePanel.setMinimumSize(new Dimension(100, 22));
        datePanel.setPreferredSize(new Dimension(100, 22));
        datePanel.setMaximumSize(new Dimension(100, 22));

        datePanel.add(Box.createHorizontalGlue());
        datePanel.add(dateLabel);
        datePanel.add(Box.createHorizontalGlue());
    }

    private void updateDateTime() {
        Calendar currentCalendar = Calendar.getInstance();
        Date currentTime = currentCalendar.getTime();

        timePanel.remove(timeLabel);
        timeLabel = new CustomTextLabel(timeFormat.format(currentTime), new Font("Noto Sans Regular", Font.PLAIN, 21),
                "black", false, 0);
        timePanel.add(timeLabel, 1);

        datePanel.remove(dateLabel);
        dateLabel = new CustomTextLabel(dateFormat.format(currentTime), new Font("Noto Sans Regular", Font.PLAIN, 17),
                "black", false, 0);
        datePanel.add(dateLabel, 1);

        validate();
//        repaint();
    }
}
