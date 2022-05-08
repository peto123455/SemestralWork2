package sk.uniza.fri.essentials;

public class HealthSystem {
    private int maxHearts;
    private int hearts;

    /**
     * Systém životov entity
     * @param maxHearts Maximálny počet životov
     */
    public HealthSystem(int maxHearts) {
        this.maxHearts = maxHearts;
        this.hearts = maxHearts;
    }

    /**
     * Odoberie jeden život
     * @return
     */
    public boolean takeHeart() {
        if (this.hearts <= 0) {
            return false;
        }
        --this.hearts;
        return true;
    }

    /**
     * Pridá jeden život
     */
    public void addHeart() {
        if (this.hearts >= this.maxHearts) {
            return;
        }
        ++this.hearts;
    }

    /**
     * @return Počet životov
     */
    public int getHearts() {
        return this.hearts;
    }

    /**
     * @return Či má plné životy
     */
    public boolean isHaxHearts() {
        return this.hearts >= this.maxHearts;
    }

    /**
     * Nastaví počet životov
     * @param hearts Počet životov
     */
    public void setHearts (int hearts) {
        this.hearts = hearts;
    }
}
