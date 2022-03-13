package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.HealthSystem;
import sk.uniza.fri.essentials.Direction;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.IEntityAlive;
import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.Vector;
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
    private boolean dead;

    public Enemy() {
        this(new Position(0, 0));
    }

    public Enemy(Position position) {
        super(new EImageList[] {EImageList.KNIGHT, EImageList.KNIGHT_I});

        this.healthSystem = new HealthSystem(2);
        this.lastHit = System.currentTimeMillis();
        this.cooldown = 1000;
        super.getPosition().setPosition(position);
        this.direction = Direction.RIGHT;
        this.dead = false;
    }

    public void update(Game game) {
        if (dead) {
            return;
        }

        if (this.canSeePlayer(game.getPlayer(), game.getMapHandler())) {
            this.updatePos(game.getMapHandler());
        }
        this.checkForPlayer(game.getPlayer());
    }

    private boolean canSeePlayer(Player player, MapHandler mapHandler) {
        Vector vector = new Vector(player.getPosition().getCoordX() - super.getPosition().getCoordX(), player.getPosition().getCoordY() - super.getPosition().getCoordY());
        for (int i = 1; i < (int)vector.length() / 20; ++i) {
            Position position = new Position(super.getPosition().getCoordX(), super.getPosition().getCoordY());
            Vector vectorPart = new Vector(vector.getX(), vector.getY());
            vectorPart.normalize();
            Position girdPos = Position.getPositionRelativeToGrid(new Position(this.getPosition().getCoordX() + (int)(vectorPart.getX() * i * 20), this.getPosition().getCoordY() + (int)(vectorPart.getY() * i * 20)));

            GameTile tile = mapHandler.getTile(girdPos.getCoordX(), girdPos.getCoordY());
            if (tile != null && tile.hasCollision()) {
                return false;
            }
        }
        return true;
    }

    public void hit(IEntityAlive entity) {
        long temp = System.currentTimeMillis();
        if (temp < lastHit + cooldown) {
            return;
        }
        entity.takeHeart();
        this.lastHit = temp + cooldown;
    }

    public int getHearts() {
        return this.healthSystem.getHearts();
    }

    public boolean takeHeart() {
        boolean taken = this.healthSystem.takeHeart();
        if (taken && this.healthSystem.getHearts() <= 0) {
            this.onDeath();
        }
        return taken;
    }

    private void onDeath() {
        this.dead = true;
        super.changeImages(new EImageList[] {EImageList.KNIGHT_DEAD});
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
        if (this.direction == Direction.LEFT && !dead) {
            return super.getImage(1);
        }
        return super.getImage(0);
    }
}
