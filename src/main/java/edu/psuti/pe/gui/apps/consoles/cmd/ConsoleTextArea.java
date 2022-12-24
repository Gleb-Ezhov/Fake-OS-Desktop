package edu.psuti.pe.gui.apps.consoles.cmd;

import edu.psuti.pe.gui.apps.consoles.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Компонент прокручиваемой области консоли
public class ConsoleTextArea extends JScrollPane implements CommandListener, Terminal {
    String prependLine = "C:\\Users\\user>";
    String[] validCommands = {"tracert", "netstat", "ping", "ipconfig", "nslookup", "cls", "help"};
    // опции для help остаются как для Linux, т.к. к этому моменту произошла подмена названий команд на линуксовские
    String[] validInfoOptions = {"traceroute", "netstat", "ping", "ifconfig", "nslookup", "clear"};
    String helpText = "\nКомандный процессор поддерживает перечисленные ниже команды.\n\ntracert\nnetstat\nping\nipconfig\nnslookup\ncls\n\n" +
            "Для получения сведений об определенной команде наберите HELP <имя команды>";

    private int userInputStart = 0;
    private Command cmd;
    Action oldEnterAction; // Прежнее действие на нажатие/ввод "Enter"
    String findedEntry = "";

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
        textArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        textArea.setText("Введите \"help\" для получения доступа к справке по командам.\n\n");
        appendText(prependLine);

        // Привязка кастомного DocumentFilter'а, который не даёт редактировать ранее выведенный командами текст
        setProtectedDocumentFilter();

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
        KeyStroke terminateKey = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
        inputMap.put(terminateKey, terminateKey.toString());
        actionMap.put(terminateKey.toString(), new CommandTerminateAction());
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

            // По варианту курсовой работу фильтруются все команды кроме сетевых.
            if (!isValidCommand(text)) {
                oldEnterAction.actionPerformed(e); // Перевод строки после отработанной команды
                if (text.isEmpty()) appendText(prependLine);
                else commandFailed(text);
                return;
            }
            text = rebuildCommandToLinuxOne(text, findedEntry); // команда пересобирается под терминал линукса

            // Выполняется вывод для команд help и info, если были введены именно они
            if (findedEntry.contentEquals("help")) {
                appendText(helpText);
                oldEnterAction.actionPerformed(e);
                appendText(prependLine);
                return;
            } else if (findedEntry.contentEquals("info")) {
                // Справка выводится только для допустимых команд
                boolean isThereValidOption = false;
                for (String entry : validInfoOptions) {
                    if (text.startsWith(entry, 5)) {
                        isThereValidOption = true;
                        break;
                    }
                }

                if (!isThereValidOption) {
                    oldEnterAction.actionPerformed(e);
                    appendText("help: нет справки для команды " + text.substring(5) + "\n" + prependLine);
                    return;
                }
            } else if (findedEntry.contentEquals("clear")) {
                ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new DocumentFilter());
                textArea.setText(null);
                appendText(prependLine);
                setProtectedDocumentFilter();
                return;
            }

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

    private boolean isValidCommand(String enteredLine) {
        for (String entry : validCommands) {
            if (enteredLine.startsWith(entry)) {
                // найденное вхождение команды заменяется на её линуксовский вариант
                findedEntry = replaceCommandToLinuxOne(entry, enteredLine.length());
                return true;
            }
        }
        return false;
    }

    private String replaceCommandToLinuxOne(String cmdCommand, int initialCommandSize) {
        switch (cmdCommand) {
            case "tracert":
                return "traceroute";
            case "ipconfig":
                return "ifconfig";
            case "cls":
                return "clear";
            case "help":
                if (initialCommandSize != 4) return "info";
            default:
                return cmdCommand;
        }
    }

    private String rebuildCommandToLinuxOne(String initialCommand, String findedEntry) {
        StringBuilder sb = new StringBuilder(initialCommand);

        // Если это была команда info с опцией, то она заменяется на линуксовскую версию
        if (findedEntry.contentEquals("info") && initialCommand.length() > 4) {
            String optionForInfo = initialCommand.substring(5);
            optionForInfo = replaceCommandToLinuxOne(optionForInfo, optionForInfo.length());
            sb.replace(5, initialCommand.length(), optionForInfo);
        }

        // Замена команды на уже известный findedEntry с командой линукса
        if (!initialCommand.contains(" ")) {
            sb.replace(0, initialCommand.length(), findedEntry);
        } else {
            sb.replace(0, sb.indexOf(" "), findedEntry);
        }

        return sb.toString();
    }

    private void setProtectedDocumentFilter() {
        ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new ProtectedDocumentFilter(this));
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
        SwingUtilities.invokeLater(new AppendTask(this, "\"" + text +  "\" не является внутренней или внешней " +
                "командой, исполняемой программой или пакетным файлом.\n\n" + prependLine));
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

    protected void updateUserInputPos() {
        int pos = textArea.getCaretPosition();
        textArea.setCaretPosition(textArea.getText().length());
        userInputStart = pos;
    }
}
