import java.util.Random;

public class Enemy {
    Random rd = new Random();
    final char enemySymbol = '●';

    int xPos;
    int yPos;
    int lastXPos;
    int lastYPos;
    int maxMoveDistance;

    private double maxHealth;
    private int XP;
    private double baseDamage;
    private double critChance;
    private double critMultiplier;
    private double attackSpeed;
    private double missChance;

    public Enemy(int maxMoveDistance, double maxHealth, int XP, double baseDamage, double critChance, double critMultiplier, double missChance) {
        this.maxMoveDistance = maxMoveDistance;
        this.maxHealth = maxHealth;
        this.baseDamage = baseDamage;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.attackSpeed = rd.nextDouble();
        this.missChance = missChance;
        this.XP = XP;
    }

    public double attack() {
        double successfulCrit = Math.random();

        if (successfulCrit >= critChance) {
            return baseDamage * (critMultiplier+1);
        } else { return baseDamage; }
    }

    public void takeDamage(double damage) {
        if ((maxHealth-damage) < 0) { maxHealth = 0; }
        else { maxHealth-=damage; }
    }
}
