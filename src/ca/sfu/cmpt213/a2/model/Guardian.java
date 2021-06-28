package ca.sfu.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class for the guardians of the maze
 * @author Taranpreet
 */
public class Guardian {
    private static final int GUARDIAN = Maze.GUARDIAN;
    private static final int RELIC = Maze.RELIC;
    private static final ArrayList<Coordinate> lastPositionOne = new ArrayList<>();
    private static final ArrayList<Coordinate> lastPositionTwo = new ArrayList<>();
    private static final ArrayList<Coordinate> lastPositionThree = new ArrayList<>();
    private static final ArrayList<Coordinate> currentPosition = new ArrayList<>();

    /**
     * Creates three guardians in the top right, bottom left, and bottom right corners
     * @param maze 2D Array of the maze
     * @return 2D Array of the maze with guardians in the maze
     */
    public static int[][] createGuardians(int[][] maze) {
        maze[1][18] = GUARDIAN;
        maze[14][1] = GUARDIAN;
        maze[14][18] = GUARDIAN;

        currentPosition.add(new Coordinate(1, 18, 1));
        currentPosition.add(new Coordinate(14, 1, 2));
        currentPosition.add(new Coordinate(14, 18, 3));
        return maze;
    }

    /**
     * Moves all three guardians pseudo-randomly
     * @param maze 2D Array of maze
     * @return 2D Array of the maze with guardians moved pseudo-randomly
     */
    public static int[][] moveGuardians(int[][] maze) {
        maze = moveGuardian(maze, 0);
        maze = moveGuardian(maze, 1);
        maze = moveGuardian(maze, 2);
        return maze;
    }

    /**
     * Checks if player and guardian are at the same place
     * @param playerPosition Coordinate object of the player's current position
     * @return Boolean, true if the guardian and player are at the same place, false otherwise
     */
    public static boolean atGuardian(Coordinate playerPosition) {
        int x = playerPosition.x;
        int y = playerPosition.y;

        if (currentPosition.get(0).x == x && currentPosition.get(0).y == y) {
            return true;
        }
        if (currentPosition.get(1).x == x && currentPosition.get(1).y == y) {
            return true;
        }
        return currentPosition.get(2).x == x && currentPosition.get(2).y == y;
    }

    /**
     * Moves a specified guardian
     * @param maze 2D Array of maze
     * @param i Guardian id
     * @return 2D Array of the maze with the guardian moved pseudo-randomly
     */
    private static int[][] moveGuardian(int[][] maze, int i) {
        ArrayList<String> moves = new ArrayList<>();
        moves.add("w");
        moves.add("a");
        moves.add("s");
        moves.add("D");

        ArrayList<Coordinate> lastPosition = new ArrayList<>();
        if (i == 0) {
            lastPosition = lastPositionOne;
        }
        if (i == 1) {
            lastPosition = lastPositionTwo;
        }
        if (i == 2) {
            lastPosition = lastPositionThree;
        }

        Random random = new Random();
        int x = currentPosition.get(i).x;
        int y = currentPosition.get(i).y;
        int id = currentPosition.get(i).id;
        while(!moves.isEmpty()) {
            int j = random.ints(0, moves.size()).findFirst().getAsInt();
            String move = moves.get(j);
            moves.remove(j);

            if (move.equals("A") || move.equals("a")) {
                int nextX = x - 1;
                int nextY = y;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER && maze[nextY][nextX] != Maze.DISCOVERED_WALL) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                            maze[nextY][nextX] = GUARDIAN;
                            currentPosition.get(i).x = nextX;
                            currentPosition.get(i).y = nextY;
                            last.x = x;
                            last.y = y;
                            break;
                        }
                    }
                }
            }
            if (move.equals("D") || move.equals("d")) {
                int nextX = x + 1;
                int nextY = y;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER && maze[nextY][nextX] != Maze.DISCOVERED_WALL) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                            maze[nextY][nextX] = GUARDIAN;
                            currentPosition.get(i).x = nextX;
                            currentPosition.get(i).y = nextY;
                            last.x = x;
                            last.y = y;
                            break;
                        }
                    }
                }
            }
            if (move.equals("S") || move.equals("s")) {
                int nextX = x;
                int nextY = y + 1;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER && maze[nextY][nextX] != Maze.DISCOVERED_WALL) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                            maze[nextY][nextX] = GUARDIAN;
                            currentPosition.get(i).x = nextX;
                            currentPosition.get(i).y = nextY;
                            last.x = x;
                            last.y = y;
                            break;
                        }
                    }
                }
            }
            if (move.equals("W") || move.equals("w")) {
                int nextX = x;
                int nextY = y - 1;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER && maze[nextY][nextX] != Maze.DISCOVERED_WALL) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i, nextX, nextY);
                            maze[nextY][nextX] = GUARDIAN;
                            currentPosition.get(i).x = nextX;
                            currentPosition.get(i).y = nextY;
                            last.x = x;
                            last.y = y;
                            break;
                        }
                    }
                }
            }
        }
        return maze;
    }

    /**
     * Checks if the guardian cannot move. Meaning the guardian is surrounded by tiles and cannot go backwards
     * @param maze 2D array of maze
     * @param currentPosition Coordinate of guardian's current position
     * @param last Coordinate of guardian's last position
     * @param nextX int guardian's next column move
     * @param nextY int guardian's next row move
     * @param lastPosition ArrayList of guardians last position
     * @return
     */
    private static boolean noLegalMove(int[][] maze, Coordinate currentPosition, Coordinate last, int nextX, int nextY, ArrayList<Coordinate> lastPosition) {
        int x = currentPosition.x;
        int y = currentPosition.y;
        int wallCounter = 0;

        if (maze[y - 1][x] == Maze.WALL || maze[y - 1][x] == Maze.BORDER || maze[y - 1][x] == Maze.DISCOVERED_WALL) {
            wallCounter = wallCounter + 1;
        }
        if (maze[y + 1][x] == Maze.WALL || maze[y + 1][x] == Maze.BORDER || maze[y + 1][x] == Maze.DISCOVERED_WALL) {
            wallCounter = wallCounter + 1;
        }
        if (maze[y][x + 1] == Maze.WALL || maze[y][x + 1] == Maze.BORDER || maze[y][x + 1] == Maze.DISCOVERED_WALL) {
            wallCounter = wallCounter + 1;
        }
        if (maze[y][x - 1] == Maze.WALL || maze[y][x - 1] == Maze.BORDER || maze[y][x - 1] == Maze.DISCOVERED_WALL) {
            wallCounter = wallCounter + 1;
        }
        if (wallCounter >= 3 && (last.x != nextX || last.y != nextY)) {
            lastPosition.remove(last);
            return true;
        }
        return false;
    }

    /**
     * Re-renders the relic after guardian has gone over the tile
     * @param maze 2D array of maze
     * @param y int guardian's current row position
     * @param x int guardian's current column position
     * @param i int guardian's id
     * @param nextX int guardian's next column position
     * @param nextY int guardian's next row position
     * @return 2D array of the maze with the relic re-rendered
     */
    private static int[][] reRenderRelic(int[][] maze, int y, int x, int i, int nextX, int nextY) {
        if (Relic.isRelic(currentPosition.get(i))) {
            Coordinate relicPosition = Relic.getRelicPosition();
            maze[relicPosition.y][relicPosition.x] = RELIC;
        }
        else if (Player.atPlayer(currentPosition.get(i))) {
            Coordinate playerPosition = Player.getPlayerPosition();
            maze[playerPosition.y][playerPosition.x] = Maze.PLAYER;
        }
        else {
            if (maze[nextY][nextX] == Maze.DISCOVERED_EMPTY_SPACE) {
                maze[y][x] = Maze.DISCOVERED_EMPTY_SPACE;
            }
            else {
                maze[y][x] = Maze.EMPTY_SPACE;
            }
        }
        return maze;
    }

    /**
     * Finds the lastposition coordinate of the guardian with the given id
     * @param id int id of guardian
     * @param lastPosition ArrayList of guardian's last position
     * @return Coordinate of guardian's last position
     */
    private static Coordinate findLastPosition(int id, ArrayList<Coordinate> lastPosition) {
        Coordinate last = new Coordinate();
        for (int i = 0; i < lastPosition.size(); i++) {
            if (lastPosition.get(i).id == id) {
                last = lastPosition.get(i);
            }
        }
        return last;
    }
}
