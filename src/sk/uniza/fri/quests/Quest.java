package sk.uniza.fri.quests;

import sk.uniza.fri.enums.EQuestAction;
import sk.uniza.fri.essentials.ItemStack;
import sk.uniza.fri.ui.MessageBox;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Quest {
    private String name;
    private boolean isCompleted;
    private ArrayList<Task> tasks;
    private ArrayList<ItemStack> itemRewards;
    private QuestHandler questHandler;

    /**
     * Quest
     * @param name Názov questu
     * @param questHandler Referencia na quest handler
     */
    public Quest(String name, QuestHandler questHandler) {
        this.name = name;
        this.isCompleted = false;
        this.tasks = new ArrayList<>();
        this.itemRewards = new ArrayList<>();
        this.questHandler = questHandler;
    }

    /**
     * @return Meno questu
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Dokončenosť questu
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Pridá úlohy do zoznamu úloh
     * @param tasks Úlohy
     */
    public void addTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Pridá úlohu do zoznamu úloh
     * @param task Úloha
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Výpis questu pre UI
     * @return String s textom questu
     */
    public String getString() {
        String string = "";
        for (Task task : this.tasks) {
            string += task.getType().getString() + ": " + task.getAmount() + "/" + task.getRequiredAmount() + "\n";
        }
        return string;
    }

    /**
     * Zavolá sa pri vyzdvihnutí odmeny
     */
    public void onRewardPickup() {
        this.onRewardPickup("Quest finished !", 2000);
    }

    /**
     * Zavolá sa pri vyzdvihnutí odmeny so zobrazením správy
     */
    public void onRewardPickup(String message, int time) {
        this.giveItems();
        new MessageBox(message, time);
        this.finishQuest();
    }

    /**
     * Dá hráčovi itemy za dokončenie questu
     */
    protected void giveItems() {
        this.questHandler.onRewardPickup(this.itemRewards);
    }

    /**
     * Dokončí quest
     */
    protected void finishQuest() {
        this.questHandler.completeQuest();
    }

    /**
     * Volá sa pri vykonaní nejakej u úloh, ktorá môže byť v queste
     * @param questAction Typ akcie questu
     * @param amount Množstvo koľko vykonal
     */
    public void onActionPerformed(EQuestAction questAction, int amount) {
        for (Task task : this.tasks) {
            if (task.getType() == questAction) {
                task.addAmount(amount);
                this.checkAllComplete();
                return;
            }
        }
    }

    /**
     * Kontrola, či sú všetky úlohy dokončené
     */
    public void checkAllComplete() {
        if (this.isCompleted) {
            return;
        }

        for (Task task : this.tasks) {
            if (!task.isComplete()) {
                return;
            }
        }
        this.isCompleted = true;
        this.onComplete();
    }

    /**
     * Vykreslenie questu na obrazovku
     * @param g2d Plátno
     */
    public void draw(Graphics2D g2d) {
        int y = 40 + g2d.getFontMetrics().getHeight();
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Quest: " + this.name, 54, y);

        if (this.isCompleted) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.WHITE);
        }

        for (String text : this.getString().split("\n")) {
            y += g2d.getFontMetrics().getHeight();
            g2d.drawString(text, 54, y);
        }

        g2d.setColor(Color.WHITE);
    }

    /**
     * Prídá predmet do zoznamu odmien
     * @param itemStack Item na pridanie do odmien
     */
    public void addItemReward(ItemStack itemStack) {
        this.itemRewards.add(itemStack);
    }

    /**
     * Volá sa pri dokončení
     */
    public abstract void onComplete();

    /**
     * Vráti quest handler
     * @return
     */
    public QuestHandler getQuestHandler() {
        return this.questHandler;
    }

    /**
     * Ak hráč interaktuje s NPC mimo questu
     */
    public void onNonQuestNpcReact() {
        new MessageBox("Not part of current quest!", 2000);
    }

    /**
     * Ak hráč interaktuje s NPC, a quest nie je hotový
     */
    public void onQuestNpcReact() {
        new MessageBox("Quest not complete yet!", 2000);
    }

    /**
     * Pri začatí questu
     */
    protected abstract void onStart();
}
