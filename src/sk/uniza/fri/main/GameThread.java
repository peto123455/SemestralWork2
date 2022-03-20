package sk.uniza.fri.main;

public class GameThread extends Thread {

    private Game game;
    private long lastFrame = System.currentTimeMillis();

    public GameThread(Game game) {
        super();
        this.game = game;
    }

    public void run() {
        while (true) {
            if (System.currentTimeMillis() - this.lastFrame >= 16.7) {
                this.game.updateGame();
                this.lastFrame = System.currentTimeMillis();
            }
        }
    }
}
