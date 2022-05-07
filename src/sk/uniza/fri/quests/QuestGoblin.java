package sk.uniza.fri.quests;

import sk.uniza.fri.entities.Npc;
import sk.uniza.fri.enums.EItemList;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.ui.MessageBox;

import java.util.Timer;
import java.util.TimerTask;

public class QuestGoblin extends Quest {
    private Npc goblin;

    public QuestGoblin(QuestHandler questHandler, Npc goblin) {
        super("Goblin's needs", questHandler);
        super.addTask(new Task(EQuestAction.BOSS_KILLED, 1));
        super.addItemReward(new ItemStack(EItemList.HEALTH_POTION, 3));

        this.goblin = goblin;

        this.onStart();
    }

    @Override
    public void onComplete() {
        new MessageBox("You did it ! Return to the\ngoblin", 5000);
    }

    @Override
    protected void onStart() {
        new MessageBox("Our people need your help.\nHelp us, and we will give you\n3 health potions. I need you\nto kill a powerful ghost mage,\nbut it's not easy. Let me open\na portal for you.", 3000);
        this.goblin.goToPos(new Position(1100, 500));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                QuestGoblin.this.goblin.getMap().openPortals();
                QuestGoblin.this.goblin.goToPos(new Position(1020, 500));
                new MessageBox("You may go now. Good Luck\nmy friend.", 1500);
            }
        }, 5500);
    }

    @Override
    public void onRewardPickup() {
        super.onRewardPickup("Wow ! You've actually killed him.\nYou're a hero !.", 5000);
        super.getQuestHandler().gameFinished();
    }
}
