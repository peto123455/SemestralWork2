package sk.uniza.fri.enums;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public enum EImageList {
    MENU("/textures/ui/menu.png"),
    PLAYER("/textures/entities/creatures/player.png"),
    KNIGHT("/textures/entities/creatures/knight.png"),
    KNIGHT_DEAD("/textures/entities/creatures/knight_dead.png"),
    COINS("/textures/entities/itemsAndChests/coins.png"),
    SLASH1("/textures/entities/particlesAndProjectiles/slash1.png"),
    SLASH2("/textures/entities/particlesAndProjectiles/slash2.png"),
    SLASH3("/textures/entities/particlesAndProjectiles/slash3.png"),
    EXPLOSION1("/textures/entities/particlesAndProjectiles/explosion1.png"),
    EXPLOSION2("/textures/entities/particlesAndProjectiles/explosion2.png"),
    EXPLOSION3("/textures/entities/particlesAndProjectiles/explosion3.png"),
    EXPLOSION4("/textures/entities/particlesAndProjectiles/explosion4.png"),
    EXPLOSION5("/textures/entities/particlesAndProjectiles/explosion5.png"),
    EXPLOSION6("/textures/entities/particlesAndProjectiles/explosion6.png"),
    EXPLOSION7("/textures/entities/particlesAndProjectiles/explosion7.png"),
    HEART("/textures/ui/heart.png"),
    HEALTH_POTION("/textures/entities/itemsAndChests/healthPotion.png"),
    SPEED_POTION("/textures/entities/itemsAndChests/speedPotion.png"),
    CHEST("/textures/entities/itemsAndChests/chest.png"),
    CHEST_OPEN("/textures/entities/itemsAndChests/chest_open.png"),
    WIZARD("/textures/entities/creatures/wizard.png"),
    GOBLIN("/textures/entities/creatures/goblin.png"),
    MAGE_BOSS("/textures/entities/creatures/mageBoss.png"),
    MAGE_BOSS_DEAD("/textures/entities/creatures/mageBossDead.png"),
    PROJECILE_MAGE("/textures/entities/particlesAndProjectiles/ProjectileMage.png"),
    CURSOR("/textures/ui/cursor.png"),
    PORTAL("/textures/entities/itemsAndChests/portal.png");

    private BufferedImage image;

    /**
     * Zoznam obrázkov
     * @param route Cesta k obrázku
     */
    EImageList(String route) {
        try {
            this.image = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream(route)));
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
