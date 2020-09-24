package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


/**
 * Represent files that are used to store the list of tasks.
 */
public class Storage {
    /**
     * Default file path that is used.
     */
    public static String defaultPath = "duke.txt";

    /**
     * Saves the current arraylist of tasks to local storage file.
     * @param filePath path of the local storage file.
     * @param list current arraylist of tasks
     * @throws IOException when errors occur while processing local storage file.
     */
    public static void writeToFile(String filePath, ArrayList<Task> list) throws IOException {

        FileWriter fw = new FileWriter(filePath);
        for (Task item : list) {
            if (item != null) {
                fw.write(item.getTaskDescription() + System.lineSeparator());
            }
        }
        fw.close();
    }

    /**
     * Loads the arraylist of tasks from the local storage file if present.
     * @param filePath path of the local storage file.
     * @param list current arraylist of tasks.
     * @throws FileNotFoundException if no local storage file is present.
     */
    public static void loadSavedText(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int listIndex = 0;
        while (s.hasNext()) {
            String importedCommand = s.nextLine();

            Integer currentStatusSymbol = importedCommand.codePointAt(4);

            if (importedCommand.startsWith(Ui.TODO_ICON)) {
                list.add(new Todo(importedCommand.substring(7).trim()));
            } else if (importedCommand.startsWith(Ui.DEADLINE_ICON)) {
                try {
                    LocalDate taskDateBy = LocalDate.parse(importedCommand.substring(importedCommand.indexOf("by:") + 3
                            , importedCommand.length() - 1)
                            , DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
                    list.add(new Deadline(importedCommand.substring(7, importedCommand.indexOf("(") - 1), taskDateBy));
                }catch(DateTimeParseException e){
                    Ui.printBetweenLines(Ui.MESSAGE_INVALID_TIME);
                }
            } else if (importedCommand.startsWith(Ui.EVENT_ICON)) {
                try {
                    LocalDate taskDateAt = LocalDate.parse(importedCommand.substring(importedCommand.indexOf("at:") + 3
                            , importedCommand.length() - 1)
                            , DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
                    list.add(new Event(importedCommand.substring(7, importedCommand.indexOf("(") - 1), taskDateAt));
                }catch(DateTimeParseException e){
                    Ui.printBetweenLines(Ui.MESSAGE_INVALID_TIME);
                }
            }
            if (currentStatusSymbol.equals(10003)) {
                list.get(listIndex).markAsDone();
            }
            listIndex += 1;
        }
    }

    /**
     * Look for local storage file and creates a new one under the default path is one isn't present.
     * @throws IOException if no local storage file is found.
     */
    public static void loadPreviousEntries() throws IOException {
        try {
            Storage.loadSavedText(Storage.defaultPath, TaskList.listOfItems);
            System.out.println(Ui.COMMENT_LOAD_ENTRIES);
            System.out.println(Ui.DIVIDER);
        }catch (FileNotFoundException e){
            File f =new File(Storage.defaultPath);
            f.createNewFile();
            System.out.println(Ui.COMMENT_CREATE_SAVE);
            System.out.println(Ui.DIVIDER);
        }
    }

    /**
     * Updates the local storage file based on current arraylist of tasks.
     * @param defaultPath path of the local storage file.
     * @param listOfItems current arraylist of tasks.
     */
    public static void updateSavedFile(String defaultPath, ArrayList<Task> listOfItems) {
        try {
            Storage.writeToFile(defaultPath, listOfItems);
        } catch (IOException e) {
            System.out.println(Ui.MESSAGE_UNKNOWN_ERROR + e.getMessage());
        }
    }
}