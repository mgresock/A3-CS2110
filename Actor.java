package cs2110;

/**
 * A character who takes turns during the game simulation.
 */
public abstract class Actor {
    /* DO NOT CHANGE THIS CLASS! YOU WILL NOT BE SUBMITTING IT! */

    /**
     * The starting and maximum value for the actor's health.
     */
    public static final int MAX_HEALTH = 20;

    /**
     * The name of this actor.
     */
    private final String name;

    /**
     * The current health level of this actor. Must be non-negative and `<= MAX_HEALTH`.
     */
    private int health;

    /**
     * The game engine that created this actor.
     */
    protected final GameEngine engine;

    /**
     * Constructs a new actor with the given `name`, associated with the `engine` that constructed
     * it. The actor's `health` is initialized to `MAX_HEALTH`.
     */
    public Actor(String name, GameEngine engine) {
        this.name = name;
        this.engine = engine;
        this.health = MAX_HEALTH;
    }

    /**
     * Returns the name of this actor.
     */
    public String name() {
        return name;
    }

    /**
     * Returns the current health level for this actor.
     */
    public int health() {
        return health;
    }

    /**
     * Process the receipt of health points from another actor. Increases the current health of
     * this actor by up to the given number of `points`, capped at their `MAX_HEALTH`, and prints
     * a massage announcing their new health amount. Requires `points >= 0`.
     */
    protected void heal(int points) {
        assert points >= 0; // defensive programming
        int newHealth = Math.min(health + points, MAX_HEALTH);
        System.out.println(name + " has been healed by " + (newHealth - health) + " points!");
        health = newHealth;
        System.out.println(name + " is now at " + health + " health.");
    }

    /**
     * Reduces this actor's health by the `damageAmount`. If they still have health after this
     * damage, their new health amount is printed. Otherwise, a message announcing their defeat
     * is printed. Requires that `damageAmount >= 0`.
     */
    public void takeDamage(int damageAmount) {
        assert damageAmount >= 0; // defensive programming
        int damageTaken = Math.min(damageAmount, health);
        System.out.printf("%s took %d points of damage.%n", name(), damageTaken);
        health -= damageTaken;
        if (health == 0) {
            System.out.println(name() + " has been defeated.");
        } else {
            System.out.println(name() + " is now at " + health + " health.");
        }
    }

    /**
     * Launches an attack against the given `target`. A random attack roll is calculated using this
     * actor's `power()` and sent to the `target` to `defend()`.
     */
    protected final void attack(Actor target) {
        int attackRoll = engine.diceRoll(1, power());
        target.defend(attackRoll);
    }

    /**
     * Responds to an attack with the given `attackRoll`. A random `defenseRoll` is calculated using
     * this actor's `toughness()`. If `attackRoll >= defenseRoll`, then the attack is successful and
     * this actor takes damage equal to the `attackRoll`. If the `attackRoll < defenseRoll`, then no
     * damage is taken and the successful defense is reported to the user.
     */
    protected void defend(int attackRoll) {
        int defenseRoll = engine.diceRoll(1, toughness());
        if (attackRoll >= defenseRoll) {
            engine.assignDamageTo(this, attackRoll);
        } else {
            System.out.println(name + " successfully defended, no damage was taken.");
        }
    }

    /**
     * Returns a reference to the weapon currently equipped by this actor, or returns null
     * if this actor does not currently have an equipped weapon.
     */
    public Weapon weapon() {
        return null; // by default, an actor is unable to equip a weapon`
    }

    @Override
    public final String toString() {
        return String.format("%s [%s, power = %d, toughness = %d, health = %d]",
                name, actorType(), power(), toughness(), health);
    }

    /**
     * Returns a String descriptor of the actor's concrete subtype.
     */
    public abstract String actorType();

    /**
     * Returns the current offensive strength of this actor.
     */
    public abstract int power();

    /**
     * Returns the current defensive strength of this actor.
     */
    public abstract int toughness();

    /**
     * Simulates the actions that take place during one turn for this actor.
     */
    public abstract void takeTurn();
}
