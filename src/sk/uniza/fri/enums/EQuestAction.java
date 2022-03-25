package sk.uniza.fri.enums;

public enum EQuestAction {
    ENEMY_KILLED("Enemies killed");

    private String string;

    EQuestAction(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
