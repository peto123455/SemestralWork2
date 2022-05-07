package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EDirection;
import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.ERenderLayer;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.essentials.Vector;
import sk.uniza.fri.main.Game;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Projectile extends Entity {
    private static ArrayList<Projectile> projectiles = new ArrayList<>();

    public Projectile(EImageList[] image, Position position, Position toPos) {
        super(image, ERenderLayer.PROJECTILES);
        super.getPosition().setPosition(position);
        super.setSpeed(6);

        this.setDirection(toPos);

        Projectile.projectiles.add(this);
    }

    @Override
    public boolean update(Game game) {
        for (EDirection direction : EDirection.values()) {
            Position pos = direction.getPos(10).addPosition(super.getPosition());
            if (super.isCollision(pos, game.getMapHandler().getMap())) {
                Projectile.projectiles.remove(this);
            }
        }
        if (super.isNearEntity(game.getPlayer(), 30)) {
            game.getPlayer().takeHeart();
            Projectile.projectiles.remove(this);
        }
        return super.update(game);
    }

    private void setDirection(Position toPos) {
        Vector vector = new Vector(toPos.getX() - super.getPosition().getX(), toPos.getY() - super.getPosition().getY());
        vector.normalize();
        vector.multiply(1280);

        super.goToPos(new Position(vector.getX(), vector.getY()).addPosition(super.getPosition()));
    }

    public static void resetProjectiles() {
        Projectile.projectiles = new ArrayList<>();
    }

    public static void updateProjectiles(Game game) {
        for (int i = 0; i < Projectile.projectiles.size(); ++i) {
            Projectile.projectiles.get(i).update(game);
        }
    }

    public static void drawProjectiles(Graphics2D g2d) {
        for (Projectile projectile : Projectile.projectiles) {
            projectile.draw(g2d);
        }
    }
}
