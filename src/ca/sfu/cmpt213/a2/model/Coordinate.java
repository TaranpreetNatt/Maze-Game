package ca.sfu.cmpt213.a2.model;

public class Coordinate {
    public int x;
    public int y;
    public int id;

    Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    Coordinate(int y, int x, int id) {
        this.y = y;
        this.x = x;
        this.id = id;
    }

    Coordinate() {}
}
