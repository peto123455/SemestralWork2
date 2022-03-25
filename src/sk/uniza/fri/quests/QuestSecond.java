package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;

public class QuestSecond extends Quest {
    public QuestSecond(QuestHandler questHandler) {
        super("Finish them", questHandler);
        super.addTask(new Task(EQuestAction.ENEMY_KILLED, 3));
        super.addItemReward(new ItemStack(EItemList.COINS, 150));
    }

    @Override
    public void onComplete() {

    }

}
