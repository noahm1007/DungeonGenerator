import java.io.StringBufferInputStream;
import java.sql.PreparedStatement;
import java.util.*;
import java.time.Clock;

public class DungeonGenerator {
    static Random rd = new Random();
    public static void main(String[] args) throws InterruptedException {
//        https://www.coolgenerator.com/ascii-text-generator
        Scanner input = new Scanner(System.in);
        Clock clock = Clock.systemDefaultZone();
        Floor floor = new Floor();
        Options options = new Options();

        String[] title = new String[]{
                "██▓███   ██▓    ▄▄▄       ▄████▄  ▓█████  ██░ ██  ▒█████   ██▓    ▓█████▄ ▓█████  ██▀███   ",
                "▓██░  ██▒▓██▒   ▒████▄    ▒██▀ ▀█  ▓█   ▀ ▓██░ ██▒▒██▒  ██▒▓██▒    ▒██▀ ██▌▓█   ▀ ▓██ ▒ ██▒",
                "▓██░ ██▓▒▒██░   ▒██  ▀█▄  ▒▓█    ▄ ▒███   ▒██▀▀██░▒██░  ██▒▒██░    ░██   █▌▒███   ▓██ ░▄█ ▒",
                "▒██▄█▓▒ ▒▒██░   ░██▄▄▄▄██ ▒▓▓▄ ▄██▒▒▓█  ▄ ░▓█ ░██ ▒██   ██░▒██░    ░▓█▄   ▌▒▓█  ▄ ▒██▀▀█▄  ",
                "▒██▒ ░  ░░██████▒▓█   ▓██▒▒ ▓███▀ ░░▒████▒░▓█▒░██▓░ ████▓▒░░██████▒░▒████▓ ░▒████▒░██▓ ▒██▒",
                "▒▓▒░ ░  ░░ ▒░▓  ░▒▒   ▓▒█░░ ░▒ ▒  ░░░ ▒░ ░ ▒ ░░▒░▒░ ▒░▒░▒░ ░ ▒░▓  ░ ▒▒▓  ▒ ░░ ▒░ ░░ ▒▓ ░▒▓░",
                "░▒ ░     ░ ░ ▒  ░ ▒   ▒▒ ░  ░  ▒    ░ ░  ░ ▒ ░▒░ ░  ░ ▒ ▒░ ░ ░ ▒  ░ ░ ▒  ▒  ░ ░  ░  ░▒ ░ ▒░",
                "░░         ░ ░    ░   ▒   ░           ░    ░  ░░ ░░ ░ ░ ▒    ░ ░    ░ ░  ░    ░     ░░   ░ ",
                "             ░  ░     ░  ░░ ░         ░  ░ ░  ░  ░    ░ ░      ░  ░   ░       ░  ░   ░     ",
                "                          ░                                         ░                      "
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

//        typewriterPrint(title, 250);
//        typewriterPrint(openingImage, 250);
//        typewriterPrint("hey, you, you're finally awake.", 200);

//        calibrateTerminal();
//        input.next();

        ArrayList<Item> lootTable = new ArrayList<>();
        lootTable.add(new Item(true, false, true, "Small Health Potion", "A bubbling blood red liquid contained in a small vile. Restores 25HP.", 90));
        lootTable.add(new Weapon(false, true, false, false, "Wooden Stick", "A short wooden stick. Looks like a sword.", 25, 10, 6, 2, 5, 17));
        lootTable.add(new Weapon(false, true, false, false, "Recursive Sword", "A spring-shaped sword. Hits multiple times, depending on a recursive algorithm.", 2, 7, 13, 1.5, 1.8, 14));
        lootTable.add(new Weapon(false, true, false, false, "For-each Blaster", "A metallic blaster. Attacks once for each item in your inventory.", 12, 6, 10, 1.4, 1, 8));
        lootTable.add(new Item(false, true, false, "Random Ring", "A magic ring. Subtracts a random number of damage from each hit you take.", 11));
        lootTable.add(new Item(true, false, true, "Big Health Potion", "Looks like fruit punch, does NOT taste like fruit punch. Restores 50HP", 20));
        lootTable.add(new Item(false, true, false, "Random Shield", "Looks like a shield. Obviously. Has a random chance to deflect an attack. What chance? IDK.", 15));
        lootTable.add(new Weapon(false, true , false, false, "Iron Stick", "Better than the Wooden Stick, probably on account of it being metal.", 24, 13, 12, 2.5, 3, 11));
        lootTable.add(new Weapon(true, false, false, false, "while(true) {} bow", "Does 1 damage, forever. Unless it throws an exception. Breaks when used.", 1, 1, 0, 1, 0.1, 33));
        lootTable.add(new Item(true, false, true, "try catch block", "If your next attack misses, catch and retry it.", 19));

        floor.lootTable = lootTable;
        floor.generateFloor();
        floor.printFloor(true, false);

        int page = 0;
        int selectedItem = -1;

        while (true) {
            inputHandler(input.next(), floor, options, page, selectedItem);
            page = floor.options.page;
            selectedItem = floor.options.selectedItem;
        }
    }

    public static void inputHandler(String input, Floor floor, Options options, int page, int selectedItem) {
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

                    Fight fight = new Fight(floor.player.currentRoom.enemies.get(Integer.parseInt(input)-1), floor.player);
                    boolean inFight = true;
                    Scanner sl1 = new Scanner(System.in);

                    while (inFight) {
                        fight.constructFightWindow();
                        fight.printFightWindow();
                        String choice = sl1.next();

                        switch (choice) {
                            case "1" -> {
                                double dmg = fight.player.activeItem.attack();
                                int attackMiss = rd.nextInt(100);
                                if (fight.player.activeItem.missChance <= attackMiss) {
                                    fight.enemy.takeDamage(dmg);
                                    System.out.println("[#] you struck the enemy for " + dmg + " damage.");
                                } else {
                                    System.out.println("[#] the attack missed!");
                                }
                            }
                        }
                    }
                    sl1.close();
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
                    case "1" -> { // print description
                    }
                    case "2" -> { // print stats
                    }
                    case "3" -> {
                        if (floor.player.inventory.inventory.get(selectedItem) instanceof Weapon) {
                            ((Weapon) floor.player.inventory.inventory.get(selectedItem)).isActive = true;
                        }
                    }
                    case "4" -> page = 5;
                }
            }
            case 5 -> {
                if (input.equalsIgnoreCase("y")) {
                    floor.player.inventory.inventory.remove(selectedItem);
                }
                page = 2;
            }
//            default -> page = 0;
        }

        floor.nextFrame(true, false, page, selectedItem);
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
            if (floor.player.currentRoom.grid[x][y] == floor.player.currentRoom.hole) {/*make death happen*/}
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
}

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