package sk.uniza.fri.essentials;

public class HealthSystem {
    private int maxHearts;
    private int hearts;

    public HealthSystem(int maxHearts) {
        this.maxHearts = maxHearts;
        this.hearts = maxHearts;
    }

    public boolean takeHeart() {
        if (this.hearts <= 0) {
            return false;
        }
        --this.hearts;
        return true;
    }

    public void addHeart() {
        if (this.hearts >= this.maxHearts) {
            return;
        }
        ++this.hearts;
    }

    public int getHearts() {
        return this.hearts;
    }

    public boolean isHaxHearts() {
        return this.hearts >= this.maxHearts;
    }
}
