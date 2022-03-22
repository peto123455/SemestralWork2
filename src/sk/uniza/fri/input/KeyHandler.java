package sk.uniza.fri.input;

import sk.uniza.fri.main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyHandler implements KeyListener {
    private final HashMap<Character, Boolean> pressed;
    private final HashMap<Character, PressableKey> pressable;

    private final Game game;

    public KeyHandler(Game game) {
        this.pressed = new HashMap<>();
        this.pressable = new HashMap<>();

        this.game = game;
        this.registerKeys();
    }

    private void registerKeys() {
        this.pressed.put('w', false); //Pohyb hore
        this.pressed.put('a', false); //Pohyb doľava
        this.pressed.put('s', false); //Pohyb dole
        this.pressed.put('d', false); //Pohyb doprava

        this.pressable.put('k', new PressableKeyAttack()); //Útok
        this.pressable.put('c', new PressableKeyEnterPortal()); //Vstúpenie do portálu
        this.pressable.put('i', new PressableKeyInventory()); //Otvorenie inventáru
        this.pressable.put('q', new PressableKeyHealthPotion()); //Použitie elixíru života
    }

    public void resetKeys() {
        this.registerKeys();
    }

    public ArrayList<Character> getPressedKeys() {
        ArrayList<Character> list = new ArrayList<>();
        this.pressed.forEach((kluc, hodnota) -> {
            if (hodnota) {
                list.add(kluc);
            }
        });
        return list;
    }

    public void keyUpdate(char c, boolean pressed) {
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyUpdate(Character.toLowerCase(e.getKeyChar()), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyUpdate(Character.toLowerCase(e.getKeyChar()), false);
    }
}
