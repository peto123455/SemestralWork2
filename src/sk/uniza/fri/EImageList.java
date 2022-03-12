package sk.uniza.fri;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum EImageList {
    WALL1("/tiles/wall_1.png", 1),
    KNIGHT("/tiles/knight.png", -1);

    private BufferedImage image;
    private int id;

    public static EImageList getByID(int id) {
        if (id < 0) {
            return null;
        }
        for (EImageList image : EImageList.values()) {
            if (id == image.getId()) {
                return image;
            }
        }
        return null;
    }

    EImageList(String route, int id) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream(route));
        } catch (IOException e) {
            //Nič
        }
        this.id = id;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getId() {
        return this.id;
    }
}
