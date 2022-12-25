package edu.psuti.pe.gui.apps.about;

import edu.psuti.pe.gui.window.WindowPanel;

import javax.swing.*;
import java.awt.*;

public class AboutAppWindow extends WindowPanel {
    // Главная панель
    private JPanel mainPanel = new JPanel();

    public AboutAppWindow(String appIconResource, String appTitle, int width, int height) {
        super(appIconResource, appTitle, width, height);

        setupMainPanel();

        getContentPanel().add(mainPanel);
    }

    private void setupMainPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(new Color(247, 247, 247));

        mainPanel.setMinimumSize(new Dimension(300, 300));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        mainPanel.add(new AboutEntryPanel("start.svgz", "Операционная система: ROSA Fresh (Desktop KDE Plasma 5)"));
        mainPanel.add(new AboutEntryPanel("about/java.svg", "Язык программирования: Java 8"));
        mainPanel.add(new AboutEntryPanel("about/group.svg", "Группа: ПрИм-21"));
        mainPanel.add(new AboutEntryPanel("about/student.svg", "Ежов Глеб Владимирович [Проектирование, разработка]"));
        mainPanel.add(new AboutEntryPanel("about/student.svg", "Бакай Юлия Олеговна [Документация, разработка]"));
        mainPanel.add(new AboutEntryPanel("about/student.svg", "Евстигнеев Сергей Владимирович [Отчётность]"));
    }
}
