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

    public Quest(String name, QuestHandler questHandler) {
        this.name = name;
        this.isCompleted = false;
        this.tasks = new ArrayList<>();
        this.itemRewards = new ArrayList<>();
        this.questHandler = questHandler;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void addTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String getString() {
        String string = "";
        for (Task task : this.tasks) {
            string += task.getType().getString() + ": " + task.getAmount() + "/" + task.getRequiredAmount() + "\n";
        }
        return string;
    }

    public void onRewardPickup() {
        this.questHandler.onRewardPickup(this.itemRewards);
        new MessageBox("Quest finished !", 2000);
        this.questHandler.removeQuest();
    }

    public void onActionPerformed(EQuestAction questAction, int amount) {
        for (Task task : this.tasks) {
            if (task.getType() == questAction) {
                task.addAmount(amount);
                this.checkAllComplete();
                return;
            }
        }
    }

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

    public void addItemReward(ItemStack itemStack) {
        this.itemRewards.add(itemStack);
    }

    public abstract void onComplete();

    public QuestHandler getQuestHandler() {
        return this.questHandler;
    }
}
