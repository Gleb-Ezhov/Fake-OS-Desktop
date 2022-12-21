package edu.psuti.pe.gui.apps.konsole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Компонент прокручиваемой области консоли
public class ConsoleTextArea extends JScrollPane implements CommandListener, Terminal {
    private int userInputStart = 0;
    private Command cmd;
    Action oldAction; // Прежнее действие на нажатие/ввод "Enter"

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

        // Привязка кастомного DocumentFilter'а, который не даёт редактировать ранее выведенный командами текст
        ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new ProtectedDocumentFilter(this));

        // Создания экземпляра Command для выполнения введённых команд
        cmd = new Command(this);

        // Привязка кастомного Action на ввод (нажатие "Enter") внутри JTextArea
        InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = textArea.getActionMap();
        oldAction = actionMap.get("insert-break"); // сохраняется дефолтный Action для дальнейшего использования
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, enterKey.toString());
        actionMap.put(enterKey.toString(), new CommandEnterAction());
    }

    protected void updateUserInputPos() {
        int pos = textArea.getCaretPosition();
        textArea.setCaretPosition(textArea.getText().length());
        userInputStart = pos;
    }

    class CommandEnterAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int range = textArea.getCaretPosition() - userInputStart;
            try {
                String text = textArea.getText(userInputStart, range).trim();
                System.out.println("[" + text + "]");
                userInputStart += range;
                if (!cmd.isRunning()) {
                    cmd.execute(text);
                } else {
                    try {
                        cmd.send(text + "\n");
                    } catch (IOException ex) {
                        appendText("!! Failed to send command to process: " + ex.getMessage() + "\n");
                    }
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(ConsoleTextArea.class.getName()).log(Level.SEVERE, null, ex);
            }
            oldAction.actionPerformed(e);
        }
    }

    // CommandListener implementation
    @Override
    public void commandOutput(String text) {
        SwingUtilities.invokeLater(new AppendTask(this, text));
    }

    @Override
    public void commandFailed(Exception exp) {
        SwingUtilities.invokeLater(new AppendTask(this, "Command failed - " + exp.getMessage()));
    }

    @Override
    public void commandCompleted(String cmd, int result) {
        appendText("\n> " + cmd + " exited with " + result + "\n");
        appendText("\n");
    }

    // Terminal implementation (и по совместительству UserInput реализация тоже)
    @Override
    public int getUserInputStart() {
        return userInputStart;
    }

    @Override
    public void appendText(String text) {
        textArea.append(text);
        updateUserInputPos();
    }
}
