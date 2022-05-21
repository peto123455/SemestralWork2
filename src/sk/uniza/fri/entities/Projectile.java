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

    /**
     * Vytvorí projektil
     * @param image Obrázok
     * @param position Pozícia
     * @param toPos Na akú pozícu sa má dostať (Smer)
     */
    public Projectile(EImageList[] image, Position position, Position toPos) {
        super(image, ERenderLayer.PROJECTILES);
        super.getPosition().setPosition(position);
        super.setSpeed(6);

        this.setDirection(toPos);

        Projectile.projectiles.add(this);
    }

    @Override
    public boolean update(Game game) {
        return super.update(game);
    }

    /**
     * Zmaže všetky projektily
     */
    public static void resetProjectiles() {
        Projectile.projectiles = new ArrayList<>();
    }

    /**
     * Stará sa o aktializáciu všetkých projektilov v hre
     * @param game Hra
     */
    public static void updateProjectiles(Game game) {
        ArrayList<Projectile> projectilesCopy = new ArrayList<>(Projectile.projectiles);

        for (Projectile projectile : projectilesCopy) {
            projectile.update(game);
        }
    }

    /**
     * Stará sa o vykreslenie všetkých projektilov v hre
     * @param g2d Plátno
     */
    public static void drawProjectiles(Graphics2D g2d) {
        for (Projectile projectile : Projectile.projectiles) {
            projectile.draw(g2d);
        }
    }

    protected void removeProjectile(Projectile projectile) {
        Projectile.projectiles.remove(projectile);
    }

    protected boolean wallHit(Game game) {
        for (EDirection direction : EDirection.values()) {
            Position pos = direction.getPos(10).addPosition(super.getPosition());
            if (super.isCollision(pos, game.getMapHandler().getMap())) {
                return true;
            }
        }
        return false;
    }

    //Private

    /**
     * Nastaví pozíciu na ktorú sa strela má dostať
     * @param toPos Pozícia
     */
    private void setDirection(Position toPos) {
        Vector vector = new Vector(toPos.getX() - super.getPosition().getX(), toPos.getY() - super.getPosition().getY());
        vector.normalize();
        vector.multiply(1280);

        super.goToPos(new Position(vector.getX(), vector.getY()).addPosition(super.getPosition()));
    }
}
