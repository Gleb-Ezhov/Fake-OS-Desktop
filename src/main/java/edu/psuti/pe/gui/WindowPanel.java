package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ComponentMover;
import edu.psuti.pe.gui.helper.ComponentResizer;

import javax.swing.*;
import java.awt.*;

// todo ? в дальнейшем сделать абстрактным
public class WindowPanel extends JPanel {
    private ComponentMover componentMover;
    private ComponentResizer componentResizer = new ComponentResizer();

    private String appTitle;
    private int width;
    private int height;

    private JPanel titleBarPanel = new JPanel();

    public WindowPanel(String appTitle, int width, int height) {
        this.width = width;
        this.height = height;
        this.appTitle = appTitle;

        setupWindow();
        setupTitleBar();
    }

    private void setupWindow() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(true);
        setBackground(new Color(255, 176, 196));
        setBounds(100, 100, width, height);

        componentResizer.setSnapSize(new Dimension(10, 10));
        componentResizer.registerComponent(this);
    }

    private void setupTitleBar() {
        titleBarPanel.setLayout(new BoxLayout(titleBarPanel, BoxLayout.LINE_AXIS));
        titleBarPanel.setOpaque(true);
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
    }
}
