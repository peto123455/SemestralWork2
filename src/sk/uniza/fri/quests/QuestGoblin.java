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
        super.addTask(new Task(EQuestAction.ENEMY_KILLED, 2));
        super.addItemReward(new ItemStack(EItemList.HEALTH_POTION, 3));

        this.goblin = goblin;

        this.onStart();
    }

    @Override
    public void onComplete() {
        new MessageBox("Ahh..., you killed them all, return\nto the wizard to collect your\nreward", 5000);
    }

    @Override
    protected void onStart() {
        new MessageBox("I need small favor. Do it, and I\nwill give you 3 health potions.\nLet me open you a portal.", 3000);
        this.goblin.goToPos(new Position(1100, 500));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                QuestGoblin.this.goblin.getMap().openPortals();
                QuestGoblin.this.goblin.goToPos(new Position(1020, 500));
                new MessageBox("You may go now.", 1500);
            }
        }, 5500);
    }

    @Override
    public void onRewardPickup() {
        super.onRewardPickup("I can't believe it, you really\ndid it ! Here are your coins and\nsmall bonus for your bravery.", 5000);
    }
}
