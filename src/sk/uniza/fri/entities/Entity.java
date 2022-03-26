package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.Vector;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.ui.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private BufferedImage[] images;

    private final Position position;
    private Position toPos;

    public Entity(EImageList[] images) {
        this.images = new BufferedImage[images.length];

        for (int i = 0; i < images.length; ++i) {
            this.images[i] = images[i].getImage();
        }

        this.position = new Position();
    }

    public BufferedImage getImage() {
        return this.getImage(0);
    }

    public BufferedImage getImage(int i) {
        if (i < 0 || i >= this.images.length) {
            return null;
        }

        return this.images[i];
    }

    public Position getPosition() {
        return this.position;
    }

    public Position getPositionRelativeToGrid() {
        return Position.getPositionRelativeToGrid(this.position);
    }

    public boolean isNearEntity(Entity entity, double distance) {
        return Position.getDistance(this.getPosition(), entity.getPosition()) <= distance;
    }

    public void changeImages(EImageList[] images) {
        this.images = new BufferedImage[images.length];

        for (int i = 0; i < images.length; ++i) {
            this.images[i] = images[i].getImage();
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.getImage(), this.getPosition().getCoordX() - GamePanel.TILE_SIZE / 2, this.getPosition().getCoordY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    protected BufferedImage[] getImages() {
        return this.images;
    }

    protected Position getToPos() {
        return this.toPos;
    }

    public void goToPos(Position toPos) {
        this.toPos = toPos;
    }

    public void update(Game game) {
        this.updatePos(game);
    }

    protected EDirection updatePos(Game game) {
        return this.updatePos(game, this.toPos);
    }

    protected EDirection updatePos(Game game, Position position) {
        if (position == null) {
            return null;
        }

        Vector vector = new Vector(position.getCoordX() - this.position.getCoordX(), position.getCoordY() - this.position.getCoordY());
        //Obmedzenie maximálnej prejdenej vzialenosti na 4px
        if (vector.length() > 4) {
            vector.normalize();
            vector.multiply(4);
        }

        Position pos = new Position((int)vector.getX(), (int)vector.getY());

        if (pos.getCoordX() != 0 || pos.getCoordY() != 0) {
            this.move(new Position(pos.getCoordX(), 0), game.getMapHandler());
            this.move(new Position(0, pos.getCoordY()), game.getMapHandler());

            if (pos.getCoordX() < 0) {
                return EDirection.LEFT;
            } else if (pos.getCoordX() > 0) {
                return EDirection.RIGHT;
            }
        } else {
            this.toPos = null;
        }
        return null;
    }


    protected void move(Position byPos, MapHandler mapHandler) {
        //Systém kolízií
        Position futurePosition = new Position().addPosition(this.getPosition()).addPosition(byPos);
        futurePosition = Position.getPositionRelativeToGrid(futurePosition);
        GameTile tile = mapHandler.getTile(futurePosition.getCoordX(), futurePosition.getCoordY());
        if (tile != null && tile.hasCollision()) {
            return;
        }

        //Pohyb
        this.getPosition().addPosition(byPos);
    }
}
