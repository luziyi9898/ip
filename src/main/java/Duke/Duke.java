package Duke;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String inputStatement;
        boolean hasEnded = false;

        String defaultPath = "duke.txt";
        ArrayList<Task> listOfItems = new ArrayList<>();
        int listWordCount;


        Ui.printWelcomeText();

        Ui.printBetweenLines("Greetings, care for a cup of coffee?");

        try {
            Storage.loadSavedText(defaultPath, listOfItems);
            System.out.println("Previous entries uploaded.");
            System.out.println(Ui.DIVIDER);
        }catch (FileNotFoundException e){
           File f =new File(defaultPath);
           f.createNewFile();
           System.out.println("Save not detected, creating new save file.");
           System.out.println(Ui.DIVIDER);
        }
        listWordCount = listOfItems.size();

        //loop begins:
        while (!hasEnded) {
            Scanner in = new Scanner(System.in);
            inputStatement = in.nextLine();
            if (inputStatement.equalsIgnoreCase("bye")) {
                //signals end of programme
                hasEnded = true;
            } else if (inputStatement.equalsIgnoreCase("list") ){
                System.out.println(Ui.DIVIDER);
                //print out list of items
                try {
                    TaskList.printArrangedList(listOfItems);
                } catch (IllegalCommandsException e){
                    //throws exception when list is empty.
                    System.out.println("Your list is empty, no lollygagging");
                }
                System.out.println(Ui.DIVIDER);
            } else if(inputStatement.startsWith("done")){
                System.out.println(Ui.DIVIDER);
                try {
                    TaskList.markAsDone(inputStatement, listOfItems);
                } catch (IllegalCommandsException e){
                    //throws exception when doing task that is already done.
                    System.out.println("Calm done mate, you finished that already.");
                }catch (NumberFormatException e){
                    //throws exception when done command is not followed by a number.
                    System.out.println("Tip of the day: End your done command with a number next time");
                }catch (ArrayIndexOutOfBoundsException | NullPointerException e){
                    //throws exception when done command is followed by an empty task or task outside of array.
                    System.out.println("You can't do something that doesn't exist, or can you?");
                }
                System.out.println(Ui.DIVIDER);
            } else if(inputStatement.startsWith("delete")){
                System.out.println(Ui.DIVIDER);
                try {
                    Storage.deleteItem(inputStatement, listOfItems);
                    listWordCount = listWordCount - 1;
                    System.out.println("Now you have " + (listWordCount) + " tasks in the list.");
                }catch(IndexOutOfBoundsException|NumberFormatException e){
                    System.out.println("You can't delete that!");
                }
                System.out.println(Ui.DIVIDER);
            }else if(inputStatement.startsWith("todo")|inputStatement.startsWith("event")
                    |inputStatement.startsWith("deadline")) {
                //proceeds to add task if task is valid.
                try {
                    listWordCount = TaskList.classifyAndAddTask(inputStatement, listOfItems, listWordCount);
                }catch (StringIndexOutOfBoundsException | IllegalCommandsException e){
                    //throws exception when adding invalid tasks.
                    Ui.printBetweenLines("That's not a valid task!");
                }

            } else{
                //throws exception when inputting invalid commands.
                Ui.printBetweenLines("That's not a valid command!");
            }
            //update the text document
            try {
                Storage.writeToFile(defaultPath, listOfItems);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }


        }
        //ending messages
        Ui.printBetweenLines("We shall meet again...");
    }


}
