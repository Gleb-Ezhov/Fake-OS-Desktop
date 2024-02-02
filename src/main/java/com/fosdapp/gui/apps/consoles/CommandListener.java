package com.fosdapp.gui.apps.consoles;

public interface CommandListener {
    void commandOutput(String text);

    void commandCompleted(String cmd, int result);

    void commandFailed(String text);
}
