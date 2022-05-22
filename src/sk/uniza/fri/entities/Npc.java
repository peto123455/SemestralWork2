package sk.uniza.fri.entities;

import sk.uniza.fri.enums.EImageList;
import sk.uniza.fri.essentials.Position;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.quests.Quest;
import sk.uniza.fri.quests.QuestGoblin;

public class Npc extends Entity {

    private Class<?> quest;
    private Map map;

    /**
     * Vytvorí NPC
     * @param position Jeho pozícia
     * @param image Obrázok
     * @param map Mapa
     */
    public Npc(Position position, EImageList image, Map map) {
        super(new EImageList[] {image});
        super.getPosition().setPosition(position);

        this.map = map;
    }

    /**
     * Nastaví NPCčku jeho quest
     * @param quest Quest
     */
    public void setQuest(Class<?> quest) {
        this.quest = quest;
    }

    /**
     * Skontroluje, či sa jedná o jeho quest, či je dokončený a podobne.
     * @param player Hráč
     * @return Či sa splnila očakávaná akcia
     */
    public boolean checkQuest(Player player) {
        Quest currentQuest = player.getQuestHandler().getCurrentQuest();

        if (currentQuest == null) {
            if (player.getQuestHandler().isQuestCompleted(this.quest)) {
                Quest.onQuestCompleteNpcReact();
                return false;
            } else if (QuestGoblin.class.isAssignableFrom(this.quest)) {
                player.getQuestHandler().setCurrentQuest(new QuestGoblin(player.getQuestHandler(), this));
            }
            return true;
        }

        if (this.quest.isInstance(currentQuest)) {
            if (currentQuest.isCompleted()) {
                currentQuest.onRewardPickup();
                return true;
            } else {
                currentQuest.onQuestNpcReact();
                return false;
            }
        }
        currentQuest.onNonQuestNpcReact();
        return false;
    }

    /**
     * @return Vráti mapu NPCčka
     */
    public Map getMap() {
        return this.map;
    }
}
