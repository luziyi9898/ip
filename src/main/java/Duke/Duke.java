package Duke;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {

        Ui.printWelcomeText();

        Storage.loadPreviousEntries();

        Parser.runCommandLoop(Parser.hasEnded, Storage.defaultPath, TaskList.listOfItems);

        Ui.printEndingText();
    }
}
