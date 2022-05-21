package entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;

public class ItemSpeedPotion extends Item {

    /**
     * Vytvorí elixír rýchlosti
     * @param x Súradnica X
     * @param y Súradnica Y
     */
    public ItemSpeedPotion(int x, int y) {
        super(new EImageList[] {EImageList.SPEED_POTION});
        super.getPosition().setPosition(new Position(x, y));
    }

    public ItemStack pickup() {
        ESoundList.playSound(ESoundList.PICKUP);
        return new ItemStack(EItemList.SPEED_POTION, 1);
    }
}
