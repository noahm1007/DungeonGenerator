import javax.naming.CannotProceedException;
import java.util.*;
import java.util.concurrent.atomic.DoubleAdder;

public class Options {
    final String leadingDesign = "[//] ";
    final String trailingDesign = "";

    int page;
    int selectedItem;

    int numberOfOptions;

    ArrayList<String> menuBar;
    public Options() {
        this.menuBar = new ArrayList<String>();
    }

    public int constructOptions(Player player, int page, int selectedItem) {
        this.page = page;
        this.selectedItem = selectedItem;
        menuBar.clear();

        menuBar.add(leadingDesign + "reading c:\\options.config\\");

        switch (page) {
            case 0 -> {
                numberOfOptions = 3;
                menuBar.add(leadingDesign + " 1.>attack enemy");
                menuBar.add(leadingDesign + " 2.>use inventory");
                menuBar.add(leadingDesign + " 3.>save game");
                menuBar.add("\tEND OF FILE");
                return 0;
            }
            case 1 -> {
                numberOfOptions = player.currentRoom.enemies.size();
                menuBar.set(0, menuBar.get(0) + "attack.exe");
                menuBar.add(leadingDesign + " >select the enemy to attack:");
                for (int i = 0; i < player.currentRoom.enemies.size(); i++) {
                    menuBar.add(leadingDesign + (i+1) + ".> enemy at (" + player.currentRoom.enemies.get(i).xPos + ", " + player.currentRoom.enemies.get(i).yPos + ")");
                }
                menuBar.add(leadingDesign + " >input \"b\" to go back");
                menuBar.add("\tEND OF FILE");
                return 1;
            }
            case 2 -> {
                numberOfOptions = player.inventory.inventory.size()+1;
                menuBar.set(0, menuBar.get(0) + "inventory.zip");
                menuBar.add(leadingDesign + " >select an item to view more options");
                menuBar.add(leadingDesign + " >input \"b\" to go back");
                menuBar.add("\tEND OF FILE");
                return 2;
            }
            case 3 -> {
                menuBar.set(0, menuBar.get(0) + "save.exe");
                menuBar.add(leadingDesign + " >are you sure you want to overwrite the current save? (Y/n)");
                menuBar.add("\tEND OF FILE");
                return 3;
            }
            case 4 -> {
                numberOfOptions = 4;
                menuBar.set(0, menuBar.get(0) + "inventory.zip\\" + player.inventory.inventory.get(selectedItem).itemName + ".txt");
                menuBar.add(leadingDesign + " 1.>view description");
                menuBar.add(leadingDesign + " 2.>view stats");
                menuBar.add(leadingDesign + " 3.>equip item");
                menuBar.add(leadingDesign + " 4.>discard item");
                menuBar.add(leadingDesign + " >input \"b\" to go back");
                menuBar.add("\tEND OF FILE");
                return 4;
            }
            case 5 -> {
                menuBar.set(0, menuBar.get(0) + "confirm.exe");
                menuBar.add(leadingDesign + " >are you sure? (Y/n)");
                menuBar.add("\tEND OF FILE");
                return 5;
            }
        }
        return -1;
    }
}
