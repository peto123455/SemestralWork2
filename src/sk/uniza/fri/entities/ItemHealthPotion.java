package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.*;

public class ItemHealthPotion extends Item {

    public ItemHealthPotion(int x, int y) {
        super(new EImageList[] {EImageList.HEALTH_POTION});
        super.getPosition().setPosition(new Position(x, y));
    }

    public ItemStack pickup() {
        return new ItemStack(EItemList.HEALTH_POTION, 1);
    }
}
