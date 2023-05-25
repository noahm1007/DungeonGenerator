import java.sql.SQLOutput;
import java.util.*;

public class Floor {
    Random rd = new Random();
    final int maxFloorSize = 5;
    final int minFloorSize = 1;
    final int gridSize = 50;
    final int gridSpacing = 3;
    Player player;
    Inventory inventory;

    private int floorLength;
    private int floorWidth;
    private int entranceRoom;
    private int exitRoom;

    ArrayList<String> floor;
    Room[][] rooms;

    public Floor() {
        this.floorLength = 3;
        this.floorWidth = 3;
        floor = new ArrayList<>();
        rooms = new Room[floorLength][floorWidth];
    }

    public Floor(int floorLength, int floorWidth) {
        if (floorLength <= maxFloorSize && floorLength >= minFloorSize && floorWidth <= maxFloorSize && floorWidth >= minFloorSize) {
            this.floorLength = floorLength;
            this.floorWidth = floorWidth;
            floor = new ArrayList<>();
            rooms = new Room[floorLength][floorWidth];
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
                if (i == floorLength-1 && j == entranceRoom) {isSouthDoor = true; }
                rooms[i][j] = new Room(7, 21, isNorthDoor, isSouthDoor, isEastDoor, isWestDoor);

                rooms[i][j].constructRoom();
                if (!(rooms[i][j].equals(rooms[floorLength-1][entranceRoom]))) {
                    rooms[i][j].fillRoom();
                    rooms[i][j].generateEnemyPositions();
                    rooms[i][j].placeEnemies();
                }
            }
        }
        Room startingRoom = rooms[floorLength-1][entranceRoom];
        inventory = new Inventory();
        player = new Player(startingRoom.roomLength/2, startingRoom.roomWidth/2, startingRoom, 100, null, inventory);
        player.currentRoom.placePlayer(player);
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
    }
    public void printFloor() {
        for (int i = 0; i < floorLength; i++) {
            for (int k = 0; k < rooms[0][0].grid.length; k++) {
                for (int j = 0; j < floorWidth; j++) {
                    Room room = rooms[i][j];
                    char[][] grid = room.grid;
                    for (int l = 0; l < grid[k].length; l++) {
                        System.out.print(grid[k][l]);
                    }
//                    System.out.print("\t\t");
                }
                System.out.println();
            }
        }
    }

}