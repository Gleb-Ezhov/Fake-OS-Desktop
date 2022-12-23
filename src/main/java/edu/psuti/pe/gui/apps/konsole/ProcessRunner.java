package edu.psuti.pe.gui.apps.konsole;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class ProcessRunner extends Thread {
    private List<String> cmds;
    private CommandListener listener;

    private StringJoiner sj;
    private Process process;

    public ProcessRunner(CommandListener listener, List<String> cmds) {
        this.cmds = cmds;
        this.listener = listener;

        // Создание строки с исполняемыми командами через пробел
        sj = new StringJoiner(" ");
        cmds.stream().forEach((cmd) -> {
            sj.add(cmd);
        });

        start();
    }

    @Override
    public void run() {
        try {
            System.out.println("cmds = " + cmds);
            ProcessBuilder pb = new ProcessBuilder(cmds);
            pb.redirectErrorStream();
            process = pb.start();
            StreamReader reader = new StreamReader(listener, process.getInputStream());
            // Need a stream writer...

            int result = process.waitFor();

            // Terminate the stream writer
            reader.join();

            listener.commandCompleted(sj.toString(), result);
        } catch (Exception exp) {
            exp.printStackTrace();
            listener.commandFailed(sj.toString());
        }
    }

    public void write(String text) throws IOException {
        if (process != null && process.isAlive()) {
            process.getOutputStream().write(text.getBytes());
            process.getOutputStream().flush();
        }
    }

    public void destroy() {
        if (process != null && process.isAlive()) {
            process.destroy();
        }
    }
}
