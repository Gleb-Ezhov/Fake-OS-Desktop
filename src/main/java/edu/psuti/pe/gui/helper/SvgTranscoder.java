package edu.psuti.pe.gui.helper;

import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import java.awt.image.BufferedImage;

// Перекодировщик SVG изображения в экземпляр BufferedImage для использования в Swing приложении.
public class SvgTranscoder extends ImageTranscoder {
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public BufferedImage createImage(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        return image;
    }

    @Override
    public void writeImage(BufferedImage bufferedImage, TranscoderOutput transcoderOutput) { }
}
