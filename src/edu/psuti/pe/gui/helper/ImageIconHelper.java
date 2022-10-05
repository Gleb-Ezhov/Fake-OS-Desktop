package edu.psuti.pe.gui.helper;

import javax.swing.*;
import java.net.URL;

// todo Загрузка изображений в отдельном потоке.
// todo В случае отсутствия изображения отрисовывать иконку-заглушку.
public class ImageIconHelper {
    private static ImageIconHelper imageIconHelper;
    private final String pathPrefix = "/../../../resources/";

    private ImageIconHelper() {}

    public static ImageIconHelper getInstance() {
        if (imageIconHelper == null) {
            imageIconHelper = new ImageIconHelper();
            return imageIconHelper;
        } else {
            return imageIconHelper;
        }
    }

    /**
     * Поиск файла по указанному пути и возврат для него экземпляра ImageIcon.
     * @param imageName Название изображения из папки resources.
     * @param description Подпись к изображению для скрин ридеров.
     * @return ImageIcon для переданного пути изображения; null, если файл не был найден.
     */
    public ImageIcon createImageIcon(String imageName, String description) {
        String resultPath = pathPrefix + imageName;
        // todo Пока не получается взять абсолютный путь из относительного
        URL imgURL = getClass().getResource(imageName); // преобразованию пути в абсолютный URL

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file for an ImageIcon: " + resultPath);
            return null;
        }
    }
}
