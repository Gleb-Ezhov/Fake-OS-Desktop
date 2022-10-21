package edu.psuti.pe.gui.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.net.URL;

// todo Загрузка изображений в отдельном потоке.
// todo В случае отсутствия изображения отрисовывать иконку-заглушку.
public class ImageHelper {
    private static ImageHelper imageHelper;
    // префиксы пути до папки с ресурсами:
    private final String pathPrefixURL = "../../../../../resources/"; // для URL
    private final String pathPrefixFIS = "src/resources/"; // для FileInputStream

    private ImageHelper() {}

    public static ImageHelper getInstance() {
        if (imageHelper == null) {
            imageHelper = new ImageHelper();
            return imageHelper;
        } else {
            return imageHelper;
        }
    }

    /**
     * Поиск файла по указанному пути и возврат для него экземпляра ImageIcon.
     * @param imageName Название изображения из папки resources.
     * @param description Подпись к изображению для скрин ридеров.
     * @return ImageIcon для переданного пути изображения; null, если файл не был найден.
     */
    public ImageIcon createImageIcon(String imageName, String description) {
        String resultPath = pathPrefixURL + imageName;
        // поиск ресурса картинки и его возрат в виде Uniform Resource Locator указателя
        URL imgURL = getClass().getResource(resultPath);

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file for an ImageIcon: " + resultPath);
            return null;
        }
    }

    /**
     * Получение Image изображения по его именя из resources с возможностью
     * вырезать из него нужную прямоугольную область.
     * @param imageName Название ресурса.
     * @param x Левый верхний угол - x
     * @param y Левый верхний угол - y
     * @param w Ширина прямоугольной области
     * @param h Высота прямоугольной области
     * @return Готовое Image изображение.
     */
    public Image getSubImageFromBufferedImage(String imageName, int x, int y, int w, int h) {
        String resultPath = pathPrefixFIS + imageName;

        try (FileInputStream fis = new FileInputStream(resultPath)) {
            BufferedImage buffImg = javax.imageio.ImageIO.read(fis);
            buffImg = buffImg.getSubimage(x, y, w, h);
            return buffImg;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
}
