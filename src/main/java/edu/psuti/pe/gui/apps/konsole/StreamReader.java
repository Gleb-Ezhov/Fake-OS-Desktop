package edu.psuti.pe.gui.apps.konsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StreamReader extends Thread {

    private InputStream is;
    private CommandListener listener;

    public StreamReader(CommandListener listener, InputStream is) {
        this.is = is;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        try {
            // Оборачивание входного потока байт в ISReader для представления их в виде символов нужной кодировки.
            // Дальнейшее оборачивание isr в BufferedReader для повышения эффективности чтения.
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            // Посимвольное чтение из потока и вывод в textArea
            int value = -1;
            while ((value = isr.read()) != -1) {
                listener.commandOutput(Character.toString((char) value));
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}
