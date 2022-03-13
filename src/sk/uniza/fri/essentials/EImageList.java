package sk.uniza.fri.essentials;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum EImageList {
    PLAYER("/textures/player.png"),
    PLAYER_I("/textures/player_inverted.png"),
    KNIGHT("/textures/knight.png"),
    KNIGHT_I("/textures/knight_inverted.png"),
    KNIGHT_DEAD("/textures/knight_dead.png"),
    COINS("/textures/coins.png"),
    SLASH1("/textures/slash1.png"),
    SLASH2("/textures/slash2.png"),
    SLASH3("/textures/slash3.png"),
    HEART("/textures/heart.png"),
    PORTAL("/textures/portal.png");

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
