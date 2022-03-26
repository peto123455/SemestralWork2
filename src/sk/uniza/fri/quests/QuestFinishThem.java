package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.ui.MessageBox;

public class QuestFinishThem extends Quest {
    public QuestFinishThem(QuestHandler questHandler) {
        super("Finish them", questHandler);
        super.addTask(new Task(EQuestAction.ENEMY_KILLED, 3));
        super.addItemReward(new ItemStack(EItemList.COINS, 150));
        super.addItemReward(new ItemStack(EItemList.HEALTH_POTION, 2));

        this.onStart();
    }

    @Override
    public void onComplete() {
        new MessageBox("Ahh..., you killed them all, return\nto the wizard to collect your\nreward", 5000);
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void onRewardPickup() {
        super.onRewardPickup("I can't believe it, you really\ndid it ! Here are your coins and\nsmall bonus for your bravery.", 5000);
        MessageBox.shceduleMessage("Now go ahead and find my friend\ngoblin, he will tell you what he\nneeds.", 5000, 5000);
    }
}
