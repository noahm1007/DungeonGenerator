import java.util.*;

public class Inventory {
    int slots;
    int inspectingItem;
    boolean isInspecting;

    final String leadingDesign = "[//] ";
    final String trailingDesign = "";
    ArrayList<Item> inventory;
    ArrayList<Integer> quantity;
    ArrayList<String> menuBar;

    public Inventory(int slots) {
        this.slots = slots;
        this.inventory = new ArrayList<Item>();
        this.quantity = new ArrayList<Integer>();
        this.menuBar = new ArrayList<String>();
    }

    public void addItem(Item item) {
        if (item.isStackable) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).equals(item)) { quantity.set(i, quantity.get(i)+1); }
            }
        } else {
            inventory.add(item);
            quantity.add(1);
        }
    }

    public void removeItem(Item item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(item)) {
                if (quantity.get(i) <= 1) {
                    inventory.remove(i);
                    quantity.remove(i);
                }
            }
        }
    }

    public void constructInventory() {
        menuBar.clear();

        menuBar.add("[*] INVENTORY [*]");
        menuBar.add(leadingDesign + "reading c:\\inventory.txt\\");
        for (int i = 0; i < inventory.size(); i++) {
            menuBar.add(leadingDesign + (i+1) +". >" + inventory.get(i).itemName + ", " + quantity.get(i));
        }
        menuBar.add("\tEND OF FILE");
        if (isInspecting) {
            menuBar.add("reading c:\\" + inventory.get(inspectingItem).itemName.toLowerCase().replace(' ', '_') + "\\");
        }
    }
}