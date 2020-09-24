package Duke;
import java.io.IOException;


/**
 *  Initialises DUKE.
 */
public class Duke {
    /**
     * Print welcoming text before interaction and ending text after exit command.
     //* @param args contains the java command line arguments.
     * @throws IOException if error is encountered when reading saved file.
     */
    public static void main(String[] args) throws IOException {

        Ui.printWelcomeText();

        Storage.loadPreviousEntries();

        Parser.runCommandLoop(Parser.hasEnded, Storage.defaultPath, TaskList.listOfItems);

        Ui.printEndingText();
    }
}
