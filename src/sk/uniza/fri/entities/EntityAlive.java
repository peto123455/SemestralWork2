package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.HealthSystem;

public abstract class EntityAlive extends Entity {
    private final HealthSystem healthSystem;

    /**
     * Žijúca entita (Má systém životov)
     * @param images
     * @param maxHearts
     */
    public EntityAlive(EImageList[] images, int maxHearts) {
        super(images);
        this.healthSystem = new HealthSystem(maxHearts);
    }

    protected HealthSystem getHealthSystem() {
        return this.healthSystem;
    }

    /**
     * Odoberie život
     * @return Či odobralo život
     */
    public boolean takeHeart() {
        boolean taken = this.healthSystem.takeHeart();
        if (taken && this.healthSystem.getHearts() <= 0) {
            this.onDeath();
            return false;
        }
        return true;
    }

    /**
     * Pridá život
     */
    public void addHeart() {
        this.healthSystem.addHeart();
    }

    /**
     * @return Vráti životy
     */
    public int getHearts() {
        return this.healthSystem.getHearts();
    }

    /**
     * Nastaví životy
     * @param amount Počet životov
     */
    public void setHearts(int amount) {
        this.healthSystem.setHearts(amount);
    }

    /**
     * @return Vráti, či má maximálny počet životov
     */
    public boolean isMaxHearts() {
        return this.healthSystem.isHaxHearts();
    }

    /**
     * @return Vráti, či žije
     */
    public boolean isDead() {
        return this.healthSystem.getHearts() <= 0;
    }

    /**
     * Volá sa pri smrti
     */
    protected abstract void onDeath();
}
