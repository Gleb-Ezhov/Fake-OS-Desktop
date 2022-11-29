package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

// Панель виджета настроек системы
public class SystemSettingsWidgetPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel panel = new JPanel();
    private JPanel systemSettingsPanel = new JPanel();
    private JLabel systemSettingsLabel = new JLabel();

    public SystemSettingsWidgetPanel()
    {   setupMainPanel();
        setupSystemSettingsPanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(23, 22));
        panel.setPreferredSize(new Dimension(23, 22));
        panel.setMaximumSize(new Dimension(23, 22));
        panel.setOpaque(false);
        panel.add(systemSettingsPanel);
    }

    private void setupSystemSettingsPanel() {
        systemSettingsPanel.setOpaque(false);
        systemSettingsPanel.addMouseListener(new SystemSettingsPanelMouseListener());
        systemSettingsPanel.setLayout(new BoxLayout(systemSettingsPanel, BoxLayout.PAGE_AXIS));

        systemSettingsPanel.setMinimumSize(new Dimension(23, 23));
        systemSettingsPanel.setPreferredSize(new Dimension(23, 23));
        systemSettingsPanel.setMaximumSize(new Dimension(23, 23));

        systemSettingsLabel.setIcon(imageHelper.createImageIconFromSvg("systemsettings.svg", "System Settings Button",
                23, 23));

        systemSettingsPanel.add(systemSettingsLabel);
    }


    // Класс-слушатель событий для панели настроек системы
    class SystemSettingsPanelMouseListener extends TaskBarMouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            System.exit(0);
        }
    }
}
