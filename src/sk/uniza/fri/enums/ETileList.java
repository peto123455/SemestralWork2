package sk.uniza.fri.enums;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public enum ETileList {
    WALL1("/textures/blocks/wall_1.png", 1, true),
    WALL2("/textures/blocks/wall_2.png", 2, true),
    WALL_CRACK("/textures/blocks/wall_crack.png", 3, true),
    FLOOR1("/textures/blocks/floor_1.png", 4, false),
    FLOOR2("/textures/blocks/floor_2.png", 5, false),
    FLOOR3("/textures/blocks/floor_3.png", 6, false),
    FLOOR4("/textures/blocks/floor_4.png", 7, false),
    FLOOR5("/textures/blocks/floor_5.png", 8, false);

    private BufferedImage image;
    private int id;
    private boolean hasCollision;

    /**
     * @param id ID
     * @return Blok na základe ID
     */
    public static ETileList getByID(int id) {
        if (id < 0) {
            return null;
        }
        for (ETileList image : ETileList.values()) {
            if (id == image.getId()) {
                return image;
            }
        }
        return null;
    }

    /**
     * Zoznam blokov
     * @param route Cesta k obrázku bloku
     * @param id ID
     * @param hasCollision Má kolízie
     */
    ETileList(String route, int id, boolean hasCollision) {
        try {
            this.image = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream(route)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = id;
        this.hasCollision = hasCollision;
    }

    /**
     * @return Vráti obrázok bloku
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * @return Vráti ID bloku
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return Má kolízie
     */
    public boolean hasCollision() {
        return this.hasCollision;
    }
}
