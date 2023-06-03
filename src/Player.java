import java.util.*;

public class Player {
    public final char playerSymbol = '◆';
//    public final String asciiArt = """
//            ⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⡀⢄⠸⣝⣍⡶⠀⠀⡠⠀⢀⠀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⡀⢨⡧⣹⢓⡃⣀⣸⣪⡴⠂⠠⠐⠀⠐⠀⠀⠀
//            ⠀⢀⣺⠵⢏⢎⢗⡻⢷⣿⢵⡲⡒⠀⠀⡀⠀⠀⠀⠀
//            ⢀⢳⠀⠀⠘⣟⢷⣟⣩⢽⣝⡓⠓⠳⢻⣿⠫⠿⢔⡦
//            ⢨⣧⠀⠀⠀⡫⣟⡿⣽⣻⣜⠆⠀⡀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠸⡸⣽⠀⢀⢞⡎⠀⠀⠀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠀⠘⡜⣧⣸⡳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠀⠀⠙⢖⢯⡁⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠀⠀⠀⠨⢷⣫⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠀⠀⠀⠀⠋⠑⢯⠄⠀⠀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⡦⡀⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡮⡧⠀⠀⠀⠀⠀⠀
//            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡜⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//            """;
 
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
    protected Item activeItem;
    public Inventory inventory;
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

    public void recieveXP(int xp) { this.xp += xp; }

}
