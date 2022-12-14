package edu.psuti.pe.gui.taskbar;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Группа виджетов "Состояние и уведомления". Настриваемая группа виджетов в Plasma KDE.
public class StatusAndNotificationsWidgetPanel extends JPanel {
    private final ImageHelper imageHelper = ImageHelper.getInstance();
    private JPanel glowingPanel = new JPanel(); // 3px полоска, подсвечивающаяся при активации настроек виджета
    private JPanel iconsPanel = new JPanel();   // панель непосредственно для кнопок
    // Буфер обмена
    private JPanel clipboardPanel = new JPanel(); // 45 x 42
    private JLabel clipboardLabel = new JLabel();
    // Громкость
    private JPanel soundVolumePanel = new JPanel();
    private JLabel soundVolumeLabel = new JLabel();
    // Раскладка клавиатуры
    private JPanel keyboardLayoutPanel = new JPanel();
    private CustomTextLabel keyboardLayoutLabel;
    // Системный трей (лоток)
    private JPanel systemTrayPanel = new JPanel();
    private JLabel systemTrayLabel = new JLabel();

    public StatusAndNotificationsWidgetPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(120, 45));
        setMaximumSize(new Dimension(120, 45));

        // Настройка дочерних элементов данного виджета
        setupGlowingPanel();
        setupIconsPanel();
        
        // Добавление дочерних элементов
        add(glowingPanel);
        add(iconsPanel);
    }

    private void setupGlowingPanel() {
        glowingPanel.setLayout(new BoxLayout(glowingPanel, BoxLayout.LINE_AXIS));
        glowingPanel.setBackground(new Color(122, 180, 220));
        glowingPanel.setOpaque(false);
        glowingPanel.setPreferredSize(new Dimension(120, 3));
        glowingPanel.setMaximumSize(new Dimension(120, 3));
    }

    private void setupIconsPanel() {
        iconsPanel.setLayout(new BoxLayout(iconsPanel, BoxLayout.LINE_AXIS));
        iconsPanel.setOpaque(false);
        iconsPanel.setPreferredSize(new Dimension(120, 42));
        iconsPanel.setMaximumSize(new Dimension(120, 42));

        // Настройка кнопочек
        setupInnerWidget(clipboardPanel, clipboardLabel,"edit-paste.svg", "Edit Paste Button", new TaskBarMouseListener());
        setupInnerWidget(soundVolumePanel, soundVolumeLabel,"audio-volume-muted.svg", "Volume Muted Button", new TaskBarMouseListener());
        setupKeyboardLayoutPanel();
        setupInnerWidget(systemTrayPanel, systemTrayLabel,"go-up.svg", "Open-Close System Tray Button", new SystemTrayPanelMouseListener(glowingPanel));

        // Добавление кнопочек
        iconsPanel.add(clipboardPanel);
        iconsPanel.add(soundVolumePanel);
        iconsPanel.add(keyboardLayoutPanel);
        iconsPanel.add(systemTrayPanel);
    }

    private void setupInnerWidget(JPanel panel, JLabel label, String iconName, String iconDescr, MouseListener listener) {
        panel.setOpaque(false);
        panel.addMouseListener(new TaskBarMouseListener());
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.addMouseListener(listener);

        panel.setMinimumSize(new Dimension(30, 42));
        panel.setPreferredSize(new Dimension(30, 42));
        panel.setMaximumSize(new Dimension(30, 42));
        panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        label.setIcon(imageHelper.createImageIconFromSvg(iconName, iconDescr,
                24, 36));

        panel.add(label);
    }

    private void setupKeyboardLayoutPanel() {
        keyboardLayoutPanel.setOpaque(false);
        keyboardLayoutPanel.addMouseListener(new TaskBarMouseListener());
        keyboardLayoutPanel.setLayout(new BoxLayout(keyboardLayoutPanel, BoxLayout.PAGE_AXIS));

        keyboardLayoutPanel.setMinimumSize(new Dimension(30, 42));
        keyboardLayoutPanel.setPreferredSize(new Dimension(30, 42));
        keyboardLayoutPanel.setMaximumSize(new Dimension(30, 42));
        keyboardLayoutPanel.setBorder(BorderFactory.createEmptyBorder(5, 3, 5, 3));

        keyboardLayoutLabel = new CustomTextLabel("us", new Font("Noto Sans Regular", Font.PLAIN, 21),
                "black", false, 0);

        keyboardLayoutPanel.add(keyboardLayoutLabel);
    }

    class SystemTrayPanelMouseListener extends TaskBarMouseListener {
        JPanel glowingPanel;
        boolean isTrayActive = false;

        public SystemTrayPanelMouseListener(JPanel glowingPanel) {
            super();
            this.glowingPanel = glowingPanel;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            isTrayActive = !isTrayActive;
            glowingPanel.setOpaque(isTrayActive);
            glowingPanel.repaint();
        }
    }
}
