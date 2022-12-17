package edu.psuti.pe.gui;

import edu.psuti.pe.gui.helper.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

// Контейнер верхнего уровня (корневой) для окна программы
public class FrameFakeOSDesktop extends JFrame {
    ImageHelper imageHelper = ImageHelper.getInstance();
    // Кастомная панель контента с фоновой картинкой для всего раб. стола
    private CustomContentPane contentPane = CustomContentPane.getInstance();
    // Панель рабочего стола с послойным управлением всеми элементами
    private DesktopPanel desktopPanel;

    // Графическое окружение и устройство для вывода изображения на дисплей (для включения fullscreen режима)
    private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private static GraphicsDevice device = ge.getScreenDevices()[0];

    public FrameFakeOSDesktop() {
        System.out.println("Initializing objects...");
        // Порядок вызова методов ИМЕЕТ ЗНАЧЕНИЕ
        registerRequiredFonts();
        setCustomContentPane();
        addChildrenComponents();
    }

    private void registerRequiredFonts() {
        String pathPrefixURL = "/"; // для URL
        String[] ttfNames = new String[]{ "Hack-Regular.ttf", "NotoSans-Regular.ttf" };

        for (String name : ttfNames) {
            String resultPath = pathPrefixURL + name;
            URL imgURL = getClass().getResource(resultPath);

            if (imgURL != null) {
                try {
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(imgURL.toURI())));
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            } else {
                System.err.println("Couldn't find specified font: " + resultPath);
            }
        }

        for (Font font : ge.getAllFonts()) {
            System.out.println(font.toString());
        }
    }

    /**
     * Установка в качестве панели содержимого кастомного JPanel объекта, чтобы работать
     * с ней как с JComponent типом, а не Container. Используется BoxLayout способ компоновки.
     */
    private void setCustomContentPane() {
        setContentPane(contentPane);
    }

    private void addChildrenComponents() {
        // DesktopPanel инициализируется здесь, т.к. её элементам нужна предварительная загрузка некоторых ресурсов.
        desktopPanel = new DesktopPanel();
        contentPane.add(desktopPanel.getPanel());
    }

    public void createAndShowGUI() {
        //Create and set up the window.
        setTitle("desktop prototype");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1280, 720));

        // Display the window.
        pack();
        boolean isFullScreen = false; // возможно будет вынесен отдельный параметр в GUI для этого
        if (isFullScreen) device.setFullScreenWindow(this);
        setVisible(true);
    }
}
