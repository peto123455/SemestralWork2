package sk.uniza.fri.quests;

import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;

import java.awt.*;
import java.util.ArrayList;

public class QuestHandler {
    private Quest currentQuest;
    private Player player;

    public QuestHandler(Player player) {
        this.currentQuest = new QuestIntro(this);
        this.player = player;
    }

    public void setCurrentQuest(Quest currentQuest) {
        this.currentQuest = currentQuest;
    }

    public void removeQuest() {
        this.currentQuest = null;
    }

    public Quest getCurrentQuest() {
        return this.currentQuest;
    }

    public void onRewardPickup(ArrayList<ItemStack> items) {
        for (ItemStack item : items) {
            player.getInventory().addItemStack(item);
        }
    }

    public void onActionPerformed(EQuestAction questAction, int amount) {
        this.currentQuest.onActionPerformed(questAction, amount);
    }

    public boolean isQuest() {
        return this.currentQuest != null;
    }

    public void draw(Graphics2D g2d) {
        this.currentQuest.draw(g2d);
    }
}
