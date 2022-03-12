package sk.uniza.fri.essentials;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum EImageList {
    KNIGHT("/textures/knight.png"),
    KNIGHT_I("/textures/knight_inverted.png"),
    COINS("/textures/coins.png");

    private BufferedImage image;

    EImageList(String route) {
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
