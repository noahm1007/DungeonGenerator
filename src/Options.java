import javax.naming.CannotProceedException;
import java.util.*;
import java.util.concurrent.atomic.DoubleAdder;

public class Options {
    final String leadingDesign = "[//] ";
    final String trailingDesign = "";

    ArrayList<String> menuBar;
    public Options() {
        this.menuBar = new ArrayList<String>();
    }

    public int constructOptions(Player player, int page, int selectedItem) {
        menuBar.clear();

        menuBar.add(leadingDesign + "reading c:\\options.config\\");

        switch (page) {
            case 0 -> {
                menuBar.add(leadingDesign + " 1.>move");
                menuBar.add(leadingDesign + " 2.>attack enemy");
                menuBar.add(leadingDesign + " 3.>use inventory");
                menuBar.add(leadingDesign + " 4.>save game");
                menuBar.add("\tEND OF FILE");
                return 0;
            }
            case 1 -> {
                menuBar.set(0, menuBar.get(0) + "move.exe");
                menuBar.add(leadingDesign + " >input player move");
                menuBar.add("\tEND OF FILE");
                return 1;
            }
            case 2 -> {
                menuBar.set(0, menuBar.get(0) + "attack.exe");
                menuBar.add(leadingDesign + " >select the enemy to attack:");
                for (int i = 0; i < player.currentRoom.enemies.size(); i++) {
                    menuBar.add(leadingDesign + (i+1) + ".> enemy at (" + player.currentRoom.enemies.get(i).xPos + ", " + player.currentRoom.enemies.get(i).yPos + ")");
                }
                menuBar.add("\tEND OF FILE");
                return 2;
            }
            case 3 -> {
                menuBar.set(0, menuBar.get(0) + "inventory.zip");
                menuBar.add(leadingDesign + " >select an item to view more options");
                menuBar.add("\tEND OF FILE");
                return 3;
            }
            case 4 -> {
                menuBar.set(0, menuBar.get(0) + "inventory.zip\\" + player.inventory.inventory.get(selectedItem).itemName + ".txt");
                menuBar.add(leadingDesign + " 1.>view description");
                menuBar.add(leadingDesign + " 2.>view stats");
                menuBar.add(leadingDesign + " 3.>equip item");
                menuBar.add(leadingDesign + " 4.>discard item");
                menuBar.add("\tEND OF FILE");
                return 4;
            }
            case 5 -> {
                menuBar.set(0, menuBar.get(0) + "save.exe");
                menuBar.add(leadingDesign + " >are you sure you want to overwrite the current save? (Y/n)");
                menuBar.add("\tEND OF FILE");
                return 5;
            }
        }
        return -1;
    }
}
