package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;

public class ItemCoins extends Item {
    private int amount;

    public ItemCoins(int x, int y, int amount) {
        super(new EImageList[] {EImageList.COINS});
        this.amount = amount;
        super.getPosition().setPosition(new Position(x, y));
    }

    public ItemStack pickup() {
        return new ItemStack(EItemList.COINS, this.amount);
    }
}
