package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

// Панель виджета настройки звука системы
public class VolumeSettingsWidgetPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel panel = new JPanel();
    private JPanel volumePanel = new JPanel();
    private JLabel volumeLabel = new JLabel();

    public VolumeSettingsWidgetPanel()
    {   setupMainPanel();
        setupVolumeSettingsPanel();
    }

    public JPanel getPanel() { return panel; }

    private void setupMainPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMinimumSize(new Dimension(23, 22));
        panel.setPreferredSize(new Dimension(23, 22));
        panel.setMaximumSize(new Dimension(23, 22));
        panel.setOpaque(false);
        panel.add(volumePanel);
    }

    private void setupVolumeSettingsPanel() {
        volumePanel.setOpaque(false);
        volumePanel.addMouseListener(new TaskBarMouseListener());
        volumePanel.setLayout(new BoxLayout(volumePanel, BoxLayout.PAGE_AXIS));

        volumePanel.setMinimumSize(new Dimension(23, 23));
        volumePanel.setPreferredSize(new Dimension(23, 23));
        volumePanel.setMaximumSize(new Dimension(23, 23));

        volumeLabel.setIcon(imageHelper.createImageIconFromSvg("audio-volume-muted.svg", "Volume Muted Button",
                23, 23));

        volumePanel.add(volumeLabel);
    }


    // Класс-слушатель событий для панели выключения системы
    class VolumeSettingsPanelMouseListener extends TaskBarMouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            System.exit(0);
        }
    }
}
