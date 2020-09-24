package Duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parses user input.
 */
public class Parser {

    public static boolean hasEnded = false;

    /**
     * Reads user inputs and react accordingly.
     * @param hasEnded flag signifying end of program.
     * @param defaultPath path of the local storage file.
     * @param listOfItems Arraylist of all the tasks currently present.
     */

    public static void runCommandLoop(boolean hasEnded, String defaultPath, ArrayList<Task> listOfItems) {
        String inputStatement;
        while (!hasEnded) {
            Scanner in = new Scanner(System.in);
            inputStatement = in.nextLine();
            int listWordCount = TaskList.listOfItems.size();

            if (inputStatement.equalsIgnoreCase(Ui.COMMAND_BYE)) {
                hasEnded = executesExit();
            } else if (inputStatement.equalsIgnoreCase(Ui.COMMAND_LIST)) {
                executesList(listOfItems);
            } else if (inputStatement.startsWith(Ui.COMMAND_DONE)) {
                executesDone(listOfItems, inputStatement);
            } else if (inputStatement.startsWith(Ui.COMMAND_DELETE)) {
                executesDelete(listOfItems, inputStatement, listWordCount);
            } else if (inputStatement.startsWith(Ui.COMMAND_TODO) | inputStatement.startsWith(Ui.COMMAND_EVENT)
                    | inputStatement.startsWith(Ui.COMMAND_DEADLINE)) {
                executesAddTask(listOfItems, inputStatement, listWordCount);
            } else {
                //throws exception when inputting invalid commands.
                Ui.printBetweenLines(Ui.MESSAGE_INVALID_COMMAND);
            }
            //update the text document
            Storage.updateSavedFile(defaultPath, listOfItems);


        }
    }

    private static void executesAddTask(ArrayList<Task> listOfItems, String inputStatement, int listWordCount) {
        //proceeds to add task if task is valid.
        try {
            TaskList.addTask(inputStatement, listOfItems, listWordCount);
        } catch (StringIndexOutOfBoundsException | IllegalCommandsException e) {
            //throws exception when adding invalid tasks.
            Ui.printBetweenLines(Ui.MESSAGE_INVALID_TASK);
        }
    }

    private static void executesDelete(ArrayList<Task> listOfItems, String inputStatement, int listWordCount) {
        System.out.println(Ui.DIVIDER);
        try {
            TaskList.deleteItem(inputStatement, listOfItems);
            listWordCount = listWordCount - 1;
            Ui.printNumberOfTasks(listWordCount);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(Ui.MESSAGE_INVALID_DELETE);
        }
        System.out.println(Ui.DIVIDER);
    }

    private static void executesDone(ArrayList<Task> listOfItems, String inputStatement) {
        System.out.println(Ui.DIVIDER);
        try {
            TaskList.markAsDone(inputStatement, listOfItems);
        } catch (IllegalCommandsException e) {
            //throws exception when doing task that is already done.
            System.out.println(Ui.MESSAGE_REPEAT_TASK);
        } catch (NumberFormatException e) {
            //throws exception when done command is not followed by a number.
            System.out.println(Ui.MESSAGE_INVALID_DONE);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            //throws exception when done command is followed by an empty task or task outside of array.
            System.out.println(Ui.MESSAGE_INVALID_DONE_INDEX);
        }
        System.out.println(Ui.DIVIDER);
    }

    private static void executesList(ArrayList<Task> listOfItems) {
        System.out.println(Ui.DIVIDER);
        //print out list of items
        try {
            TaskList.printArrangedList(listOfItems);
        } catch (IllegalCommandsException e) {
            //throws exception when list is empty.
            System.out.println(Ui.MESSAGE_EMPTY_LIST);
        }
        System.out.println(Ui.DIVIDER);
    }

    private static boolean executesExit() {
        //signals end of programme
        return true;
    }


}