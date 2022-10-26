package edu.psuti.pe.gui.iconsgrid;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AppIconPanel extends JPanel {
    ImageHelper imageHelper = ImageHelper.getInstance();
    JPanel icon = new JPanel(new BorderLayout());
    JPanel name = new JPanel(new BorderLayout());

    private DragGestureRecognizer dragGestureRecognizer;
    private DragGestureHandler dragGestureHandler;

    public AppIconPanel(String appIconResource, String appName) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(true);
        setBackground(Color.MAGENTA);

        setMinimumSize(new Dimension(80, 90));
        setPreferredSize(new Dimension(80, 90));
        setMaximumSize(new Dimension(80, 90));

        //addMouseListener(new IconsGridMouseListener());

        setupIcon(appIconResource);
        setupTextLabel(appName);

        //setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(icon);
        add(name);
    }

    private void setupIcon(String appIconResource) {
        JLabel iconLabel = new JLabel(imageHelper.createImageIconFromSvg(appIconResource, "Placeholder App Icon",
                50, 50));
        iconLabel.setOpaque(true);
        iconLabel.setBackground(Color.red);
        iconLabel.setMinimumSize(new Dimension(80, 60));
        iconLabel.setPreferredSize(new Dimension(80, 60));
        iconLabel.setMaximumSize(new Dimension(80, 60));

        icon.setOpaque(true);
        icon.setBackground(Color.pink);
        icon.setMinimumSize(new Dimension(80, 60));
        icon.setPreferredSize(new Dimension(80, 60));
        icon.setMaximumSize(new Dimension(80, 60));
        icon.add(iconLabel);
    }

    private void setupTextLabel(String appName) {
        JLabel nameLabel = new JLabel();

        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.orange);
        nameLabel.setMinimumSize(new Dimension(80, 30));
        nameLabel.setPreferredSize(new Dimension(80, 30));
        nameLabel.setMaximumSize(new Dimension(80, 30));

        // todo: ? отрисовка текста с тенью через Graphics2D
        // todo: шрифт масштабируется, вместо изменения кегля. нужно исправить
        nameLabel.setFont(new Font("Noto Sans Regular", Font.PLAIN, 12));
        //nameLabel.setForeground(new Color(0xffffdd));
        String labelText = String.format(
                "<html>" +
                    "<div style='width:%dpx; text-align:center; color:white;'>" +
                        "%s" +
                    "</div>" +
                "</html>",
                65, appName);
        nameLabel.setText(labelText);

        name.setOpaque(true);
        name.setBackground(Color.green);
        name.setMinimumSize(new Dimension(80, 30));
        name.setPreferredSize(new Dimension(80, 30));
        name.setMaximumSize(new Dimension(80, 30));
        name.add(nameLabel);
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
