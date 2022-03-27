package sk.uniza.fri.main;

public class GameThread extends Thread {

    private static GameThread instance;

    public static GameThread getInstance() {
        if (GameThread.instance == null) {
            GameThread.instance = new GameThread();
        }
        return GameThread.instance;
    }

    private Game game;
    private long lastFrame = System.nanoTime();
    private long deltaTime;

    private GameThread() {
        this.deltaTime = System.nanoTime();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void run() {
        while (this.game != null) {
            this.deltaTime = System.nanoTime() - this.lastFrame;
            if (this.deltaTime >= 16700000) {
                this.game.updateGame();
                this.lastFrame = System.nanoTime();
            }
        }
    }

    public double getDeltaTime() {
        return (double)this.deltaTime / 1000000000;
    }
}
