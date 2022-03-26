package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ImageTools;
import sk.uniza.fri.essentials.Inventory;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.main.GameTile;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.quests.QuestHandler;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends EntityAlive {
    private EDirection eDirection;
    private Inventory inventory;
    private Game game;
    private QuestHandler questHandler;

    public Player(Game game) {
        super(new EImageList[] {EImageList.PLAYER}, 5);

        this.eDirection = EDirection.RIGHT;
        this.inventory = new Inventory();
        this.game = game;
        this.questHandler = new QuestHandler(this);
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

    public void hit(ArrayList<Enemy> enemies) {
        ESoundList.playSound(ESoundList.SWORD_SLASH);
        new ParticleSlash(this.getPosition(), this.eDirection);

        for (Enemy enemy : enemies) {
            if (super.isNearEntity(enemy, 50)) {
                if (!enemy.takeHeart() && this.questHandler.isQuest()) {
                    this.questHandler.onActionPerformed(EQuestAction.ENEMY_KILLED, 1);
                }
            }
        }
    }

    @Override
    protected void onDeath() {
        this.game.onDeath();
    }

    public void handleKeys(ArrayList<Integer> keys, Map map) {
        Position finalPosition = new Position();

        for (Integer c : keys) {
            //Systém kolízií
            Position futurePosition = EDirection.getPosByInt(c, 16);
            futurePosition.addPosition(this.getPosition());
            futurePosition = Position.getPositionRelativeToGrid(futurePosition);

            GameTile tile = map.getTile(futurePosition.getCoordX(), futurePosition.getCoordY());
            if (tile != null && tile.hasCollision()) {
                continue;
            }

            //Pohyb hráča
            EDirection direction = EDirection.getDirByInt(c);
            if (direction == EDirection.RIGHT || direction == EDirection.LEFT) {
                this.setDirection(direction);
            }
            finalPosition.addPosition(EDirection.getPosByInt(c, 4));
        }

        this.getPosition().addPosition(finalPosition);
    }

    public QuestHandler getQuestHandler() {
        return this.questHandler;
    }
}
