package edu.psuti.pe.gui.apps.konsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    private CommandListener listener;
    private ProcessRunner runner;

    public Command(CommandListener listener) {
        this.listener = listener;
    }

    public boolean isRunning() {

        return runner != null && runner.isAlive();

    }

    public void execute(String cmd) {

        if (!cmd.isEmpty()) {

            List<String> values = new ArrayList<>(25);
            if (cmd.contains("\"")) {

                while (cmd.contains("\"")) {

                    String start = cmd.substring(0, cmd.indexOf("\""));
                    cmd = cmd.substring(start.length());
                    String quote = cmd.substring(cmd.indexOf("\"") + 1);
                    cmd = cmd.substring(cmd.indexOf("\"") + 1);
                    quote = quote.substring(0, cmd.indexOf("\""));
                    cmd = cmd.substring(cmd.indexOf("\"") + 1);

                    if (!start.trim().isEmpty()) {
                        String parts[] = start.trim().split(" ");
                        values.addAll(Arrays.asList(parts));
                    }
                    values.add(quote.trim());

                }

                if (!cmd.trim().isEmpty()) {
                    String parts[] = cmd.trim().split(" ");
                    values.addAll(Arrays.asList(parts));
                }

                for (String value : values) {
                    System.out.println("[" + value + "]");
                }

            } else {

                if (!cmd.trim().isEmpty()) {
                    String parts[] = cmd.trim().split(" ");
                    values.addAll(Arrays.asList(parts));
                }

            }

            runner = new ProcessRunner(listener, values);

        }

    }

    public void send(String cmd) throws IOException {
        runner.write(cmd);
    }

    public void terminate() {
        runner.destroy();
    }
}
