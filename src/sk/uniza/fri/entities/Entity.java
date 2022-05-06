package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.Vector;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameThread;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.map.MapHandler;
import sk.uniza.fri.ui.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private BufferedImage[] images;

    private final Position position;
    private Position toPos;
    private int speed;

    public Entity(EImageList[] images) {
        this.images = new BufferedImage[images.length];

        for (int i = 0; i < images.length; ++i) {
            this.images[i] = images[i].getImage();
        }

        this.speed = 4;
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
        g2d.drawImage(this.getImage(), (int)this.getPosition().getX() - GamePanel.TILE_SIZE / 2, (int)this.getPosition().getY() - GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
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

    public boolean update(Game game) {
        this.updatePos(game);
        return true;
    }

    protected EDirection updatePos(Game game) {
        return this.updatePos(game, this.toPos);
    }

    protected EDirection updatePos(Game game, Position position) {
        if (position == null) {
            return null;
        }

        Vector vector = new Vector(position.getX() - this.position.getX(), position.getY() - this.position.getY());
        //Obmedzenie maximálnej prejdenej vzialenosti na 4px
        if (vector.length() > 50 * this.speed * GameThread.getInstance().getDeltaTime()) {
            vector.normalize();
            vector.multiply(50 * this.speed * GameThread.getInstance().getDeltaTime());
        }

        Position pos = new Position(vector.getX(), vector.getY());

        if (pos.getX() != 0 || pos.getY() != 0) {
            this.move(new Position(pos.getX(), 0), game.getMapHandler());
            this.move(new Position(0, pos.getY()), game.getMapHandler());

            if (pos.getX() < 0) {
                return EDirection.LEFT;
            } else if (pos.getX() > 0) {
                return EDirection.RIGHT;
            }
        } else {
            this.toPos = null;
        }
        return null;
    }

    protected void move(Position byPos, MapHandler mapHandler) {
        //byPos.multiply(GameThread.getInstance().getDeltaTime() * 50);

        //Systém kolízií
        Position futurePosition = new Position().addPosition(this.getPosition()).addPosition(byPos);

        if (this.isCollision(futurePosition, mapHandler.getMap())) {
            return;
        }

        //Pohyb
        this.getPosition().addPosition(byPos);
    }

    protected boolean isCollision(Position futurePosition, Map map) {
        futurePosition = Position.getPositionRelativeToGrid(futurePosition);
        GameTile tile = map.getTile((int)futurePosition.getX(), (int)futurePosition.getY());
        if (tile != null && tile.hasCollision()) {
            return true;
        }
        return false;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
