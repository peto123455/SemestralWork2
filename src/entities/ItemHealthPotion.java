package entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;

public class ItemHealthPotion extends Item {

    /**
     * Vytvorí elixír života
     * @param x Súradnica X
     * @param y Súradnica Y
     */
    public ItemHealthPotion(int x, int y) {
        super(new EImageList[] {EImageList.HEALTH_POTION});
        super.getPosition().setPosition(new Position(x, y));
    }

    public ItemStack pickup() {
        ESoundList.playSound(ESoundList.PICKUP);
        return new ItemStack(EItemList.HEALTH_POTION, 1);
    }
}
