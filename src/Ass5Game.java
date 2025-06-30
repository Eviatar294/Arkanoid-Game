// 216756650 Eviatar Sayada
import game.Game;
/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The main class for running the game application.
 * It creates an instance of the Game class, initializes it, and runs the game.
 */
public class Ass5Game {
    /**
     * The main method of the application.
     * It creates a Game object, initializes it, and starts the game loop.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.initialize();
        myGame.run();
    }
}
