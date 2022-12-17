package edu.psuti.pe.gui.apps.dolphin;

public class FilesViewportSingleton {
    private static FilesViewportSingleton instance;
    private FilesViewportPanel filesViewportPanel;

    private FilesViewportSingleton(FilesViewportPanel filesViewport) { filesViewportPanel = filesViewport; }

    public static FilesViewportSingleton getInstance(FilesViewportPanel filesViewport) {
        if (instance == null) {
            instance = new FilesViewportSingleton(filesViewport);
            return instance;
        } else {
            return instance;
        }
    }

    public FilesViewportPanel getFilesViewportPanel() {
        return filesViewportPanel;
    }
}
