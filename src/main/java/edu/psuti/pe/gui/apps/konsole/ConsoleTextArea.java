package edu.psuti.pe.gui.apps.konsole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

// Компонент прокручиваемой области консоли
public class ConsoleTextArea extends JScrollPane {
    JTextArea textArea = new JTextArea() {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2D = (Graphics2D)g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            super.paintComponent(g2D);
        }
    };

    public ConsoleTextArea() {
        setMinimumSize(new Dimension(100, 100));
        setPreferredSize(new Dimension(100, 100));
        getVerticalScrollBar().setUnitIncrement(16); // увеличение скорости прокрутки
        setViewportView(textArea);
        setBorder(new EmptyBorder(0, 0, 0, 0)); // убирается декоративная граница у JScrollPane

        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setLineWrap(true);
        textArea.setTabSize(4); // можно вообще отключить
        textArea.setFont(new Font("Roboto Mono Regular", Font.PLAIN, 18));

        InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = textArea.getActionMap();
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, enterKey.toString());
        actionMap.put(enterKey.toString(), new CommandEnterAction());
    }

    class CommandEnterAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea txtArea = (JTextArea) e.getSource();
            txtArea.setText("CommandEnterAction performed!");
        }
    }
}
