import java.util.*;

public class Item {
    final int rarityWeighting = 10;
    boolean isConsumable;
    boolean isEquipable;
    boolean isActive;

    private String itemName;
    private String itemDescription;

    int inventoryRow;
    int inventoryColumn;
    int quantity;

    public Item(boolean isConsumable, boolean isEquipable, boolean isActive, String itemName, String itemDescription) {
        this.isConsumable = isConsumable;
        this.isEquipable = isEquipable;
        this.isActive = isActive;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
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
