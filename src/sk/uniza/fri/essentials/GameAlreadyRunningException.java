package sk.uniza.fri.essentials;

/**
 * 8. 3. 2022 - 17:02
 *
 * @author peto1
 */
public class GameAlreadyRunningException extends RuntimeException {
    public GameAlreadyRunningException() {
        super("Game is already running !");
    }
}
