package edu.psuti.pe.gui.window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WindowsManager implements Serializable {
    private static WindowsManager windowsManager;
    private static JLayeredPane workspace;
    private static List<WindowPanel> windows = new LinkedList<>();

    private WindowsManager() {}

    // getInstance при первом использовании принимает ссылку на рабочую область,
    // после этого в getInstance можно передавать null
    public static WindowsManager getInstance(JLayeredPane workspacePanel) {
        if (workspace == null) {
            workspace = workspacePanel;
        }

        if (windowsManager == null) {
            windowsManager = new WindowsManager();
            return windowsManager;
        } else {
            return windowsManager;
        }
    }

    public void addWindow(WindowPanel window) {
        windows.add(window);
        window.setId(windows.size() - 1);
        workspace.add(window);
        rearrangeWindows();
        workspace.validate();
        workspace.repaint();
    }

    public void removeWindow(WindowPanel window) {
        windows.remove(window);
        resetWindowsIds();
        workspace.remove(window);
        workspace.validate();
        workspace.repaint();
    }

    private void resetWindowsIds() {
        for (int i = 0; i < windows.size(); ++i) {
            windows.get(i).setId(i);
        }
    }

    public void maximizeWindow(WindowPanel window) {
        window.setPreviousBounds(window.getBounds());

        Rectangle bounds = workspace.getBounds();
        int shadowPixels = window.getShadowPixels();

        // Размер и позиция окна подгоняются под размер workspace'а
        bounds.setLocation(-shadowPixels, -shadowPixels);
        bounds.setSize(bounds.width + shadowPixels * 2, bounds.height + shadowPixels * 2);
        window.setBounds(bounds);

        ((TitleBarPanel)window.getComponent(0)).swapMaximizeRestoreButton();
        window.validate();
    }

    public void restoreWindow(WindowPanel window) {
        window.setBounds(window.getPreviousBounds());

        ((TitleBarPanel)window.getComponent(0)).swapMaximizeRestoreButton();
        window.validate();
    }

    public void moveWindowToFront(WindowPanel window) {
        // Если окно в данный момент не находится впереди всех, то передвигаем его
        if (windows.get(windows.size() - 1).getId() != window.getId()) {
            windows.add(windows.remove(window.getId()));
            resetWindowsIds();
            rearrangeWindows();
        }
    }

    public void repaintAllWindows() {
        for (WindowPanel window : windows) {
            window.repaint();
        }
        workspace.repaint();
    }

    // расположить окна по слоям в соответствии с их списком
    private void rearrangeWindows() {
        for (int i = 0; i < windows.size(); ++i) {
            workspace.setLayer(windows.get(i), i);
            windows.get(i).repaint();
        }
    }
}
