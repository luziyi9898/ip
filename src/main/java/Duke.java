import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String inputStatement;
        int endFlag = 0;
        String separatingLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        Task[] listOfItems = new Task[100];
        int listWordCount = 0;

        printWelcomeText(separatingLine);
        printBetweenLines(separatingLine, "Greetings, care for a cup of coffee?");

        //loop begins:
        while (endFlag == 0) {
            Scanner in = new Scanner(System.in);
            inputStatement = in.nextLine();
            if (inputStatement.equalsIgnoreCase("bye")) {
                //signals end of programme
                endFlag = 1;
            }
            else if (inputStatement.equalsIgnoreCase("list") ){
                System.out.println(separatingLine);
                //makes sure list is not empty
                if (checkListForItems(separatingLine, listOfItems[0] == null)) continue;
                //print out list of items
                System.out.println("Here's what you have:");
                printArrangedList(listOfItems);
                System.out.println(separatingLine);
            }
            else if(inputStatement.startsWith("done")){
                System.out.println(separatingLine);
                markAsDone(inputStatement, separatingLine, listOfItems);
            }
            else {
                //proceeds to add task if task is valid
                listWordCount = classifyAndAddTask(inputStatement, separatingLine, listOfItems, listWordCount);
            }
        }
        //loop ends

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
            ,Task[] listOfItems, int listWordCount) {
        //determines what type of task is inputted, before add it to listOfItems and then incrementing listWordCount
        int indexOfSlash = inputStatement.indexOf("/");
        String numberOfTasks = "\nNow you have " + (listWordCount+1) + " tasks in the list.";
        if(inputStatement.startsWith("todo")){
            listOfItems[listWordCount] = new Todo(inputStatement.substring(4).trim());
            printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                    + listOfItems[listWordCount].getTaskDescription() + numberOfTasks);
            listWordCount++;
        }
       else  if(inputStatement.startsWith("deadline")){
           if(!inputStatement.contains("/by")){
               printBetweenLines(separatingLine, "That's not a valid deadline");
           }
           else {
               listOfItems[listWordCount] = new Deadline(getDescriptionFromInput(inputStatement, 8)
                       , getTimeFromInput(inputStatement, indexOfSlash + 3));

               printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                       + listOfItems[listWordCount].getTaskDescription() + numberOfTasks);
               listWordCount++;
           }
        }
        else if(inputStatement.startsWith("event")){
            if(!inputStatement.contains("/at")){
                printBetweenLines(separatingLine, "That's not a valid event");
            }
            else {
                listOfItems[listWordCount] = new Event(getDescriptionFromInput(inputStatement, 5)
                        , getTimeFromInput(inputStatement, indexOfSlash + 3));

                printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                        + listOfItems[listWordCount].getTaskDescription() + numberOfTasks);
                listWordCount++;
            }
        }
        else{
            printBetweenLines(separatingLine, "That's not a valid command.");
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

    private static void markAsDone(String inputStatement, String separatingLine, Task[] listOfItems) {
        //confirms that input after done is a number
        if (!checkIfDoneIsValid(inputStatement.substring(4).trim())){
            System.out.println("Tip of the day: End your done command with a number next time");
            System.out.println(separatingLine);
            return;
        }
        //confirms that input after done is valid
        int indexOfItem = Integer.parseInt(inputStatement.substring(4).trim());
        if ((indexOfItem>99) || ( listOfItems[indexOfItem-1]==null) ){
            //checks if the completed item is valid.
            System.out.println("You can't do something that doesn't exist, or can you?");
            System.out.println(separatingLine);
            return;
        }
        else if(listOfItems[indexOfItem - 1].getStatus()){
            //checks if the completed item is already done.
            System.out.println("Calm done mate, you finished that already.");
            System.out.println(separatingLine);
            return;
        }
        System.out.println("One more down, but at what cost...");
        listOfItems[indexOfItem-1].markAsDone();
        System.out.println(listOfItems[indexOfItem-1].getTaskDescription());
        System.out.println(separatingLine);
    }

    private static boolean checkListForItems(String separatingLine, boolean b) {
        if (b) {
            //checks if the list has a first item
            System.out.println("Your list is empty, no lollygagging");
            System.out.println(separatingLine);
            return true;
        }
        return false;
    }

    private static void printBetweenLines(String separatingLine, String s) {
        System.out.println(separatingLine);
        System.out.println(s);
        System.out.println(separatingLine);
    }

    private static void printArrangedList(Task[] list){
        //a function that prints the array without returning any values
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

    private static Boolean checkIfDoneIsValid(String stringAfterDone){
        //checks what comes after done are valid numerics, or is not just empty space
        char[] string = stringAfterDone.toCharArray();
        if(string.length == 0){
            return false;
        }
        for (char word : string) {
            if (!Character.isDigit(word)){
                return false;
            }
        }
        return true;
    }

}
