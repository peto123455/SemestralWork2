package sk.uniza.fri.enums;


public enum EItemList {
    COINS("Coins"),
    HEALTH_POTION("Health potion");

    private String name;

    /**
     * Zoznam predmetov
     * @param name Meno predmetu
     */
    EItemList(String name) {
        this.name = name;
    }

    /**
     * @return Vráti meno predmetu
     */
    public String getName() {
        return this.name;
    }
}
