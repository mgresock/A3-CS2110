package cs2110;

/**
 * A mage whose spell heals a chosen player by a random amount between 0 and this
 * mage's power, inclusive.
 */
public class HealingMage extends Mage{
    /** Constructs a healing mage with the given name and game engine. */
    public HealingMage(String name, GameEngine engine) {
        super(name, engine);
    }

    /** {@inheritDoc} */
    @Override
    public String actorType() {
        return "healing mage";
    }

    /** {@inheritDoc} */
    @Override
    protected String spellName() {
        return "healing spell";
    }

    /**
     * Heals a selected player by a random amount from 0 through
     * this mage's power(), inclusive.
     */
    @Override
    protected void castSpell() {
        Player target = engine.selectPlayerTarget(this);
        int points = engine.diceRoll(0, power());
        target.heal(points);
    }
}
