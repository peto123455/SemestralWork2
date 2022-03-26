package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;

public class QuestWelcome extends Quest {
    public QuestWelcome(QuestHandler questHandler) {
        super("Welcome", questHandler);
        super.addTask(new Task(EQuestAction.ENEMY_KILLED, 2));
        super.addItemReward(new ItemStack(EItemList.COINS, 150));
    }

    @Override
    public void onComplete() {
        super.onRewardPickup("The wizard gave you some coins for your heroism\nHe promised more if you kill 3 more");
        super.getQuestHandler().setCurrentQuest(new QuestSecond(super.getQuestHandler()));
    }

}
