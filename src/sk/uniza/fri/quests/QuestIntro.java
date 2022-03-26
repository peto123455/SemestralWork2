package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;

public class QuestIntro extends Quest {
    public QuestIntro(QuestHandler questHandler) {
        super("Introduction", questHandler);
        super.addTask(new Task(EQuestAction.ENEMY_KILLED, 2));
        super.addItemReward(new ItemStack(EItemList.COINS, 150));
    }

    @Override
    public void onComplete() {
        super.onRewardPickup("The wizard gave you some coins\nfor your heroism. He promised\nyou more if you kill 3 more\nof them.", 5000);
        super.getQuestHandler().setCurrentQuest(new QuestSecond(super.getQuestHandler()));
    }

}
