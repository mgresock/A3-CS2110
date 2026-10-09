package cs2110;

/**
 * A potion that makes a player invisible, so they cannot be targeted by monster attacks
 * or by other players' spells, until the start of their next turn.
 */
public class InvisibilityPotion extends Potion{
    /**
     * Constructs an invisibility potion.
     */
    public InvisibilityPotion() {
        super("invisibility potion", "makes player invisible");
    }

    /**
     * Makes the player invisible until their next turn begins.
     */
    @Override
    public void applyTo(Player player) {
        player.makeInvisible();
    }
}
