package ca.sfu.cmpt213.a2.model;

public class Player {
    private static Coordinate playerPosition;
    private static int obtainedRelics;
    private static int PLAYER = Maze.PLAYER;

    public static int[][] createPlayer(int[][] maze) {
        maze[1][1] = PLAYER;
        playerPosition = new Coordinate(1, 1);
        return maze;
    }

    public static int getObtainedRelics() {
        return obtainedRelics;
    }

    public static boolean atRelic(Coordinate playerPosition) {
        if (Relic.isRelic(playerPosition)) {
            obtainedRelics = obtainedRelics + 1;
            return true;
        }
        return false;
    }

    public static int[][] movePlayer(int[][] maze, char move) {
        int x = playerPosition.x;
        int y = playerPosition.y;

        if (move == 'D'|| move == 'd') {
            int nextX = x + 1;
            int nextY = y;
            if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                maze[y][x] = Maze.EMPTY_SPACE;
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
                atRelic(playerPosition);
            }
        }
        if (move == 'S' || move == 's') {
            int nextX = x;
            int nextY = y + 1;
            if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                maze[y][x] = Maze.EMPTY_SPACE;
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
                atRelic(playerPosition);
            }
        }
        if (move == 'A' || move == 'a') {
            int nextX = x - 1;
            int nextY = y;
            if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                maze[y][x] = Maze.EMPTY_SPACE;
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
            }
        }
        if (move == 'W' || move == 'w') {
            int nextX = x;
            int nextY = y - 1;
            if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                maze[y][x] = Maze.EMPTY_SPACE;
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
            }
        }
        return maze;
    }
}
