import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String inputStatement;
        int endEchoFlag = 0;
        String separatingLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        Task[] listOfItems = new Task[100];
        int listWordCount = 0;

        //greeting lines start:
        System.out.println(separatingLine);
        System.out.println("Greetings, care for a cup of coffee?");
        System.out.println(separatingLine);
        //greeting lines end:

        //echo loop begins:
        while (endEchoFlag == 0) {
            Scanner in = new Scanner(System.in);
            inputStatement = in.nextLine();
            if (inputStatement.equalsIgnoreCase("bye")) {
                endEchoFlag = 1;
            }
            else if (inputStatement.equalsIgnoreCase("list") ){
                System.out.println(separatingLine);
                System.out.println("Here's what you have:");
                printArrangedList(listOfItems);
                System.out.println(separatingLine);
            }
            else{
                System.out.println(separatingLine);
                System.out.println("More work ay? Here's what you need to do: " + inputStatement);
                System.out.println(separatingLine);


                listOfItems[listWordCount] = new Task(inputStatement);
                listWordCount++;
            }
        }
        //echo loop ends

        System.out.println(separatingLine);
        System.out.println("We shall meet again...");
        System.out.println(separatingLine);


    }

    public static void printArrangedList(Task[] list){    //a function that prints the array without returning any values
        String[] newList = new String[list.length];
        int listIndex = 0;
        for(Task item: list ) {
            if (item != null) {
                newList[listIndex] = (listIndex+1) + ". " + item.getTaskDescription() ;
                listIndex++;
            }
        }
        for(String item: Arrays.copyOf(newList, listIndex) ){
            System.out.println(item);
        }


    }

}
