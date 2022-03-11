package sk.uniza.fri;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ETileList {
    WALL1("/tiles/wall_1.png");

    private BufferedImage image;

    ETileList(String route) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream(route));
        } catch (IOException e) {
            //Nič
        }
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
