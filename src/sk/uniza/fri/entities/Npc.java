package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.quests.Quest;
import sk.uniza.fri.quests.QuestGoblin;

public class Npc extends Entity {

    private Class quest;
    private Map map;

    public Npc(Position position, EImageList image, Map map) {
        super(new EImageList[] {image});
        super.getPosition().setPosition(position);

        this.map = map;
    }

    public void setQuest(Class quest) {
        this.quest = quest;
    }

    public boolean checkQuest(Player player) {
        Quest curerentQuest = player.getQuestHandler().getCurrentQuest();

        if (curerentQuest == null) {
            if (player.getQuestHandler().isQuestCompleted(this.quest)) {
                return false;
            } else if (QuestGoblin.class.isAssignableFrom(this.quest)) {
                player.getQuestHandler().setCurrentQuest(new QuestGoblin(player.getQuestHandler(), this));
            }

            return true;
        }

        if (this.quest.isInstance(curerentQuest)) {
            if (curerentQuest.isCompleted()) {
                curerentQuest.onRewardPickup();
                return true;
            } else {
                curerentQuest.onQuestNpcReact();
                return false;
            }
        }
        curerentQuest.onNonQuestNpcReact();
        return false;
    }

    public Map getMap() {
        return map;
    }
}
