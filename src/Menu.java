import java.util.*;
import java.time.Clock;

public class Menu {
    Clock clock = Clock.systemDefaultZone();
    public final String leadingDesign = "[//] ";
    public final String lineBreak = "";
    public final String trailingDesign = "";

    public ArrayList<String> menuBar;
    public Player player;

    public boolean showEnemyCount;
    public boolean showPlayerXP;
    public boolean showPlayerHealth;
    public boolean showMaxMoveDistance;
    public boolean showRoomsCleared;
    public boolean playerCanAnalyze;
    public boolean showHeldItem;

    public Menu(Player player) {
        this.menuBar = new ArrayList<String>();
        this.player = player;
    }

    public void constructMenu(int numEnemies, int level) {
        menuBar.clear();
        StringBuilder health = new StringBuilder("[");
        StringBuilder experience = new StringBuilder("[");

//        menuBar.add("[*] MENU [*]");

        menuBar.add(leadingDesign + "reading c:\\player_info.txt\\");
        menuBar.add(leadingDesign + " >name: " + "Noah");
        menuBar.add(leadingDesign + " >level: " + player.level);

        if (showPlayerHealth) {
            for (int i = 0; i < 10; i++) {
                health.append('█');
            }
            health.append("]");
            for (int i = health.length()-2; i > Math.round(((double)player.health/player.maxHealth)*10); i--) { health.setCharAt(i, '░'); }

            String hp = health.toString();
            menuBar.add(leadingDesign + " >health: " + hp + " " + player.health + "/" + player.maxHealth);
        } else { menuBar.add(leadingDesign + " >health: ???"); }
        if (showPlayerXP) {
            for (int i = 0; i < 10; i++) {
                experience.append('░');
            }
            experience.append("]");
            for (int i = 1; i <= Math.round(((double)player.xp/player.requiredXP)*10); i++) { experience.setCharAt(i, '█'); }

            String xp = experience.toString();
            menuBar.add(leadingDesign + " >xp: " + xp + " " + player.xp + "/" + player.requiredXP);
        } else { menuBar.add(leadingDesign + " >xp: ???"); }
        if (showMaxMoveDistance) {
            menuBar.add(leadingDesign + " >max_move_dist: " + player.maxMoveDistance);
        } else { menuBar.add(leadingDesign + " >max_move_dist: ???"); }
        if (showHeldItem) {
            // implement later
        }
        menuBar.add("\t END OF FILE");
        menuBar.add(leadingDesign + "reading c:\\level_info.txt\\");
        menuBar.add(leadingDesign + " >floor: " + level);

        if (showEnemyCount) { menuBar.add(leadingDesign + " >enemies_remaining: " + numEnemies); }
        else { menuBar.add(leadingDesign + " >enemies_remaining: ???"); }

        menuBar.add("\t END OF FILE");
        menuBar.add(leadingDesign + "executing c:\\magnifying_glass.exe\\");


    }
}
