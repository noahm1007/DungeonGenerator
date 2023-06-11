import java.nio.charset.CharacterCodingException;
import java.util.*;

public class Fight {
    final int length;
    final int width;
    final char empty = ' ';

    Enemy enemy;
    Player player;
    String[] enemyArt;
//    String[] playerArt;
    
    Character[][] fightWindow;

    public Fight(Enemy enemy, Player player) {
        this.length = 63;
        this.width = 35;
        this.enemy = enemy;
        this.player = player;
        this.enemyArt = enemy.asciiArt.split("\\r?\\n");
//        this.playerArt = player.asciiArt.split("\\r?\\n");
        this.fightWindow = new Character[length][width];
    }

    public void constructFightWindow() {
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
}