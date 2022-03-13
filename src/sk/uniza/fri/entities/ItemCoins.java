package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.*;

public class ItemCoins extends Item {
    private int amount;

    public ItemCoins(int x, int y, int amount) {
        super(new EImageList[] {EImageList.COINS});
        this.amount = amount;
        super.getPosition().setPosition(new Position(x, y));
    }

    public ItemStack pickup() {
        ESoundList.playSound(ESoundList.COIN_PICKUP);
        return new ItemStack(EItemList.COINS, this.amount);
    }
}
