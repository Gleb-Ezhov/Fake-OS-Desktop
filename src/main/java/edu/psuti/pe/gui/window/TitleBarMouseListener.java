package edu.psuti.pe.gui.window;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TitleBarMouseListener implements MouseListener {
    private final ImageHelper imageHelper = ImageHelper.getInstance();

    private TitleBarButtonType type;

    public TitleBarMouseListener(TitleBarButtonType buttonType) {
        this.type = buttonType;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        JLabel labelToChange;

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
                labelToChange = ((TitleBarPanel)thisComponent.getParent()).getRestoreButtonLabel();
                if (labelToChange != null)
                    labelToChange.setIcon(imageHelper.createImageIconFromSvg("window-restore-gray.svg", "App's Restore Button",
                            23, 23));
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        JLabel labelToChange;

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
                labelToChange = ((TitleBarPanel)thisComponent.getParent()).getRestoreButtonLabel();
                if (labelToChange != null)
                    labelToChange.setIcon(imageHelper.createImageIconFromSvg("window-restore-black.svg", "App's Restore Button",
                            23, 23));
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        JLabel labelToChange;

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
                labelToChange = ((TitleBarPanel)thisComponent.getParent()).getRestoreButtonLabel();
                if (labelToChange != null)
                    labelToChange.setIcon(imageHelper.createImageIconFromSvg("window-restore-black.svg", "App's Restore Button",
                        23, 23));
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JComponent thisComponent = (JComponent) e.getComponent();
        JLabel labelToChange;

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
                labelToChange = ((TitleBarPanel)thisComponent.getParent()).getRestoreButtonLabel();
                if (labelToChange != null)
                    labelToChange.setIcon(imageHelper.createImageIconFromSvg("window-restore.svg", "App's Restore Button",
                        23, 23));
                break;
        }
    }
}
