package ca.sfu.cmpt213.a2.model;

/**
 * A class for the game state. Checks for win, loss conditions and sets relic win condition
 * @author Taranpreet
 */
public class Game {
    private static int RELIC_WIN_CONDITION = 3;

    /**
     * Sets the number of relics win condition
     * @param num int number of relics required to win
     */
    public static void setNumOfRelics(int num) {
        RELIC_WIN_CONDITION = num;
    }

    /**
     * Checks whether the player has won
     * @return Boolean, true if the player won and false if the player has not won
     */
    public static boolean win() {
        return Player.getObtainedRelics() == RELIC_WIN_CONDITION;
    }

    /**
     * Gets the number of relics required to win
     * @return int number of relics
     */
    public static int getRelicWinCondition() {
        return RELIC_WIN_CONDITION;
    }

    /**
     * Checks whether the players has lost
     * @return Boolean, true if the player lost and false if the player has not lost
     */
    public static boolean lose() {
        Coordinate playerPosition = Player.getPlayerPosition();
        return Guardian.atGuardian(playerPosition);
    }

    /**
     * If the player has lost mark the spot the player died
     * @param maze 2D int array of maze
     * @return 2D int array of the maze with a spot marked where the player died
     */
    public static int[][] mazeLose(int[][] maze) {
        if (lose()) {
            Coordinate playerPosition = Player.getPlayerPosition();
            maze[playerPosition.y][playerPosition.x] = Maze.LOSE;
        }
        return maze;
    }

    public static int[][] move(int[][] maze, char move) {
        maze = Player.movePlayer(maze, move);
        maze = Guardian.moveGuardians(maze);
        return maze;
    }
}
