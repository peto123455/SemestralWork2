package sk.uniza.fri.essentials;


public class HealthSystem {
    private int maxHearts;
    private int hearts;

    public HealthSystem(int maxHearts) {
        this.maxHearts = maxHearts;
        this.hearts = maxHearts;
    }

    public void takeHeart() {
        if (this.hearts <= 0) {
            return;
        }
        --this.hearts;
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
}
