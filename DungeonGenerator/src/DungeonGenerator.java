import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.time.Clock;

public class DungeonGenerator {
    public static void main(String[] args) {
//        https://www.coolgenerator.com/ascii-text-generator
        Scanner input = new Scanner(System.in);
        Clock clock = Clock.systemDefaultZone();
        Floor floor = new Floor();
        floor.generateFloor();
        floor.printFloor();
//        MAKE INPUT FOR NEXT ROOM !!!
//        input.next();
        System.out.println("\n");
        floor.nextFrame();
        floor.printFloor();
        input.next();
    }
}

/*
 * ideas:
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