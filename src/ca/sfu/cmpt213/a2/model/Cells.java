package ca.sfu.cmpt213.a2.model;

/** Methods to manipulate cells of maze
 * @author Taranpreet
 *
 */

public class Cells {

    /**
     * Fills maze with border, walls and removes walls from 4 corners
     * @return 2D int array
     */
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
}
