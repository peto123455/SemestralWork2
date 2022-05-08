package sk.uniza.fri.enums;

public enum EQuestAction {
    ENEMY_KILLED("Enemies killed"),
    BOSS_KILLED("Bosses killed");

    private String string;

    /**
     * Typy úloh v queste
     * @param string
     */
    EQuestAction(String string) {
        this.string = string;
    }

    /**
     * @return Vráti popis úlohy
     */
    public String getString() {
        return this.string;
    }
}
