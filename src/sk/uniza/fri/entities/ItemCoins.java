package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;

public class ItemCoins extends Item {
    private int amount;

    /**
     * Vytvorí peniaze
     * @param x Súradnica X
     * @param y Súradnica Y
     * @param amount Množstvo
     */
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
