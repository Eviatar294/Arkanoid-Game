// 216756650 Eviatar Sayada
package hit_listeners;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The hit.listeners.HitNotifier interface defines methods for adding and removing hit event listeners.
 * Classes implementing this interface can notify listeners when hit events occur.
 */
public interface HitNotifier {

    /**
     * Adds a hit.listeners.HitListener to the list of listeners for hit events.
     *
     * @param hl the hit.listeners.HitListener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a hit.listeners.HitListener from the list of listeners for hit events.
     *
     * @param hl the hit.listeners.HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}
