package cs2110;
/**
 * A player who, on their turn, may cast a spell specific to their subtype instead of
 * attacking. Casting a spell ends the turn; declining it proceeds to the attack phase.
 */
public abstract class Mage extends Player{
    /** Constructs a mage with the given name and game engine. */
    public Mage(String name, GameEngine engine) {
        super(name, engine);
    }

    /** Returns this mage's spell name, as used in the spell prompt. */
    protected abstract String spellName();

    /** Carries out this mage's spell. */
    protected abstract void castSpell();

    /**
     * Offers to cast a spell. If accepted, casts it and returns false
     * to end the turn. Otherwise, returns true to allow an attack.
     */
    @Override
    public boolean chooseAction() {
        boolean shouldCast = engine.queryBoolean("Would you like to cast a " + spellName() + "?");
        if (shouldCast) {
            castSpell();
            return false;
        }
        return true;
    }
}
