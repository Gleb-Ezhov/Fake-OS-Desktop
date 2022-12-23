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
    String prependLine = "user@rosa2021 ~ $ ";
    private int userInputStart = 0;
    private Command cmd;
    Action oldEnterAction; // Прежнее действие на нажатие/ввод "Enter"

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
        appendText(prependLine);

        // Привязка кастомного DocumentFilter'а, который не даёт редактировать ранее выведенный командами текст
        ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new ProtectedDocumentFilter(this));

        // Создания экземпляра Command для выполнения введённых команд
        cmd = new Command(this);

        // Привязка кастомного Action на ввод (нажатие "Enter") внутри JTextArea
        InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = textArea.getActionMap();
        oldEnterAction = actionMap.get("insert-break"); // сохраняется дефолтный Action для дальнейшего использования
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, enterKey.toString());
        actionMap.put(enterKey.toString(), new CommandEnterAction());

        // Привязка кастомного действия на комбинацию Ctrl + C внутри области с текстом
        KeyStroke terminateKey = KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK);
        inputMap.put(terminateKey, terminateKey.toString());
        actionMap.put(terminateKey.toString(), new CommandTerminateAction());
    }

    protected void updateUserInputPos() {
        int pos = textArea.getCaretPosition();
        textArea.setCaretPosition(textArea.getText().length());
        userInputStart = pos;
    }

    class CommandEnterAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = "";
            // Выделение команды, устранение лишних пробелов в начале и конце команды,
            // обновление позиции пользовательского ввода.
            try {
                int range = textArea.getCaretPosition() - userInputStart;
                text = textArea.getText(userInputStart, range).trim();
                userInputStart += range;

                System.out.println("[" + text + "]");
            } catch (BadLocationException ex) {
                Logger.getLogger(ConsoleTextArea.class.getName()).log(Level.SEVERE, null, ex);
            }

            // todo: стартовое сообщение при запуске, сообщающее о команде "help"
            // todo: команда help, выводящая список доступных команд
            // todo: фильтр для команд (воспринимаются только сетевые)

            // Дальнейший разбор введённой команды и её запуск
            if (!cmd.isRunning()) {
                cmd.execute(text);
            } else {
                try {
                    cmd.send(text + "\n");
                } catch (IOException ex) {
                    appendText("!! Failed to send command to process: " + ex.getMessage() + "\n");
                }
            }

            oldEnterAction.actionPerformed(e); // Перевод строки после отработанной команды
            // Добавление предваряющего текста, если была введена пустая строка
            if (text.isEmpty()) appendText(prependLine);
        }
    }

    class CommandTerminateAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            cmd.terminate();
        }
    }

    // CommandListener implementation
    @Override
    public void commandOutput(String text) {
        SwingUtilities.invokeLater(new AppendTask(this, text));
    }

    @Override
    public void commandFailed(String text) {
        SwingUtilities.invokeLater(new AppendTask(this, "bash: " + text + ": команда не найдена\n" + prependLine));
    }

    @Override
    public void commandCompleted(String cmd, int result) {
        SwingUtilities.invokeLater(new AppendTask(this, prependLine));
        //appendText("\n> " + cmd + " exited with " + result + "\n");
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
