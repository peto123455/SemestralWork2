package sk.uniza.fri.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyHandler implements KeyListener {
    private final HashMap<Character, Boolean> pressed;
    private final Game game;

    public KeyHandler(Game game) {
        this.pressed = new HashMap<Character, Boolean>();


        this.game = game;
        this.registerKeys();
    }

    private void registerKeys() {
        this.pressed.put('w', false);
        this.pressed.put('a', false);
        this.pressed.put('s', false);
        this.pressed.put('d', false);
        this.pressed.put('k', false);
    }

    public ArrayList<Character> getPressedKeys() {
        ArrayList<Character> list = new ArrayList<>();
        this.pressed.forEach((kluc, hodnota) -> {
            if (hodnota && kluc != 'k') {
                list.add(kluc);
            }
        });
        return list;
    }

    public void keyUpdate(char c, boolean pressed) {
        if (this.pressed.containsKey(c)) {
            if (c == 'k' && !this.pressed.get(c)) {
                this.game.attack();
            }
            this.pressed.put(c, pressed);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == Character.toLowerCase('c')) {
            this.game.enterPortal();
            return;
        }
        if (e.getKeyChar() == Character.toLowerCase('i')) {
            this.game.switchInventory();
            return;
        }
        if (e.getKeyChar() == Character.toLowerCase('q')) {
            this.game.useHealthPotion();
            return;
        }

        this.keyUpdate(Character.toLowerCase(e.getKeyChar()), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyUpdate(Character.toLowerCase(e.getKeyChar()), false);
    }
}
