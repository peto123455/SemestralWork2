package sk.uniza.fri;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class GameFrame extends JFrame {

    private Game game;

    public GameFrame(GamePanel panel, Game game) {
        this.game = game;
        this.setTitle("Semestralka");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();

        this.createKeyListener();
    }

    private void createKeyListener() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                GameFrame.this.game.getKeyHandler().keyUpdate(Character.toLowerCase(e.getKeyChar()), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                GameFrame.this.game.getKeyHandler().keyUpdate(Character.toLowerCase(e.getKeyChar()), false);
            }
        });
    }
}
