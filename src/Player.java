import java.util.*;

public class Player {
    public final char playerSymbol = '◆';
 
    public int xPos;
    public int yPos;
    public Room currentRoom;

    protected int maxHealth;
    public int level;
    public int health;
    protected Item activeItem;
    protected Inventory inventory;
    protected int maxMoveDistance;

    public Player(int xPos, int yPos, Room currentRoom, int maxHealth, Item activeItem, Inventory inventory) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentRoom = currentRoom;
        this.maxHealth = maxHealth;
        this.activeItem = activeItem;
        this.inventory = inventory;
        this.maxMoveDistance = 5;
        this.health = maxHealth;
        this.level = 0;
    }

    public void move(int x, int y) {
        if (xPos - Math.abs(x) <= maxMoveDistance || yPos - Math.abs(y) <= maxMoveDistance) {
            xPos = x;
            yPos = y;
        }
    }

    public void takeDamage(double damage) {
        if ((health-damage) < 0) { health = 0; }
        else { health-=damage; }
    }

}
