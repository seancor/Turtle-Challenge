import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Program that Moves a small turtle around a minefield based off config files passed as arguments.
 * @author Sean Corcoran
 */
public class Main {

    public static void main(String[] args) {
        Properties configFile = new Properties();
        Properties moveSequencesFile = new Properties();
        try {
            configFile.load(new FileInputStream(new File(args[0])));
            moveSequencesFile.load(new FileInputStream(new File(args[1])));
            MineFieldGame newGame = new MineFieldGame(configFile, moveSequencesFile);
            newGame.runGame();
        } catch (IndexOutOfBoundsException | IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}


