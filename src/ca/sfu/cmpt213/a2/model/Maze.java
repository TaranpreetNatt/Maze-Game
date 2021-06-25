package ca.sfu.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Random;

/** Creates maze
 * @author Taranpreet
 *
 */

public class Maze {
    // 16
    public static int ROW = 16;
    // 20
    public static int COL = 20;
    public static int WALL = 1;
    public static int BORDER = 9;
    public static int EMPTY_SPACE = 0;
    public static int PLAYER = 2;
    public static int GUARDIAN = 3;
    public static int RELIC = 4;

    private static class Wait {
        private int x;
        private int y;

        Wait(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    private static ArrayList<Wait> addWaitList(ArrayList<Wait> waitList, int[][] maze, int x, int y) {
        if (maze[y - 1][x] == 1) {
            waitList.add(new Wait(y - 1, x));
        }

        if (maze[y + 1][x] == 1) {
            waitList.add(new Wait(y + 1, x));
        }

        if (maze[y][x - 1] == 1) {
            waitList.add(new Wait(y, x - 1));
        }

        if (maze[y][x + 1] == 1) {
            waitList.add(new Wait(y, x + 1));
        }
        return waitList;
    }

    public static int[][] generateMaze() {
        int[][] maze = Cells.fillMaze();
        int xStart = 1;
        int yStart = 1;

        ArrayList<Wait> waitList = new ArrayList<Wait>();
        maze[xStart][yStart] = EMPTY_SPACE;

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

            System.out.print(waitY);
            System.out.print(" ");
            System.out.print(waitX);
            System.out.println();

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
                    addWaitList(waitList, maze, waitX + 1, waitY);
                }

            }
            if (pathNum != 2 && pathRight == 1) {
                maze[waitY][waitX] = EMPTY_SPACE;
                if (maze[waitY][waitX - 1] != BORDER) {
                    maze[waitY][waitX - 1] = EMPTY_SPACE;
                    addWaitList(waitList, maze, waitX - 1, waitY);
                }
            }
            if (pathNum != 2 && pathTop == 1) {
                maze[waitY][waitX] = EMPTY_SPACE;
                if (maze[waitY + 1][waitX] != BORDER) {
                    maze[waitY + 1][waitX] = EMPTY_SPACE;
                    addWaitList(waitList, maze, waitX, waitY + 1);
                }
            }
            if (pathNum != 2 && pathBot == 1) {
                maze[waitY][waitX] = EMPTY_SPACE;
                if (maze[waitY - 1][waitX] != BORDER) {
                    maze[waitY - 1][waitX] = EMPTY_SPACE;
                    addWaitList(waitList, maze, waitX, waitY - 1);
                }
            }
        }
        maze[ROW - 2][COL - 3] = EMPTY_SPACE;
        maze[ROW - 4][COL - 2] = EMPTY_SPACE;
        return maze;
    }

    public static void printMaze() {
        int[][] maze = generateMaze();

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (maze[i][j] != 0) {
                    System.out.print("#");
                }
                if (maze[i][j] == 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void printMazeNum() {
        int[][] maze = generateMaze();

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printMaze();
//        printMazeNum();
    }
}
