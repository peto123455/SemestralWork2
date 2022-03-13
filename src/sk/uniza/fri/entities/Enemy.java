package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.*;
import sk.uniza.fri.main.Game;

public class Enemy extends Entity implements IEntityAlive {
    private HealthSystem healthSystem;
    private long lastHit;
    private int cooldown;
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
    }

    public void update(Game game) {
        this.updatePos();
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

    private void updatePos() {
        if (this.toPos == null) {
            return;
        }

        Vector vector = new Vector(toPos.getCoordX() - super.getPosition().getCoordX(), toPos.getCoordY() - super.getPosition().getCoordY());
        vector.normalize();
        vector.multiply(4);

        Position pos = new Position((int)vector.getX(), (int)vector.getY());
        if (pos.getCoordX() != 0 || pos.getCoordY() != 0) {
            this.getPosition().addPosition(pos);
        }
    }
}
