package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.*;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.MapHandler;

import java.awt.image.BufferedImage;

public class Enemy extends Entity implements IEntityAlive {
    private HealthSystem healthSystem;
    private long lastHit;
    private int cooldown;
    private Direction direction;
    private Position toPos;

    public Enemy() {
        this(new Position(0, 0));
    }

    public Enemy(Position position) {
        super(new EImageList[] {EImageList.KNIGHT, EImageList.KNIGHT_I});

        this.healthSystem = new HealthSystem(5);
        this.lastHit = System.currentTimeMillis();
        this.cooldown = 1000;
        super.getPosition().setPosition(position);
        this.direction = Direction.RIGHT;
    }

    public void update(Game game) {
        this.updatePos(game.getMapHandler());
        this.checkForPlayer(game.getPlayer());
    }

    public void hit(IEntityAlive entity) {
        long temp = System.currentTimeMillis();
        if (temp < lastHit + cooldown) {
            return;
        }
        entity.getHealthSystem().takeHeart();
        this.lastHit = temp + cooldown;
    }

    public HealthSystem getHealthSystem() {
        return this.healthSystem;
    }

    private void checkForPlayer(Player player) {
        if (this.isNearEntity(player, 40)) {
            this.hit(player);
        }
    }

    public void goToPos(Position toPos) {
        this.toPos = toPos;
    }

    private void updatePos(MapHandler mapHandler) {
        if (this.toPos == null) {
            return;
        }

        Vector vector = new Vector(toPos.getCoordX() - super.getPosition().getCoordX(), toPos.getCoordY() - super.getPosition().getCoordY());
        vector.normalize();
        vector.multiply(4);

        Position pos = new Position((int)vector.getX(), (int)vector.getY());
        if (pos.getCoordX() != 0 || pos.getCoordY() != 0) {
            //Zmena smeru
            if (pos.getCoordX() < 0) {
                this.direction = Direction.LEFT;
            } else if (pos.getCoordX() > 0) {
                this.direction = Direction.RIGHT;
            }

            this.move(new Position(pos.getCoordX(), 0), mapHandler);
            this.move(new Position(0, pos.getCoordY()), mapHandler);
        }
    }

    private void move(Position byPos, MapHandler mapHandler) {
        Position futurePosition = new Position().addPosition(this.getPosition()).addPosition(byPos);
        futurePosition = Position.getPositionRelativeToGrid(futurePosition);
        GameTile tile = mapHandler.getTile(futurePosition.getCoordX(), futurePosition.getCoordY());
        if (tile != null && tile.hasCollision()) {
            return;
        }

        //Pohyb
        this.getPosition().addPosition(byPos);
    }

    @Override
    public BufferedImage getImage() {
        if (this.direction == Direction.RIGHT) {
            return super.getImage(0);
        }
        return super.getImage(1);
    }
}
