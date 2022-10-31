package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ComponentMover;
import edu.psuti.pe.gui.helper.ComponentResizer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// todo ? в дальнейшем сделать абстрактным
public class WindowPanel extends JPanel {
    private ComponentMover componentMover;
    private ComponentResizer componentResizer = new ComponentResizer();

    private String appTitle;
    private int width;
    private int height;

    // Размеры основного содержимого окна
    int contentWidth;
    int contentHeight;

    private int shadowPixels = 5; // Ширина тени для окна в пикселях
    private int topOpacity = 80; // Максимальная непрозрачность для тени

    // todo ? попробовать месте этого дамика сделать отдельную панель для основного содержимого окна и чтобы оно ресайзилось на его краях
    private JPanel dummyPanel = new JPanel(new BorderLayout());

    private JPanel titleBarPanel = new JPanel() {
        // todo сделать тень для полосы заголовка
        @Override
        protected void paintComponent(Graphics g) {
            Dimension arcs = new Dimension(15, 15); // Upper corners arcs {width, height}
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, width, height, arcs.width, arcs.height); // рисуется закруглённый прямоугольник
            g2d.fillRect(0, 20, width, height / 2); // закрашивается его нижняя часть
        }
    };

    public WindowPanel(String appTitle, int width, int height) {
        this.width = width;
        this.height = height;
        this.appTitle = appTitle;

        contentWidth = getWidth() * (shadowPixels * 2);
        contentHeight = getHeight() * (shadowPixels * 2);

        setupWindow();
        setupTitleBar();
    }

    private void setupWindow() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
        setBackground(new Color(255, 176, 196));

        // Устанавливается невидимая граница, служащая пространством для отрисовки тени от окна.
        // Такой подход необходим, так как рисовать тень можно только в границах самого комонента.
        Border border = BorderFactory.createEmptyBorder(shadowPixels, shadowPixels, shadowPixels, shadowPixels);
        setBorder(BorderFactory.createCompoundBorder(getBorder(), border));

        // При остановке размеров окна учитываются невидимые границы с тенью
        Insets borderInsets = getInsets();
        setBounds(100, 100, width + borderInsets.left + borderInsets.right,
                height + borderInsets.top + borderInsets.bottom);

        componentResizer.setSnapSize(new Dimension(10, 10));
        componentResizer.registerComponent(this);

        dummyPanel.setSize(new Dimension(100, 100));
    }

    private void setupTitleBar() {
        titleBarPanel.setLayout(new BoxLayout(titleBarPanel, BoxLayout.LINE_AXIS));
        titleBarPanel.setOpaque(false);
        titleBarPanel.setBackground(new Color(222, 224, 226));

        titleBarPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 29));
        titleBarPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 29));
        titleBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 29));

        // События перетаскивания на полосе зоголовка будут передаваться панели окна
        componentMover = new ComponentMover(this.getClass(), titleBarPanel);
        componentMover.setChangeCursor(true);
        componentMover.setEdgeInsets(new Insets(0, -500, 0, -500)); // можно выезжать за края
        componentMover.registerComponent(titleBarPanel);

        add(titleBarPanel);
        // дамик
        add(dummyPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // отрисовка нижней части окна (без полосы заголовка)
        g2d.fillRect(0, 28, contentWidth, contentHeight);

        // Отрисовка тени окна несколькими прямоугольниками, начиная с самого прозрачного и
        // заканчивая самым непрозрачным уже ближе к самому содержимому окна.
        for (int i = 0; i < shadowPixels; ++i) {
            // +5 к непрозрачности, чтобы избежать пустой тени при i = 0
            // +-29, чтобы опуститься ниже полосы заголовка и окружить тенью основное содержимое окна
            // -+1 на X начальной координате и ширине, т.к. почему-то в ходе рендера одна вертикальная полоска в 1px не закрашивалась
            g2d.setColor(new Color(0, 0, 0, (topOpacity / shadowPixels) * i + 5));
            g2d.drawRect(i - 1, i + 29, getWidth() - (i * 2) + 1, getHeight() - (i * 2) - 29);
        }

        // Выделение краёв основного содержимого окна тёмным контуром в 1px
        g2d.setColor(new Color(0, 0, 0, 110));
        g2d.drawRect(shadowPixels - 1, shadowPixels + 29, getWidth() - shadowPixels * 2 + 1, getHeight() - shadowPixels * 2 - 29);
    }
}
