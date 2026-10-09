package cs2110;

/**
 * An actor that attacks a random player on its turn.
 */
public class Monster extends Actor {

    /**
     * The power level of this monster.
     */
    private final int power;

    /**
     * The toughness level of this monster.
     */
    private final int toughness;

    /**
     * Whether this monster must skip its next turn
     */
    private boolean stunned;

    /**
     * Constructs a new monster with the given `name`. Its power and toughness levels are randomly
     * initialized to an int value between 10 and 20, inclusive.
     */
    public Monster(String name, GameEngine engine) {
        super(name, engine);
        power = engine.diceRoll(10, 20);
        toughness = engine.diceRoll(10, 20);
    }

    @Override
    public String actorType() {
        return "monster";
    }

    @Override
    public int power() {
        return power;
    }

    @Override
    public int toughness() {
        return toughness;
    }

    /**
     * Makes this monster skip its next turn.
     * Repeated stuns before that turn do not stack.
     */
    public void stun() {
        stunned = true;
    }
    /**
     * Launches an attack against a random targetable player unless stunned, in which case it passes
     * its turn. If no players are targetable, this monster also passes its turn.
     */
    @Override
    public void takeTurn()
    {
        if (stunned) {
            stunned = false;
            System.out.println(
                    name() + " is stunned! Their turn is skipped.");
            return;
        }

        Player[] targetablePlayers = engine.targetablePlayers();
        int l = targetablePlayers.length;

        if (l == 0) {
            System.out.println(
                    name() + " couldn't see anyone to attack this turn.");
            return;
        }

        Player target =
                targetablePlayers[engine.diceRoll(0, l - 1)];

        System.out.printf(
                "%s chooses to attack %s.%n",
                name(), target.name());

        attack(target);
    }
}
