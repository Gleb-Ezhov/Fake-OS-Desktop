package edu.psuti.pe;

import edu.psuti.pe.gui.FrameFakeOSDesktop;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            FrameFakeOSDesktop desktop = new FrameFakeOSDesktop();
            SwingUtilities.invokeLater(desktop::createAndShowGUI);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
