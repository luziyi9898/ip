package Duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

/**
 * Contains task list  and operations to add and remove tasks.
 */

public class TaskList {

    public static ArrayList<Task> listOfItems = new ArrayList<>();

    /**
     * Adds a new task to the arraylist of tasks.
     * @param inputStatement user input that describes the task.
     * @param listOfItems Arraylist of all the tasks currently present.
     * @param listWordCount Counter for current number of tasks in arraylist.
     * @return updated number of tasks in the list.
     * @throws IllegalCommandsException when command inputted is invalid.
     */
    public static int addTask(String inputStatement
            , ArrayList<Task> listOfItems, int listWordCount) throws IllegalCommandsException {
        //determines what type of task is inputted, before add it to listOfItems and then incrementing listWordCount
        int indexOfSlash = inputStatement.indexOf("/");
        String numberOfTasks = "\nNow you have " + (listWordCount + 1) + " tasks in the list.";

        if (inputStatement.startsWith(Ui.COMMAND_TODO)) {
            listWordCount = addTodo(inputStatement, listOfItems, listWordCount, numberOfTasks);
        } else if (inputStatement.startsWith(Ui.COMMAND_DEADLINE)) {
            listWordCount = addDeadline(inputStatement, listOfItems, listWordCount, indexOfSlash, numberOfTasks);
        } else if (inputStatement.startsWith(Ui.COMMAND_EVENT)) {
            listWordCount = addEvent(inputStatement, listOfItems, listWordCount, indexOfSlash, numberOfTasks);
        }
        return listWordCount;
    }


    /**
     * Mark a specified task as done based on their index.
     * @param inputStatement user input that determines which task is to be done based on index.
     * @param listOfItems Arraylist of all the tasks currently present.
     * @throws IllegalCommandsException when trying to do a task that's already done.
     */
    public static void markAsDone(String inputStatement, ArrayList<Task> listOfItems) throws IllegalCommandsException {
        int indexOfItem = Integer.parseInt(inputStatement.substring(4).trim());
        if (listOfItems.get(indexOfItem - 1).getStatus()) {
            //checks if the completed item is already done.
            throw new IllegalCommandsException();
        }
        System.out.println(Ui.COMMENT_MARK_AS_DONE);
        listOfItems.get(indexOfItem - 1).markAsDone();
        System.out.println(listOfItems.get(indexOfItem - 1).getTaskDescription());
    }

    /**
     * Print out current list of inputs with their indexes.
     * @param list Arraylist of all the tasks currently present.
     * @throws IllegalCommandsException when list is empty.
     */

    public static void printArrangedList(ArrayList<Task> list) throws IllegalCommandsException {
        //throws an error exception if list is empty
        if (list.size() == 0) {
            throw new IllegalCommandsException();
        }
        //a function that prints the array without returning any values
        System.out.println(Ui.COMMENT_PRINT_LIST);
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


    public static void findTask(ArrayList<Task> list, String inputStatement) throws IllegalCommandsException {
        String keyWord = inputStatement.substring(4).trim();
        String[] newList = new String[list.size()];
        int listIndex = 0;
        for (Task item : list) {
            if (item.getTaskName().contains(keyWord)) {
                newList[listIndex] = (listIndex + 1) + ". " + item.getTaskDescription();
                listIndex++;
            }
        }
        if (listIndex > 0) {
            System.out.println(Ui.COMMENT_PRINT_SEARCH);
            for (String item : Arrays.copyOf(newList, listIndex)) {
                System.out.println(item);
            }
        } else
            throw new IllegalCommandsException();
    }
    /**
     * Delete a task from the arraylist of tasks.
     * @param inputStatement user input that determines which task is to be deleted based on index.
     * @param list Arraylist of all the tasks currently present.
     */

    public static void deleteItem(String inputStatement, ArrayList<Task> list) {
        int indexOfRemovedItem = Integer.parseInt(inputStatement.substring(6).trim());
        System.out.println(Ui.COMMENT_DELETE_ITEM
                + list.get(indexOfRemovedItem - 1).getTaskDescription());
        list.remove(indexOfRemovedItem - 1);

    }

    private static int addEvent(String inputStatement, ArrayList<Task> listOfItems, int listWordCount
            , int indexOfSlash, String numberOfTasks) throws IllegalCommandsException {
        if (!inputStatement.contains("/at")) {
            //throws exception when event commands are not followed with /at
            throw new IllegalCommandsException();
        }
        try {
            listOfItems.add(new Event(getDescriptionFromInput(inputStatement, 5),
                    getTimeFromInput(inputStatement.substring(indexOfSlash + 3))));
            Ui.printBetweenLines(Ui.COMMENT_ADD_TASK
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
            listWordCount++;
        }catch(DateTimeParseException e){
            Ui.printBetweenLines(Ui.MESSAGE_INVALID_TIME);
        }
        return listWordCount;
    }

    private static int addDeadline(String inputStatement, ArrayList<Task> listOfItems
            , int listWordCount, int indexOfSlash, String numberOfTasks) throws IllegalCommandsException {
        if (!inputStatement.contains("/by")) {
            //throws exception when deadline commands are not followed with /by
            throw new IllegalCommandsException();
        }
        try {
            listOfItems.add(new Deadline(getDescriptionFromInput(inputStatement, 8),
                    getTimeFromInput(inputStatement.substring(indexOfSlash + 3))));
            Ui.printBetweenLines(Ui.COMMENT_ADD_TASK
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
            listWordCount++;
        }catch(DateTimeParseException e){
            Ui.printBetweenLines(Ui.MESSAGE_INVALID_TIME);
        }
        return listWordCount;
    }

    private static int addTodo(String inputStatement, ArrayList<Task> listOfItems, int listWordCount, String numberOfTasks) {
        listOfItems.add(new Todo(inputStatement.substring(4).trim()));
        Ui.printBetweenLines(Ui.COMMENT_ADD_TASK
                + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
        listWordCount++;
        return listWordCount;
    }

    private static LocalDate getTimeFromInput(String inputStatement) {
        return LocalDate.parse(inputStatement.trim().replace('/','-'));
    }

    private static String getDescriptionFromInput(String inputStatement, int i) {
        //i represents the number of letters in the command
        return inputStatement.substring(i, inputStatement.indexOf("/")).trim();
    }


}