package sk.uniza.fri.entities;


import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.quests.Quest;

public class Npc extends Entity {

    private Class quest;

    public Npc(Position position, EImageList image) {
        super(new EImageList[] {image});
        super.getPosition().setPosition(position);
    }

    public void setQuest (Class quest) {
        this.quest = quest;
    }

    public boolean checkQuest(Quest quest) {
        if (this.quest.isInstance(quest)) {
            if (quest.isCompleted()) {
                quest.onRewardPickup();
                return true;
            } else {
                quest.onQuestNpcReact();
                return false;
            }
        }
        quest.onNonQuestNpcReact();
        return false;
    }
}
