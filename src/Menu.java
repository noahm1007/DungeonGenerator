import java.util.*;

public class Menu {
    final String leadingDesign = "[//]";
    final String trailingDesign = "";

    boolean showEnemyCount;
    boolean showPlayerHealth;
    boolean showTimeRemaining;
    boolean showMaxMoveDistance;
    boolean showRoomsCleared;
    boolean playerCanAnalyze;

    Floor floor;

    public Menu(Floor floor) {
        this.floor = floor;
    }


}
