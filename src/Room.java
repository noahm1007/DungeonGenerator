import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Random;

public class Room {
    Random rd = new Random();

    final public int minRoomSize = 7;
    final public int maxRoomSize = 25;
    final int boxWeight = 45;
    final int holeWeight = 15;
    final int treasureWeight = 5;
    int generalWeight = 30;
    final int groupWeight = 2;
    final private String northDoorway = "╝XXX╚";
    final private String southDoorway = "╗XXX╔";
    final private String verticalDoorway = "╩X╦";
    final private char empty = ' ';
    final private char hole = '0';
    final private char box = '#';
    final private char treasure = '@';

    protected int roomWidth;
    protected int roomLength;
    //make enum
    protected boolean northDoor;
    protected boolean southDoor;
    protected boolean westDoor;
    protected boolean eastDoor;
    protected int numEnemies;

    public char[][] grid;

    ArrayList<Enemy> enemies;

    public Room() {
        this.roomLength = (int)((maxRoomSize/3)*Math.random()+minRoomSize);
        this.roomWidth = (int)(maxRoomSize*Math.random()+minRoomSize);
        this.generalWeight = generalWeight * (roomLength + roomWidth) / 2;
        this.grid = new char[roomLength][roomWidth];
        this.northDoor = rd.nextBoolean();
        this.southDoor = rd.nextBoolean();
        this.westDoor = rd.nextBoolean();
        this.eastDoor = rd.nextBoolean();
        this.enemies = new ArrayList<>();

        this.numEnemies = rd.nextInt(4);
        for (int i = 0; i < numEnemies; i++) { enemies.add(new Enemy(1, rd.nextInt(10)+15, rd.nextInt(25)+10, rd.nextInt(6)+1, rd.nextDouble(.3), rd.nextInt(3), rd.nextDouble(0.5))); }
    }

    public Room(int roomLength, int roomWidth) {
        if ((roomWidth >= minRoomSize && roomWidth <= maxRoomSize) && (roomLength >= minRoomSize && roomLength <= maxRoomSize)) {
            this.roomLength = roomLength;
            this.roomWidth = roomWidth;
            this.grid = new char[roomLength][roomWidth];
            this.northDoor = rd.nextBoolean();
            this.southDoor = rd.nextBoolean();
            this.westDoor = rd.nextBoolean();
            this.eastDoor = rd.nextBoolean();
        } else {
            this.roomLength = minRoomSize;
            this.roomWidth = maxRoomSize;
        }
        this.numEnemies = rd.nextInt(4);
        this.enemies = new ArrayList<>();
        for (int i = 0; i < numEnemies; i++) { enemies.add(new Enemy(1, rd.nextInt(10)+15, rd.nextInt(25)+10, rd.nextInt(6)+1, rd.nextDouble(.3), rd.nextInt(3), rd.nextDouble(0.5))); }
    }

    public Room(int roomLength, int roomWidth, boolean northDoor, boolean southDoor, boolean eastDoor, boolean westDoor) {
        if ((roomWidth >= minRoomSize && roomWidth <= maxRoomSize) && (roomLength >= minRoomSize && roomLength <= maxRoomSize)) {
            this.roomLength = roomLength;
            this.roomWidth = roomWidth;
            this.grid = new char[roomLength][roomWidth];
            this.northDoor = northDoor;
            this.southDoor = southDoor;
            this.westDoor = westDoor;
            this.eastDoor = eastDoor;
        } else {
            this.roomLength = minRoomSize;
            this.roomWidth = maxRoomSize;
        }
        this.numEnemies = rd.nextInt(4);
        this.enemies = new ArrayList<>();
        for (int i = 0; i < numEnemies; i++) { enemies.add(new Enemy(1, rd.nextInt(10)+15, rd.nextInt(25)+10, rd.nextInt(6)+1, rd.nextDouble(.3), rd.nextInt(3), rd.nextDouble(0.5))); }
    }

    public boolean isNorthDoor() { return northDoor; }
    public boolean isSouthDoor() { return southDoor; }
    public boolean isEastDoor() { return eastDoor; }
    public boolean isWestDoor() { return westDoor; }
    public int getRoomLength() { return roomLength; }
    public int getRoomWidth() { return roomWidth; }

    public void constructRoom() {
        // construct walls
        for (int i = 0; i < roomLength; i++) {
            for (int j = 0; j < roomWidth; j++) {
                if (i == 0 || i == roomLength-1) grid[i][j] = '═';
                else if (j == 0 || j == roomWidth-1) grid[i][j] = '║';
                else grid[i][j] = empty;
            }
        }
        grid[0][0] = '╔';
        grid[0][roomWidth-1] = '╗';
        grid[roomLength-1][0] = '╚';
        grid[roomLength-1][roomWidth-1] = '╝';

        // make doors
        if (northDoor) {
            for (int k = 0; k < northDoorway.length(); k++) {
                grid[0][(3+roomWidth/2)-northDoorway.length()+k] = northDoorway.charAt(k);
            }
        }
        if (southDoor) {
            for (int k = 0; k < southDoorway.length(); k++) {
                grid[roomLength-1][(3+roomWidth/2)-southDoorway.length()+k] = southDoorway.charAt(k);
            }
        }
        if (westDoor) {
            for (int k = 0; k < verticalDoorway.length(); k++) {
                grid[k+(roomLength-2)/2][0] = verticalDoorway.charAt(k);
            }
        }
        if (eastDoor) {
            for (int k = 0; k < verticalDoorway.length(); k++) {
                grid[k+(roomLength-2)/2][roomWidth-1] = verticalDoorway.charAt(k);
            }
        }
    }

    public void fillRoom() {

        for (int i = 0; i < generalWeight; i++) {
            int x = (int)((roomLength-2)*Math.random()+1);
            int y = (int)((roomWidth-2)*Math.random()+1);

            while (grid[x][y] != empty) {
                x = (int)((roomLength-2)*Math.random()+1);
                y = (int)((roomWidth-2)*Math.random()+1);
            }

            int numNeighbors = countNeighbors(x, y);
            int choice = (int)((boxWeight+holeWeight+treasureWeight)*Math.random()+1);
            char object = box;

            if (choice <= boxWeight) {
                object = box;
            } else if (choice <= boxWeight + holeWeight) {
                object = hole;
            } else if (choice <= boxWeight + holeWeight + treasureWeight) {
                object = treasure;
            }

            if (numNeighbors <= 3 && numNeighbors != 0) {
                int neighbor = (int)(8*Math.random()+1);

                switch (neighbor) {
                    case 1 -> { if (grid[x+1][y] == empty) { grid[x+1][y] = object; } }
                    case 2 -> { if (grid[x+1][y+1] == empty) { grid[x+1][y+1] = object; } }
                    case 3 -> { if (grid[x-1][y] == empty) { grid[x-1][y] = object; } }
                    case 4 -> { if (grid[x-1][y-1] == empty) { grid[x-1][y-1] = object; } }
                    case 5 -> { if (grid[x][y+1] == empty) { grid[x][y+1] = object; } }
                    case 6 -> { if (grid[x][y-1] == empty) { grid[x][y-1] = object; } }
                    case 7 -> { if (grid[x-1][y+1] == empty) { grid[x-1][y+1] = object; } }
                    case 8 -> { if (grid[x+1][y-1] == empty) { grid[x+1][y-1] = object; } }
                }
            } else grid[x][y] = empty;
        }

        for (int i = 0; i < groupWeight; i++) { groupNeighbors(); }
    }
    private void groupNeighbors() {
        int centralX = (int) ((roomLength - 3) * Math.random() + 1);
        int centralY = (int) ((roomWidth - 2) * Math.random() + 1);

        for (int x = 1; x < roomLength - 1; x++) {
            for (int y = 1; y < roomWidth - 1; y++) {
                char currentObject = grid[x][y];

                if (currentObject == box || currentObject == hole || currentObject == treasure) {
                    int newX = x < centralX ? x + 1 : (x > centralX ? x - 1 : x);
                    int newY = y < centralY ? y + 1 : (y > centralY ? y - 1 : y);

                    if (grid[newX][newY] == empty) {
                        grid[newX][newY] = currentObject;
                        grid[x][y] = empty;
                    }
                }
            }
        }
    }

    private int countNeighbors(int x, int y) {
        int numNeighbors = 0;

        if (grid[x+1][y] != grid[x][y]) { numNeighbors++; }
        if (grid[x+1][y+1] != grid[x][y]) { numNeighbors++; }
        if (grid[x-1][y] != grid[x][y]) { numNeighbors++; }
        if (grid[x-1][y-1] != grid[x][y]) { numNeighbors++; }
        if (grid[x][y+1] != grid[x][y]) { numNeighbors++; }
        if (grid[x][y-1] != grid[x][y]) { numNeighbors++; }
        if (grid[x-1][y+1] != grid[x][y]) { numNeighbors++; }
        if (grid[x+1][y-1] != grid[x][y]) { numNeighbors++; }

        return numNeighbors;
    }

    public void generateEnemyPositions() {
        for (int i = 0; i < enemies.size(); i++) {
            int x = rd.nextInt(roomLength-3)+1;
            int y = rd.nextInt(roomWidth-3)+1;

            while (grid[x][y] != empty) {
                x = rd.nextInt(roomLength-3)+1;
                y = rd.nextInt(roomWidth-3)+1;
            }

            enemies.get(i).xPos = x;
            enemies.get(i).yPos = y;
            enemies.get(i).lastXPos = enemies.get(i).xPos;
            enemies.get(i).lastYPos = enemies.get(i).yPos;
        }
    }

    public void placeEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            grid[enemies.get(i).lastXPos][enemies.get(i).lastYPos] = empty;
            grid[enemies.get(i).xPos][enemies.get(i).yPos] = enemies.get(i).enemySymbol;
        }
    }

    public void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            // 1=north, 2=east, 3=south, 4=west, 5=nw, 6=ne, 7=sw, 8=se
            enemies.get(i).lastXPos = enemies.get(i).xPos;
            enemies.get(i).lastYPos = enemies.get(i).yPos;

            while (grid[enemies.get(i).xPos][enemies.get(i).yPos] != empty) {
                int direction = rd.nextInt(8)+1;

                switch (direction) {
                    case 1 -> enemies.get(i).xPos++;
                    case 2 -> enemies.get(i).yPos++;
                    case 3 -> enemies.get(i).xPos--;
                    case 4 -> enemies.get(i).yPos--;
                    case 5 -> { enemies.get(i).xPos++; enemies.get(i).yPos--; }
                    case 6 -> { enemies.get(i).xPos++; enemies.get(i).yPos++; }
                    case 7 -> { enemies.get(i).xPos--; enemies.get(i).yPos--; }
                    case 8 -> { enemies.get(i).xPos--; enemies.get(i).yPos++; }
                }

                if (grid[enemies.get(i).xPos][enemies.get(i).yPos] != empty) {
                    enemies.get(i).xPos = enemies.get(i).lastXPos;
                    enemies.get(i).yPos = enemies.get(i).lastYPos;
                }
            }
        }
    }

    public void placePlayer(Player player) { grid[player.xPos][player.yPos] = player.playerSymbol; }

    public void printRoom() {
        for (int i = 0; i < this.roomLength; i++) {
            for (int j = 0; j < this.roomWidth; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}
