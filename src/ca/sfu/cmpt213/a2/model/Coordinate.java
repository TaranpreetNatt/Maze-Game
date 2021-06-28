package ca.sfu.cmpt213.a2.model;

/**
 * A class to store x, y coordinate of player, relic, guardians
 * @author Taranpreet
 */
public class Coordinate {
    public int x;
    public int y;
    public int id;

    /**
     * Craetes coordinate with x, y
     * @param y int row
     * @param x int column
     */
    Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * Creates coordinate with x, y, and id
     * @param y int row
     * @param x int column
     * @param id int id of item
     */
    Coordinate(int y, int x, int id) {
        this.y = y;
        this.x = x;
        this.id = id;
    }

    /**
     * Empty constructor
     */
    Coordinate() {}
}
