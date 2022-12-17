package edu.psuti.pe.gui.window;

import edu.psuti.pe.gui.helper.ComponentMover;
import edu.psuti.pe.gui.helper.ComponentResizer;
import edu.psuti.pe.gui.taskbar.AppTabPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Общая панель для окна какой-либо программы
public class WindowPanel extends JPanel implements MouseListener {
    private WindowsManager windowsManager = WindowsManager.getInstance(null);
    private ComponentMover componentMover;
    private final ComponentResizer componentResizer = new ComponentResizer();
    private AppTabPanel appTabPanel; // вкладка программы для панели задач

    private String appTitle; // Название приложения
    private String appIconResource;
    private int id;
    private final int shadowPixels = 8; // Ширина тени для окна в пикселях
    private final int topOpacity = 60; // Максимальная непрозрачность для тени
    Dimension arcs = new Dimension(15, 15); // Изгибы верхних углов окна и его тени {width, height}

    // Полоса заголовка
    private TitleBarPanel titleBarPanel;
    // Панель с основным содержимым
    private final JPanel contentPanel = new JPanel();

    private Rectangle previousBounds; // Предыдущая позиция и размеры для разворачивания и восстановления окна

    public WindowPanel(String appIconResource, String appTitle, int width, int height) {
        this.appTitle = appTitle;
        this.appIconResource = appIconResource;
        appTabPanel = new AppTabPanel(appIconResource, appTitle);

        setupWindow(width, height);
        setupTitleBar();
        setupContentPanel();
    }

    public AppTabPanel getAppTabPanel() { return appTabPanel; }
    public int getShadowPixels() { return shadowPixels; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Rectangle getPreviousBounds() { return previousBounds; }
    public void setPreviousBounds(Rectangle bounds) { previousBounds = bounds; }
    public JPanel getContentPanel() { return contentPanel; }

    // Ширина окна без учёта границы с тенью
    private int getContentWidth() {
        return getWidth() - shadowPixels * 2;
    }

    // Высота окна без учёта границы с тенью
    private int getContentHeight() {
        return getHeight() - shadowPixels * 2;
    }

    private void setupWindow(int width, int height) {
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 176, 196));
        addMouseListener(this);

        // Устанавливается невидимая граница, служащая пространством для отрисовки тени от окна.
        // Такой подход необходим, так как рисовать тень можно только в границах самого комонента.
        Border border = BorderFactory.createEmptyBorder(shadowPixels, shadowPixels, shadowPixels, shadowPixels);
        setBorder(BorderFactory.createCompoundBorder(getBorder(), border));

        // При установке размеров окна учитываются невидимые границы для тени
        setBounds(200, 80, width + shadowPixels * 2, height + shadowPixels * 2);
        previousBounds = getBounds();

        componentResizer.setSnapSize(new Dimension(10, 10));
        componentResizer.setMinimumSize(new Dimension(100, 100));
        componentResizer.setDragInsets(new Insets(12, 12, 12, 12));
        componentResizer.registerComponent(this);
    }

    private void setupTitleBar() {
        titleBarPanel = new TitleBarPanel(appIconResource, appTitle, arcs);

        // События перетаскивания на полосе зоголовка будут передаваться панели окна
        componentMover = new ComponentMover(this.getClass(), titleBarPanel);
        componentMover.setChangeCursor(true);
        componentMover.setEdgeInsets(new Insets(0, -500, 0, -500)); // можно выезжать за края
        componentMover.registerComponent(titleBarPanel);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(titleBarPanel, gridBagConstraints);
    }

    private void setupContentPanel() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBackground(new Color(255, 235, 17));
        contentPanel.setOpaque(true);
        contentPanel.setMinimumSize(new Dimension(100, 100));
        contentPanel.setPreferredSize(new Dimension(100, 100));
        contentPanel.setMaximumSize(new Dimension(100, 100));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(contentPanel, gridBagConstraints);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Отрисовка тени окна несколькими закруглёнными прямоугольниками, начиная с самого прозрачного и
        // заканчивая самым непрозрачным уже ближе к самому содержимому окна.
        for (int i = 0; i < shadowPixels; ++i) {
            // +5 к непрозрачности, чтобы избежать пустой тени при i = 0
            // -+1 на X начальной координате и ширине, т.к. почему-то в ходе рендера одна вертикальная полоска в 1px не закрашивалась
            g2d.setColor(new Color(0, 0, 0, (topOpacity / shadowPixels) * i + 5));
            g2d.drawRoundRect(i - 1, i, getWidth() - (i * 2) + 1, getHeight() - (i * 2), arcs.width, arcs.height);

            // Закомментирована реализация тени в виде отдельной квадартной тени для нижней части окна
            // и закруглённой тени для полосы заголовка.

//          +-29, чтобы опуститься ниже полосы заголовка и окружить тенью основное содержимое окна
//            g2d.drawRect(i - 1, i + 29, getWidth() - (i * 2) + 1, getHeight() - (i * 2) - 29);
//            g2d.drawRoundRect(i - 1, i,
//                    getWidth() - (i * 2) + 1, 29 + shadowPixels * 2 - (i * 2),
//                    arcs.width, arcs.height);
        }

        // Выделение краёв основного содержимого окна тёмным контуром в 1px
        g2d.setColor(new Color(0, 0, 0, 70));
        g2d.drawRect(shadowPixels - 1, shadowPixels + 29, getContentWidth() + 1, getContentHeight() - 29);

        // Выделение полосы заголовка:
        // белым контуром  сверху для придания объёма
        g2d.setColor(new Color(255, 255, 255, 240));
        g2d.drawRoundRect(shadowPixels - 1, shadowPixels - 1,
                getComponent(0).getWidth() + 1, 8,
                arcs.width, arcs.height);
        // и тёмным контуром в остальной части, чтобы плавно перейти в контур основного содержимого
        g2d.setColor(new Color(0, 0, 0, 70));
        g2d.drawRect(shadowPixels - 1, shadowPixels - 1 + 5,
                getComponent(0).getWidth() + 1, 24);

        // Отрисовка нижней части окна (его фон ниже полосы заголовка)
        // использовалась при отладке, отключена для повышения производительности
//        g2d.setColor(getBackground());
//        g2d.fillRect(shadowPixels, shadowPixels + 28, getContentWidth(), getContentHeight() - 28);
    }

    // MouseListener окна

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        windowsManager.moveWindowToFront(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
