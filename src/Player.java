import java.util.*;

public class Player {
    public final char playerSymbol = '◆';

    public int xPos;
    public int yPos;
    public int lastXPos;
    public int lastYPos;
    public Room currentRoom;

    protected int maxHealth;
    public int level;
    public int xp;
    public int requiredXP;
    public int health;
    public Weapon activeItem;
    public Inventory inventory;
    protected int maxMoveDistance;

    public Player(int xPos, int yPos, Room currentRoom, int maxHealth, Weapon activeItem, Inventory inventory) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentRoom = currentRoom;
        this.maxHealth = maxHealth;
        this.activeItem = new Weapon(false, true, false, true, "stick", "its a stick.", 0, 10, 7, 2, 10, 16);
        this.inventory = inventory;
        this.maxMoveDistance = 5;
        this.health = maxHealth;
        this.level = 1;
        this.requiredXP = level*100;
    }

    public void move(int x, int y) {
            lastXPos = xPos;
            lastYPos = yPos;
            xPos = x;
            yPos = y;
    }

    public void takeDamage(double damage) {
        if ((health-damage) < 0) { health = 0; }
        else { health-=damage; }
    }

    public void recieveXP(int xp) {
        if (xp + this.xp >= requiredXP) {
            level++;
            this.xp = 0;
            this.requiredXP = level*100;
        } else {
            this.xp += xp;
        }
    }
}
