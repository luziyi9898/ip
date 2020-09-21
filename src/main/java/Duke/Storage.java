package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static String defaultPath = "duke.txt";

    public static void writeToFile(String filePath, ArrayList<Task> list) throws IOException {

        FileWriter fw = new FileWriter(filePath);
        for (Task item : list) {
            if (item != null) {
                fw.write(item.getTaskDescription() + System.lineSeparator());
            }
        }
        fw.close();
    }

    public static void deleteItem(String inputStatement, ArrayList<Task> list) {
        int indexOfRemovedItem = Integer.parseInt(inputStatement.substring(6).trim());
        System.out.println("Must be nice to have less things to do, right? Here's what you removed:\n"
                + list.get(indexOfRemovedItem - 1).getTaskDescription());
        list.remove(indexOfRemovedItem - 1);
    }

    public static void loadSavedText(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int listIndex = 0;
        while (s.hasNext()) {
            String importedCommand = s.nextLine();

            Integer currentStatusSymbol = importedCommand.codePointAt(4);

            if (importedCommand.startsWith("[T]")) {
                list.add(new Todo(importedCommand.substring(7).trim()));
            } else if (importedCommand.startsWith("[D]")) {
                list.add(new Deadline(importedCommand.substring(7, importedCommand.indexOf("(") - 1),
                        importedCommand.substring(importedCommand.indexOf("by:") + 3, importedCommand.length() - 1)));
            } else if (importedCommand.startsWith("[E]")) {
                list.add(new Event(importedCommand.substring(7, importedCommand.indexOf("(") - 1),
                        importedCommand.substring(importedCommand.indexOf("at:") + 3, importedCommand.length() - 1)));
            }
            if (currentStatusSymbol.equals(10003)) {
                list.get(listIndex).markAsDone();
            }
            listIndex += 1;
        }
    }

    public static void loadPreviousEntries() throws IOException {
        try {
            Storage.loadSavedText(Storage.defaultPath, TaskList.listOfItems);
            System.out.println("Previous entries uploaded.");
            System.out.println(Ui.DIVIDER);
        }catch (FileNotFoundException e){
            File f =new File(Storage.defaultPath);
            f.createNewFile();
            System.out.println("Save not detected, creating new save file.");
            System.out.println(Ui.DIVIDER);
        }
    }
    public static void updateSavedFile(String defaultPath, ArrayList<Task> listOfItems) {
        try {
            Storage.writeToFile(defaultPath, listOfItems);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}