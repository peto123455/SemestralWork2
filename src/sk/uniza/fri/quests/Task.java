package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EQuestAction;

public class Task {
    private final EQuestAction type;
    private int amount;
    private final int requiredAmount;

    /**
     * Jendá sa o úlohu v queste
     * @param type Typ questu (Zabiť nepriateľov, ...)
     * @param requiredAmount Požadované množstvo na splnenie úlohy
     */
    public Task(EQuestAction type, int requiredAmount) {
        this.type = type;
        this.amount = 0;
        this.requiredAmount = requiredAmount;
    }

    /**
     * Vráti typ úlohy
     * @return Typ úlohy
     */
    public EQuestAction getType() {
        return this.type;
    }

    /**
     * Vráti množstvo
     * @return Doteraz dokončené množstvo
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Vráti požadované množstvo na splnenie
     * @return Požadované množstvo
     */
    public int getRequiredAmount() {
        return this.requiredAmount;
    }

    /**
     * Nastaví množstvo
     * @return Nastaví doteraz dokončené množstvo
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Pridá k doteraz dokončenému množstvu hodnotu
     * @param amount koľko má pridať
     * @return Splnená úloha
     */
    public boolean addAmount(int amount) {
        this.amount += amount;
        if (this.amount >= this.requiredAmount) {
            this.amount = this.requiredAmount;
            return true;
        }
        return false;
    }

    /**
     * Či už je úloha splnená
     * @return Splnenosť úlohy
     */
    public boolean isComplete() {
        return this.amount >= this.requiredAmount;
    }
}
