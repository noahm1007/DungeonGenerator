public class Weapon extends Item {
    // 0-1 weight
    private double baseDamage;
    private double critChance;
    private double critMultiplier;
    private double attackSpeed;
    private double missChance;

    public Weapon(boolean isConsumable, boolean isEquipable, boolean isStackable, boolean isActive, String itemName, String itemDescription, int rarityWeighting, double baseDamage, double rarity, double critChance, double critMultiplier, double attackSpeed, double missChance) {
        super(isConsumable, isEquipable, isStackable, isActive,itemName, itemDescription, rarityWeighting);
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
