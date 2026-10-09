package cs2110;

/**
 * Models a weapon that can be equipped by a Fighter. Each weapon has a `name`, and offers an
 * additive increment to the `power` and `tougness` of the player that equips it.
 */
public record Weapon(String name, int power, int toughness) {
    /* DO NOT CHANGE THIS CLASS! YOU WILL NOT BE SUBMITTING IT! */

    @Override
    public String toString() {
        return name + " [power = " + power + ", toughness = " + toughness + "]";
    }
}
