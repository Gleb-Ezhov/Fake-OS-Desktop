package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ComponentMover;
import edu.psuti.pe.gui.helper.ComponentResizer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Общая панель для окна какой-либо программы
public class WindowPanel extends JPanel {
    private ComponentMover componentMover;
    private ComponentResizer componentResizer = new ComponentResizer();

    private String appTitle; // Название приложения
    private int shadowPixels = 8; // Ширина тени для окна в пикселях
    private int topOpacity = 60; // Максимальная непрозрачность для тени
    Dimension arcs = new Dimension(15, 15); // Изгибы верхних углов окна и его тени {width, height}

    private JPanel contentPanel = new JPanel(); // Панель с основным содержимым

    private JPanel titleBarPanel = new JPanel() {
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
    };

    public WindowPanel(String appTitle, int width, int height) {
        this.appTitle = appTitle;

        setupWindow(width, height);
        setupTitleBar();
        setupContentPanel();
    }

    // Ширина окна без учёта границы с тенью
    private int getContentWidth() { return getWidth() - shadowPixels * 2; }
    // Высота окна без учёта границы с тенью
    private int getContentHeight() { return getHeight() - shadowPixels * 2; }

    private void setupWindow(int width, int height) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(new Color(255, 176, 196));

        // Устанавливается невидимая граница, служащая пространством для отрисовки тени от окна.
        // Такой подход необходим, так как рисовать тень можно только в границах самого комонента.
        Border border = BorderFactory.createEmptyBorder(shadowPixels, shadowPixels, shadowPixels, shadowPixels);
        setBorder(BorderFactory.createCompoundBorder(getBorder(), border));

        // При установке размеров окна учитываются невидимые границы для тени
        setBounds(100, 100, width + shadowPixels * 2, height + shadowPixels * 2);

        componentResizer.setSnapSize(new Dimension(10, 10));
        componentResizer.setMinimumSize(new Dimension(100, 100));
        componentResizer.setDragInsets(new Insets(12, 12, 12, 12));
        componentResizer.registerComponent(this);
    }

    private void setupTitleBar() {
        titleBarPanel.setLayout(new BoxLayout(titleBarPanel, BoxLayout.LINE_AXIS));
        titleBarPanel.setBackground(new Color(222, 224, 226));

        // В высоту добавляется пространство для отрисовки тени
        Insets borderInsets = getInsets();
        titleBarPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 29));
        titleBarPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 29));
        titleBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 29));

        // События перетаскивания на полосе зоголовка будут передаваться панели окна
        componentMover = new ComponentMover(this.getClass(), titleBarPanel);
        componentMover.setChangeCursor(true);
        componentMover.setEdgeInsets(new Insets(0, -500, 0, -500)); // можно выезжать за края
        componentMover.registerComponent(titleBarPanel);

        add(titleBarPanel);
    }

    private void setupContentPanel() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.setBackground(new Color(255, 235, 17));
        contentPanel.setOpaque(true);

        // todo : панель отображается некорректно в боксе окна
//        contentPanel.setSize(new Dimension(100, 100));
        contentPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        contentPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        contentPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        add(contentPanel);
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
        // исп. при отладке, отключен для повышения производительности
//        g2d.setColor(getBackground());
//        g2d.fillRect(shadowPixels, shadowPixels + 28, getContentWidth(), getContentHeight() - 28);
    }
}
