package game;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * game.Counter is a simple class that manages a count and provides methods to modify and retrieve its value.
 */
public class Counter {
    private int counter;

    /**
     * Constructs a game.Counter with an initial count of zero.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Adds a specified number to the current count.
     *
     * @param number the number to be added to the current count.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtracts a specified number from the current count.
     *
     * @param number the number to be subtracted from the current count.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}


