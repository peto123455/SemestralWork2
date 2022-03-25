package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EQuestAction;

public class Task {
    private final EQuestAction type;
    private int amount;
    private final int requiredAmount;

    public Task(EQuestAction type, int requiredAmount) {
        this.type = type;
        this.amount = 0;
        this.requiredAmount = requiredAmount;
    }

    public EQuestAction getType() {
        return this.type;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getRequiredAmount() {
        return this.requiredAmount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean addAmount(int amount) {
        this.amount += amount;
        if (this.amount >= this.requiredAmount) {
            this.amount = this.requiredAmount;
            return true;
        }
        return false;
    }

    public boolean isComplete() {
        return this.amount >= this.requiredAmount;
    }
}
