package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.HealthSystem;

public abstract class EntityAlive extends Entity {
    private final HealthSystem healthSystem;

    public EntityAlive(EImageList[] images, int maxHearts) {
        super(images);
        this.healthSystem = new HealthSystem(maxHearts);
    }

    protected HealthSystem getHealthSystem() {
        return this.healthSystem;
    }

    public boolean takeHeart() {
        boolean taken = this.healthSystem.takeHeart();
        if (taken && this.healthSystem.getHearts() <= 0) {
            this.onDeath();
            return false;
        }
        return true;
    }

    public void addHeart() {
        this.healthSystem.addHeart();
    }

    public int getHearts() {
        return this.healthSystem.getHearts();
    }

    public void setHearts(int amount) {
        this.healthSystem.setHearts(amount);
    }

    public boolean isMaxHearts() {
        return this.healthSystem.isHaxHearts();
    }

    public boolean isDead() {
        return this.healthSystem.getHearts() <= 0;
    }

    protected abstract void onDeath();
}
