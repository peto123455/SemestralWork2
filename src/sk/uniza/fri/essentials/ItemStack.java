package sk.uniza.fri.essentials;

import sk.uniza.fri.enums.EItemList;

public class ItemStack {
    private EItemList item;
    private int amount;

    public ItemStack(EItemList item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public EItemList getItem() {
        return this.item;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }
}
