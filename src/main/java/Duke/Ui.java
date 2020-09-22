package Duke;

public class Ui {

    public static final String DIVIDER= "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public static void printWelcomeText() {
        System.out.println(DIVIDER);
        System.out.println("Hey, welcome to my version of Duke and please make good use of it.");
        System.out.println("The bot is meant to be a bit cheeky at times so don't mind it.");
        System.out.println("Here is a list of commands you can try out:");
        System.out.println("\u2022[todo ___] adds a todo task to your list.");
        System.out.println("\u2022[deadline ___ /by yyyy-mm-dd] adds a deadline task to your list with a deadline.");
        System.out.println("\u2022[event ___ /at yyyy-mm-dd] adds a event task to your list with a duration.");
        System.out.println("\u2022[done (index of task)] marks a task as done in your list.");
        System.out.println("\u2022[list] prints out your current list and status of tasks.");
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
}