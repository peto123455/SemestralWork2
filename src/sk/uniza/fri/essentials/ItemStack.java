package sk.uniza.fri.essentials;

import sk.uniza.fri.enums.EItemList;

public class ItemStack {
    private EItemList item;
    private int amount;

    /**
     * Jedná sa o "Kôpku" predmetov, obsahuje informácie o predmete a jeho počte
     * @param item
     * @param amount
     */
    public ItemStack(EItemList item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    /**
     * @return Typ itemu
     */
    public EItemList getItem() {
        return this.item;
    }

    /**
     * @return Množstvo itemu
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Nastaví jeho množstvo
     * @param amount Množstvo
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Pridá k množstvu
     * @param amount Množstvo
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }
}
