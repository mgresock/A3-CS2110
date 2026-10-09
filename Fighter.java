package cs2110;

/**
 * A player who can equip at most one weapon, which adds to their power and toughness.
 * On their turn, a fighter may change their equipment before attacking. A fighter's
 * weapon is returned to the available pool before they take lethal damage.
 */
public class Fighter extends Player{
    /** The equipped weapon, or null if unarmed. */
    private Weapon equippedWeapon;

    /** Constructs an unarmed fighter with the given name and engine. */
    public Fighter(String name, GameEngine engine) {
        super(name, engine);
        equippedWeapon = null;
    }

    @Override
    public String actorType() {
        return "fighter";
    }

    @Override
    public Weapon weapon() {
        return equippedWeapon;
    }

    /**
     * Sets the equipped weapon. Called only by GameEngine during
     * an equipment change.
     */
    void setWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    @Override
    /** Returns this fighter's power, including any bonus from their equipped weapon. */
    public int power() {
        return super.power()
                + (equippedWeapon == null ? 0 : equippedWeapon.power());
    }

    @Override
    /** Returns this fighter's toughness, including any bonus from their equipped weapon. */
    public int toughness() {
        return super.toughness()
                + (equippedWeapon == null ? 0 : equippedWeapon.toughness());
    }

    /**
     * Offers an equipment change, then returns true to proceed
     * to the attack phase. Requires valid user selections.
     */
    @Override
    public boolean chooseAction() {
        if (engine.queryBoolean(
                "Would you like to change your current equipment?")) {

            Weapon[] available = engine.availableWeapons();

            System.out.println(
                    "Select the weapon that you'd like to equip:");

            for (int i = 0; i < available.length; i++) {
                System.out.printf("  [%d] %s%n", i, available[i]);
            }

            if (equippedWeapon != null) {
                System.out.println("-".repeat(23));
                System.out.println(
                        "  [-1] Unequip " + equippedWeapon.name());
            }

            System.out.print("Selection: ");
            int selection;
            try {
                selection = Integer.parseInt(engine.getInputLine());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Query of Weapon unsuccessful: " + e.getMessage());
            }
            if (selection >= available.length || selection < -1
                    || (selection == -1 && equippedWeapon == null)) {
                throw new RuntimeException(
                        "Query of Weapon unsuccessful: invalid selection " + selection);
            }
            Weapon selected =
                    selection == -1 ? null : available[selection];

            engine.changeEquipment(this, selected);
        }

        return true;
    }

    /**
     * Applies damage, releasing the equipped weapon before lethal
     * damage. Requires damageAmount >= 0.
     */
    @Override
    public void takeDamage(int damageAmount) {
        assert damageAmount >= 0;

        if (health() > 0 && damageAmount >= health()
                && equippedWeapon != null) {
            engine.changeEquipment(this, null);
        }

        super.takeDamage(damageAmount);
    }
}
