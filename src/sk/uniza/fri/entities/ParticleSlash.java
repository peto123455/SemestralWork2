package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ParticleSlash extends Particle {
    private Direction direction;

    public ParticleSlash(Position position, Direction direction) {
        super(position, new EImageList[]{EImageList.SLASH1, EImageList.SLASH2, EImageList.SLASH3}, 150);
        super.getPosition().addPosition(direction.getPos(20));
        this.direction = direction;
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == Direction.LEFT) {
            return ImageTools.flip(super.getImage());
        }

        return super.getImage();
    }

    /*@Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.getImage(), super.getPosition().getCoordX() - GamePanel.TILE_SIZE / 2, super.getPosition().getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }*/
}
