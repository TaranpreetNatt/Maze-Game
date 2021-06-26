package ca.sfu.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Random;

public class Relic {
    private static Coordinate relicPosition;
    private static int RELIC = Maze.RELIC;

    public static boolean isRelic(Coordinate playerPosition) {
        if (relicPosition.x == playerPosition.x && relicPosition.y == playerPosition.y) {
            return true;
        }
        return false;
    }

    public static int[][] createRelic(int[][] maze) {
        ArrayList<Coordinate> pathList = Maze.getPathList();
        Random random = new Random();
        int j = random.ints(0, pathList.size()).findFirst().getAsInt();

        Coordinate coordinate = new Coordinate(pathList.get(j).y, pathList.get(j).x);
        relicPosition = coordinate;
        maze[relicPosition.y][relicPosition.x] = RELIC;
        return maze;
    };
}
