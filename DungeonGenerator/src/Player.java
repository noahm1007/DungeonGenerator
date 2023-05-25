import java.util.*;

public class Player {
    public final char playerSymbol = '◆';
 
    public int xPos;
    public int yPos;
    public Room currentRoom;

    protected int maxHealth;
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
    }

    public void move(int x, int y) {
        if (xPos - Math.abs(x) <= maxMoveDistance || yPos - Math.abs(y) <= maxMoveDistance) {
            xPos = x;
            yPos = y;
        }
    }

}
