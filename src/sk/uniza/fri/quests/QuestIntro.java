package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.ui.MessageBox;

public class QuestIntro extends Quest {
    public QuestIntro(QuestHandler questHandler) {
        super("Introduction", questHandler);
        super.addTask(new Task(EQuestAction.ENEMY_KILLED, 2));
        super.addItemReward(new ItemStack(EItemList.COINS, 150));

        this.onStart();
    }

    @Override
    public void onComplete() {
        super.onRewardPickup("The wizard gave you some coins\nfor your heroism. He promised\nyou more if you save his friend\nGoblin.", 5000);
        super.getQuestHandler().setCurrentQuest(new QuestFinishThem(super.getQuestHandler()));
    }

    @Override
    protected void onStart() {
        new MessageBox("Welcome to the game. Open the\nchest, pickup the potion and kill\nthe enemy.", 3000);
    }

}
