package sk.uniza.fri.ui;

import sk.uniza.fri.enums.ESoundList;

public class ErrorMessageBox extends MessageBox {

    /**
     * Chybové správy, ktoré sa hráčovi zobrazujú (Napríklad použite elixíru, ktorý nemá)
     * @param text Text správy
     * @param duration dĺžka zobrazenia
     */
    public ErrorMessageBox(String text, int duration) {
        super(text, duration, true);
        ESoundList.playSound(ESoundList.MESSAGE_WRONG);
    }
}
