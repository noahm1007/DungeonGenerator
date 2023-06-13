import java.nio.charset.CharacterCodingException;
import java.util.*;

public class Fight {
    final int length;
    final int width;
    final char empty = ' ';
    final String leadingDesign = " [//] ";

    Enemy enemy;
    Player player;
    String[] enemyArt;
    ArrayList<String> menuBar;
    Character[][] fightWindow;

    public Fight(Enemy enemy, Player player) {
        this.length = 15;
        this.width = 63;
        this.enemy = enemy;
        this.player = player;
        this.enemyArt = enemy.asciiArt.split("\\r?\\n");
//        this.playerArt = player.asciiArt.split("\\r?\\n");
        this.fightWindow = new Character[length][width];
        this.menuBar = new ArrayList<>();
    }

    public void constructFightWindow() {
        StringBuilder health = new StringBuilder("[");
        StringBuilder enemyHealth = new StringBuilder("[");

        menuBar.clear();
        menuBar.add(leadingDesign + "reading c:\\battle.exe\\");

        health.append("█".repeat(10));
        health.append("]");
        for (int i = health.length()-2; i > Math.round((double)(player.health/player.maxHealth)*100); i--) { health.setCharAt(i, '░'); }

        String hp = health.toString();
        menuBar.add(leadingDesign + " >health: " + hp + " " + player.health + "/" + player.maxHealth);

        //  -----------

        enemyHealth.append("█".repeat(10));
        enemyHealth.append("]");
        for (int i = enemyHealth.length()-2; i > Math.round((enemy.health/enemy.maxHealth)*100); i--) { enemyHealth.setCharAt(i, '░'); }

        String ehp = enemyHealth.toString();
        menuBar.add(leadingDesign + " > enemies health: " + ehp + " " + enemy.health + "/" + enemy.maxHealth);

        menuBar.add(leadingDesign + " 1.>attack with main item");
        menuBar.add(leadingDesign + " 2.>use item from inventory");
        menuBar.add("\t\tEND OF FILE");

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == length-1) fightWindow[i][j] = '═';
                else if (j == 0 || j == width-1) fightWindow[i][j] = '║';
                else fightWindow[i][j] = empty;
            }
        }
        fightWindow[0][0] = '╔';
        fightWindow[0][width-1] = '╗';
        fightWindow[length-1][0] = '╚';
        fightWindow[length-1][width-1] = '╝';
    }

    public void printFightWindow() {
        int c = 0;

        for (int i = 0; i < fightWindow.length; i++) {
            for (int j = 0; j < fightWindow[i].length; j++) {
                System.out.print(fightWindow[i][j]);
            }

            if (!((c + 1) > menuBar.size())) {
                System.out.print(menuBar.get(c));
                c++;
            }

            System.out.println();
        }
    }
}