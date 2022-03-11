package sk.uniza.fri;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class GameTile {

    private BufferedImage image;

    public GameTile(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
