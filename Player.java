package cs2110;

/**
 * An actor that is controlled by the user through console input. On an actor's turn, they can
 * choose to use a potion (if any are available), take a special action that is determined by
 * their subtype, and then (possibly) carry out an attack on a chosen monster.
 */
public abstract class Player extends Actor {

    /**
     * The base power level of this player.
     */
    private final int basePower;

    /**
     * The base toughness level of this player.
     */
    private final int baseToughness;

    /**
     * Whether a buff potion is doubling the player's base power.
     */
    private boolean buffed;

    /**
     * Whether a tuff potion is doubling the player's base toughness.
     */
    private boolean tuffed;

    /**
     * Whether the player is invisible to other actors.
     */
    private boolean invisible;

    /**
     * Constructs a new player with the given `name`. Their base power and toughness levels are
     * randomly initialized to an int value between 10 and 20, inclusive.
     */
    public Player(String name, GameEngine engine) {
        super(name, engine);
        basePower = engine.diceRoll(10, 20);
        baseToughness = engine.diceRoll(10, 20);
    }

    @Override
    public int power() {
        return buffed ? 2 * basePower : basePower;
    }

    @Override
    public int toughness() {
        return tuffed ? 2* baseToughness : baseToughness;
    }

    /**
     * Doubles base power until the start of this player's next turn.
     */
    public void applyBuff() {
        buffed = true;
    }

    /**
     * Doubles base toughness until the start of this player's next turn.
     */
    public void applyTuff() {
        tuffed = true;
    }

    /**
     * Makes this player invisible until the start of their next turn.
     */
    public void makeInvisible() {
        invisible = true;
    }

    /**
     * Returns whether this player is currently invisible to other actors.
     */
    public boolean isInvisible() {
        return invisible;
    }
    /**
     * Uses the console to query the user for whether they would like to use a potion and 
     * which action they would like to take on their turn. Depending on which action is 
     * selected, the player's turn may also include an attack phase.
     */
    @Override
    public void takeTurn() {
        buffed = false;
        tuffed = false;
        invisible = false;
        Potion potion = engine.selectPotion();

        if (potion != null) {
            potion.applyTo(this);
        }

        if (chooseAction()) {
            Actor target = engine.selectMonsterTarget();
            attack(target);
        }
    }

    /**
     * Uses the console to query the user for which action they would like to take on their turn,
     * and carries out the action of their choice. The available actions are determined by the
     * Player's subtype. Returns `true` if the chosen action is followed by an attack phase and
     * returns `false` if the chosen action completes the player's turn.
     */
    public abstract boolean chooseAction();
}
