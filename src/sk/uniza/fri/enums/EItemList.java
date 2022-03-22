package sk.uniza.fri.enums;


public enum EItemList {
    COINS("Coins"),
    HEALTH_POTION("Health potion");

    private String name;

    EItemList(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
