package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.*;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.Map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity implements IEntityAlive {
    private EDirection eDirection;
    private Inventory inventory;
    private HealthSystem healthSystem;
    private Game game;

    public Player(Game game) {
        super(new EImageList[] {EImageList.PLAYER});

        this.eDirection = EDirection.RIGHT;
        this.inventory = new Inventory();
        this.healthSystem = new HealthSystem(5);
        this.game = game;
    }

    @Override
    public BufferedImage getImage() {
        if (this.eDirection == EDirection.LEFT) {
            return ImageTools.flip(super.getImage());
        }
        return super.getImage();
    }

    public void setDirection(EDirection eDirection) {
        this.eDirection = eDirection;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getHearts() {
        return this.healthSystem.getHearts();
    }

    public boolean takeHeart() {
        boolean taken = this.healthSystem.takeHeart();
        if (taken && this.healthSystem.getHearts() <= 0) {
            this.onDeath();
            return false;
        }
        return true;
    }

    public int getHearths() {
        return this.healthSystem.getHearts();
    }

    public void hit(ArrayList<Enemy> enemies) {
        ESoundList.playSound(ESoundList.SWORD_SLASH);
        new ParticleSlash(this.getPosition(), this.eDirection);

        for (Enemy enemy : enemies) {
            if (super.isNearEntity(enemy, 50)) {
                enemy.takeHeart();
            }
        }
    }

    private void onDeath() {
        this.game.onDeath();
    }

    public boolean isMaxHearts() {
        return this.healthSystem.isHaxHearts();
    }

    public void addHeart() {
        this.healthSystem.addHeart();
    }

    public void handleKeys(ArrayList<Character> keys, Map map) {
        Position finalPosition = new Position();

        for (Character c : keys) {
            //Systém kolízií
            Position futurePosition = EDirection.getPosByChar(c, 16);
            futurePosition.addPosition(this.getPosition());
            futurePosition = Position.getPositionRelativeToGrid(futurePosition);

            GameTile tile = map.getTile(futurePosition.getCoordX(), futurePosition.getCoordY());
            if (tile != null && tile.hasCollision()) {
                continue;
            }

            //Pohyb hráča
            EDirection direction = EDirection.getDirByChar(c);
            if (direction == EDirection.RIGHT || direction == EDirection.LEFT) {
                this.setDirection(direction);
            }
            finalPosition.addPosition(EDirection.getPosByChar(c, 4));
        }

        this.getPosition().addPosition(finalPosition);
    }
}
