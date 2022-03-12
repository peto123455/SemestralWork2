package sk.uniza.fri;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ETileList {
    WALL1("/textures/wall_1.png", 1, true),
    WALL2("/textures/wall_2.png", 2, true),
    WALL_CRACK("/textures/wall_crack.png", 3, true),
    FLOOR1("/textures/floor_1.png", 4, false),
    FLOOR2("/textures/floor_2.png", 5, false),
    FLOOR3("/textures/floor_3.png", 6, false),
    FLOOR4("/textures/floor_4.png", 7, false),
    FLOOR5("/textures/floor_5.png", 8, false);

    private BufferedImage image;
    private int id;
    private boolean hasCollision;

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

    ETileList(String route, int id, boolean hasCollision) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream(route));
        } catch (IOException e) {
            //Nič
        }
        this.id = id;
        this.hasCollision = hasCollision;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getId() {
        return this.id;
    }

    public boolean hasCollision() {
        return this.hasCollision;
    }
}
