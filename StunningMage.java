package cs2110;

/**
 * A mage whose spell stuns a chosen living monster, causing it to skip its next turn.
 */
public class StunningMage extends Mage{
    /**
     * Constructs a stunning mage with the given name and game engine.
     */
    public StunningMage(String name, GameEngine engine) {
        super(name, engine);
    }

    @Override
    public String actorType() {
        return "stunning mage";
    }

    @Override
    protected String spellName() {
        return "stunning spell";
    }

    /**
     * Stuns the selected living monster so it skips its next turn.
     */
    @Override
    protected void castSpell() {
        Monster target = engine.selectMonsterTarget();
        target.stun();
        System.out.println(target.name() + " has been stunned!");
    }
}
