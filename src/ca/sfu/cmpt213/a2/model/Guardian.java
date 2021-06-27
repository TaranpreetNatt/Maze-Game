package ca.sfu.cmpt213.a2.model;

import java.util.ArrayList;
import java.util.Random;

public class Guardian {
    private static final int GUARDIAN = Maze.GUARDIAN;
    private static final int RELIC = Maze.RELIC;
    private static ArrayList<Coordinate> lastPositionOne = new ArrayList<>();
    private static ArrayList<Coordinate> lastPositionTwo = new ArrayList<>();
    private static ArrayList<Coordinate> lastPositionThree = new ArrayList<>();
    private static ArrayList<Coordinate> currentPosition = new ArrayList<>();

    public static int[][] createGuardians(int[][] maze) {
        maze[1][18] = GUARDIAN;
        maze[14][1] = GUARDIAN;
        maze[14][18] = GUARDIAN;

        currentPosition.add(new Coordinate(1, 18, 1));
        currentPosition.add(new Coordinate(14, 1, 2));
        currentPosition.add(new Coordinate(14, 18, 3));
        return maze;
    }

    public static int[][] moveGuardians(int[][] maze) {
        maze = moveGuardian(maze, 0);
        maze = moveGuardian(maze, 1);
        maze = moveGuardian(maze, 2);
        return maze;
    }

    public static boolean atGuardian(Coordinate playerPosition) {
        int x = playerPosition.x;
        int y = playerPosition.y;

        if (currentPosition.get(0).x == x && currentPosition.get(0).y == y) {
            return true;
        }
        if (currentPosition.get(1).x == x && currentPosition.get(1).y == y) {
            return true;
        }
        if (currentPosition.get(2).x == x && currentPosition.get(2).y == y) {
            return true;
        }
        return false;
    }

    public static int[][] moveGuardian(int[][] maze, int i) {
        ArrayList<String> moves = new ArrayList<String>();
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

            if (move == "A" || move == "a") {
                int nextX = x - 1;
                int nextY = y;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i);
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
            if (move == "D" || move == "d") {
                int nextX = x + 1;
                int nextY = y;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i);
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
            if (move == "S" || move == "s") {
                int nextX = x;
                int nextY = y + 1;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i);
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
            if (move == "W" || move == "w") {
                int nextX = x;
                int nextY = y - 1;

                if (!lastPosition.isEmpty()) {
                    Coordinate last = findLastPosition(id, lastPosition);
                    noLegalMove(maze, currentPosition.get(i), last, nextX, nextY, lastPosition);
                }
                if (maze[nextY][nextX] != Maze.WALL && maze[nextY][nextX] != Maze.BORDER) {
                    if (lastPosition.isEmpty()) {
                        maze = reRenderRelic(maze, y, x, i);
                        maze[nextY][nextX] = GUARDIAN;
                        currentPosition.get(i).x = nextX;
                        currentPosition.get(i).y = nextY;
                        lastPosition.add(new Coordinate(y, x, currentPosition.get(i).id));
                        break;
                    }
                    if (!lastPosition.isEmpty()) {
                        Coordinate last = findLastPosition(id, lastPosition);
                        if (last.x != nextX || last.y != nextY) {
                            maze = reRenderRelic(maze, y, x, i);
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

    private static boolean noLegalMove(int[][] maze, Coordinate currentPosition, Coordinate last, int nextX, int nextY, ArrayList<Coordinate> lastPosition) {
        int x = currentPosition.x;
        int y = currentPosition.y;
        int wallCounter = 0;

        if (maze[y - 1][x] == Maze.WALL || maze[y - 1][x] == Maze.BORDER) {
            wallCounter = wallCounter + 1;
        }
        if (maze[y + 1][x] == Maze.WALL || maze[y + 1][x] == Maze.BORDER) {
            wallCounter = wallCounter + 1;
        }
        if (maze[y][x + 1] == Maze.WALL || maze[y][x + 1] == Maze.BORDER) {
            wallCounter = wallCounter + 1;
        }
        if (maze[y][x - 1] == Maze.WALL || maze[y][x - 1] == Maze.BORDER) {
            wallCounter = wallCounter + 1;
        }
        if (wallCounter >= 3 && (last.x != nextX || last.y != nextY)) {
            lastPosition.remove(last);
            return true;
        }
        return false;
    }

    private static int[][] reRenderRelic(int[][] maze, int y, int x, int i) {
        if (Relic.isRelic(currentPosition.get(i))) {
            Coordinate relicPosition = Relic.getRelicPosition();
            maze[relicPosition.y][relicPosition.x] = RELIC;
        }
        else if (Player.atPlayer(currentPosition.get(i))) {
            Coordinate playerPosition = Player.getPlayerPosition();
            maze[playerPosition.y][playerPosition.x] = Maze.PLAYER;
        }
        else {
            maze[y][x] = Maze.EMPTY_SPACE;
        }
        return maze;
    }

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
