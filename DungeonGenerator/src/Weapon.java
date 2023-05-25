public class Weapon extends Item {
    private int rarity; // 1 = common, 2 = uncommon, 3 = rare, 4 = epic, 5 = legendary

    // 0-1 weight
    private double baseDamage;
    private double critChance;
    private double critMultiplier;
    private double attackSpeed;
    private double missChance;

    public Weapon(boolean isConsumable, boolean isEquipable, boolean isActive, String itemName, String itemDescription, double baseDamage, double rarity, double critChance, double critMultiplier, double attackSpeed, double missChance) {
        super(isConsumable, isEquipable, isActive, itemName, itemDescription);
        this.baseDamage = baseDamage;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.attackSpeed = attackSpeed;
        this.missChance = missChance;
    }

    public double attack() {
        double successfulCrit = Math.random();

        if (successfulCrit >= critChance) {
            return baseDamage * (critMultiplier+1);
        } else { return baseDamage; }
    }
}
