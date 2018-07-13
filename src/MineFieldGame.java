import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Class that initialises and runs the game, based off passed config files.
 */
public class MineFieldGame {

    /**
     * Constants used throughout the program. Represents config parameters and end game messages.
     */
    private final static String BOARD_SIZE = "BoardSize";
    private final static String TURTLE_LOCATION= "TurtlePos";
    private final static String TURTLE_DIRECTION = "TurtleDir";
    private final static String EXIT_LOCATION = "Exit";
    private final static String MINE_LOCATIONS = "MineLocations";
    private final static String SEQUENCE = "Sequence";
    private final static String KILLED_BY_MINE_MESSAGE = "Turtle Stepped on Mine and is DEAD";
    private final static String TURTLE_LEFT_BOARD_MESSAGE = "Turtle entered uncharted territory and DIED";
    private final static String TURTLE_FOUND_EXIT_MESSAGE = "Turtle Has Escaped!";
    private final static String TURTLE_NEVER_FOUND_EXIT_MESSAGE = "Turtle never found the exit and is stranded in the Minefield";

    private final Properties configFile;
    private final Properties movesFile;

    /**
     * Miine field game constructor. Takes a config file and a Moves file.
     * @param configFile Properties file for the initial setup of the board.
     * @param moveFile File that lists the moves of each sequence of each run.
     */
    public MineFieldGame(final Properties configFile, final Properties moveFile) {
        this.configFile = configFile;
        this.movesFile = moveFile;
    }

    /**
     * Sets up and runs the game according the to config files.
     */
    public void runGame() {
        List<Integer> boardSizes = stringArrayToIntList(configFile.getProperty(BOARD_SIZE).split(","));
        List<Integer> turtleLocation = stringArrayToIntList(configFile.getProperty(TURTLE_LOCATION).split (","));
        List<Integer> exitLocation = stringArrayToIntList(configFile.getProperty(EXIT_LOCATION).split(","));
        DirectionsEnum turtleDirection = DirectionsEnum.valueOf(configFile.getProperty(TURTLE_DIRECTION).toUpperCase());
        List<String> mineSizes  = Arrays.asList(configFile.getProperty(MINE_LOCATIONS).split(" "));

        ArrayList<List<Integer>> individualMines = new ArrayList<>();
        mineSizes.forEach(mine -> individualMines.add(stringArrayToIntList(mine.split(","))));

        Board board = new Board(boardSizes.get(0), boardSizes.get(1), individualMines, exitLocation );

        ArrayList<List<ActionsEnum>> sequences = getSequences();
        sequences.forEach(sequence -> System.out.println(runSequence(sequence,
                new Turtle(turtleLocation.get(0),turtleLocation.get(1),turtleDirection),
                board)));
    }

    /**
     * Method that parses the moves of each sequence and returns them as a list of Action enums.
     * @return Arraylist of lists of Action enums.
     */
    private ArrayList<List<ActionsEnum>> getSequences(){
        int sequenceNumber = 1;
        ArrayList<List<ActionsEnum>> sequences = new ArrayList();
        while (movesFile.getProperty(SEQUENCE + sequenceNumber) != null) {
            sequences.add(Arrays.stream((movesFile.getProperty(SEQUENCE+ sequenceNumber).toUpperCase().split(" ")))
                    .map(ActionsEnum::valueOf)
                    .collect(Collectors.toList()));
            sequenceNumber++;
        }
        return sequences;
    }

    /**
     * @param sequence List of Action Enums for the turtle to perform
     * @param turtle The turtle object that will be responding to actions
     * @param board The board object that contains the grid the turtle will traverse.
     * @return Returns the String declaring the result of the passed sequence
     */
    private String runSequence(final List<ActionsEnum> sequence, final Turtle turtle, final Board board)  {
        for (ActionsEnum action: sequence) {
            if (action.equals(ActionsEnum.MOVE)){
                turtle.move();
                if (board.isTurtleOffBoard(turtle.getxPosition(), turtle.getyPosition())) {
                    return TURTLE_LEFT_BOARD_MESSAGE;
                }
                else if (board.isTurtleOnMine(turtle.getxPosition(), turtle.getyPosition())){
                    return KILLED_BY_MINE_MESSAGE;
                }
                else if (board.isTurtleOnExit(turtle.getxPosition(), turtle.getyPosition())){
                    return TURTLE_FOUND_EXIT_MESSAGE;
                }
            }
            else if (action.equals(ActionsEnum.ROTATE)) {
                turtle.rotate();
            }
        }
        return TURTLE_NEVER_FOUND_EXIT_MESSAGE;
    }

    /**
     * Converts an array of strings to an array of Integers.
     * @param arrayToConvert An array of strings that is to be converted to Integers.
     * @return A List of Integers.
     */
    private List<Integer> stringArrayToIntList(final String[] arrayToConvert) {
        return Arrays.stream(arrayToConvert).map(Integer::valueOf).collect(Collectors.toList());
    }
}
