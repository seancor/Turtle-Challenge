/**
 * Class that represents the turtle object that moves around the board.
 */
public class Turtle {

    private int xPosition;
    private int yPosition;
    private DirectionsEnum currentDirection;

    /**
     * Turtle constructor
     * @param xStartPosition Turtles initial x co-ordinate.
     * @param ySTartPosition Turtles initial y co-ordinate.
     * @param startDirection Turtles initial direction.
     */
    public Turtle(final int xStartPosition, final int ySTartPosition, final DirectionsEnum startDirection){
        this.xPosition = xStartPosition;
        this.yPosition = ySTartPosition;
        this.currentDirection = startDirection;
    }

    /**
     * Moves turtle based on current direction turtle is facing.
     */
    public void move() {
        switch(currentDirection) {
            case NORTH: yPosition++; break;
            case EAST: xPosition++; break;
            case SOUTH: yPosition--; break;
            case WEST: xPosition--; break;
        }
    }

    /**
     * Rotates turtle 90 degrees clockwise.
     */
    public void rotate() {
        switch(currentDirection) {
            case NORTH: currentDirection = DirectionsEnum.EAST; break;
            case EAST: currentDirection = DirectionsEnum.SOUTH; break;
            case SOUTH: currentDirection = DirectionsEnum.WEST; break;
            case WEST: currentDirection = DirectionsEnum.NORTH; break;
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
