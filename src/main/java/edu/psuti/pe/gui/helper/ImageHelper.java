package edu.psuti.pe.gui.helper;

import edu.psuti.pe.gui.MissingIcon;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.DOMImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageHelper {
    private static ImageHelper imageHelper;
    // префиксы пути до папки с ресурсами:
    private final String pathPrefixURL = "/"; // для URL
    private final String pathPrefixFIS = "src/main/resources/"; // для FileInputStream

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
    public Icon createImageIcon(String imageName, String description) {
        String resultPath = pathPrefixURL + imageName;
        // поиск ресурса картинки и его возрат в виде Uniform Resource Locator указателя
        URL imgURL = getClass().getResource(resultPath);

        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file for an ImageIcon: " + resultPath);
            return new MissingIcon();
        }
    }

    /**
     * Метод, создающий изображение ImageIcon из SVG файла, используя библиотеку Apache Batik.
     * @param svgImageName Название файла-ресурса.
     * @param description Описание изображения для скринридера.
     * @param svgWidth Ширина итогового изображения.
     * @param svgHeight Высота итогового изображения.
     * @return Экземпляр ImageIcon по ссылке типа Icon.
     */
    public Icon createImageIconFromSvg(String svgImageName, String description, float svgWidth, float svgHeight) {
        System.out.println("Trying to create Image from an SVG file:");
        String resultPath = pathPrefixURL + svgImageName;
        URL imgURL = getClass().getResource(resultPath);

        if (imgURL != null) {
            try {
                System.out.println("URL of founded image: " + imgURL.getPath());
                System.out.println("URI respectively: " + imgURL.toURI().toString());

                SvgTranscoder transcoder = new SvgTranscoder();
                transcoder.setTranscodingHints(getHints(svgWidth, svgHeight));
                transcoder.transcode(new TranscoderInput(imgURL.toURI().toString()), null);

                return new ImageIcon(transcoder.getImage(), description);
            } catch (Exception exc) {
                exc.printStackTrace();
                return new MissingIcon();
            }
        } else {
            System.err.println("Couldn't find file for an ImageIcon: " + resultPath);
            return new MissingIcon();
        }
    }

    // Создание необходимых подсказок для перекодировки из SVG файла в экземпляр Image
    private TranscodingHints getHints(float width, float height) {
        TranscodingHints hints = new TranscodingHints();
        hints.put(ImageTranscoder.KEY_WIDTH, width);
        hints.put(ImageTranscoder.KEY_HEIGHT, height);
        hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
        hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
        hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
        hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);
        return hints;
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
