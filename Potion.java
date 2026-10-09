package cs2110;

/**
 * A consumable item that applies an effect, specific to its subtype, to a player when used.
 */
public abstract class Potion {
    /* DO NOT CHANGE THIS CLASS! YOU WILL NOT BE SUBMITTING IT! */

    /**
     * The name of this potion.
     */
    private final String name;

    /**
     * Describes the effect of this potion.
     */
    private final String effect;

    /**
     * Constructs a new Potion with the given `name` and `effect` description.
     */
    public Potion(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    /**
     * Returns the name of this potion.
     */
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name + " [effect = " + effect + "]";
    }

    /**
     * Applies this potion's effect to the given `player`.
     */
    public abstract void applyTo(Player player);
}
