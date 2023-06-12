public class Weapon extends Item {
    // 0-1 weight
    public double baseDamage;
    public boolean isActive;
    public double critChance;
    public double critMultiplier;
    public double attackSpeed;
    public double missChance;

    public Weapon(boolean isConsumable, boolean isEquipable, boolean isStackable, boolean isActive, String itemName, String itemDescription, int rarityWeighting, double baseDamage, double critChance, double critMultiplier, double attackSpeed, double missChance) {
        super(isConsumable, isEquipable, isStackable, itemName, itemDescription, rarityWeighting);
        this.baseDamage = baseDamage;
        this.critChance = critChance;
        this.isActive = isActive;
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
