package ca.sfu.cmpt213.a2.model;

/** Creates maze using Randomized Prim's algorithm and no 2x2 walls and no 2x2 empty spaces
 * @author Taranpreet
 *
 */
public class Player {
    private static Coordinate playerPosition;
    private static int obtainedRelics;
    private static final int PLAYER = Maze.PLAYER;

    public static int[][] createPlayer(int[][] maze) {
        maze[1][1] = PLAYER;
        playerPosition = new Coordinate(1, 1);
        maze = reveal(maze);
        return maze;
    }

    public static int getObtainedRelics() {
        return obtainedRelics;
    }

    public static int[][] atRelic(Coordinate playerPosition, int[][] maze) {
        if (Relic.isRelic(playerPosition)) {
            maze = Relic.createRelic(maze);
            obtainedRelics = obtainedRelics + 1;
            return maze;
        }
        return maze;
    }

    public static Coordinate getPlayerPosition() {
        return playerPosition;
    }

    public static boolean atPlayer(Coordinate guardian) {
        return guardian.x == playerPosition.x && guardian.y == playerPosition.y;
    }

    private static int[][] reveal(int[][] maze) {
        int x = playerPosition.x;
        int y = playerPosition.y;

        // top left
        if (maze[y - 1][x - 1] == Maze.WALL) {
            maze[y - 1][x - 1] = Maze.DISCOVERED_WALL;
        }
        if (maze[y - 1][x - 1] == Maze.EMPTY_SPACE) {
            maze[y - 1][x - 1] = Maze.DISCOVERED_EMPTY_SPACE;
        }

        // top
        if (maze[y - 1][x] == Maze.WALL) {
            maze[y - 1][x] = Maze.DISCOVERED_WALL;
        }
        if (maze[y - 1][x] == Maze.EMPTY_SPACE) {
            maze[y - 1][x] = Maze.DISCOVERED_EMPTY_SPACE;
        }

        // top right
        if (maze[y - 1][x + 1] == Maze.WALL) {
            maze[y - 1][x + 1] = Maze.DISCOVERED_WALL;
        }
        if (maze[y - 1][x + 1] == Maze.EMPTY_SPACE) {
            maze[y - 1][x + 1] = Maze.DISCOVERED_EMPTY_SPACE;
        }

        // left
        if (maze[y][x - 1] == Maze.WALL) {
            maze[y][x - 1] = Maze.DISCOVERED_WALL;
        }
        if (maze[y][x - 1] == Maze.EMPTY_SPACE) {
            maze[y][x - 1] = Maze.DISCOVERED_EMPTY_SPACE;
        }

        // right
        if (maze[y][x + 1] == Maze.WALL) {
            maze[y][x + 1] = Maze.DISCOVERED_WALL;
        }
        if (maze[y][x + 1] == Maze.EMPTY_SPACE) {
            maze[y][x + 1] = Maze.DISCOVERED_EMPTY_SPACE;
        }

        // bottom left
        if (maze[y + 1][x - 1] == Maze.WALL) {
            maze[y + 1][x - 1] = Maze.DISCOVERED_WALL;
        }
        if (maze[y + 1][x - 1] == Maze.EMPTY_SPACE) {
            maze[y + 1][x - 1] = Maze.DISCOVERED_EMPTY_SPACE;
        }

        // bottom
        if (maze[y + 1][x] == Maze.WALL) {
            maze[y + 1][x] = Maze.DISCOVERED_WALL;
        }
        if (maze[y + 1][x] == Maze.EMPTY_SPACE) {
            maze[y + 1][x] = Maze.DISCOVERED_EMPTY_SPACE;
        }

        // bottom right
        if (maze[y + 1][x + 1] == Maze.WALL) {
            maze[y + 1][x + 1] = Maze.DISCOVERED_WALL;
        }
        if (maze[y + 1][x + 1] == Maze.EMPTY_SPACE) {
            maze[y + 1][x + 1] = Maze.DISCOVERED_EMPTY_SPACE;
        }
        return maze;
    }

    public static int[][] movePlayer(int[][] maze, char move) {
        int x = playerPosition.x;
        int y = playerPosition.y;
        if (move == 'D'|| move == 'd') {
            int nextX = x + 1;
            int nextY = y;
            if ((maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) && (maze[nextY][nextX] != Maze.DISCOVERED_WALL)) {
                if (Guardian.atGuardian(playerPosition)) {
                    maze[y][x] = Maze.GUARDIAN;
                }
                else {
                    maze[y][x] = Maze.EMPTY_SPACE;
                }
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
                atRelic(playerPosition, maze);
                maze = reveal(maze);
            }
        }
        if (move == 'S' || move == 's') {
            int nextX = x;
            int nextY = y + 1;
            if ((maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) && (maze[nextY][nextX] != Maze.DISCOVERED_WALL)) {
                if (Guardian.atGuardian(playerPosition)) {
                    maze[y][x] = Maze.GUARDIAN;
                }
                else {
                    maze[y][x] = Maze.EMPTY_SPACE;
                }
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
                maze = atRelic(playerPosition, maze);
                maze = reveal(maze);
            }
        }
        if (move == 'A' || move == 'a') {
            int nextX = x - 1;
            int nextY = y;
            if ((maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) && (maze[nextY][nextX] != Maze.DISCOVERED_WALL)) {
                if (Guardian.atGuardian(playerPosition)) {
                    maze[y][x] = Maze.GUARDIAN;
                }
                else {
                    maze[y][x] = Maze.EMPTY_SPACE;
                }
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
                maze = atRelic(playerPosition, maze);
                maze = reveal(maze);
            }
        }
        if (move == 'W' || move == 'w') {
            int nextX = x;
            int nextY = y - 1;
            if ((maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) && (maze[nextY][nextX] != Maze.DISCOVERED_WALL)) {
                if (Guardian.atGuardian(playerPosition)) {
                    maze[y][x] = Maze.GUARDIAN;
                }
                else {
                    maze[y][x] = Maze.EMPTY_SPACE;
                }
                maze[nextY][nextX] = PLAYER;

                playerPosition.x = nextX;
                playerPosition.y = nextY;
                maze = atRelic(playerPosition, maze);
                maze = reveal(maze);
            }
        }
        return maze;
    }
}
