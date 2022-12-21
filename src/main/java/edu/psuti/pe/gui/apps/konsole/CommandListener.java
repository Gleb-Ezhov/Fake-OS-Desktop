package edu.psuti.pe.gui.apps.konsole;

public interface CommandListener {
    void commandOutput(String text);

    void commandCompleted(String cmd, int result);

    void commandFailed(Exception exp);
}
