import java.util.*;

public class Fight {
    final int length;
    final int width;

    Enemy enemy;
    Player player;

    String[] enemyArt;
//    String[] playerArt;

    public Fight(Enemy enemy, Player player) {
        this.length = 63;
        this.width = 35;
        this.enemy = enemy;
        this.player = player;
        this.enemyArt = enemy.asciiArt.split("\\r?\\n");
//        this.playerArt = player.asciiArt.split("\\r?\\n");
    }

    public void constructFightWindow() {

    }
}
