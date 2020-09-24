package Duke;

public class Ui {

    public static final String DIVIDER= "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public static final String MESSAGE_EMPTY_LIST = "Your list is empty, no lollygagging";
    public static final String MESSAGE_REPEAT_TASK = "Calm done mate, you finished that already.";
    public static final String MESSAGE_INVALID_DONE = "Tip of the day: End your done command with a number next time";
    public static final String MESSAGE_INVALID_DONE_INDEX = "You can't do something that doesn't exist, or can you?";
    public static final String MESSAGE_INVALID_DELETE = "You can't delete that!";
    public static final String MESSAGE_INVALID_TASK = "That's not a valid task!";
    public static final String MESSAGE_INVALID_COMMAND = "That's not a valid command!";
    public static final String COMMENT_ADD_TASK = "More work ay? Here's what you need to do: \n";
    public static final String MESSAGE_INVALID_TIME = "Add time as yyyy/mm/dd";
    public static final String COMMENT_MARK_AS_DONE = "One more down, but at what cost...";
    public static final String COMMENT_PRINT_LIST = "Here's what you have:";
    public static final String COMMENT_DELETE_ITEM = "Must be nice to have less things to do, right? " +
            "Here's what you removed:\n";
    public static final String COMMENT_LOAD_ENTRIES = "Previous entries uploaded.";
    public static final String COMMENT_CREATE_SAVE = "Save not detected, creating new save file.";
    public static final String MESSAGE_UNKNOWN_ERROR ="Something went wrong: ";
    public static final String MESSAGE_INVALID_FIND = "Unable to find this task.";
    public static final String COMMENT_PRINT_SEARCH = "Here's the matching tasks:";

    public static final String COMMAND_BYE= "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_FIND = "find";

    public static final String TODO_ICON = "[T]";
    public static final String EVENT_ICON = "[E]";
    public static final String DEADLINE_ICON = "[D]";


    public static void printWelcomeText() {
        System.out.println(DIVIDER);
        System.out.println("Hey, welcome to my version of Duke and please make good use of it.");
        System.out.println("The bot is meant to be a bit cheeky at times so don't mind it.");
        System.out.println("Here is a list of commands you can try out:");
        System.out.println("\u2022[todo ___] adds a todo task to your list.");
        System.out.println("\u2022[deadline ___ /by yyyy/mm/dd] adds a deadline task to your list with a deadline.");
        System.out.println("\u2022[event ___ /at yyyy/mm/dd] adds a event task to your list with a duration.");
        System.out.println("\u2022[done (index of task)] marks a task as done in your list.");
        System.out.println("\u2022[list] prints out your current list and status of tasks.");
        System.out.println("\u2022[find ___] lets you find a task based on its name.");
        System.out.println("\u2022[bye] exits the programme.");
        System.out.println(DIVIDER);
        System.out.println("Greetings, care for a cup of coffee?");
        System.out.println(DIVIDER);
    }

    public static void printBetweenLines(String s) {
        System.out.println(DIVIDER);
        System.out.println(s);
        System.out.println(DIVIDER);
    }
    public static void printEndingText() {
        printBetweenLines("We shall meet again...");
    }

    public static void printNumberOfTasks(Integer listWordCount) {
        System.out.println("Now you have " + listWordCount + " tasks in the list.");
    }

}