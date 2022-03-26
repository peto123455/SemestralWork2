package sk.uniza.fri.entities;


import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.quests.Quest;
import sk.uniza.fri.ui.MessageBox;

public class Npc extends Entity {

    private Map map;
    private Class quest;

    public Npc(Position position, EImageList image, Map map) {
        super(new EImageList[] {image});
        super.getPosition().setPosition(position);
        this.map = map;
    }

    public void setQuest (Class quest) {
        this.quest = quest;
    }

    public Class getQuest() {
        return this.quest;
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
