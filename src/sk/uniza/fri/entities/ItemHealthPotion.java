package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.EItemList;
import sk.uniza.fri.essentials.ESoundList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;

public class ItemHealthPotion extends Item {

    public ItemHealthPotion(int x, int y) {
        super(new EImageList[] {EImageList.HEALTH_POTION});
        super.getPosition().setPosition(new Position(x, y));
    }

    public ItemStack pickup() {
        ESoundList.playSound(ESoundList.PICKUP);
        return new ItemStack(EItemList.HEALTH_POTION, 1);
    }
}
