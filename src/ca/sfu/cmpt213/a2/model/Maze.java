package ca.sfu.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Random;

/** Creates maze using Randomized Prim's algorithm and no 2x2 walls and no 2x2 empty spaces
 * @author Taranpreet
 */
public class Maze {
    protected static final int ROW = 16;
    protected static final int COL = 20;
    protected static final int WALL = 1;
    protected static final int BORDER = 9;
    protected static final int EMPTY_SPACE = 0;
    protected static final int DISCOVERED_EMPTY_SPACE = 5;
    protected static final int DISCOVERED_WALL = 6;
    protected static final int PLAYER = 2;
    protected static final int GUARDIAN = 3;
    protected static final int RELIC = 4;
    protected static final int LOSE = 7;
    private static ArrayList<Coordinate> pathList = new ArrayList<Coordinate>();

    public static int getRow() {
        return ROW;
    }
    public static int getCol() {
        return COL;
    }
    public static  int getWall() {
        return WALL;
    }
    public static int getBorder() {
        return BORDER;
    }
    public static int getEmptySpace() {
        return EMPTY_SPACE;
    }
    public static int getDiscoveredEmptySpace() {
        return DISCOVERED_EMPTY_SPACE;
    }
    public static int getDiscoveredWall() {
        return DISCOVERED_WALL;
    }
    public static int getPlayer() {
        return PLAYER;
    }
    public static int getGuardian() {
        return GUARDIAN;
    }
    public static int getRelic() {
        return RELIC;
    }
    public static int getLose() {
        return LOSE;
    }
    public static ArrayList<Coordinate> getPathList() {
        return pathList;
    }

    /**
     * Adds unvisited cells to a waitlist
     * @param waitList Coordinate ArrayList waitlist of unvisited cells
     * @param maze 2D array of the maze
     * @param x int column of current empty cell
     * @param y int row of current empty cell
     * @return Coordinate ArrayList of unvisited cells
     */
    private static ArrayList<Coordinate> addWaitList(ArrayList<Coordinate> waitList, int[][] maze, int x, int y) {
        if (maze[y - 1][x] == 1) {
            waitList.add(new Coordinate(y - 1, x));
        }
        if (maze[y + 1][x] == 1) {
            waitList.add(new Coordinate(y + 1, x));
        }
        if (maze[y][x - 1] == 1) {
            waitList.add(new Coordinate(y, x - 1));
        }
        if (maze[y][x + 1] == 1) {
            waitList.add(new Coordinate(y, x + 1));
        }
        return waitList;
    }

    /**
     * Fills maze with walls and makes empty space in the top left, top right, bottom left, and bottom right cells
     * @return 2D array of the maze with the cells filled to specification
     */
    private static int[][] fillMaze() {
        int row = Maze.ROW;
        int col = Maze.COL;
        int border = Maze.BORDER;
        int wall = Maze.WALL;
        int emptySpace = Maze.EMPTY_SPACE;

        int[][] maze = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // fill border
                if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
                    maze[i][j] = border;
                }
                else {
                    // fills all of maze with wall
                    maze[i][j] = wall;
                }
                // remove wall from corners
                // top left corner
                maze[1][1] = emptySpace;

                // top right corner
                maze[1][col - 2] = emptySpace;

                // bottom left corner
                maze[row - 2][1] = emptySpace;

                // bottom right corner
                maze[row - 2][col - 2] = emptySpace;
            }
        }
        return maze;
    }

    /**
     * Generates the maze using Randomized Prim's Algorithm
     * @return 2D Array of maze after it has been fully generated
     */
    public static int[][] generateMaze() {
        int[][] maze = fillMaze();
        int xStart = 1;
        int yStart = 1;

        ArrayList<Coordinate> waitList = new ArrayList<Coordinate>();
        maze[yStart][xStart] = EMPTY_SPACE;

        waitList = addWaitList(waitList, maze, xStart, yStart);

        Random random = new Random();
        int j;
        int waitX;
        int waitY;
        int pathNum = 0;

        while (!waitList.isEmpty()) {
            j = random.ints(0, waitList.size()).findFirst().getAsInt();
            waitX = waitList.get(j).x;
            waitY = waitList.get(j).y;
            waitList.remove(j);

            pathNum = 0;
            int pathLeft = 0;
            int pathRight = 0;
            int pathTop = 0;
            int pathBot = 0;

            if (maze[waitY][waitX + 1] == EMPTY_SPACE) {
                pathRight = 1;
                pathNum++;
            }
            if (maze[waitY][waitX - 1] == EMPTY_SPACE) {
                pathLeft = 1;
                pathNum++;
            }
            if (maze[waitY + 1][waitX] == EMPTY_SPACE) {
                pathBot = 1;
                pathNum++;
            }
            if (maze[waitY - 1][waitX] == EMPTY_SPACE) {
                pathTop = 1;
                pathNum++;
            }

            if (pathNum != 2 && pathLeft == 1) {
                maze[waitY][waitX] = EMPTY_SPACE;
                if (maze[waitY][waitX + 1] != BORDER) {
                    maze[waitY][waitX + 1] = EMPTY_SPACE;
                    pathList.add(new Coordinate(waitY, waitX + 1));
                    addWaitList(waitList, maze, waitX + 1, waitY);
                }
                pathList.add(new Coordinate(waitY, waitX));
            }
            if (pathNum != 2 && pathRight == 1) {
                maze[waitY][waitX] = EMPTY_SPACE;
                if (maze[waitY][waitX - 1] != BORDER) {
                    maze[waitY][waitX - 1] = EMPTY_SPACE;
                    pathList.add(new Coordinate(waitY, waitX - 1));
                    addWaitList(waitList, maze, waitX - 1, waitY);
                }
                pathList.add(new Coordinate(waitY, waitX));
            }
            if (pathNum != 2 && pathTop == 1) {
                maze[waitY][waitX] = EMPTY_SPACE;
                if (maze[waitY + 1][waitX] != BORDER) {
                    maze[waitY + 1][waitX] = EMPTY_SPACE;
                    pathList.add(new Coordinate(waitY + 1, waitX));
                    addWaitList(waitList, maze, waitX, waitY + 1);
                }
                pathList.add(new Coordinate(waitY, waitX));
            }
            if (pathNum != 2 && pathBot == 1) {
                maze[waitY][waitX] = EMPTY_SPACE;
                if (maze[waitY - 1][waitX] != BORDER) {
                    maze[waitY - 1][waitX] = EMPTY_SPACE;
                    pathList.add(new Coordinate(waitY - 1, waitX));
                    addWaitList(waitList, maze, waitX, waitY - 1);
                }
                pathList.add(new Coordinate(waitY, waitX));
            }
        }
        maze[ROW - 2][COL - 3] = EMPTY_SPACE;
        maze[ROW - 4][COL - 2] = EMPTY_SPACE;
        maze = Relic.createRelic(maze);
        maze = Player.createPlayer(maze);
        maze = Guardian.createGuardians(maze);
        return maze;
    }

    /**
     * Checks whether maze has 2x2 walls
     * @param maze 2D array of maze
     * @return Boolean, true if there exists 2x2 walls or false if there is no 2x2 walls
     */
    public static boolean checkFourWalls(int[][] maze) {
        int counter = 1;
        for (int i = 1; i < ROW - 1; i++) {
            for (int j = 1; j < COL - 1; j++) {
                if (maze[i][j] == WALL) {

                    if ((maze[i][j - 1] == WALL || maze[i][j - 1] == BORDER) && (maze[i - 1][j - 1] == WALL || maze[i - 1][j - 1] == BORDER) && (maze[i - 1][j] == WALL || maze[i - 1][j] == BORDER)) {
                        return true;
                    }
                    if ((maze[i][j + 1] == WALL || maze[i][j + 1] == BORDER) && (maze[i - 1][j + 1] == WALL || maze[i - 1][j + 1] == BORDER) && (maze[i - 1][j] == WALL || maze[i - 1][j] == BORDER)) {
                        return true;
                    }
                    if ((maze[i][j - 1] == WALL || maze[i][j - 1] == BORDER) && (maze[i + 1][j - 1] == WALL || maze[i + 1][j - 1] == BORDER)&& (maze[i + 1][j] == WALL || maze[i + 1][j] == BORDER)) {
                        return true;
                    }
                    if ((maze[i][j + 1] == WALL || maze[i][j + 1] == BORDER) && (maze[i + 1][j + 1] == WALL || maze[i][j + 1] == BORDER) && (maze[i + 1][j] == WALL || maze[i + 1][j] == BORDER)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
