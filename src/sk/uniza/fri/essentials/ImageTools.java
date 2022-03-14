package sk.uniza.fri.essentials;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageTools {
    public static BufferedImage flip(BufferedImage image) {
        AffineTransform at = new AffineTransform();
        BufferedImage imageFliped = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imageFliped.createGraphics();

        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
        at.concatenate(AffineTransform.getTranslateInstance(-image.getHeight(), 0));

        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return imageFliped;
    }
}
