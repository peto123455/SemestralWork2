package sk.uniza.fri.enums;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum EImageList {
    MENU("/textures/menu.png"),
    PLAYER("/textures/player.png"),
    KNIGHT("/textures/knight.png"),
    KNIGHT_DEAD("/textures/knight_dead.png"),
    COINS("/textures/coins.png"),
    SLASH1("/textures/slash1.png"),
    SLASH2("/textures/slash2.png"),
    SLASH3("/textures/slash3.png"),
    HEART("/textures/heart.png"),
    HEALTH_POTION("/textures/healthPotion.png"),
    CHEST("/textures/chest.png"),
    CHEST_OPEN("/textures/chest_open.png"),
    WIZARD("/textures/wizard.png"),
    GOBLIN("/textures/goblin.png"),
    PORTAL("/textures/portal.png");

    private BufferedImage image;

    EImageList(String route) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream(route));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
