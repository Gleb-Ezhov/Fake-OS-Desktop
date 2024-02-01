package edu.psuti.pe.gui.window;

import edu.psuti.pe.gui.apps.about.AboutAppWindow;
import edu.psuti.pe.gui.apps.consoles.cmd.CmdWindow;
import edu.psuti.pe.gui.apps.consoles.konsole.KonsoleWindow;
import edu.psuti.pe.gui.apps.devicemanager.DeviceManagerWindow;
import edu.psuti.pe.gui.apps.dolphin.Window;

import java.io.Serializable;

public class WindowCreator implements Serializable {
    private static WindowCreator windowCreator;
    WindowsManager windowsManager = WindowsManager.getInstance(null);

    private WindowCreator() {}

    public static WindowCreator getInstance() {
        if (windowCreator == null) {
            windowCreator = new WindowCreator();
        }
        return windowCreator;
    }

    public void createWindow(String imageResource, String appName) {
        if (appName.equals("Dolphin")) {
            WindowPanel dolphin = new Window(imageResource, appName, 1000, 500);
            windowsManager.addWindow(dolphin);
        } else if (appName.equals("Konsole")) {
            WindowPanel konsole = new KonsoleWindow(imageResource, appName, 800, 470);
            windowsManager.addWindow(konsole);
        } else if (appName.equals("Командная строка")) {
            WindowPanel cmd = new CmdWindow(imageResource, appName, 800, 470);
            windowsManager.addWindow(cmd);
        } else if (appName.equals("Диспетчер устройств")) {
            WindowPanel deviceManager = new DeviceManagerWindow(imageResource, appName, 800, 510);
            windowsManager.addWindow(deviceManager);
        } else if (appName.equals("About")) {
            WindowPanel aboutWindow = new AboutAppWindow(imageResource, appName, 400, 200);
            windowsManager.addWindow(aboutWindow);
        } else {
            WindowPanel testWindow = new WindowPanel(imageResource, appName, 300, 350);
            windowsManager.addWindow(testWindow);
        }
    }
}
