import java.util.*;

public class Item {
    final int rarityWeighting;
    boolean isConsumable;
    boolean isEquipable;
    boolean isStackable;
    boolean isActive;

    public String itemName;
    public String itemDescription;

    int inventoryRow;
    int inventoryColumn;
    int quantity;

    public Item(boolean isConsumable, boolean isEquipable, boolean isStackable, boolean isActive, String itemName, String itemDescription, int rarityWeighting) {
        this.isConsumable = isConsumable;
        this.isEquipable = isEquipable;
        this.isStackable = isStackable;
        this.isActive = isActive;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.rarityWeighting = rarityWeighting;
    }

    public void printItemInfo(int signWidth) {
        String line = "";

        line+="╔";
        for (int i = 0; i < signWidth; i++) {
            line+="═";
        } line+="╗";
        System.out.println(line);
        line = "";



        line+="╚";
        for (int i = 0; i < signWidth; i++) {
            line+="═";
        } line+="╝";
        System.out.println(line);
        line = "";
    }
}
