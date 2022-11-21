package edu.psuti.pe.gui.window;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TitleBarMouseListener implements MouseListener {
    private final WindowsManager windowsManager = WindowsManager.getInstance(null);

    private TitleBarButtonType type;
    boolean isPressed = false;
    boolean isPressedInButtonZone = false;

    public TitleBarMouseListener(TitleBarButtonType buttonType) {
        this.type = buttonType;
    }

    public TitleBarButtonType getButtonType() { return type; }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
        isPressedInButtonZone = true;
        JComponent thisComponent = (JComponent) e.getComponent();

        switch (type) {
            case CLOSE:
                ((TitleBarPanel)thisComponent.getParent()).setPressedIconForCloseButton();
                break;

            case MINIMIZE:
                ((TitleBarPanel)thisComponent.getParent()).setPressedIconForMinimizeButton();
                break;

            case MAXIMIZE:
                ((TitleBarPanel)thisComponent.getParent()).setPressedIconForMaximizeButton();
                break;

            case RESTORE:
                ((TitleBarPanel)thisComponent.getParent()).setPressedIconForRestoreButton();
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
        JComponent thisComponent = (JComponent) e.getComponent();

        switch (type) {
            case CLOSE:
                if (isPressedInButtonZone) {
                    ((TitleBarPanel) thisComponent.getParent()).setEnteredIconForCloseButton();
                    System.out.println("close");
                    windowsManager.removeWindow((WindowPanel)thisComponent.getParent().getParent());
                    isPressedInButtonZone = false;
                } else {
                    ((TitleBarPanel) thisComponent.getParent()).setExitedIconForCloseButton();
                }
                break;

            case MINIMIZE:
                if (isPressedInButtonZone) {
                    ((TitleBarPanel) thisComponent.getParent()).setEnteredIconForMinimizeButton();
                    System.out.println("minimize");
                    // action
                    isPressedInButtonZone = false;
                } else {
                    ((TitleBarPanel) thisComponent.getParent()).setExitedIconForMinimizeButton();
                }
                break;

            case MAXIMIZE:
                if (isPressedInButtonZone) {
                    System.out.println("maximize");
                    WindowPanel window = (WindowPanel)thisComponent.getParent().getParent();
                    windowsManager.maximizeWindow(window);
                    windowsManager.moveWindowToFront(window);
                    isPressedInButtonZone = false;
                } else {
                    ((TitleBarPanel) thisComponent.getParent()).setExitedIconForMaximizeButton();
                }
                break;

            case RESTORE:
                if (isPressedInButtonZone) {
                    System.out.println("restore");
                    windowsManager.restoreWindow((WindowPanel)thisComponent.getParent().getParent());
                    isPressedInButtonZone = false;
                } else {
                    ((TitleBarPanel) thisComponent.getParent()).setExitedIconForRestoreButton();
                }
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isPressed) isPressedInButtonZone = true;
        JComponent thisComponent = (JComponent) e.getComponent();

        if (!isPressed) {
            switch (type) {
                case CLOSE:
                    ((TitleBarPanel)thisComponent.getParent()).setEnteredIconForCloseButton();
                    break;

                case MINIMIZE:
                    ((TitleBarPanel)thisComponent.getParent()).setEnteredIconForMinimizeButton();
                    break;

                case MAXIMIZE:
                    ((TitleBarPanel)thisComponent.getParent()).setEnteredIconForMaximizeButton();
                    break;

                case RESTORE:
                    ((TitleBarPanel)thisComponent.getParent()).setEnteredIconForRestoreButton();
                    break;
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isPressedInButtonZone = false;
        JComponent thisComponent = (JComponent) e.getComponent();

        if (!isPressed) {
            switch (type) {
                case CLOSE:
                    ((TitleBarPanel)thisComponent.getParent()).setExitedIconForCloseButton();
                    break;

                case MINIMIZE:
                    ((TitleBarPanel)thisComponent.getParent()).setExitedIconForMinimizeButton();
                    break;

                case MAXIMIZE:
                    ((TitleBarPanel)thisComponent.getParent()).setExitedIconForMaximizeButton();
                    break;

                case RESTORE:
                    ((TitleBarPanel)thisComponent.getParent()).setExitedIconForRestoreButton();
                    break;
            }
        }
    }
}
