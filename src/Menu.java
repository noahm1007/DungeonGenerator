import java.util.*;

public class Menu {
    public final String leadingDesign = "[//] ";
    public final String lineBreak = "";
    public final String trailingDesign = "";

    public ArrayList<String> menuBar;
    public Player player;

    public boolean showEnemyCount;
    public boolean showPlayerHealth;
    public boolean showTimeRemaining;
    public boolean showMaxMoveDistance;
    public boolean showRoomsCleared;
    public boolean playerCanAnalyze;
    public boolean showCurrentWeapon;

    public Menu(Player player) {
        this.menuBar = new ArrayList<String>();
        this.player = player;
    }

    public void constructMenu(int numEnemies) {
        menuBar.clear();
        StringBuilder sb = new StringBuilder("[");

        menuBar.add("[*] MENU [*]");

        menuBar.add(leadingDesign + "reading c:\\player_info.txt\\");
        menuBar.add(leadingDesign + " >name: " + "Noah");

        if (showPlayerHealth) {
            for (int i = player.maxHealth/10; i > 0; i--) { sb.append("█"); }
            sb.append("]");
            for (int i = sb.length()-2; i > Math.round(((double)(player.health) / 10)); i--) {
                sb.setCharAt(i, '░');
            }

            String health = sb.toString();
            menuBar.add(leadingDesign + " >health: " + health + " " + player.health + "/" + player.maxHealth);
        } else { menuBar.add(leadingDesign + " >health: ???"); }
        if (showMaxMoveDistance) {
            menuBar.add(leadingDesign + " >max_move_dist: " + player.maxMoveDistance);
        } else { menuBar.add(leadingDesign + " >max_move_dist: ???"); }
        if (showCurrentWeapon) {
            // implement later
        }
        menuBar.add(leadingDesign + " >level: " + player.level);
        menuBar.add("\t END OF FILE");
        menuBar.add(leadingDesign + "reading c:\\level_info.txt\\");
        menuBar.add(leadingDesign + " >floor: " + "3");

        if (showEnemyCount) { menuBar.add(leadingDesign + " >enemies_remaining: " + numEnemies); }
        else { menuBar.add(leadingDesign + " >enemies_remaining: ???"); }
    }
}
