import java.nio.charset.CharacterCodingException;
import java.util.*;

public class Fight {
    final char empty = ' ';
    final String leadingDesign = " [//] ";

    Inventory inventory;

    Enemy enemy;
    Player player;
    String[] enemyArt;
    ArrayList<String> menuBar;
    Character[][] fightWindow;
    Character[][] enemyAscii;

    public Fight(Enemy enemy, Player player, Inventory inventory) {
        this.enemy = enemy;
        this.player = player;
        this.enemyArt = enemy.asciiArt.split("\n");
        this.enemyAscii = new Character[enemyArt.length][enemyArt[0].length()];

        for (int i = 0; i < enemyAscii.length; i++) {
            for (int j = 0; j < enemyAscii[i].length; j++) {
                enemyAscii[i][j] = enemyArt[i].charAt(j);
            }
        }

//        this.playerArt = player.asciiArt.split("\\r?\\n");
        this.fightWindow = new Character[(enemyAscii.length)+2][(enemyAscii[0].length)+20];
        this.menuBar = new ArrayList<>();
        this.inventory = inventory;
    }

    public void constructFightWindow() {
        StringBuilder health = new StringBuilder("[");
        StringBuilder enemyHealth = new StringBuilder("[");

        menuBar.clear();
        menuBar.add(leadingDesign + "reading c:\\battle.exe\\");

        health.append("█".repeat(10));
        health.append("]");
        for (int i = health.length()-2; i > Math.round(((double)player.health/player.maxHealth)*10); i--) { health.setCharAt(i, '░'); }

        String hp = health.toString();
        menuBar.add(leadingDesign + " >health: " + hp + " " + player.health + "/" + player.maxHealth);

        //  -----------

        enemyHealth.append("█".repeat(10));
        enemyHealth.append("]");
        for (int i = enemyHealth.length()-2; i > Math.round(((double)enemy.health/enemy.maxHealth)*10); i--) { enemyHealth.setCharAt(i, '░'); }

        String ehp = enemyHealth.toString();
        menuBar.add(leadingDesign + " > enemies health: " + ehp + " " + enemy.health + "/" + enemy.maxHealth);

        menuBar.add(leadingDesign + " 1.>attack with main item");
//            menuBar.add(leadingDesign + " 2.>use item from inventory");
        menuBar.add("\t\tEND OF FILE");
//        else {
//            inventory.constructInventory(10, 1);
//            inventory.menuBar.add(leadingDesign + " >select item from inventory");
//            inventory.menuBar.add(leadingDesign + " >input \"b\" to go back");
//        }

//        fightWindow[0][0] = '╔';
//        fightWindow[0][fightWindow[0].length-1] = '╗';
//        fightWindow[fightWindow.length-1][0] = '╚';
//        fightWindow[fightWindow.length-1][fightWindow[0].length-1] = '╝';
    }

    public void printFightWindow() {
        int c = 0;
        int c1 = 0;

        for (int i = 0; i < enemyAscii.length; i++) {
            for (int j = 0; j < enemyAscii[i].length; j++) {
                System.out.print(enemyAscii[i][j]);
            }

            if (!((c + 1) > menuBar.size())) {
                System.out.print(menuBar.get(c));
                c++;
            }


            System.out.println();
        }
    }
}