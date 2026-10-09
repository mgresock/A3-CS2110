package cs2110;

/**
 * A mage whose spell adds three independently chosen random potions to the players'
 * shared inventory.
 */
public class PotionMage extends Mage{
    /**
     * Constructs a potion mage with the given name and game engine.
     */
    public PotionMage(String name, GameEngine engine) {
        super(name, engine);
    }

    @Override
    public String actorType() {
        return "potion mage";
    }

    @Override
    protected String spellName() {
        return "potion spell";
    }

    /**
     * Adds three independently selected random potions to the inventory.
     */
    @Override
    protected void castSpell() {
        for (int i = 0; i < 3; i++) {
            int type = engine.diceRoll(0, 2);
            engine.addPotion(type);
        }
    }
}
