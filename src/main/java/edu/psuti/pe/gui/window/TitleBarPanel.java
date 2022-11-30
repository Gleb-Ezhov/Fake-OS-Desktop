package edu.psuti.pe.gui.window;

import edu.psuti.pe.gui.helper.CustomTextLabel;
import edu.psuti.pe.gui.helper.ImageHelper;
import edu.psuti.pe.gui.helper.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Панель полосы заголовка для окна WindowPanel
public class TitleBarPanel extends JPanel implements MouseListener {
    WindowsManager windowsManager = WindowsManager.getInstance(null);
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    // Иконка программы
    private JPanel appIconPanel = new JPanel();
    private JLabel appIconLabel = new JLabel();
    // Текст с названием программы
    private JPanel appTitlePanel = new JPanel(new BorderLayout());
    private JLabel appTitleLabel;
    // Кнопка "Закрыть"
    private JPanel closeButtonPanel = new JPanel();
    private JLabel closeButtonLabel = new JLabel();
    // Кнопка "Свернуть"
    private JPanel minimizeButtonPanel = new JPanel();
    private JLabel minimizeButtonLabel = new JLabel();
    // Кнопка "Распахнуть"
    private JPanel maximizeButtonPanel = new JPanel();
    private JLabel maximizeButtonLabel = new JLabel();
    // Кнопка "Восстановить"
    private JPanel restoreButtonPanel = new JPanel();
    private JLabel restoreButtonLabel = new JLabel();

    Dimension arcs; // Изгибы верхних углов окна
    String appIconResource; // Ресурс иконки программы
    String appTitle; // Название программы

    public TitleBarPanel(String appIconResource, String appTitle, Dimension arcs) {
        this.arcs = arcs;
        this.appIconResource = appIconResource;
        this.appTitle = appTitle;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(new Color(222, 224, 226));
        addMouseListener(this);

        setMinimumSize(new Dimension(Integer.MAX_VALUE, 29));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 29));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 29));

        // Настройка/инциализация дочерних элементов полосы заголовка
        setupAppIconPanel();
        setupAppTitlePanel();
        setupMinimizeButtonPanel();
        setupMaximizeButtonPanel();
        setupRestoreButtonPanel();
        setupCloseButtonPanel();

        // Добавление всех дочерних элементов в полосу
        add(appIconPanel);
        //add(Box.createHorizontalGlue()); // горизонтальный "наполнитель-клей"
        add(appTitlePanel);
        add(minimizeButtonPanel);
        add(maximizeButtonPanel);
        add(closeButtonPanel);
    }

    public JLabel getCloseButtonLabel() { return closeButtonLabel; }
    public JLabel getMinimizeButtonLabel() { return minimizeButtonLabel; }
    public JLabel getMaximizeButtonLabel() { return maximizeButtonLabel; }
    public JLabel getRestoreButtonLabel() { return restoreButtonLabel; }

    private void setupAppIconPanel() {
        appIconPanel.setOpaque(false);
        // здесь можно будет прикрепить MouseListener, если он понадобится
        appIconPanel.setLayout(new BoxLayout(appIconPanel, BoxLayout.PAGE_AXIS));

        appIconPanel.setMinimumSize(new Dimension(29, 29));
        appIconPanel.setPreferredSize(new Dimension(29, 29));
        appIconPanel.setMaximumSize(new Dimension(29, 29));
        appIconPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        appIconLabel.setIcon(imageHelper.createImageIconFromSvg(appIconResource, "App Icon",
                21, 21));

        appIconPanel.add(appIconLabel);
    }

    private void setupAppTitlePanel() {
        appTitlePanel.setOpaque(false);
        appTitlePanel.setBackground(Color.red);

        appTitleLabel = new CustomTextLabel(appTitle,
                new Font("Noto Sans Regular", Font.PLAIN, 13),
                "black", false, 0);

        appTitlePanel.add(appTitleLabel);
    }

    private void setupMinimizeButtonPanel() {
        minimizeButtonPanel.setOpaque(false);
        minimizeButtonPanel.addMouseListener(new TitleBarMouseListener(TitleBarButtonType.MINIMIZE));
        minimizeButtonPanel.setLayout(new BoxLayout(minimizeButtonPanel, BoxLayout.PAGE_AXIS));

        minimizeButtonPanel.setMinimumSize(new Dimension(23, 29));
        minimizeButtonPanel.setPreferredSize(new Dimension(23, 29));
        minimizeButtonPanel.setMaximumSize(new Dimension(23, 29));
        minimizeButtonPanel.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3));

        minimizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-down.svg", "App's Minimize Button",
                17, 17));

        minimizeButtonPanel.add(minimizeButtonLabel);
    }

    private void setupMaximizeButtonPanel() {
        maximizeButtonPanel.setOpaque(false);
        maximizeButtonPanel.addMouseListener(new TitleBarMouseListener(TitleBarButtonType.MAXIMIZE));
        maximizeButtonPanel.setLayout(new BoxLayout(maximizeButtonPanel, BoxLayout.PAGE_AXIS));

        maximizeButtonPanel.setMinimumSize(new Dimension(23, 29));
        maximizeButtonPanel.setPreferredSize(new Dimension(23, 29));
        maximizeButtonPanel.setMaximumSize(new Dimension(23, 29));
        maximizeButtonPanel.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3));

        maximizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-up.svg", "App's Maximize Button",
                17, 17));

        maximizeButtonPanel.add(maximizeButtonLabel);
    }

    private void setupRestoreButtonPanel() {
        restoreButtonPanel.setOpaque(false);
        restoreButtonPanel.addMouseListener(new TitleBarMouseListener(TitleBarButtonType.RESTORE));
        restoreButtonPanel.setLayout(new BoxLayout(restoreButtonPanel, BoxLayout.PAGE_AXIS));

        restoreButtonPanel.setMinimumSize(new Dimension(23, 29));
        restoreButtonPanel.setPreferredSize(new Dimension(23, 29));
        restoreButtonPanel.setMaximumSize(new Dimension(23, 29));
        restoreButtonPanel.setBorder(BorderFactory.createEmptyBorder(6, 3, 6, 3));

        restoreButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-restore.svg", "App's Restore Button",
                17, 17));

        restoreButtonPanel.add(restoreButtonLabel);
    }

    private void setupCloseButtonPanel() {
        closeButtonPanel.setOpaque(false);
        closeButtonPanel.addMouseListener(new TitleBarMouseListener(TitleBarButtonType.CLOSE));
        closeButtonPanel.setLayout(new BoxLayout(closeButtonPanel, BoxLayout.PAGE_AXIS));

        closeButtonPanel.setMinimumSize(new Dimension(26, 29));
        closeButtonPanel.setPreferredSize(new Dimension(26, 29));
        closeButtonPanel.setMaximumSize(new Dimension(26, 29));
        closeButtonPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 3));

        closeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-close-black.svg", "App's Close Button",
                23, 23));

        closeButtonPanel.add(closeButtonLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Отрисовка полосы заголовка
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, arcs.width, arcs.height); // рисуется закруглённый прямоугольник
        g2d.fillRect(0, 20, width, height / 2); // закрашивается его нижняя часть
    }

    // Ниже методы для манипуляций иконоками лейблов и панелями для кнопок управления окном

    public void swapMaximizeRestoreButton() {
        if (((TitleBarMouseListener)getComponent(3).getMouseListeners()[0]).getButtonType() == TitleBarButtonType.MAXIMIZE) {
            remove(3);
            add(restoreButtonPanel, 3);
            setExitedIconForRestoreButton();
        } else {
            remove(3);
            add(maximizeButtonPanel, 3);
            setExitedIconForMaximizeButton();
        }
    }

    public void setEnteredIconForMinimizeButton() {
        minimizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-down-black.svg", "App's Minimize Button",
                17, 17));
    }

    public void setEnteredIconForCloseButton() {
        closeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-close.svg", "App's Close Button",
                23, 23));
    }

    public void setEnteredIconForMaximizeButton() {
        maximizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-up-black.svg", "App's Maximize Button",
                17, 17));
    }

    public void setEnteredIconForRestoreButton() {
        restoreButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-restore-black.svg", "App's Restore Button",
                17, 17));
    }

    public void setExitedIconForMinimizeButton() {
        minimizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-down.svg", "App's Minimize Button",
                17, 17));
    }

    public void setExitedIconForCloseButton() {
        closeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-close-black.svg", "App's Close Button",
                23, 23));
    }

    public void setExitedIconForMaximizeButton() {
        maximizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-up.svg", "App's Maximize Button",
                17, 17));
    }

    public void setExitedIconForRestoreButton() {
        restoreButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-restore.svg", "App's Restore Button",
                17, 17));
    }

    public void setPressedIconForMinimizeButton() {
        minimizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-down-gray.svg", "App's Minimize Button",
                17, 17));
    }

    public void setPressedIconForCloseButton() {
        closeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-close-vinous.svg", "App's Close Button",
                23, 23));
    }

    public void setPressedIconForMaximizeButton() {
        maximizeButtonLabel.setIcon(imageHelper.createImageIconFromSvg("go-up-gray.svg", "App's Maximize Button",
                17, 17));
    }

    public void setPressedIconForRestoreButton() {
        restoreButtonLabel.setIcon(imageHelper.createImageIconFromSvg("window-restore-gray.svg", "App's Restore Button",
                17, 17));
    }

    // MouseListener панели заголовка

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        WindowPanel window = (WindowPanel)this.getParent();
        windowsManager.moveWindowToFront(window);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Отрисовка всех окон, чтобы окна позади тени корректно перерисовались после перемещения
        windowsManager.repaintAllWindows();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
