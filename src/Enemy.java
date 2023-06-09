import java.util.Random;

public class Enemy {
    Random rd = new Random();
    final char enemySymbol = '●';
    final String asciiArt = """
            https://ultrakill.fandom.com/wiki/Florp
            [═══════════════════════════════════════════════]
            ⠀                ⢀⣀⡤⣤⢤⢤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⢔⡺⣙⢎⡳⡍⢎⡚⢬⠙⠳⠦⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⡟⢮⠱⣬⢦⡑⢎⡱⡘⣿⣞⡗⡈⠤⢉⠉⠒⣄⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡞⣵⢫⠜⡦⣿⠻⣿⡗⡎⡔⢡⡛⢏⠳⠐⡂⠆⡈⠡⢀⠃⠄⠀⠀⢀⡄⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⢣⠛⣌⠣⢚⠱⠛⡝⢧⣙⠖⡩⢆⠹⣌⠡⡘⢄⠣⠄⡑⠈⡌⣸⡓⢌⠂⡜⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⢀⠞⡌⢡⠊⢄⠢⢁⠎⡱⢈⠒⡌⡙⢇⡎⡲⢱⠠⡁⢎⠰⠐⠠⡑⡜⣡⣷⣼⠞⠂⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⣞⡼⣠⢃⡌⠄⢂⠡⠌⠰⢡⠊⡔⢡⠊⡱⣉⠬⢃⡘⢀⠂⡡⢎⠱⣰⡿⠛⠁⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⣤⠛⣌⠳⢁⠎⠰⠈⢄⠂⠌⠠⢁⠂⡘⠠⠁⡔⠤⠓⠡⠐⣠⢇⠳⣉⡾⠋⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠠⢞⡌⠓⠠⢁⠂⡈⠔⡡⢂⠌⡘⠰⣀⠲⡠⣑⠢⡐⢄⢡⣂⠷⣩⡾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⢠⡙⢮⣧⠉⡐⢀⠂⡐⢨⠰⣏⣶⣉⠶⣡⢓⠕⡫⢖⢫⣚⣵⣾⠟⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⢫⠖⠙⠊⠀⢣⣔⣪⣶⣵⣾⣿⣿⣻⣿⣿⣿⣿⣿⡿⠿⠟⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠈⠺⣭⠋⣏⠉⠉⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            [═══════════════════════════════════════════════]
            """;

    int xPos;
    int yPos;
    int lastXPos;
    int lastYPos;
    int maxMoveDistance;

    public int maxHealth;
    public int health;
    public int XP;
    public double baseDamage;
    public double critChance;
    public double critMultiplier;
    public double attackSpeed;
    public double missChance;

    public Enemy(int maxMoveDistance, int maxHealth, int XP, double baseDamage, double critChance, double critMultiplier, double missChance) {
        this.maxMoveDistance = maxMoveDistance;
        this.maxHealth = maxHealth;
        this.baseDamage = baseDamage;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.attackSpeed = rd.nextDouble();
        this.missChance = missChance;
        this.XP = XP;
        this.health = maxHealth;
    }

    public double attack() {
        double successfulCrit = Math.random();

        if (successfulCrit >= critChance) {
            return baseDamage * (critMultiplier+1);
        } else { return baseDamage; }
    }

    public void takeDamage(double damage) {
        if ((this.health-damage) < 0) { this.health = 0; }
        else { this.health-=damage; }
    }
}
