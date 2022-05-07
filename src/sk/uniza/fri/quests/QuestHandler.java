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

    /**
     * Správa questov
     * @param player
     */
    public QuestHandler(Player player) {
        this.completedQuests = new ArrayList<>();
        this.currentQuest = new QuestIntro(this);
        this.player = player;
    }

    /**
     * Dokončí quest
     */
    public void completeQuest() {
        ESoundList.playSound(ESoundList.QUEST_COMPLETE);
        this.completedQuests.add(this.currentQuest);
        this.removeQuest();
    }

    /**
     * Skontroluje dokončenosť questov
     * @param quest Trieda questu
     * @return Dokončenosť questu
     */
    public boolean isQuestCompleted(Class quest) {
        for (Quest completedQuest : this.completedQuests) {
            if (completedQuest.getClass().isAssignableFrom(quest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Nastaví aktuálny quest
     * @param currentQuest
     */
    public void setCurrentQuest(Quest currentQuest) {
        this.currentQuest = currentQuest;
    }

    /**
     * Zruší aktuálny quest
     */
    public void removeQuest() {
        this.currentQuest = null;
    }

    /**
     * @return Aktuálny quest
     */
    public Quest getCurrentQuest() {
        return this.currentQuest;
    }

    /**
     * Dá hráčovi odmenu za quest
     * @param items
     */
    public void onRewardPickup(ArrayList<ItemStack> items) {
        for (ItemStack item : items) {
            player.getInventory().addItemStack(item);
        }
    }

    /**
     * Volá sa pri vykonaní nejakej u úloh, ktorá môže byť v queste
     * @param questAction Typ akcie questu
     * @param amount Množstvo koľko vykonal
     */
    public void onActionPerformed(EQuestAction questAction, int amount) {
        this.currentQuest.onActionPerformed(questAction, amount);
    }

    /**
     * @return Má hráč aktuálne quest
     */
    public boolean isQuest() {
        return this.currentQuest != null;
    }

    /**
     * Vykreslí aktuálny quest
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        this.currentQuest.draw(g2d);
    }

    /**
     * Ukončí hru
     */
    public void gameFinished() {
        this.player.getGame().finishGame();
    }
}
