package sk.uniza.fri.entities;

import sk.uniza.fri.essentials.EImageList;
import sk.uniza.fri.essentials.Position;

public class ItemCoins extends Item {
    public ItemCoins(int x, int y) {
        super(new EImageList[] {EImageList.COINS});
        super.getPosition().setPosition(new Position(x, y));
    }

    public void pickup() {
        System.out.println("Coins picked up!");
    }
}
