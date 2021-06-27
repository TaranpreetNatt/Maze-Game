package ca.sfu.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Random;

public class Relic {
    private static Coordinate relicPosition;
    private static final int RELIC = Maze.RELIC;

    public static boolean isRelic(Coordinate position) {
        return relicPosition.x == position.x && relicPosition.y == position.y;
    }

    public static Coordinate getRelicPosition() {
        return relicPosition;
    }

    public static int[][] createRelic(int[][] maze) {
        ArrayList<Coordinate> pathList = Maze.getPathList();
        Random random = new Random();
        int j = random.ints(0, pathList.size()).findFirst().getAsInt();

//        relicPosition = new Coordinate(pathList.get(j).y, pathList.get(j).x);
//        maze[relicPosition.y][relicPosition.x] = RELIC;
        maze[1][17] = RELIC;
        relicPosition = new Coordinate(1, 17);
        return maze;
    }
}
