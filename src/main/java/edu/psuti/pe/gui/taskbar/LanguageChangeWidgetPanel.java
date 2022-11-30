package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;

// Панель виджета преключения языка системы
public class LanguageChangeWidgetPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel panel = new JPanel();
    private JPanel languageChangePanel = new JPanel();
    private JLabel languageChangeLabel = new JLabel();

    Font customFont = new Font("Noto Sans Regular", Font.PLAIN, 13);

    public LanguageChangeWidgetPanel()
    {   setupMainPanel();
        setupLanguageChangePanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(23, 23));
        panel.setPreferredSize(new Dimension(23, 23));
        panel.setMaximumSize(new Dimension(23, 23));
        panel.setOpaque(false);
        panel.add(languageChangePanel);
    }

    private void setupLanguageChangePanel() {
        languageChangePanel.setOpaque(false);
        languageChangePanel.addMouseListener(new TaskBarMouseListener());
        languageChangePanel.setLayout(new BoxLayout(languageChangePanel, BoxLayout.PAGE_AXIS));

        languageChangePanel.setMinimumSize(new Dimension(23, 23));
        languageChangePanel.setPreferredSize(new Dimension(23, 23));
        languageChangePanel.setMaximumSize(new Dimension(23, 23));

        languageChangeLabel.setFont(customFont);
        languageChangeLabel.setText("EN");

        languageChangePanel.add(languageChangeLabel);
    }

}
