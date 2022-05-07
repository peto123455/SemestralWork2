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

    public KeyHandler(Game game) {
        this.pressed = new HashMap<>();
        this.pressable = new HashMap<>();

        this.game = game;
        this.registerKeys();
    }

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

    public void resetKeys() {
        this.registerKeys();
    }

    public ArrayList<Integer> getPressedKeys() {
        ArrayList<Integer> list = new ArrayList<>();
        this.pressed.forEach((kluc, hodnota) -> {
            if (hodnota) {
                list.add(kluc);
            }
        });
        return list;
    }

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

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyUpdate(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyUpdate(e.getKeyCode(), false);
    }
}
