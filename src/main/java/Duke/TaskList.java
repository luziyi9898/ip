package Duke;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    public static ArrayList<Task> listOfItems = new ArrayList<>();
    public static int listWordCount;


    public static int addTask(String inputStatement
            , ArrayList<Task> listOfItems, int listWordCount) throws IllegalCommandsException {
        //determines what type of task is inputted, before add it to listOfItems and then incrementing listWordCount
        int indexOfSlash = inputStatement.indexOf("/");
        String numberOfTasks = "\nNow you have " + (listWordCount + 1) + " tasks in the list.";
        if (inputStatement.startsWith("todo")) {
            listOfItems.add(new Todo(inputStatement.substring(4).trim()));
            Ui.printBetweenLines("More work ay? Here's what you need to do: \n"
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
            listWordCount++;
        } else if (inputStatement.startsWith("deadline")) {
            if (!inputStatement.contains("/by")) {
                //throws exception when deadline commands are not followed with /by
                throw new IllegalCommandsException();
            }
            listOfItems.add(new Deadline(getDescriptionFromInput(inputStatement, 8),
                    getTimeFromInput(inputStatement, indexOfSlash + 3)));

            Ui.printBetweenLines("More work ay? Here's what you need to do: \n"
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
            listWordCount++;

        } else if (inputStatement.startsWith("event")) {
            if (!inputStatement.contains("/at")) {
                //throws exception when event commands are not followed with /at
                throw new IllegalCommandsException();
            }
            listOfItems.add(new Event(getDescriptionFromInput(inputStatement, 5)
                    , getTimeFromInput(inputStatement, indexOfSlash + 3)));

            Ui.printBetweenLines("More work ay? Here's what you need to do: \n"
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
            listWordCount++;

        }
        return listWordCount;
    }

    static String getTimeFromInput(String inputStatement, int i) {
        //i represents default letters in command i.e. (/at )
        return inputStatement.substring(i).trim();
    }

    static String getDescriptionFromInput(String inputStatement, int i) {
        //i represents the number of letters in the command
        return inputStatement.substring(i, inputStatement.indexOf("/")).trim();
    }

    public static void markAsDone(String inputStatement, ArrayList<Task> listOfItems) throws IllegalCommandsException {
        int indexOfItem = Integer.parseInt(inputStatement.substring(4).trim());
        if (listOfItems.get(indexOfItem - 1).getStatus()) {
            //checks if the completed item is already done.
            throw new IllegalCommandsException();
        }
        System.out.println("One more down, but at what cost...");
        listOfItems.get(indexOfItem - 1).markAsDone();
        System.out.println(listOfItems.get(indexOfItem - 1).getTaskDescription());
    }

    public static void printArrangedList(ArrayList<Task> list) throws IllegalCommandsException {
        //throws an error exception if list is empty
        if (list.size() == 0) {
            throw new IllegalCommandsException();
        }
        //a function that prints the array without returning any values
        System.out.println("Here's what you have:");
        String[] newList = new String[list.size()];
        int listIndex = 0;
        for (Task item : list) {
            if (item != null) {
                newList[listIndex] = (listIndex + 1) + ". " + item.getTaskDescription();
                listIndex++;
            }
        }
        for (String item : Arrays.copyOf(newList, listIndex)) {
            System.out.println(item);
        }
    }
}