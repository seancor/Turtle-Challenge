import java.util.ArrayList;
import java.util.List;

/**
 * A Class that represents the minefield board on which the game takes place.
 */
public class Board {

    public Tile[][] tiles;
    private final int boardXSize;
    private final int boardYSize;

    /**
     * Board Constructor. Populates the Tile array according to the config. Places Mines and exit on the board also.
     * @param boardXSize The size of the board along the x co-ordinate.
     * @param boardYSize The size of the board along the y co-ordinate.
     * @param mineLocations A List of a mine locations to be placed on the board.
     * @param exitLocation A list containing the xy co-ordinates of the exit.
     */
    public Board(final int boardXSize, final int boardYSize, final ArrayList<List<Integer>> mineLocations, final List<Integer> exitLocation){
        this.tiles = new Tile[boardXSize][boardYSize];
        this.boardXSize = boardXSize;
        this.boardYSize = boardYSize;

        for(int x = 0; x < boardXSize; x++) {
            for(int y = 0; y< boardYSize; y++) {
                tiles[x][y] = new Tile();
            }
        }

        mineLocations.forEach(mine -> tiles[mine.get(0)][mine.get(1)].setMine(true));

        if (tiles[exitLocation.get(0)][exitLocation.get(1)].isMine()){
            throw new IllegalArgumentException("Exit Cannot be on a mine");
        }
        tiles[exitLocation.get(0)][exitLocation.get(1)].setExit(true);
    }

    /**
     * Checks if the turtle is currently positioned on a mine.
     * @param turtleXPos X co-ordinate of the turtle
     * @param turtleYPos Y co-ordinate of the turtle
     * @return Boolean true if turtles position is a mine. False if not.
     */
    public boolean isTurtleOnMine(int turtleXPos, int turtleYPos) {
        return tiles[turtleXPos][turtleYPos].isMine();
    }

    /**
     * Checks if the turtle is currently positioned on the exit.
     * @param turtleXPos X co-ordinate of the turtle
     * @param turtleYPos Y co-ordinate of the turtle
     * @return Boolean true if turtles position is the exit. False if not.
     */
    public boolean isTurtleOnExit(int turtleXPos, int turtleYPos) {
        return tiles[turtleXPos][turtleYPos].isExit();
    }

    /**
     * Checks if the turtle has moved off the board.
     * @param turtleXPos X co-ordinate of the turtle
     * @param turtleYPos Y co-ordinate of the turtle
     * @return Boolean true if the turtle has left the board. False if not.
     */
    public boolean isTurtleOffBoard(int turtleXPos, int turtleYPos) {
        return turtleXPos>= boardYSize || turtleYPos>=boardXSize;
    }
}
