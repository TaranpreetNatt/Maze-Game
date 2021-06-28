package ca.sfu.cmpt213.a2.textui;
import ca.sfu.cmpt213.a2.model.Game;
import ca.sfu.cmpt213.a2.model.Guardian;
import ca.sfu.cmpt213.a2.model.Maze;
import ca.sfu.cmpt213.a2.model.Player;

import static ca.sfu.cmpt213.a2.model.Maze.checkFourWalls;
import static ca.sfu.cmpt213.a2.model.Maze.generateMaze;

public class DisplayGame {
    protected static final int ROW = Maze.getRow();
    protected static final int COL = Maze.getCol();
    protected static final int WALL = Maze.getWall();
    protected static final int BORDER = Maze.getBorder();
    protected static final int EMPTY_SPACE = Maze.getEmptySpace();
    protected static final int DISCOVERED_EMPTY_SPACE = Maze.getDiscoveredEmptySpace();
    protected static final int DISCOVERED_WALL = Maze.getDiscoveredWall();
    protected static final int PLAYER = Maze.getPlayer();
    protected static final int GUARDIAN = Maze.getGuardian();
    protected static final int RELIC = Maze.getRelic();
    protected static final int LOSE = Maze.getLose();

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
                if (maze[i][j] == LOSE) {
                    System.out.print("X");
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
                if (maze[i][j] == LOSE) {
                    System.out.print("X");
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

    private static int[][] move(int[][] maze, char move) {
        maze = Player.movePlayer(maze, move);
        maze = Guardian.moveGuardians(maze);
        return maze;
    }

    public static void main(String[] args) {
        Help help = new Help();
        Input input = new Input();
        help.help();

        int[][] maze = generateMaze();
        while(checkFourWalls(maze)) {
            maze = generateMaze();
            System.out.println("Four walls were detected, remaking maze");
        }
        char move;

        while (!Game.win()) {
            printMazeHidden(maze);
            help.displayRelicCount();
            move = input.userMoveInput();
            if (Game.lose()) {
                break;
            }

            if (move == '?') {
                help.help();
                continue;
            }

            if (move == 'm') {
                printMaze(maze);
                help.displayRelicCount();
                move = input.userMoveInput();
            }

            if (move == 'c') {
                Game.setNumOfRelics(1);
            }

            maze = move(maze, move);
            printMazeHidden(maze);
            help.displayRelicCount();
            maze = move(maze, move);
        }

        if (Game.win()) {
            System.out.println();
            System.out.println("Conragulations hunter, You have won");
            System.out.println();
            printMaze(maze);
            help.displayRelicCount();
        }

        if (Game.lose()) {
            System.out.println();
            System.out.println("You have been killed hunter");
            maze = Game.mazeLose(maze);
            printMaze(maze);
            help.displayRelicCount();
            System.out.println("Game Over...Please Try again");
        }
    }
}
