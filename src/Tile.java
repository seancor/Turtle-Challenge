/**
 * Tile class, represents a tile in the grid, can  represent a mine or an exit.
 */
public class Tile {

    private boolean isMine = false;
    private boolean isExit = false;

    public Tile() {}

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
