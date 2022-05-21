package sk.uniza.fri.inventory;

import sk.uniza.fri.entities.ItemCoins;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.ui.ErrorMessageBox;
import sk.uniza.fri.ui.MessageBox;

public class InventoryItemCoins implements IInventoryItem {

    public InventoryItemCoins() {

    }

    @Override
    public EItemList getType() {
        return EItemList.COINS;
    }

    @Override
    public boolean useItem(Game game) {
        new ErrorMessageBox("Can't use that !", 3000);
        return false;
    }

    @Override
    public void spawnItem(Map map, ItemStack item, Position position) {
        map.addItem(new ItemCoins((int)position.getX(), (int)position.getY(), item.getAmount()));
    }


}
