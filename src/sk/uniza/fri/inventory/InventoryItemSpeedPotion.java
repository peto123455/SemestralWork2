package sk.uniza.fri.inventory;

import sk.uniza.fri.entities.ItemSpeedPotion;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.main.Game;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.ui.ErrorMessageBox;

public class InventoryItemSpeedPotion implements IInventoryItem {

    public InventoryItemSpeedPotion() {
    }

    @Override
    public EItemList getType() {
        return EItemList.SPEED_POTION;
    }

    @Override
    public boolean useItem(Game game) {
        if (game.getPlayer().getInventory().removeItemStack(new ItemStack(this.getType(), 1))) {
            ESoundList.playSound(ESoundList.POTION_DRINK);
            game.getPlayer().setBoost(5);
            return true;
        } else {
            new ErrorMessageBox("No Speed potion !", 3000);
        }
        return false;
    }

    @Override
    public void spawnItem(Map map, ItemStack item, Position position) {
        for (int i = 0; i < item.getAmount(); ++i) {
            map.addItem(new ItemSpeedPotion((int)position.getX(), (int)position.getY()));
            position.addPosition(new Position(15, 0));
        }
    }
}
