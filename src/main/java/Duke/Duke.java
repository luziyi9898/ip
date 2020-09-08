package Duke;

import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String inputStatement;
        boolean hasEnded = false;
        String separatingLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        final int MAX_TASKS_IN_ARRAY = 100;
        Task[] listOfItems = new Task[MAX_TASKS_IN_ARRAY];
        int listWordCount = 0;

        printWelcomeText(separatingLine);
        printBetweenLines(separatingLine, "Greetings, care for a cup of coffee?");

        //loop begins:
        while (!hasEnded) {
            Scanner in = new Scanner(System.in);
            inputStatement = in.nextLine();
            if (inputStatement.equalsIgnoreCase("bye")) {
                //signals end of programme
                hasEnded = true;
            } else if (inputStatement.equalsIgnoreCase("list") ){
                System.out.println(separatingLine);
                //print out list of items
                try {
                    printArrangedList(listOfItems);
                } catch (IllegalCommandsException e){
                    System.out.println("Your list is empty, no lollygagging");
                }
                System.out.println(separatingLine);
            } else if(inputStatement.startsWith("done")){
                System.out.println(separatingLine);
                try {
                    markAsDone(inputStatement, listOfItems);
                } catch (IllegalCommandsException e){
                    System.out.println("Calm done mate, you finished that already.");
                }catch (NumberFormatException e){
                    System.out.println("Tip of the day: End your done command with a number next time");
                }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
                    System.out.println("You can't do something that doesn't exist, or can you?");
                }
                System.out.println(separatingLine);
            } else if(inputStatement.startsWith("todo")|inputStatement.startsWith("event")
                    |inputStatement.startsWith("deadline")) {
                //proceeds to add task if task is valid
                try {
                    listWordCount = classifyAndAddTask(inputStatement, separatingLine, listOfItems, listWordCount);
                }catch (StringIndexOutOfBoundsException | IllegalCommandsException e){
                    printBetweenLines(separatingLine, "That's not a valid task!");
                }
            } else{
                printBetweenLines(separatingLine, "That's not a valid command!");
            }

        }
        //ending messages
        printBetweenLines(separatingLine, "We shall meet again...");
    }

    private static void printWelcomeText(String separatingLine) {
        System.out.println(separatingLine);
        System.out.println("Hey, welcome to my version of Duke and please make good use of it.");
        System.out.println("The bot is meant to be a bit cheeky at times so don't mind it.");
        System.out.println("Here is a list of commands you can try out:");
        System.out.println("\u2022[todo ___] adds a todo task to your list.");
        System.out.println("\u2022[deadline ___ /by ___] adds a deadline task to your list with a deadline.");
        System.out.println("\u2022[event ___ /at ___] adds a event task to your list with a duration.");
        System.out.println("\u2022[done (index of task)] marks a task as done in your list.");
        System.out.println("\u2022[list] prints out your current list and status of tasks.");
        System.out.println("\u2022[bye] exits the programme.");
    }

    private static int classifyAndAddTask(String inputStatement, String separatingLine
            , Task[] listOfItems, int listWordCount) throws IllegalCommandsException {
        //determines what type of task is inputted, before add it to listOfItems and then incrementing listWordCount
        int indexOfSlash = inputStatement.indexOf("/");
        String numberOfTasks = "\nNow you have " + (listWordCount+1) + " tasks in the list.";
        if(inputStatement.startsWith("todo")){
            listOfItems[listWordCount] = new Todo(inputStatement.substring(4).trim());
            printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                    + listOfItems[listWordCount].getTaskDescription() + numberOfTasks);
            listWordCount++;
        } else  if(inputStatement.startsWith("deadline")){
            if(!inputStatement.contains("/by")){
                throw new IllegalCommandsException();
            }
            listOfItems[listWordCount] = new Deadline(getDescriptionFromInput(inputStatement, 8)
                    , getTimeFromInput(inputStatement, indexOfSlash + 3));

            printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                    + listOfItems[listWordCount].getTaskDescription() + numberOfTasks);
            listWordCount++;

        }
        else if(inputStatement.startsWith("event")){
            if(!inputStatement.contains("/at")){
                throw new IllegalCommandsException();
            }
            listOfItems[listWordCount] = new Event(getDescriptionFromInput(inputStatement, 5)
                    , getTimeFromInput(inputStatement, indexOfSlash + 3));

            printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                    + listOfItems[listWordCount].getTaskDescription() + numberOfTasks);
            listWordCount++;

        }
        return listWordCount;
    }

    private static String getTimeFromInput(String inputStatement, int i) {
        //i represents default letters in command i.e. (/at )
        return inputStatement.substring(i).trim();
    }

    private static String getDescriptionFromInput(String inputStatement, int i) {
        //i represents the number of letters in the command
        return inputStatement.substring(i, inputStatement.indexOf("/")).trim();
    }

    private static void markAsDone(String inputStatement, Task[] listOfItems) throws IllegalCommandsException {
        int indexOfItem = Integer.parseInt(inputStatement.substring(4).trim());
        if(listOfItems[indexOfItem - 1].getStatus()){
            //checks if the completed item is already done.
            throw new IllegalCommandsException();
        }
        System.out.println("One more down, but at what cost...");
        listOfItems[indexOfItem-1].markAsDone();
        System.out.println(listOfItems[indexOfItem-1].getTaskDescription());
    }

    private static void printBetweenLines(String separatingLine, String s) {
        System.out.println(separatingLine);
        System.out.println(s);
        System.out.println(separatingLine);
    }

    private static void printArrangedList(Task[] list) throws IllegalCommandsException {
        //throws an error exception if list is empty
        if (list[0] == null) {
            throw new IllegalCommandsException();
        }
        //a function that prints the array without returning any values
        System.out.println("Here's what you have:");
        String[] newList = new String[list.length];
        int listIndex = 0;
        for(Task item: list ) {
            if (item != null) {
                newList[listIndex] = (listIndex+1)  + ". "+ item.getTaskDescription() ;
                listIndex++;
            }
        }
        for(String item: Arrays.copyOf(newList, listIndex) ){
            System.out.println(item);
        }
    }




}
