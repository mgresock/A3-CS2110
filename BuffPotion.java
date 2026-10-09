package cs2110;

/**
 * A potion that doubles a player's base power until the start of their next turn.
 */
public class BuffPotion extends Potion{
    /**
     * Constructs a buff potion.
     */
    public BuffPotion() {
        super("buff potion", "increases power");
    }

    /**
     * Doubles the player's base power until their next turn begins.
     */
    @Override
    public void applyTo(Player player) {
        player.applyBuff();
    }
}
