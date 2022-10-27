package edu.psuti.pe.gui.iconsgrid;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Панель иконки-ярлыка
public class AppIconPanel extends JPanel {
    ImageHelper imageHelper = ImageHelper.getInstance();
    JPanel icon = new JPanel(new BorderLayout());
    JPanel name = new JPanel(new BorderLayout());

    private DragGestureRecognizer dragGestureRecognizer;
    private DragGestureHandler dragGestureHandler;

    public AppIconPanel(String appIconResource, String appName) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);
        setBackground(Color.MAGENTA);

        setMinimumSize(new Dimension(80, 100));
        setPreferredSize(new Dimension(80, 100));
        setMaximumSize(new Dimension(80, 100));

        //addMouseListener(new IconsGridMouseListener());

        setupIcon(appIconResource);
        setupTextLabel(appName);

        add(icon);
        add(name);
    }

    private void setupIcon(String appIconResource) {
        JLabel iconLabel = new JLabel(imageHelper.createImageIconFromSvg(appIconResource, "Placeholder App Icon",
                50, 50));
        iconLabel.setOpaque(false);
        iconLabel.setBackground(Color.red);
        iconLabel.setMinimumSize(new Dimension(80, 60));
        iconLabel.setPreferredSize(new Dimension(80, 60));
        iconLabel.setMaximumSize(new Dimension(80, 60));

        icon.setOpaque(false);
        icon.setBackground(Color.pink);
        icon.setMinimumSize(new Dimension(80, 60));
        icon.setPreferredSize(new Dimension(80, 60));
        icon.setMaximumSize(new Dimension(80, 60));
        icon.add(iconLabel);
    }

    private void setupTextLabel(String appName) {
        name.setOpaque(false);
        name.setBackground(Color.green);
        name.setMinimumSize(new Dimension(80, 40));
        name.setPreferredSize(new Dimension(80, 40));
        name.setMaximumSize(new Dimension(80, 40));
        name.setBorder(new EmptyBorder(3, 0, 0, 0));
        IconTextPanel iconTextPanel = new IconTextPanel(appName);
        name.add(iconTextPanel);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (dragGestureRecognizer == null) {
            dragGestureHandler = new DragGestureHandler(this);
            dragGestureRecognizer = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
                    this,
                    DnDConstants.ACTION_MOVE,
                    dragGestureHandler);
            System.out.println("Drag Gesture Recognizer for AppIconPanel has been setted up.");
        }
    }

    @Override
    public void removeNotify() {
        if (dragGestureRecognizer != null) {
            dragGestureRecognizer.removeDragGestureListener(dragGestureHandler);
            dragGestureHandler = null;
            System.out.println("Drag Gesture Recognizer for AppIconPanel was disposed.");
        }

        dragGestureRecognizer = null;
        super.removeNotify();
    }

    private class IconsGridMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
//            JComponent thisComponent = (JComponent) e.getComponent();
//            thisComponent.setOpaque(true);
//            thisComponent.setBackground(new Color(144, 150, 156));
//            thisComponent.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(Color.GREEN);
            thisComponent.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JComponent thisComponent = (JComponent) e.getComponent();
            thisComponent.setOpaque(true);
            thisComponent.setBackground(Color.RED);
            thisComponent.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
//            JComponent thisComponent = (JComponent) e.getComponent();
//            thisComponent.setOpaque(false);
//            thisComponent.repaint();
        }
    }
}
