import java.awt.image.LookupTable;
import java.io.StringBufferInputStream;
import java.sql.PreparedStatement;
import java.util.*;
import java.time.Clock;

public class DungeonGenerator {
    static Random rd = new Random();
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
//        https://www.coolgenerator.com/ascii-text-generator
        boolean newFloor = true;

//        String[] title = new String[]{
//                "██▓███   ██▓    ▄▄▄       ▄████▄  ▓█████  ██░ ██  ▒█████   ██▓    ▓█████▄ ▓█████  ██▀███   ",
//                "▓██░  ██▒▓██▒   ▒████▄    ▒██▀ ▀█  ▓█   ▀ ▓██░ ██▒▒██▒  ██▒▓██▒    ▒██▀ ██▌▓█   ▀ ▓██ ▒ ██▒",
//                "▓██░ ██▓▒▒██░   ▒██  ▀█▄  ▒▓█    ▄ ▒███   ▒██▀▀██░▒██░  ██▒▒██░    ░██   █▌▒███   ▓██ ░▄█ ▒",
//                "▒██▄█▓▒ ▒▒██░   ░██▄▄▄▄██ ▒▓▓▄ ▄██▒▒▓█  ▄ ░▓█ ░██ ▒██   ██░▒██░    ░▓█▄   ▌▒▓█  ▄ ▒██▀▀█▄  ",
//                "▒██▒ ░  ░░██████▒▓█   ▓██▒▒ ▓███▀ ░░▒████▒░▓█▒░██▓░ ████▓▒░░██████▒░▒████▓ ░▒████▒░██▓ ▒██▒",
//                "▒▓▒░ ░  ░░ ▒░▓  ░▒▒   ▓▒█░░ ░▒ ▒  ░░░ ▒░ ░ ▒ ░░▒░▒░ ▒░▒░▒░ ░ ▒░▓  ░ ▒▒▓  ▒ ░░ ▒░ ░░ ▒▓ ░▒▓░",
//                "░▒ ░     ░ ░ ▒  ░ ▒   ▒▒ ░  ░  ▒    ░ ░  ░ ▒ ░▒░ ░  ░ ▒ ▒░ ░ ░ ▒  ░ ░ ▒  ▒  ░ ░  ░  ░▒ ░ ▒░",
//                "░░         ░ ░    ░   ▒   ░           ░    ░  ░░ ░░ ░ ░ ▒    ░ ░    ░ ░  ░    ░     ░░   ░ ",
//                "             ░  ░     ░  ░░ ░         ░  ░ ░  ░  ░    ░ ░      ░  ░   ░       ░  ░   ░     ",
//                "                          ░                                         ░                      "
//        };

        String[] title = new String[]{
                " ██▀███   ▄▄▄     ▄▄▄█████▓     ▄████  ▄▄▄       ███▄ ▄███▓▓█████  ",
                "▓██ ▒ ██▒▒████▄   ▓  ██▒ ▓▒    ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀  ",
                "▓██ ░▄█ ▒▒██  ▀█▄ ▒ ▓██░ ▒░   ▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███    ",
                "▒██▀▀█▄  ░██▄▄▄▄██░ ▓██▓ ░    ░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄  ",
                "░██▓ ▒██▒ ▓█   ▓██▒ ▒██▒ ░    ░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒ ",
                "░ ▒▓ ░▒▓░ ▒▒   ▓▒█░ ▒ ░░       ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░ ",
                " ░▒ ░ ▒░  ▒   ▒▒ ░   ░         ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░  ",
                "  ░░   ░   ░   ▒    ░         ░ ░   ░   ░   ▒   ░      ░      ░    ",
                "   ░           ░  ░                 ░       ░  ░       ░      ░  ░ "
        };

        String[] openingImage = new String[] {
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠈⠄",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡠⡤⡀⠠⡀⠒⠐⡀⠄⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠠",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⣈⡛⠁⡀⡀⡀⡀⡀⡀⠘⣖⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⣭⡀⡀⣀⣀⡤⣴⣶⣾⣦⣿⣟⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢀⡀⢀⣀⣀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢹⣶⣿⣿⣿⠁⠘⠛⠋⢉⣽⣿⣇⡄⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⣀⣀⣀⣀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⣴⣾⣿⣿⣿⣿⣿⣿⣦⣄⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢺⣿⣯⣯⣀⣤⣤⣶⣴⣍⣿⣿⣟⢠⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠤⠤⡀⠒⠒⠂⠈⠉⠁⠠⠤⠴⢿⣷",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣇⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢸⣿⢿⣿⣿⣿⣿⠟⠛⢻⣿⣿⣿⣿⣦⢤⣄⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢀⣀⣀⣀⢠⡀⡀⠤⡀⡀⢻",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡌⢆⡀⡀⡀⡀⡀⠠⠄⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠈⣿⣼⣿⣿⣦⣶⣾⣧⣼⣿⠇⣷⣿⣿⣿⣿⣿⣿⣷⣶⣤⡀⡀⡀⡀⠠⠤⡀⡀⡀⡀⡀⡀⡀⢈⣀⣀⣠⠭⢥⣠⣤⠴⠶⠦⠦⠤⠍⠛⠛",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣧⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢀⣠⣿⣿⣿⠙⣿⣿⣿⣿⣿⣿⡀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⠶⠒⠒⠙⠛⠛⠛⠉⠁⠉⠛⢉⣀⣀⣀⣀⡤⠤⠠⠤⡀⡀⡀⠉⢑",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⣠⣴⣿⣿⣿⣿⣿⣷⠻⣿⣿⣿⣿⡟⣀⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣗⣬⡁⠍⠉⡀⡀⡀⡀⣤⣤⣶⣯⡉⠝⠒⠂⠤⠠⠶⠶⠋⠉⠠⣭",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢰⢿⣿⣿⣿⡿⢿⣿⣿⣿⡝⣽⣿⣿⡿⣿⣬⣿⣿⣷⣶⣦⣄⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣼⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠉⠉⠉⠉⠁⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢻",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠈⣊⣩⠴⡶⣽⣷⣏⢇⣻⣿⣷⣿⣿⣖⣯⣿⣿⣿⣿⣿⣿⣿⣆⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠘",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠠⣴⡖⢼⣿⢟⡣⣽⣿⣿⣿⣼⣿⣿⢻⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⡖⠁⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣗⡐⠒⠒⣒⠒⠄⠠⠄⣀⣤⣄⣀⣠⣴⣶⣦⣠⣤⡀⢀⡀⠈",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢀⣠⣄⣜⠿⣿⣬⣳⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠁⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣖⣶⣒⣒⣒⣄⣀⡀⢀⡀⣀⣉⣉⠁⡀⠈⡀⡀⡀⡀⠩⠿",
                "⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⡀⡀⡀⡀⡀⡀⡀⡀⠐⠒⡲⡀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠠⠉⠁⠉⠉⠉⠁⠁⠒⠊⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⢹",
                "⡀⡀⡀⠘⡀⡀⡀⡀⡀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⡀⡀⡀⡀⡀⡀⡀⡀⡀⡄⠲⣿⣿⣾⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⡿⣿⣿⣻⣿⣿⣿⣿⣿⣿⡧⠤⠤⠤⠤⠤⠤⢀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⡀⡀⢠⣿",
                "⡀⡀⡀⡀⡀⡀⠐⠲⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⡀⡀⡀⡀⡀⡀⡀⢀⣧⣥⣿⣿⣿⣿⣿⢿⣿⠟⠻⠟⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⡀⠁⠁⠉⠻⠿⠟⢿⠿⣿⣿⡇⡀⡀⡀⡀⠒⠒⡀⡀⠤⡀⠄⡀⡀⡀⡀⠈⠉⠉⠉⠉⠉⠉⠙",
                "⡀⡀⡀⡀⡀⡀⡀⡀⢻⣿⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡆⣀⣀⣤⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⡆⡀⡀⡀⡀⡀⡀⡀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⡀⡀⡀⡀⡀⡀⡀⡀⡀⣰⣛⠿⠶⢤⣶⣦⣤⣖⣲⣤⣴⣥⣤⣄⣀⣀⣐⣂⣒⣂⣀⡀⣀⡀⡀",
                "⡀⡀⡀⡀⡀⡀⡀⡀⢸⣿⠓⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠩⠉⣴⣾⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⡀⡀⡀⡀⡀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⡀⡀⡀⡀⡀⡀⡀⣼⣍⣉⡀⠐⠛⠘⡀⠒⠚⠲⠒⠊⠻⠿⠍⠿⣏⠙⣛⣿⣿⣿⠿⣿⣿⣿",
                "⡀⡀⡀⡀⡀⡀⠈⡀⠈⠁⡀⡀⡀⠙⠻⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⡀⠄⣂⣴⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⡀⡀⡀⡀⡀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⡀⡀⡀⡀⡀⢀⣴⣿⣧⣀⣈⣉⠠⡔⡀⡀⠌⢠⣤⣤⠠⠄⡀⣨⣄⣀⣉⣉⣉⢉⡉⢉⠛⠁",
                "⢀⡀⡀⡀⡀⡀⡀⢀⣤⣤⣤⣀⡀⡀⡀⡀⣤⣍⣾⣿⣆⣀⣿⣿⣿⣿⣿⡿⢟⡋⠁⡀⢀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⡀⡀⡀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠁⡀⡀⡀⡀⡀⡀⡀⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⢿⣿⣿⣦⣤⣤⣥⣄⣀⣹⣿⡁⡀⠛⠛⠉⠱",
                "⣾⠋⡀⡀⡀⡀⣰⣿⣿⣿⣿⣿⡇⡀⠈⣷⣾⣿⣿⣿⡿⣹⣿⣿⣿⣭⡤⠶⢊⣀⣴⣎⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⢿⠢⡀⡀⡀⡀⡀⡀⠙⣿⣿⣿⣿⣿⠿⠋⡀⡀⡀⡀⡀⡀⢀⣤⣶⣿⣿⣿⣶⠒⠉⠉⠉⠙⠋⠉⠙⣿⣿⣷⣾⣿⣯⣭⣿⣿⣻⣿⣿⢿⣿⣿⣿⣿",
                "⣿⣢⡀⡀⠘⣿⣿⣿⣿⣿⣿⡿⠃⢀⣴⣿⣿⣽⣿⣿⡨⡝⠟⠿⢿⣩⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣹⣿⣷⣄⡀⡀⡀⡀⡀⡀⣛⢿⠟⠁⡀⡀⡀⡀⡀⢀⣀⣈⣿⡿⢿⣿⣿⣿⣿⣆⣀⣀⡀⡀⡀⡀⠉⢉⣿⣿⣿⣿⣿⡿⣿⣿⣟⡻⠿⠷⠶⣷⣿⣿",
                "⣿⣿⡖⡀⡀⣿⣿⣿⣿⣿⡿⣤⡔⢫⣽⡿⠟⠏⠇⡀⡀⣠⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⡀⣀⣴⣿⣿⣾⠄⡀⡀⡀⠠⡀⡀⣾⣷⣿⣥⣦⣀⣈⡉⡉⢛⠟⢿⠃⣠⣄⣀⡀⠈⠉⠛⠛⠛⠟⠿⠿⢶⣦⣬⣭⣍⡼⢒⣶⠤⢬",
                "⣿⣿⣿⣧⣿⣿⣿⣿⣿⣿⣿⠟⠋⠊⡀⠈⣀⣠⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠿⣷⣜⣻⣿⣓⡀⡀⡀⢀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣾⡤⠐⠈⠈⠁⠉⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠉⠉⠑⠚⠫⠴⢒"
        };

        typewriterPrint(title, 250);
        typewriterPrint(openingImage, 250);
        typewriterPrint("hey, you, you're finally awake.", 200);

        calibrateTerminal();
        input.next();

        ArrayList<Item> lootTable = new ArrayList<>();
        lootTable.add(new Item(true, false, true, "Small Health Potion", "A bubbling blood red liquid contained in a small vile. Restores 25HP.", 90));
        lootTable.add(new Item(true, false, true, "Big Health Potion", "Looks like fruit punch, does NOT taste like fruit punch. Restores 50HP", 20));
        lootTable.add(new Item(true, true, true, "try catch block", "If your next attack misses, catch and retry it.", 19));
        lootTable.add(new Weapon(false, true, false, false, "Wooden Stick", "A short wooden stick. Looks like a sword.", 25, 10, 6, 2, 5, 17));
        lootTable.add(new Weapon(false, true, false, false, "Recursive Sword", "A spring-shaped sword. Not in a recursive algorithm, because its 2:30AM right now.", 2, 7, 13, 1.5, 1.8, 14));
        lootTable.add(new Weapon(false, true, false, false, "For-each Blaster", "A metallic blaster. Does alot of damage.", 12, 6, 10, 1.4, 1, 8));
        lootTable.add(new Item(false, true, false, "Random Shield", "Looks like a shield. Obviously. Has a random chance to deflect an attack. What chance? IDK.", 15));
//        lootTable.add(new Item(false, true, false, "Random Ring", "A magic ring. Subtracts a random number of damage from each hit you take.", 19)); // for reasons beyond my understanding this doesnt work -- 2:58AM
        lootTable.add(new Weapon(false, true , false, false, "Iron Stick", "Better than the Wooden Stick, probably on account of it being metal.", 24, 13, 12, 2.5, 3, 11));
//        lootTable.add(new Weapon(true, true, false, false, "while(true) {} bow", "Does 1 damage, forever. Unless it throws an exception. Breaks when used.", 1, 1, 0, 1, 0.1, 33));

        Clock clock = Clock.systemDefaultZone();
        Floor floor = new Floor();
        Options options = new Options();
        boolean pastFirstFloor = false;

        int page = 0;
        int selectedItem = -1;

        while (true) {
            if (floor.player.health <= 0) died();
            if (newFloor) {
                newFloor = false;
                floor.lootTable = lootTable;
                floor.generateFloor();
                floor.player.currentRoom.grid[floor.player.xPos][floor.player.yPos] = floor.player.currentRoom.empty;
                Room startingRoom = floor.rooms[floor.floorLength-1][floor.entranceRoom];
                floor.player.xPos = startingRoom.roomLength/2;
                floor.player.yPos = startingRoom.roomWidth/2;
                floor.player.currentRoom = startingRoom;
                floor.player.currentRoom.placePlayer(floor.player);
                floor.printFloor(true, false);
            }

            inputHandler(input.next(), floor, options, page, selectedItem);
            page = floor.options.page;
            selectedItem = floor.options.selectedItem;

            if (floor.player.currentRoom.enemies.size() == 0 && floor.player.currentRoom.equals(floor.rooms[0][floor.exitRoom])) {
                newFloor = true;
            }
        }
    }

    public static void inputHandler(String input, Floor floor, Options options, int page, int selectedItem) {
        boolean tryCatchEquipped = false;
//        boolean randomRingEquipped = false;
        boolean randomShieldEquipped = false;
        if (input.equalsIgnoreCase("b")) {
            page = 0;
            selectedItem = -1;
            floor.nextFrame(true, false, page, selectedItem);
            return;
        }

        if (isValidMovementString(input)) {
            move(input, floor);
        }

        // save
        switch (page) {
            case 0 -> {
                switch (input) {
                    case "1" -> page = 1;
                    case "2" -> page = 2;
                    case "3" -> page = 3;
                }
            }
            case 1 -> {
                if (input.matches("[0-9]+") && (Integer.parseInt(input) <= floor.player.currentRoom.enemies.size()) && (Integer.parseInt(input) > 0)) {
                    if (floor.player.activeItem == null) {
                        //die
                    }

                    Fight fight = new Fight(floor.player.currentRoom.enemies.get(Integer.parseInt(input)-1), floor.player, floor.player.inventory);
                    boolean inFight = true;
//                    boolean inInventory = false;
                    Scanner sl1 = new Scanner(System.in);

                    while (inFight) {
                        fight.constructFightWindow();
                        fight.printFightWindow();
                        String choice = sl1.next();

                        switch (choice) {
                            case "1" -> {
                                double dmg = fight.player.activeItem.attack();
                                int attackMiss = rd.nextInt(100);
                                if (tryCatchEquipped) attackMiss = 101; tryCatchEquipped = false;

                                if (fight.player.activeItem.missChance <= attackMiss) {
                                    fight.enemy.takeDamage(dmg);
                                    System.out.println("[#] you struck the enemy for " + dmg + " damage.");
                                    if (fight.enemy.health <= 0) {
                                        inFight = false;
                                        System.out.println("[#] you killed the enemy!");
                                        System.out.println("[#] you gained " + fight.enemy.XP + " experience!");
                                        fight.player.recieveXP(fight.enemy.XP);
                                        floor.player.currentRoom.grid[fight.enemy.xPos][fight.enemy.yPos] = floor.player.currentRoom.empty;
                                        floor.player.currentRoom.enemies.remove(fight.enemy);
                                    }
                                } else {
                                    System.out.println("[#] the attack missed!");
                                }

                                double edmg = fight.enemy.baseDamage;
//                                if (randomRingEquipped) edmg -= rd.nextInt(5);

                                int eAttackMiss = rd.nextInt(100);
                                if (randomShieldEquipped && rd.nextInt(3) == 3) eAttackMiss = 101;

                                if (fight.enemy.missChance <= eAttackMiss && inFight) {
                                    fight.player.takeDamage(edmg);
                                    System.out.println("[#] the enemy hit you for " + edmg + " damage.");

                                    if (fight.player.health <= 0) {
                                        died();
                                    }
                                } else {
                                    System.out.println("[#] the enemy missed.");
                                }
                            }
//                            case "2" -> {
//                                inInventory = true;
//
//                                if (input.equalsIgnoreCase("b")) {
//                                    inInventory = false;
//                                } else if (input.matches("[0-9]+") && (Integer.parseInt(input) <= floor.player.inventory.inventory.size()) && (Integer.parseInt(input) > 0)) {
//                                    int usingItem = Integer.parseInt(input)-1;
//
//                                    if (floor.player.inventory.inventory.get(usingItem) instanceof Weapon) {
//                                        floor.player.activeItem.isActive = false;
//                                        floor.player.activeItem = (Weapon)floor.player.inventory.inventory.get(usingItem);
//                                        floor.player.activeItem.isActive = true;
//                                    } else {
//                                        if (floor.player.inventory.inventory.get(usingItem).equals(floor.lootTable.get(0))) {
//                                            if ((floor.player.health + 25) > floor.player.maxHealth) {
//                                                floor.player.health = floor.player.maxHealth;
//                                            } else {
//                                                floor.player.health += 25;
//                                            }
//                                            floor.player.inventory.removeItem(floor.player.inventory.inventory.get(usingItem));
//                                        } else if (floor.player.inventory.inventory.get(usingItem).equals(floor.lootTable.get(1))) {
//                                            if ((floor.player.health + 50) > floor.player.maxHealth) {
//                                                floor.player.health = floor.player.maxHealth;
//                                            } else {
//                                                floor.player.health += 50;
//                                            }
//                                            floor.player.inventory.removeItem(floor.player.inventory.inventory.get(usingItem));
//                                        } else if (floor.player.inventory.inventory.get(usingItem).equals(floor.lootTable.get(2))) {
//                                            tryCatchEquipped = true;
//                                            floor.player.inventory.removeItem(floor.player.inventory.inventory.get(usingItem));
//                                        }
//                                    }
//                                    inInventory = false;
//                                }
//                            }
                        }
                    }
//                    sl1.close();
                }
            }
            case 2 -> {
                if (input.matches("[0-9]+") && (Integer.parseInt(input) <= floor.player.inventory.inventory.size()) && (Integer.parseInt(input) > 0)) {
                    page = 4;
                    selectedItem = Integer.parseInt(input)-1;
                }
            }
            case 3 -> {
                if (input.equalsIgnoreCase("y")) {
                    System.out.println("[#] there is no way i have time to implement a save function. good luck! :)");
                }
                page = 0;
            }
            case 4 -> {
                switch (input) {
                    case "1" -> {
                        String[] splitString = floor.player.inventory.inventory.get(selectedItem).itemDescription.split(" ");

                        for (int i = 0; i < splitString.length; i++) {
                            if (i % 10 == 0) {
                                System.out.print("\n[#] ");
                            }
                            System.out.print(splitString[i] + " ");
                            if (i == splitString.length-1) System.out.println();
                        }
                    }
                    case "2" -> {
                        if (floor.player.inventory.inventory.get(selectedItem) instanceof Weapon) {
                            System.out.println("[║] base damage: " + ((Weapon) floor.player.inventory.inventory.get(selectedItem)).baseDamage);
                            System.out.println("[║] critical hit chance: " + ((Weapon) floor.player.inventory.inventory.get(selectedItem)).critChance + "%");
                            System.out.println("[║] critical hit multiplier damage: " + ((Weapon) floor.player.inventory.inventory.get(selectedItem)).critMultiplier);
                            System.out.println("[║] attack speed: " + ((Weapon) floor.player.inventory.inventory.get(selectedItem)).attackSpeed);
                            System.out.println("[║] miss chance: " + ((Weapon) floor.player.inventory.inventory.get(selectedItem)).missChance + "%");
                        } else {
                            System.out.println("[║] no available info :(");
                        }
                    }
                    case "3" -> {
                        if (floor.player.inventory.inventory.get(selectedItem) instanceof Weapon) {
                            ((Weapon) floor.player.inventory.inventory.get(selectedItem)).isActive = true;
                            floor.player.activeItem = (Weapon) floor.player.inventory.inventory.get(selectedItem);
                            System.out.println("[#] equipped " + floor.player.inventory.inventory.get(selectedItem).itemName);
                        } else if (!(floor.player.inventory.inventory.get(selectedItem).isEquipable)) {
                            if (floor.player.inventory.inventory.get(selectedItem).equals(floor.lootTable.get(0))) {
                                if ((floor.player.health + 25) > floor.player.maxHealth) {
                                    floor.player.health = floor.player.maxHealth;
                                } else {
                                    floor.player.health += 25;
                                }
                                floor.player.inventory.removeItem(floor.player.inventory.inventory.get(selectedItem));
                            } else if (floor.player.inventory.inventory.get(selectedItem).equals(floor.lootTable.get(1))) {
                                if ((floor.player.health + 50) > floor.player.maxHealth) {
                                    floor.player.health = floor.player.maxHealth;
                                } else {
                                    floor.player.health += 50;
                                }
                                floor.player.inventory.removeItem(floor.player.inventory.inventory.get(selectedItem));
                            }

                            floor.player.inventory.removeItem(floor.player.inventory.inventory.get(selectedItem));
                        } else {
//                            floor.player.inventory.inventory.get(selectedItem).isActive = true;
                            System.out.println("[#] equipped " + floor.player.inventory.inventory.get(selectedItem).itemName);
                            floor.player.inventory.removeItem(floor.player.inventory.inventory.get(selectedItem));

//                            if (floor.lootTable.get(7).equals(floor.player.inventory.inventory.get(selectedItem))) randomRingEquipped = true;
                            if (floor.lootTable.get(2).equals(floor.player.inventory.inventory.get(selectedItem))) tryCatchEquipped = true;
                            if (floor.lootTable.get(6).equals(floor.player.inventory.inventory.get(selectedItem))) randomShieldEquipped = true;
                        }
                    }
                    case "4" -> page = 5;
                }
            }
            case 5 -> {
                if (input.equalsIgnoreCase("y")) {
                    floor.player.inventory.removeItem(floor.player.inventory.inventory.get(selectedItem));
                    System.out.println("[#] discarded 1x " + floor.player.inventory.inventory.get(selectedItem).quantity + floor.player.inventory.inventory.get(selectedItem).itemName);
                }
                page = 2;
            }
//            default -> page = 0;
        }

        floor.nextFrame((page != 2 && page != 4 && page != 5), (page == 2 || page == 4 || page == 5), page, selectedItem);
    }

    public static void typewriterPrint(String text, int speed) throws InterruptedException {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Thread.sleep(speed);
        }

        System.out.println();
    }
    
    public static void typewriterPrint(String[] image, int speed) throws InterruptedException {
        for (int i = 0; i < image.length; i++) {
            System.out.println(image[i]);
            Thread.sleep(speed);
        }
    }

    public static boolean isValidMovementString(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                if (!Character.isLetter(input.charAt(i))) { return false; }
            } else { if (!Character.isDigit(input.charAt(i))) { return false; } }
        } return input.length() >= 2;
    }

    public static void move(String move, Floor floor) {
        move = move.toLowerCase();
        int distance = 0;
        
        if (!isValidMovementString(move)) { return; }

        for (int i = 0; i < move.length(); i++) {
            try {
                distance += Integer.parseInt(Character.toString(move.charAt(i)));
            } catch (NumberFormatException ignored) {}
        }
        
        if (distance > floor.player.maxMoveDistance) { return; }

        int x, y;

        while (move.length() > 0) {
            String input = move.substring(0, 2);
            move = move.substring(2);

            x = floor.player.xPos;
            y = floor.player.yPos;

            for (int i = 0; i < input.length() - 1; i++) {
                if (i % 2 == 0) {
                    switch (input.charAt(i)) {
                        case 'w' -> x -= Integer.parseInt(Character.toString(input.charAt(i + 1)));
                        case 'a' -> y -= Integer.parseInt(Character.toString(input.charAt(i + 1)));
                        case 's' -> x += Integer.parseInt(Character.toString(input.charAt(i + 1)));
                        case 'd' -> y += Integer.parseInt(Character.toString(input.charAt(i + 1)));
                    }
                }
            }

            if ((x == floor.player.currentRoom.roomLength / 2) ^ (y == floor.player.currentRoom.roomWidth / 2 || y == (floor.player.currentRoom.roomWidth / 2) + 1 || y == (floor.player.currentRoom.roomWidth / 2) - 1)) {
                floor.player.currentRoom.grid[floor.player.xPos][floor.player.yPos] = floor.player.currentRoom.empty;
                int roomX = 0;
                int roomY = 0;

                for (int i = 0; i < floor.getFloorLength(); i++) {
                    for (int j = 0; j < floor.getFloorWidth(); j++) {
                        if (floor.player.currentRoom.equals(floor.rooms[i][j])) {
                            roomX = i;
                            roomY = j;
                        }
                    }
                }

                try {
                    if (x <= 0 && !(floor.rooms[roomX][roomY].isClosed)) {
                        floor.player.currentRoom = floor.rooms[roomX - 1][roomY];
                        x = floor.player.currentRoom.roomLength - 2 - Math.abs(x);
                    } else if (x <= 0) { x = 1; }
                    if (x >= floor.player.currentRoom.roomLength - 1 && !(floor.rooms[roomX][roomY].isClosed)) {
                        floor.player.currentRoom = floor.rooms[roomX + 1][roomY];
                        x -= floor.player.currentRoom.roomLength - 2;
                    } else if (x >= floor.player.currentRoom.roomLength - 1) { x = floor.player.currentRoom.roomLength - 2; }
                    if (y <= 0 && !(floor.rooms[roomX][roomY].isClosed)) {
                        floor.player.currentRoom = floor.rooms[roomX][roomY - 1];
                        y = floor.player.currentRoom.roomWidth - 2 - Math.abs(y);
                    } else if (y <= 0) { y = 1; }
                    if (y >= floor.player.currentRoom.roomWidth - 1 && !(floor.rooms[roomX][roomY].isClosed)) {
                        floor.player.currentRoom = floor.rooms[roomX][roomY + 1];
                        y -= floor.player.currentRoom.roomWidth - 2;
                    } else if (y >= floor.player.currentRoom.roomWidth - 1) { y = floor.player.currentRoom.roomWidth - 2; }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }

            if (x < 1) {
                x = 1;
            }
            if (x >= floor.player.currentRoom.roomLength - 1) {
                x = floor.player.currentRoom.roomLength - 2;
            }
            if (y < 1) {
                y = 1;
            }
            if (y >= floor.player.currentRoom.roomWidth - 1) {
                y = floor.player.currentRoom.roomWidth - 2;
            }

//            if (floor.player.currentRoom.grid[x][y] == floor.player.currentRoom.enemySymbol) {/*initiate fight*/}
            if (floor.player.currentRoom.grid[x][y] == floor.player.currentRoom.hole) {died();}
            while (floor.player.currentRoom.grid[x][y] == floor.player.currentRoom.box) {
                switch (input.charAt(0)) {
                    case 'w' -> x++;
                    case 'a' -> y++;
                    case 's' -> x--;
                    case 'd' -> y--;
                }
            }
            if (floor.player.currentRoom.grid[x][y] == floor.player.currentRoom.treasure) {
                Item chosenItem = null;
                ArrayList<Item> rarityArray = new ArrayList();

                for (int i = 0; i < floor.lootTable.size(); i++) {
                    for (int j = 0; j < floor.lootTable.get(i).rarityWeighting; j++) {
                        rarityArray.add(floor.lootTable.get(i));
                    }
                }

                floor.player.inventory.addItem(rarityArray.get(rd.nextInt(rarityArray.size())));
            }

            floor.player.currentRoom.grid[floor.player.xPos][floor.player.yPos] = floor.player.currentRoom.empty;
            floor.player.move(x, y);
        }
    }

    public static void calibrateTerminal() {
        System.out.println("[#] to calibrate the terminal, please shrink/expand the terminal until you can see this text and the last \"[#]\"");
        for (int i = 0; i < 21; i++) {
            System.out.println("[#]");
        }
    }

    public static void died() {
        System.out.println("[#] you died...");
        System.out.println("""
                ⡹⡽⣝⡽⣝⡽⣝⢽⢝⡽⣝⢽⣪⢯⡳⡽⣝⢮⢯⡳⡽⣝⢮⢯⡫⣞⢽⣪⢟⡮⡯⣫⢯⣫⢯⢯⣫⢯⢯⡫⣏⢯⢞⢽⡹⣝⡵⡯⣫⢏⡯⣞⢽⡹⡮⣫⢯⡺⣝⢵⡫⣏⢯⡫⡯⣫⢯⣳⡫⣯⡺⣝⢮⢪⢪⣳⡫⣗⢽⣕⢯⣫⢞⡽⣝⢞⡽⣹⢺⢝⢵⡫⣗⡝⣜⢵⣫⡳⣝⢼
                ⢾⢝⡮⡯⣞⣞⢽⣝⢽⡺⣝⡵⣳⢝⡮⡯⣺⢝⢮⡫⡞⡎⡗⡕⡝⣜⢕⢗⢽⡪⡯⣳⡻⡮⡯⣗⢽⡱⡳⣹⣸⢼⣸⣱⢩⡪⡺⡹⡪⡯⡺⡕⣏⢮⢫⡪⣎⢮⣺⣼⢮⣮⣮⣪⡫⢮⢳⢕⡯⡺⡮⣳⡣⡇⡇⣗⢝⢮⢳⢕⢯⢺⢕⢯⢪⡓⡭⡪⡪⣪⢪⢪⢗⡽⣪⢗⢗⡵⣣⢣
                ⡺⡽⣝⢽⡺⣪⢗⣗⢯⣫⢞⡽⡺⣝⢮⢯⡺⣝⢵⢫⢪⣞⣾⣯⣿⢷⣿⣽⣵⡽⣮⣳⣝⣮⣳⣵⢷⣻⠽⢝⢚⢟⣿⣽⣿⡾⣾⡼⣜⣮⣝⡮⣾⣼⢷⣟⣿⡿⣟⣿⢫⢛⢚⢳⢿⢷⣽⣪⢮⡹⣜⡪⣪⢎⣮⢮⡾⣵⢷⣽⣜⣎⢎⢎⢎⣞⢼⣪⢯⢎⡇⡯⣳⢽⢕⢭⡣⡯⢮⡳
                ⡯⡯⣺⡳⣝⣗⢯⢞⣵⣳⣫⢾⢝⡮⣳⡳⣝⢮⡫⣪⣿⣽⡿⣾⣿⣻⣯⢿⢕⢯⣫⣷⣿⣷⢿⣻⣿⣯⣯⡷⡧⡣⣿⣟⣷⣿⡿⣿⣟⣯⣿⢿⣿⣾⣿⢿⣻⣿⣟⢪⣲⣯⣷⡷⣜⣽⣿⣽⡿⣯⣷⣿⣟⣿⣟⣿⣻⢿⡿⣯⣿⣻⣷⣕⢕⢕⢯⢮⡳⡱⡱⣝⢮⡳⡹⡜⣞⢎⡇⡇
                ⡽⣝⢮⢯⢞⡮⡯⣫⢞⣼⡪⣗⡽⡺⡵⣝⢮⡳⡕⡵⣿⣾⢿⣿⣽⣟⣯⢗⡝⣎⡾⣾⣿⣾⡿⣿⣻⡾⡳⣹⣽⣼⣿⢿⣻⣽⣿⣟⣿⣻⣿⣻⣷⣿⣾⣿⡿⣷⣷⣳⣟⣮⢺⢿⣟⣯⣷⣿⢿⣟⣿⣾⣟⣯⣿⡲⡱⡱⡻⣟⣯⣿⢿⡾⡸⣨⡳⡣⡣⣣⡫⡮⡳⣝⢎⢗⢵⡫⣎⢎
                ⢺⢵⣫⢯⡳⡽⣺⢝⣵⡳⣝⣞⢮⢯⡳⣝⢵⡫⣞⢜⣿⣽⣿⡳⣻⣽⣿⣳⣽⣞⣿⢿⣾⢷⣿⡻⡝⢕⢱⣳⢿⣷⣿⣿⣿⣟⣯⣿⣟⣯⣿⢿⣽⣾⣿⣾⢿⣿⣽⣟⣿⢷⡣⡛⣟⡿⣯⣿⣟⣿⢿⣾⣟⣿⣽⣗⣧⢧⣫⣿⣟⣿⢿⢝⢜⡲⡹⡸⡸⣸⣪⡳⣝⢎⢧⢫⡣⡯⢮⡳
                ⣝⢗⣗⢽⡺⣝⣞⢽⣪⢾⢕⣗⡽⡵⣝⢮⡳⣝⡮⣳⢪⢿⣾⣪⣿⣽⣾⡿⣿⣽⢿⣻⢽⢫⠪⢪⣸⣾⣿⣾⣗⣗⡽⣺⢿⣽⣿⣯⣿⣟⣿⢿⣻⣽⣾⡿⣟⣯⣿⢯⢟⢯⣳⢬⡨⢫⢻⣺⡽⣿⣻⣷⣿⣻⣽⣿⣽⣿⣻⣽⣯⡿⡝⡕⡵⣹⢕⢕⢜⢮⡲⣝⢼⡹⡕⡇⡗⣝⢵⢝
                ⢮⣳⢽⢵⡻⣺⣪⢯⣺⢵⣻⡪⣞⢽⡪⣗⢯⡳⡽⣺⢪⢳⡻⡽⡯⣟⢗⢟⠗⠝⠕⠡⠁⠠⠀⠡⠑⠩⠫⢻⢿⢿⣿⣯⣷⢝⣷⢿⣷⡿⣟⣿⣟⣯⣷⡿⣟⣝⣮⣯⣿⣿⢿⢻⠛⠆⠂⡈⠊⠓⠝⢞⢳⢟⢿⢞⡿⣞⢿⢽⢗⢯⢣⢳⡹⡪⣇⢇⢕⢕⢧⢳⢳⡹⡪⡣⡣⡳⡝⡮
                ⡹⣮⡳⡯⣞⣗⢗⡯⣞⣗⢗⡽⣝⡵⣻⡪⣗⢯⡻⡪⣇⢗⢕⣕⣕⣜⢌⠐⠈⠀⠠⠠⢀⠐⢄⢂⠄⡁⠄⠐⠈⡉⢟⢿⣿⣿⣮⡻⣽⣿⢿⣻⣽⣯⢗⢯⣾⣾⣿⢻⠹⠈⠂⠀⠄⠁⠄⡀⠂⢁⠀⠂⠀⠌⡢⣣⣱⣡⡡⣡⢵⣕⢕⡳⣹⢹⢜⡎⡆⡇⡕⢝⢜⢪⠪⡢⡣⡳⣹⢺
                ⣜⣗⡽⣽⡺⣮⣻⡺⣵⡳⡯⣻⣪⢟⡮⡯⣞⣗⡽⡝⡎⣇⣿⣾⢟⠊⠐⢀⠡⡨⠈⠌⠂⠅⠁⠠⠑⠐⢡⢡⢑⢀⠀⡉⢟⣿⣿⣽⢝⣾⣿⣿⣻⡪⣳⣿⡿⡫⠊⠀⠠⢀⢅⠢⡈⠜⠐⠨⢊⠆⡆⡡⢐⢀⠈⠻⡿⣿⣽⣮⢳⡳⡕⣝⢼⢕⢗⣝⢮⡪⣪⢪⢪⢸⢸⡸⡜⡮⡳⡝
                ⣳⡳⣽⣺⣺⢵⡳⣯⡳⡯⣯⣳⣝⢷⢽⢝⣞⢮⣞⡝⣜⣾⡿⡝⠐⢈⢐⠔⡀⠁⠁⠀⠀⠀⠀⢀⢰⣨⣆⡇⡇⠅⡂⠄⠐⢙⣿⣿⣯⢮⢷⡿⣕⣽⣿⢯⠡⠀⡂⠅⠕⡐⠀⠁⠀⠀   ⠀⠀⡀⢌⡐⡜⢔⢐⠄⡁⠊⢿⣿⣿⣗⢍⢎⢞⢎⣗⢽⢜⢮⢺⡪⣺⢪⡳⣕⢗⣝⢮⡫
                ⢼⢽⣺⣺⣺⢽⢽⢮⢯⢯⢞⣞⢮⢯⣫⢷⢽⢵⡳⡱⣵⣿⠯⠨⢐⠠⢑⠐⠀⠀⠀⠀⠀⠀⠀⠢⣳⣿⣿⣯⢎  ⠂⠪⡊⠄⢀⢫⣿⣿⣯⡫⣏⢾⣿⢏⠂⡀⠢⢪⠨⠀⠀⠀⠀⠀    ⠀⢂⢮⣷⣿⣞⢔⠅⢕⠄⠂⢘⢺⣿⣿⣧⡣⡫⡧⡳⡳⣝⢮⡳⣝⢮⡳⣝⢮⡳⣕⢗⣝
                ⣝⣗⣗⢷⢽⢽⢽⢽⢝⡽⣝⢾⢝⡷⡽⡵⣫⢗⡝⣜⣷⣟⠅⠨⢐⢌⢂⠀⠀⠀⠀⠀⠀⠀⠀⠁⡓⡟⡿⡺  ⠡⠈⠀⢂⠡⠀⠂⣟⣿⣷⠣⡱⣻⣟⠐⠀⠄⡣⡅⠀⠀⠀⠀⠀⠀    ⠀⠀⡑⡽⣿⡿⣯⠣⠈⠠⡨⡠⢂⠩⣻⣿⣷⣇⢏⢮⡫⣞⢮⢳⡹⣜⢞⢞⣎⢗⣝⢮⡳⡳
                ⢼⣺⣪⢯⢯⡳⡯⣫⢯⢯⢞⡽⣳⢽⡺⡽⣕⢯⡪⣺⣿⢎⠆⢈⢐⢕⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠈⠀  ⠀⠀⠀⠢⠣⡁⡁⣞⣿⣿⢅⢽⡽⣎⠂⠠⡑⢌⠄⠀⠀⠀⠀⠀⠀⠀      ⠀⠈⠊⠪⠁⠁⠀⠠⢑⠕⡁⠄⢯⣿⣿⡾⡸⡵⣹⡪⣞⢵⢝⢮⡫⡧⡳⣝⢮⡣⡯⣝
                ⢞⡞⣮⡫⣗⡽⣝⣞⢽⡺⣝⣞⢵⡳⣝⢮⡳⣳⢱⣹⣾⡳⡁⡐⡔⡗⠔⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀  ⠡⡁⠄⢐⢼⣿⡿⡨⣺⢽⡕⡀⠅⡪⡪⡂⠀⠀⠀⠀⠀⠀⠀⠀       ⠀⠀⠀⠀⠀⠀⡢⠡⠂⠠⣹⣿⣿⡏⡮⡺⣜⢞⢼⢕⣏⢧⡳⣝⢽⡸⡵⣹⣪⡺⣪
                ⣎⢯⡺⣪⡳⣝⢮⢮⡳⣝⢮⢮⡳⣝⢮⡳⣝⢮⠢⡳⣿⣳⡐⠌⢎⢍⣎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀  ⠀⡐⡕⠔⠀⢬⣿⣿⡏⠔⡵⡯⣗⠄⠂⡊⡎⡆⠀⠀⠀⠀⠀⠀⠀⠀       ⠀⠀⠀⠀⠀⠀⠐⠜⠅⠡⠨⣾⣿⣿⡪⣪⢳⢕⡏⣗⡕⡧⣳⢹⢜⢮⢎⡗⡵⡪⡞
                ⡸⣕⢽⢜⢮⢺⡪⣣⡳⡕⣗⢕⢧⡳⡕⡧⣳⢹⢜⢸⣻⣞⣜⡈⡀⢣⠳⡡⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢀⢀⢂⠌⡪⠐⢈⣾⣿⡿⠨⡊⠮⡯⣗⡕⡐⠨⡸⣰⠠⠀⠀⠀⠀⠀⠀    ⠀⠀⠀⠂⠀⠀⠐⢈⢜⢌⠌⠠⣹⣿⣿⣳⢱⡱⡝⣎⢞⡜⣎⢧⢳⡹⡪⣣⢳⡹⡪⣫⢺
                ⡧⡳⣕⢝⡎⡧⡫⡮⣪⡫⢮⢝⢮⣚⢎⢧⢳⡹⡜⡔⢹⢷⣗⢧⡂⠄⠅⢣⢮⣆⡂⡀⠀⠀⠀⠀⠄⢀⢐⡄⢔⠹⢌⠂⢌⣮⣿⣟⢊⢰⣪⡘⢽⣳⣝⢆⠅⢊⢪⠝⡄⡄⠀⠀   ⠀⠀⢀⠀⠀⠀⠀⡀⢜⡼⡪⠂⡂⢵⣿⣿⣟⢜⢜⡜⡼⡸⣪⢺⡸⡪⡣⣣⢫⡪⣣⢳⢹⢜⢎⢇
                ⢪⡺⡜⣕⢝⡎⣗⢝⢼⡸⣕⡳⡕⣎⢧⡫⢮⢺⢜⢎⡪⢹⢯⣷⡳⣅⢂⠅⡓⢧⢳⠕⡜⣜⡬⡢⡪⡒⡵⡫⠣⠐⡀⣮⣷⣿⠫⡂⡂⣝⣿⡢⠡⢻⣺⣳⢵⢐⠐⠡⡃⠧⡧⡣⡠⡨⣀⢄⣔⢄⢕⢆⠕⡝⠄⢅⣮⣿⡿⡏⠆⡇⡧⡳⡱⡝⣜⢎⢮⢪⡣⡳⡱⡕⡵⡱⡣⡳⡹⣸
                ⢸⢪⡺⡪⣣⢫⡪⣎⢧⢳⢱⢕⡝⡼⡸⡜⣕⢇⢯⢪⢎⢦⢙⢗⢿⣮⣳⣢⢪⢠⠡⢁⢑⠑⡑⠱⢡⠡⢐⢈⢌⣼⣾⡿⡏⢇⢕⡪⡂⣯⣿⡪⡪⡪⡺⢽⡯⣗⣕⢔⢈⠘⠌⢇⢊⠎⡪⢫⠺⠑⠄⡁⡂⡢⣕⣯⡿⡯⡏⡪⡸⡪⡺⡸⣱⢹⡸⡜⡎⡇⡇⣏⢮⢪⢎⢞⢜⡕⣝⢼
                ⢎⢇⢧⢫⡪⡎⣞⢜⡜⡎⡧⡣⡳⡱⡣⡫⣪⢺⢸⡱⡕⣕⢕⡐⡕⢕⢟⢾⢽⡵⣯⢮⢦⣳⢼⣜⣶⡵⣗⣯⡿⡟⡝⡌⣜⢔⡕⡕⢜⢼⣿⡪⣪⡳⣱⢑⢝⠽⢾⣽⣞⣮⣪⡢⣂⢆⣐⢄⡢⣡⣲⣲⣽⡽⡯⡗⢏⡪⡮⡇⡇⣏⠮⡺⡸⣸⢸⡸⡸⡜⡎⡎⡎⣎⢮⢪⢣⢣⡣⡣
                ⡕⣝⢜⢎⢮⢺⢸⢜⡜⣎⢞⢜⡕⣝⢜⢵⢱⢕⢇⡇⣏⢎⢆⢇⢽⡸⣔⢅⢇⢫⠹⡙⡏⠯⡻⡹⡺⡫⡫⢣⢣⢣⢣⣣⡳⣕⢇⢏⢜⣺⣿⣣⣳⣽⣪⢗⢵⢱⢱⢘⠜⡕⢯⢻⡽⣻⢞⣯⢟⢷⢻⢚⢇⢫⢪⣜⣮⡿⡯⡪⡪⣪⢣⢳⢹⢸⢸⢸⢸⢸⢸⡸⡪⡪⡪⡪⡎⡇⡇⡏
                ⡣⣣⢳⢹⡸⡪⡣⡣⡇⡧⡳⡱⡕⡵⡹⡸⡪⣪⢣⡣⡣⡇⡇⡇⡧⡳⣕⢝⡕⡗⣝⡜⣎⢧⡣⡧⣣⢣⢣⢣⢣⣣⡳⡵⣟⣗⡝⡜⡔⣽⣟⡮⣺⣾⣯⣯⣗⣝⢮⡪⡮⣪⢪⡢⡣⣅⢇⢧⡱⣌⢦⡲⡵⣝⣷⣯⢿⡯⡣⡣⡫⡪⡪⡣⡣⡣⡣⡣⡣⡣⡇⡗⡝⣎⢧⢳⢱⢱⢕⢕
                ⢜⢼⢸⢱⢱⢕⢝⡜⡎⡮⡪⡎⡞⡜⡎⡇⡏⡎⣎⠮⡺⡸⡸⡘⣾⡽⣮⣗⡽⣮⡳⣝⣮⣳⢽⣺⡵⣯⢾⣽⣵⡷⣟⣿⡽⣞⢎⢇⢪⣺⣿⡝⣞⣿⣾⢷⣿⣯⣷⡯⣯⣞⣵⣫⢯⣞⡽⣕⢯⡾⣽⣽⣯⣿⣽⣾⣿⡳⡱⡱⡱⡹⡸⡱⡹⡸⡸⡪⡪⡺⡸⡕⡽⡸⡜⡎⡮⡪⡪⡪
                ⢇⢧⢳⢹⢸⡸⡱⡱⡕⡇⡧⡳⡱⡕⡇⡏⡮⡺⡸⡜⡎⡞⡜⣜⣾⣟⣿⢾⣻⡷⣿⢿⡾⣯⣿⢷⣿⣻⡿⣷⣟⣿⢯⣷⢿⢝⠪⢑⢡⣻⣷⢯⡺⡿⣾⣿⣷⡿⣷⣿⣿⣽⣾⡾⣯⢾⣝⢮⢫⣟⣿⢾⡷⣿⢷⣿⣯⣗⢕⢕⢝⢜⢕⢵⢱⢕⢇⢗⢝⢜⢵⢹⢜⢕⡕⡇⡧⡣⣣⢣
                ⢸⢪⢪⢪⢣⢣⢫⢪⢪⢪⢪⢪⢪⢪⢪⡪⡎⡎⡎⡎⡮⡪⡪⣺⢾⣿⣽⡿⣕⢝⡽⣿⢿⣟⣿⣟⣯⣿⣻⣯⣿⣽⢿⢝⢌⡆⡎⢆⢢⣻⡿⡧⣑⢍⢏⢷⣿⡿⣟⣷⣿⣯⣷⡿⡯⣟⢮⡣⡇⡗⣟⣿⣻⡿⣟⣿⣾⡇⡇⡇⡇⡧⡳⡱⡱⡱⣱⢱⢕⡝⣜⢕⢵⢱⢱⢱⡱⡹⡸⡸
                ⢱⢱⢱⢱⢱⢱⢱⢱⢱⢱⢱⢱⢱⢱⢱⢱⠱⡱⡱⡱⡱⡱⡱⡹⣻⣯⣷⣿⣿⣻⣾⡿⣟⣿⣽⣟⣯⣿⣽⡷⣿⢾⢹⡸⡮⣳⢱⢡⠢⣿⢿⣏⢎⣗⢵⡱⡹⣿⡿⣿⣯⣿⡽⡯⡿⡽⣕⢕⣇⢏⢞⣽⡟⡎⣳⣻⢷⢹⢘⢜⢜⢜⢜⢜⢎⢇⢇⢧⢣⢳⢱⢱⢱⢱⢱⢱⢱⢱⡱⡹
                ⡇⡇⡇⡇⡇⡇⢇⢣⢣⠣⡣⡣⡣⡣⡣⡱⡱⡱⡱⡑⡕⢕⢜⢌⠯⣿⢿⣽⣾⡿⣯⣿⢿⣿⣽⣟⣯⣿⡾⣿⣻⢕⢵⡿⣽⡺⡜⡜⢜⣿⢿⣗⢽⣯⣿⢾⡜⡾⣿⡿⣯⣷⢟⣽⣟⡽⡜⡜⣼⢷⡕⣽⣽⡸⣔⡿⡹⡨⡪⡪⡪⢕⢕⢵⢱⡹⡜⡎⡎⡇⡇⡇⡇⡗⡕⡕⡇⡇⡇⡇
                ⢕⢕⢕⢕⢕⢕⢕⢕⢕⢕⢕⢱⢸⢰⢱⢑⢕⢕⢜⢜⢜⢜⢔⠱⡱⣹⣿⣿⣽⢝⢎⢎⡓⡗⣯⢿⣯⣿⣻⣟⣗⢽⣽⣟⣷⣫⢺⢸⢸⣻⣿⣗⢽⣿⣾⣿⣯⡺⣟⢿⣻⣽⣝⡾⣞⣞⢮⢚⢾⡻⣕⣽⣾⣯⣷⡫⣢⢪⠸⡨⡪⡪⡪⡪⡪⡪⡪⡪⡪⡪⡪⡪⡪⡪⡪⡪⡎⡮⡪⡪
                ⡕⢕⢱⢡⢣⢪⢪⢢⢣⢪⠪⡊⡎⡆⢇⢣⢣⢪⢢⢣⢪⠢⡣⣕⣯⡺⣷⣿⣾⣵⢱⢱⢸⣘⣞⣿⣻⣾⢏⢞⣷⡽⣺⢽⣷⡳⡣⡣⡹⣿⣽⡾⣝⣷⣿⡪⡷⡿⡏⡯⣿⣳⡳⣻⣻⢮⡺⡜⡜⣜⣴⡷⣿⣷⡷⣝⣽⣧⠡⢣⢱⢸⢸⢸⢸⢸⢸⢸⢸⢸⢸⢸⢸⢪⢪⢪⡪⡎⡮⡊
                ⡕⡕⡕⡕⡕⢕⢅⢇⢕⢅⢇⠇⡇⡎⡎⢎⢆⢇⢕⢅⢇⢣⣳⣿⣗⡯⣻⢾⡷⡿⡯⡷⣳⢷⣟⣯⣿⣽⢿⡜⡜⡍⣞⣯⢷⢯⡪⡊⣞⣿⣟⣯⢯⣿⣽⣷⢭⣫⣮⡿⣟⣗⢯⡳⡯⣻⢽⡽⣞⣾⣾⢿⣻⣾⣟⢮⣾⣿⡘⡌⡎⡎⡮⡪⡪⡪⢪⢊⢎⢪⠪⡪⠪⡘⢜⢜⢜⢼⢸⠌
                ⢣⢱⢑⢕⢜⢜⠜⡌⡆⡇⡕⢕⢱⢘⠜⡌⡆⢇⢎⢪⠪⣒⣿⣽⣯⣿⢜⢵⣝⣮⢾⣝⣮⣳⣹⡪⣳⡹⡱⡹⣼⡼⣼⣜⣜⡕⢌⠂⣷⡿⣿⣽⢱⡱⣫⢞⢯⢳⢳⣹⡪⣪⣣⣳⢹⡪⡯⡻⡝⣝⢞⡻⡫⣟⢮⣳⣿⣿⢎⢜⢜⢜⢜⠜⡌⢎⢪⠪⡸⡘⡜⢬⢑⠐⡕⡕⣝⢜⢕⢕
                ⡪⡊⡎⡪⡸⡰⡱⢱⠸⡰⡑⡕⢅⢣⠱⡑⡜⢜⢌⢎⢎⢆⡓⢍⢊⢊⠕⣽⣿⣿⣿⣿⣷⣿⣾⣿⣷⣿⣷⣟⣮⣟⡽⣯⣷⡻⡜⡜⣼⣿⣿⣯⡳⣽⢮⢿⣽⣟⣟⣞⣾⣷⣿⣿⣷⣟⣾⣾⣽⣾⣿⢾⡵⡡⢓⠻⡹⢻⢑⢅⢇⢣⢑⠕⢜⢸⠰⡱⡑⡅⡇⡇⠥⡨⢂⠇⡇⡇⡇⢇
                ⡆⡣⡱⡑⡕⢜⢌⢎⢪⠢⡣⢪⢊⢎⠪⡪⢸⢘⢜⢜⢌⠆⡎⢌⢂⢪⡪⡚⠟⡝⢟⢻⠻⡻⡻⡻⢟⢿⢻⢿⡻⡿⢾⢮⢮⢞⡝⢜⣽⣿⣾⡷⡝⣿⣿⢵⢽⣺⣾⣿⣿⡿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣯⡪⣎⢪⢸⢨⢪⠪⡸⡐⡅⡣⠣⡑⡅⢇⠎⡎⡂⡊⢌⢐⠅⢕⢕⢕⠕⡅
                ⡸⡐⢕⢜⢸⢨⢢⢣⢱⢑⢅⢇⢪⠢⡣⡱⡑⡕⢕⢜⠰⡑⢌⠢⡂⡳⡳⠠⢑⠈⠐⠀⠁⠐⠀⠈⠢⡑⠌⠐⠐⠈⠐⠁⠁⡁⠂⢡⣻⣾⣿⣽⠨⠐⠈⠌⠨⠈⠢⢑⠌⡊⠜⢈⠊⢘⠘⠘⢘⠘⠌⡃⡣⡹⡜⢔⠱⡑⡅⢕⢌⠢⢪⠨⠪⡘⡌⡆⡇⡇⡕⠠⡂⢆⠕⡡⡱⡡⡃⢎
                ⡆⡕⡱⡨⡢⢣⢱⢱⢘⢌⢆⢕⢜⢌⢆⢣⢱⢑⠱⡐⠕⢌⠢⡑⠔⢭⡳⠡⠡⡂⡂⡂⡢⢐⠠⡁⢅⠪⡨⢐⢀⠄⡠⢀⠄⡀⡀⢰⣻⣿⣾⣟⠔⡀⠄⡠⢀⠐⡈⢔⠨⡐⡀⡀⢀⠀⡀⢀⠀⡀⡑⢔⢐⢕⣏⢪⠪⡨⢌⠢⡢⠣⡑⢅⢣⢱⠸⣘⢜⢜⢌⠌⠜⢄⠕⡐⡕⢜⠨⡂
                ⣕⡧⣗⡵⣕⢷⢵⣳⣝⣞⡮⡾⣜⡮⣞⡼⣜⢼⡸⣸⡸⡬⣪⡪⣊⠮⡮⠨⡊⢔⢌⠢⡊⡢⡱⢨⠢⡱⢨⠢⢢⢑⢌⠢⡑⢔⢐⢐⢹⢻⢾⢣⢑⢐⡑⠔⡅⡪⡐⡅⠕⢌⠢⡊⠔⢅⠪⡐⢅⢆⠪⢌⢂⢗⡕⢌⢊⢢⠡⡑⢌⠪⡘⡌⢆⢣⢃⢇⢕⢕⢕⠨⠨⡂⡅⡣⡱⠡⡃⡊
                ⢗⡯⣗⡯⡯⣯⣻⣺⡺⡮⣯⣻⣺⢽⣮⡻⣮⡳⣽⡺⣪⢟⡮⡯⡎⢮⢎⠆⡊⡂⡪⡘⢌⠢⡑⠅⠕⢜⠰⠡⢃⠪⡐⢅⠣⡑⡐⠔⡠⢑⠌⡢⢡⠱⢨⠱⠨⡢⠱⡘⡘⢌⠪⠨⡊⡪⡘⢌⠢⡢⡩⡊⡂⣗⢕⢕⣕⢆⣇⢎⢦⡣⣕⢜⡜⣔⢕⣕⢕⡕⡥⡈⡢⢑⢌⢆⢣⢑⢌⢢
                ⢽⢽⢵⣫⢟⣞⣞⡮⣯⢯⣗⣷⢯⣿⣺⡽⡮⡯⣞⣞⡽⣳⢽⢽⢸⣪⡿⣜⢔⢵⢮⡲⡠⠡⡂⢅⢑⢐⠨⠠⡁⡂⡂⠅⣎⣖⡮⣮⣢⢵⢬⣢⢆⣎⢦⣪⡪⡄⠅⡂⠌⡐⡈⠌⡐⡐⢨⢠⢅⡆⣆⢪⢠⣳⡣⣫⢾⢝⡮⣯⣳⣝⡮⣗⣟⢾⣝⣞⣗⣟⣞⢮⣺⣜⢮⡳⣣⡳⣕⢗
                ⣽⣯⢿⣾⣻⡷⣯⣿⣻⣿⣯⣿⣿⣻⡾⣽⣽⡽⣾⣺⡽⡽⣽⡹⡼⣺⢽⣣⢣⢯⣟⢎⡐⡡⢊⢐⠔⡡⡊⡪⠰⡰⠨⠌⠮⡾⣽⣳⣯⢿⣽⣞⣿⣺⣻⣺⡕⡅⠕⡨⠨⡂⢎⠌⢆⢊⠢⣫⣗⣟⣮⢣⣳⡳⣏⠮⡯⣯⣻⣺⣺⣺⣺⣳⢽⣳⣳⣳⣳⣳⢽⣝⣞⢮⣳⢝⣞⢮⡳⣝
                ⣺⣿⣟⣯⣿⣟⣿⣽⣟⣷⣿⣻⣾⣿⢿⡿⣾⣿⣻⣽⡿⣽⣳⢕⣝⢧⢓⡗⣕⣿⢾⣳⣵⢮⣖⣖⢔⢔⣴⡨⣬⣢⢥⣅⡵⣽⣻⢾⣽⢯⣷⣟⣾⣳⣯⢷⣻⣼⢼⣴⣑⢌⡆⣅⣕⡬⣢⣗⣗⣟⡮⣇⢷⡹⣪⡫⣯⢷⣳⣗⣷⣳⣗⣯⣟⣗⣟⡾⣺⢞⣗⢷⢝⣗⡽⡵⣳⢽⢝⣞
                ⣽⣷⡿⣯⣷⡿⣷⢿⣽⣯⣿⣻⣯⣿⣿⢿⣻⣾⣟⣷⣿⣻⡾⣕⢮⢪⡣⡹⡸⣾⢿⣻⣽⣿⣯⣿⡱⣫⡷⡝⣾⣽⣯⣷⢿⣻⣾⢿⣽⣟⣷⢿⡷⣿⢾⣟⡿⣾⣻⣗⡇⣗⢿⢜⣾⣻⣽⣾⣻⣾⢽⡪⡺⡸⡵⣝⡾⣯⡷⣟⣾⣞⣾⣳⣯⡷⣯⢿⢽⣽⣺⢽⢽⣺⡺⡽⣵⣫⢯⣞
                ⣺⣯⣿⣟⣷⡿⣟⣿⢿⡾⣯⣿⣯⣿⣾⣿⢿⣯⡿⣷⣟⣷⣿⣻⢼⢸⢪⢮⣾⣿⣿⣿⣿⣯⣷⣿⡪⣞⣮⣳⣽⣾⣷⢿⣿⣯⣿⡿⣟⣿⣽⣿⡿⣿⣟⣯⣿⢷⣟⡷⡕⡵⣝⢽⣽⣻⣾⡷⣿⣽⡿⣮⢸⢸⣝⢾⣻⣽⣿⣻⣷⢿⣞⣿⣺⣯⢿⣽⣻⣺⣞⣯⣟⣮⢯⣟⣞⣞⢷⡽
                ⣽⣿⢾⣯⣷⣿⣿⣻⣿⣻⡿⣷⢿⣷⣿⣻⣿⢷⣿⢿⣽⣿⣽⡿⣧⡣⡃⣗⣿⣾⡿⣷⣿⢿⣻⡿⡜⣴⣳⢼⢾⣿⣾⣿⣷⣿⣾⣿⣿⣿⡿⣯⣿⣿⣻⣿⣻⣿⣻⡯⡇⣧⣣⣻⣟⣿⣷⣿⣿⣿⣿⣗⢕⠵⣝⣿⣿⣯⡿⣯⣿⣻⣽⣯⣿⢾⣟⣷⣻⣞⣷⢷⣻⣞⣿⣺⣗⣯⡿⣽
                ⣺⣿⣻⣯⣷⣿⣽⣿⣽⣟⣿⣟⣿⣷⡿⣟⣿⣟⣿⣟⣿⡾⣯⣿⣿⡾⣾⣿⡿⣿⣿⣿⢿⣿⣿⣿⡊⣮⣮⡫⣿⣟⣿⣽⣯⣿⣿⣽⣿⡷⣿⣿⣿⣻⣯⣿⣿⣻⣽⡿⣕⡵⣕⣵⣿⣿⣟⣿⣿⣽⣾⣷⣷⣯⣾⣾⣿⣾⣿⣻⣾⣻⣽⣷⣟⣿⣽⡾⣯⡿⣾⣟⣿⣺⣟⣾⡽⣾⣻⣽
                ⣺⣿⣽⣯⣷⡿⣷⡿⣾⣿⣽⣯⣷⢿⣻⣿⣿⣻⣯⣿⣽⢿⣻⣽⣾⣿⣟⣷⣿⣿⣿⣾⣿⣿⣿⣷⡝⣾⣟⡮⣿⣿⢿⣿⣟⣯⣷⣿⣷⣿⣿⣿⣽⣿⡿⣟⣿⣿⣿⡿⣕⣿⡷⣽⣿⣯⣿⣿⣟⣯⣷⣿⢿⣽⢟⡯⣿⣷⡿⣿⡾⣟⣯⣷⣟⣷⣯⢿⡯⣟⣷⣟⣾⣳⢷⣳⢿⡽⣾⢽
                ⣺⣯⣿⣽⣾⣿⣻⣟⣿⡾⣿⡾⣟⣿⣟⣿⣾⡿⣿⣽⣾⣿⢿⣿⣽⣾⣿⣻⢿⠽⡪⢯⣿⢿⡷⡻⡙⡾⣟⢮⢻⣟⣿⣿⣯⣿⣿⢿⣾⢷⡿⣾⣿⣯⣿⣿⣿⣿⢿⠯⡳⣿⡣⠻⡯⣿⣿⣿⣿⣿⣿⣿⣽⣯⡷⣽⣿⣷⣿⣿⣟⣿⣻⣽⣯⣷⣟⣯⣿⣻⢷⣻⣾⣺⡯⣿⢽⣫⢯⢿
                ⣾⣯⣿⣽⣷⢿⣯⣿⣽⣟⣯⣿⣟⣯⣿⣽⣷⣿⢿⣯⣿⣾⡿⣿⣞⣯⡿⡉⠂⢡⢢⠂⠙⢏⠃⡀⠐⠝⡟⠐⡑⣽⡿⣾⣯⣷⢿⣽⡿⣿⢽⣿⣟⣿⣿⢿⣿⣞⠏⠀⠪⢿⠂⠂⠨⣻⡿⡳⠫⡛⢞⢯⢿⣷⣻⣯⣷⣿⣯⣷⣿⢯⣿⣳⡿⣾⣯⡷⣿⢽⣟⣯⣷⢿⡽⣯⣟⡷⣟⣯
                ⡾⣷⡿⣷⡿⣿⣽⣾⡷⣿⣻⡷⣿⣽⣾⢷⣿⡾⣿⣻⣾⣷⣿⣟⣿⣯⡯⡀⠀⠀⡁⠂⠈⠀⡀⠠⠐⠀⠄⠂⢨⡺⡽⣫⢾⣻⢟⡯⣟⣟⢿⢽⣻⣻⢽⣟⢿⡪⠀⠂⠈⠠⠈⡐⢈⢘⠨⢐⠱⢵⢇⠣⣿⣿⣿⣿⢿⣷⣿⣿⣽⣿⣽⣯⣿⣻⣾⣻⣽⣻⣽⣻⢾⣻⣽⢷⣯⣟⣗⣯
                ⢾⣿⢿⣻⣿⣯⣷⣿⣻⣿⣽⣿⣻⣾⣿⣻⣷⣿⣿⣿⢿⣾⣿⣽⣟⡾⣝⢔⠠⡀⡀⡐⠈⡀⡠⣂⠄⡂⡀⠌⣆⢯⡺⣕⢯⣪⡳⣝⢮⡪⡮⡳⣕⡕⡧⡳⡱⡕⡁⠄⠁⡐⢀⢂⠔⡐⠡⠢⡑⡡⢡⢑⡵⣿⢿⣾⣿⣿⣿⣽⣿⣾⢿⣾⣿⣽⣾⣯⣷⣷⣟⣾⢿⣻⣽⣿⢾⣯⣿⣽
                """);
        System.exit(0);
    }
}

// 4:15AM -- all the scrapped ideas because of no time
/*
 * ideas:
 *
 * wd40 movement easter egg
 * florp bossfight easter egg
 *
 *  - item that displays enemy stats in the menu
 *  - boss room, big room with big enemy !?!??!
 *  - ascii text art side menu beside the room generation that displays inventory, health, items, etc. **implementing now!
 *  - different enemy types that have weapons?
 *  - types of rooms
 *    - shop room that holds item that the player can buy, maybe even kill the shopkeeper like spelunky
 *    - upgrade room that improves stats like health or weapon damage
 *  - ascii art of character interaction?
 *  - timed levels?
 *  - ^^ only a certain number of moves to accentuate strategy
 *  - chatgpt if you can get it to work
 *  - !!! health and shield? xp? items that are collected from treasure, or separate items altogether
 * */