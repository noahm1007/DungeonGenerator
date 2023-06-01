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
                if (isEqual(item, inventory.get(i))) {
                    quantity.set(i, quantity.get(i)+1);
                    return;
                }
            }
        }
        inventory.add(item);
        quantity.add(1);
    }

    public void removeItem(Item item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(item)) {
                if (quantity.get(i) <= 1) {
                    inventory.remove(i);
                    quantity.remove(i);
                } else {
                    quantity.set(i, quantity.get(i)-1);
                }
            }
        }
    }

    public boolean isEqual(Item item1, Item item2) {
        return item1.isEquipable == item2.isEquipable &&
                item1.isConsumable == item2.isConsumable &&
                item1.isStackable == item2.isStackable &&
                item1.itemName.equals(item2.itemName) &&
                item1.itemDescription.equals(item2.itemDescription);
    }

    public void constructInventory(int maxPerPage, int currentPage) {
        int numPages = 0;

        if (currentPage == 0 || maxPerPage == 0) { return; }

        if (inventory.size() == 0 || maxPerPage > inventory.size()) { numPages = 1; }
        else if (maxPerPage % inventory.size() == 0) { numPages = inventory.size() / maxPerPage; }
        else { numPages = (inventory.size() / maxPerPage) + 1; }

        if (currentPage > numPages) { currentPage = numPages; }

//        menuBar.add("[*] INVENTORY [*]");
        menuBar.add(leadingDesign + "reading c:\\inventory.zip\\");

        if (inventory.size() - (currentPage * maxPerPage) <= 0) {
            for (int i = (currentPage-1) * maxPerPage; i < inventory.size(); i++) {
                menuBar.add(leadingDesign + (i + 1) + ". >" + inventory.get(i).itemName + ": " + quantity.get(i));
            }
        } else {
            for (int i = (currentPage-1) * maxPerPage; i < ((currentPage - 1) * maxPerPage) + maxPerPage; i++) {
                menuBar.add(leadingDesign + (i + 1) + ". >" + inventory.get(i).itemName + ": " + quantity.get(i));
            }
        }
        if (inventory.size() == 0) { menuBar.add(leadingDesign + "  >empty :("); }
        menuBar.add(leadingDesign + "...page " + currentPage + "/" + numPages);
        menuBar.add("\tEND OF FILE");

        if (isInspecting) {
            menuBar.add("reading c:\\" + inventory.get(inspectingItem).itemName.toLowerCase().replace(' ', '_') + ".txt\\");
        }
    }
}