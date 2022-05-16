package sk.uniza.fri.input;

import sk.uniza.fri.main.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyHandler extends KeyAdapter {
    private final HashMap<Integer, Boolean> pressed;
    private final HashMap<Integer, PressableKey> pressable;

    private final Game game;

    /**
     * Stará sa o správu stlačených kláves
     * @param game Hra
     */
    public KeyHandler(Game game) {
        this.pressed = new HashMap<>();
        this.pressable = new HashMap<>();

        this.game = game;
        this.registerKeys();
    }

    /**
     * Vyresetuje stlačené klávesi
     */
    public void resetKeys() {
        this.registerKeys();
    }

    /**
     * @return Vráti zoznam stlačených kláves
     */
    public ArrayList<Integer> getPressedKeys() {
        ArrayList<Integer> list = new ArrayList<>();
        this.pressed.forEach((kluc, hodnota) -> {
            if (hodnota) {
                list.add(kluc);
            }
        });
        return list;
    }

    /**
     * Aktualizuje stav klávesi
     * @param c Klávesa
     * @param pressed Jej stav
     */
    public void keyUpdate(int c, boolean pressed) {
        if (this.pressed.containsKey(c)) {
            this.pressed.put(c, pressed);
        }

        if (this.pressable.containsKey(c)) {
            PressableKey key = this.pressable.get(c);
            if (!key.isPressed()) {
                key.action(this.game);
            }
            key.setPressed(pressed);
        }
    }

    /**
     * Volá sa pri stlační klávesi, aktualizuje jej stav
     * @param e Event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.keyUpdate(e.getKeyCode(), true);
    }

    /**
     * Volá sa pri pustení klávesi, aktualizuje jej stav
     * @param e Event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.keyUpdate(e.getKeyCode(), false);
    }

    //Private

    /**
     * Zaregistruje základné klávesy
     */
    private void registerKeys() {
        this.pressed.put(KeyEvent.VK_W, false); //Pohyb hore
        this.pressed.put(KeyEvent.VK_A, false); //Pohyb doľava
        this.pressed.put(KeyEvent.VK_S, false); //Pohyb dole
        this.pressed.put(KeyEvent.VK_D, false); //Pohyb doprava

        this.pressable.put(KeyEvent.VK_K, new PressableKeyAttack()); //Útok
        this.pressable.put(KeyEvent.VK_I, new PressableKeyInventory()); //Otvorenie inventáru
        this.pressable.put(KeyEvent.VK_Q, new PressableKeyHealthPotion()); //Použitie elixíru života
        this.pressable.put(KeyEvent.VK_E, new PressableKeyUseAction()); //Klávesa akcie
    }
}
