package sk.uniza.fri.enums;


import sk.uniza.fri.inventory.IInventoryItem;
import sk.uniza.fri.inventory.InventoryItemCoins;
import sk.uniza.fri.inventory.InventoryItemHealthPotion;
import sk.uniza.fri.inventory.InventoryItemSpeedPotion;

public enum EItemList {
    COINS("Coins", new InventoryItemCoins()),
    HEALTH_POTION("Health potion", new InventoryItemHealthPotion()),
    SPEED_POTION("SPEED potion", new InventoryItemSpeedPotion());

    private String name;
    private IInventoryItem item;

    /**
     * Zoznam predmetov
     * @param name Meno predmetu
     */
    EItemList(String name, IInventoryItem item) {
        this.name = name;
        this.item = item;
    }

    /**
     * @return Vráti meno predmetu
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Vráti inštanciu itemu pre inventár
     */
    public IInventoryItem getItem() {
        return this.item;
    }
}
