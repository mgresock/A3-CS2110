package cs2110;

/**
 * Models a player with no special abilities. This class can be used to test your `GameEngine`
 * methods during the earlier parts of the assignment (TODOs 1-3), but it should not be referenced
 * in later parts of the assignment.
 */
public class BasicPlayer extends Player {

    /**
     * Constructs a new basic player with the given `name` and initializes their base health
     * and power levels.
     */
    public BasicPlayer(String name, GameEngine engine) {
        super(name, engine);
    }

    /**
     * Returns true without taking any special action. BasicPlayers immediately proceed to their
     * combat phase.
     */
    @Override
    public boolean chooseAction() {
        return true;
    }

    @Override
    public String actorType() {
        return "basic player";
    }
}
