import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String inputStatement;
        int endFlag = 0;
        String separatingLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        Task[] listOfItems = new Task[100];
        int listWordCount = 0;

        //greeting lines start:
        System.out.println(separatingLine);
        System.out.println("Greetings, care for a cup of coffee?");
        System.out.println(separatingLine);
        //greeting lines end:

        //loop begins:
        while (endFlag == 0) {
            Scanner in = new Scanner(System.in);
            inputStatement = in.nextLine();
            if (inputStatement.equalsIgnoreCase("bye")) {
                //signals end of programme
                endFlag = 1;
            }
            else if (inputStatement.equalsIgnoreCase("list") ){
                //checks list
                System.out.println(separatingLine);
                if(listOfItems[0] == null){
                    //checks if the list has a first item
                    System.out.println("Your list is empty, no lollygagging");
                    System.out.println(separatingLine);
                    continue;
                }
                System.out.println("Here's what you have:");
                printArrangedList(listOfItems);
                System.out.println(separatingLine);
            }
            else if(inputStatement.startsWith("done")){
                //mark task as done
                System.out.println(separatingLine);
                if (!checkIfDoneIsValid(inputStatement.substring(4).trim())){
                    System.out.println("Tip of the day: End your done command with a number next time");
                    System.out.println(separatingLine);
                    continue;
                }
                int indexOfItem = Integer.parseInt(inputStatement.substring(4).trim());

                if ((indexOfItem>99) || ( listOfItems[indexOfItem-1]==null) ){
                    //checks if the completed item is valid.
                    System.out.println("You can't do something that doesn't exist, or can you?");
                    System.out.println(separatingLine);
                    continue;
                }
                if(listOfItems[indexOfItem - 1].getStatus()){
                    //checks if the completed item is already done.
                    System.out.println("Calm done mate, you finished that already.");
                    System.out.println(separatingLine);
                    continue;
                }
                System.out.println("One more down, but at what cost...");
                listOfItems[indexOfItem-1].markAsDone();
                System.out.println("[" + listOfItems[indexOfItem-1].getStatusIcon() +"] " + listOfItems[indexOfItem-1].getTaskDescription());
                System.out.println(separatingLine);
            }

            else{
                //echo input and add to task
                System.out.println(separatingLine);
                System.out.println("More work ay? Here's what you need to do: " + inputStatement);
                System.out.println(separatingLine);


                listOfItems[listWordCount] = new Task(inputStatement);
                listWordCount++;
            }
        }
        //loop ends

        //ending messages
        System.out.println(separatingLine);
        System.out.println("We shall meet again...");
        System.out.println(separatingLine);


    }

    public static void printArrangedList(Task[] list){
        //a function that prints the array without returning any values
        String[] newList = new String[list.length];
        int listIndex = 0;
        for(Task item: list ) {
            if (item != null) {
                newList[listIndex] = (listIndex+1)  + ". [" + item.getStatusIcon() +"] " + item.getTaskDescription() ;
                listIndex++;
            }
        }
        for(String item: Arrays.copyOf(newList, listIndex) ){
            System.out.println(item);
        }
    }

    public static Boolean checkIfDoneIsValid(String stringAfterDone){
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
