package cs2110;

/**
 * A potion that doubles a player's base toughness until the start of their next turn.
 */
public class TuffPotion extends Potion{
    /**
     * Constructs a tuff potion.
     */
    public TuffPotion() {
        super("tuff potion", "increases toughness");
    }

    /**
     * Doubles the player's base toughness until their next turn begins.
     */
    @Override
    public void applyTo(Player player) {
        player.applyTuff();
    }
}
