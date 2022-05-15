package sk.uniza.fri.enums;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum EImageList {
    MENU("/textures/ui/menu.png"),
    PLAYER("/textures/entities/creatures/player.png"),
    KNIGHT("/textures/entities/creatures/knight.png"),
    KNIGHT_DEAD("/textures/entities/creatures/knight_dead.png"),
    COINS("/textures/entities/itemsAndChests/coins.png"),
    SLASH1("/textures/entities/particlesAndProjectiles/slash1.png"),
    SLASH2("/textures/entities/particlesAndProjectiles/slash2.png"),
    SLASH3("/textures/entities/particlesAndProjectiles/slash3.png"),
    HEART("/textures/ui/heart.png"),
    HEALTH_POTION("/textures/entities/itemsAndChests/healthPotion.png"),
    CHEST("/textures/entities/itemsAndChests/chest.png"),
    CHEST_OPEN("/textures/entities/itemsAndChests/chest_open.png"),
    WIZARD("/textures/entities/creatures/wizard.png"),
    GOBLIN("/textures/entities/creatures/goblin.png"),
    MAGE_BOSS("/textures/entities/creatures/mageBoss.png"),
    MAGE_BOSS_DEAD("/textures/entities/creatures/mageBossDead.png"),
    PROJECILE_MAGE("/textures/entities/itemsAndChests/ProjectileMage.png"),
    PORTAL("/textures/entities/itemsAndChests/portal.png");

    private BufferedImage image;

    /**
     * Zoznam obrázkov
     * @param route Cesta k obrázku
     */
    EImageList(String route) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream(route));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Vráti obrázok
     */
    public BufferedImage getImage() {
        return this.image;
    }
}
