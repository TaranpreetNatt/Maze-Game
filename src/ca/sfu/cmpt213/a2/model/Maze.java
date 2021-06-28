package ca.sfu.cmpt213.a2.model;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** Creates maze
 * @author Taranpreet
 *
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
    private static ArrayList<Coordinate> pathList = new ArrayList<Coordinate>();

    public static ArrayList<Coordinate> getPathList() {
        return pathList;
    }

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

    public static int[][] fillMaze() {
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
        return maze;
    }

    public static void printMaze(int[][] maze) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (maze[i][j] == BORDER || maze[i][j] == WALL || maze[i][j] == DISCOVERED_WALL) {
                    System.out.print("#");
                }
                if (maze[i][j] == EMPTY_SPACE || maze[i][j] == DISCOVERED_EMPTY_SPACE) {
                    System.out.print(" ");
                }

                if (maze[i][j] == RELIC) {
                    System.out.print("^");
                }
                if (maze[i][j] == PLAYER) {
                    System.out.print("@");
                }
                if (maze[i][j] == GUARDIAN) {
                    System.out.print("!");
                }

            }
            System.out.println();
        }
    }

    public static void printMazeHidden(int[][] maze) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (maze[i][j] == BORDER) {
                    System.out.print("#");
                }

                if (maze[i][j] == WALL) {
                    System.out.print(".");
                }

                if (maze[i][j] == DISCOVERED_WALL) {
                    System.out.print("#");
                }

                if (maze[i][j] == EMPTY_SPACE) {
                    System.out.print(".");
                }

                if (maze[i][j] == DISCOVERED_EMPTY_SPACE) {
                    System.out.print(" ");
                }

                if (maze[i][j] == RELIC) {
                    System.out.print("^");
                }
                if (maze[i][j] == PLAYER) {
                    System.out.print("@");
                }
                if (maze[i][j] == GUARDIAN) {
                    System.out.print("!");
                }

            }
            System.out.println();
        }
    }

    private static void printMazeNum(int[][] maze) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

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

    public static void main(String[] args) {
        int[][] maze = generateMaze();

//        while(checkFourWalls(maze)) {
//            maze = generateMaze();
//            System.out.println("Four walls were detected, remaking maze");
//        }

        maze = Relic.createRelic(maze);
        maze = Player.createPlayer(maze);
        maze = Guardian.createGuardians(maze);


        printMazeHidden(maze);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter move");
        char move = scanner.next().charAt(0);
        System.out.println(move);

        Game.setNumOfRelics(1);
        maze = Player.movePlayer(maze, move);
        maze = Guardian.moveGuardians(maze);
        if (Game.win()) {
            System.out.println("You won");
        }

        if (Game.lose()) {
            System.out.println("You lost");
        }
        while (move != 'z') {
            printMazeHidden(maze);
            move = scanner.next().charAt(0);
            maze = Player.movePlayer(maze, move);
            maze = Guardian.moveGuardians(maze);

            if (Game.win()) {
                System.out.println("You won");
                break;
            }

            if (Game.lose()) {
                System.out.println("You lost");
                break;
            }


        }
        printMaze(maze);
    }
}
