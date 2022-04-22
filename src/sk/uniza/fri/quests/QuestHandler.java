package sk.uniza.fri.quests;

import sk.uniza.fri.entities.Player;
import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.enums.ESoundList;
import sk.uniza.fri.essentials.ItemStack;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class QuestHandler {
    private ArrayList<Quest> completedQuests;
    private Quest currentQuest;
    private Player player;

    public QuestHandler(Player player) {
        this.completedQuests = new ArrayList<>();
        this.currentQuest = new QuestIntro(this);
        this.player = player;
    }

    public void completeQuest() {
        ESoundList.playSound(ESoundList.QUEST_COMPLETE);
        this.completedQuests.add(this.currentQuest);
        this.removeQuest();
    }

    public boolean isQuestCompleted(Class quest) {
        for (Quest completedQuest : this.completedQuests) {
            if (completedQuest.getClass().isAssignableFrom(quest)) {
                return true;
            }
        }
        return false;
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

    public void gameFinished() {
        this.player.getGame().finishGame();
    }
}
