import java.lang.reflect.Array;
import java.util.*;

public class Floor {
    Random rd = new Random();
    final int maxFloorSize = 5;
    final int minFloorSize = 1;
    final int gridSize = 50;
    final int gridSpacing = 3;
    int level;
    Inventory inventory;
    Player player;
    Menu menu;

    private int floorLength;
    private int floorWidth;
    private int entranceRoom;
    private int exitRoom;

    ArrayList<String> floor;
    ArrayList<Item> lootTable;
    Room[][] rooms;

    public Floor() {
        this.floorLength = 3;
        this.floorWidth = 3;
        floor = new ArrayList<>();
        rooms = new Room[floorLength][floorWidth];
        this.inventory = new Inventory(20);
        this.player = new Player(0, 0, null, 100, null, inventory);
        this.menu = new Menu(player);
        this.level = 0;
    }

    public Floor(int floorLength, int floorWidth, int level, ArrayList<Item> lootTable) {
        if (floorLength <= maxFloorSize && floorLength >= minFloorSize && floorWidth <= maxFloorSize && floorWidth >= minFloorSize) {
            this.floorLength = floorLength;
            this.floorWidth = floorWidth;
            floor = new ArrayList<>();
            rooms = new Room[floorLength][floorWidth];
            this.inventory = new Inventory(20);
            this.player = new Player(0, 0, null, 100, null, inventory);
            this.menu = new Menu(player);
            this.level = level;
            this.lootTable = lootTable;
        }
    }

    public int getFloorLength() { return floorLength; }
    public int getFloorWidth() { return floorWidth; }

    public void generateFloor() {
        int entranceRoom = rd.nextInt(floorLength);
        int exitRoom = rd.nextInt(floorWidth);
        this.entranceRoom = entranceRoom;
        this.exitRoom = exitRoom;

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                boolean isNorthDoor = true;
                boolean isSouthDoor = true;
                boolean isEastDoor = true;
                boolean isWestDoor = true;

                if (i == 0) { isNorthDoor = false; }
                if (i == floorLength-1) { isSouthDoor = false; }
                if (j == 0) { isWestDoor = false; }
                if (j == floorWidth-1) { isEastDoor = false; }

                if (i == 0 && j == exitRoom) { isNorthDoor = true; }
                if (i == floorLength-1 && j == entranceRoom) { isSouthDoor = true; }
                rooms[i][j] = new Room(7, 21, isNorthDoor, isSouthDoor, isEastDoor, isWestDoor);

                rooms[i][j].constructRoom();
                if (!(rooms[i][j].equals(rooms[floorLength-1][entranceRoom]))) {
                    rooms[i][j].fillRoom();
                    rooms[i][j].generateEnemyPositions();
                    rooms[i][j].placeEnemies();
                } else { rooms[i][j].numEnemies = 0; rooms[i][j].enemies.clear(); }
            }
        }
        Room startingRoom = rooms[floorLength-1][entranceRoom];
        player.xPos = startingRoom.roomLength/2;
        player.yPos = startingRoom.roomWidth/2;
        player.currentRoom = startingRoom;
        player.currentRoom.placePlayer(player);
    }

    public int getTotalEnemies() {
        int total = 0;

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                total += rooms[i][j].numEnemies;
            }
        }

        return total;
    }

    public void nextFrame() {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (!(rooms[i][j].equals(rooms[floorLength-1][entranceRoom]))) {
                    rooms[i][j].moveEnemies();
                    rooms[i][j].placeEnemies();
                }
            }
        }

        player.currentRoom.placePlayer(player);
    }
    public void printFloor(boolean showMenu, boolean showInventory) {
        menu.showHeldItem = true;
        menu.showPlayerHealth = true;
        menu.showMaxMoveDistance = true;
        menu.showEnemyCount = true;
        menu.showPlayerXP = true;
        menu.constructMenu(getTotalEnemies(), level);

        int c = 0;

        for (int i = 0; i < floorLength; i++) {
            for (int k = 0; k < rooms[0][0].grid.length; k++) {
                for (int j = 0; j < floorWidth; j++) {
                    Room room = rooms[i][j];
                    char[][] grid = room.grid;
                    for (int l = 0; l < grid[k].length; l++) {
                        if (rooms[i][j].isClosed && grid[k][l] == 'X') { grid[k][l] = '*'; }
                        System.out.print(grid[k][l]);
                    }
//                    System.out.print("\t\t");
                }

                if (showMenu) {
                    if (!((c + 1) > menu.menuBar.size())) {
                        System.out.print(" " + menu.menuBar.get(c));
                        c++;
                    }
                } else if (showInventory) {
                    if (!((c + 1) > inventory.menuBar.size())) {
                        System.out.print(" " + inventory.menuBar.get(c));
                        c++;
                    }
                }

                System.out.println();
            }
        }
    }

}