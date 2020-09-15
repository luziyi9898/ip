package Duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String inputStatement;
        boolean hasEnded = false;
        String separatingLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String defaultPath = "docs/duke.txt";
        ArrayList<Task> listOfItems = new ArrayList<>();
        int listWordCount;


        printWelcomeText(separatingLine);
        printBetweenLines(separatingLine, "Greetings, care for a cup of coffee?");

        try {
            //loadSavedText("docs/duke.txt", listOfItems);
            //listOfItems = loadSavedText("docs/duke.txt", listOfItems);
            loadSavedText(defaultPath, listOfItems);
            System.out.println("Previous entries uploaded.");
            System.out.println(separatingLine);
        }catch (FileNotFoundException e){
           File f =new File(defaultPath);
           f.createNewFile();
           System.out.println("Save not detected, creating new save file.");
           System.out.println(separatingLine);
        }

        //listWordCount = updateListWordCount(listWordCount, listOfItems);
        listWordCount = listOfItems.size();


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
                    //throws exception when list is empty.
                    System.out.println("Your list is empty, no lollygagging");
                }
                System.out.println(separatingLine);
            } else if(inputStatement.startsWith("done")){
                System.out.println(separatingLine);
                try {
                    markAsDone(inputStatement, listOfItems);
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
                System.out.println(separatingLine);
            } else if(inputStatement.startsWith("delete")){
                System.out.println(separatingLine);
                try {
                    deleteItem(inputStatement, listOfItems);
                    listWordCount = listWordCount - 1;
                    System.out.println("Now you have " + (listWordCount) + " tasks in the list.");
                }catch(IndexOutOfBoundsException|NumberFormatException e){
                    System.out.println("You can't delete that!");
                }
                System.out.println(separatingLine);
            }else if(inputStatement.startsWith("todo")|inputStatement.startsWith("event")
                    |inputStatement.startsWith("deadline")) {
                //proceeds to add task if task is valid.
                try {
                    listWordCount = classifyAndAddTask(inputStatement, separatingLine, listOfItems, listWordCount);
                }catch (StringIndexOutOfBoundsException | IllegalCommandsException e){
                    //throws exception when adding invalid tasks.
                    printBetweenLines(separatingLine, "That's not a valid task!");
                }

            } else{
                //throws exception when inputting invalid commands.
                printBetweenLines(separatingLine, "That's not a valid command!");
            }
            //update the text document
            try {
                writeToFile("docs/duke.txt", listOfItems);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
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
            , ArrayList<Task> listOfItems, int listWordCount) throws IllegalCommandsException {
        //determines what type of task is inputted, before add it to listOfItems and then incrementing listWordCount
        int indexOfSlash = inputStatement.indexOf("/");
        String numberOfTasks = "\nNow you have " + (listWordCount+1) + " tasks in the list.";
        if(inputStatement.startsWith("todo")){
            listOfItems.add(new Todo(inputStatement.substring(4).trim()));
            printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
            listWordCount++;
        } else  if(inputStatement.startsWith("deadline")){
            if(!inputStatement.contains("/by")){
                //throws exception when deadline commands are not followed with /by
                throw new IllegalCommandsException();
            }
            listOfItems.add(new Deadline(getDescriptionFromInput(inputStatement, 8),
                    getTimeFromInput(inputStatement, indexOfSlash + 3)));

            printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
            listWordCount++;

        }
        else if(inputStatement.startsWith("event")){
            if(!inputStatement.contains("/at")){
                //throws exception when event commands are not followed with /at
                throw new IllegalCommandsException();
            }
            listOfItems.add(new Event(getDescriptionFromInput(inputStatement, 5)
                    , getTimeFromInput(inputStatement, indexOfSlash + 3)));

            printBetweenLines(separatingLine, "More work ay? Here's what you need to do: \n"
                    + listOfItems.get(listWordCount).getTaskDescription() + numberOfTasks);
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

    private static void markAsDone(String inputStatement, ArrayList<Task> listOfItems) throws IllegalCommandsException {
        int indexOfItem = Integer.parseInt(inputStatement.substring(4).trim());
        if(listOfItems.get(indexOfItem - 1).getStatus()){
            //checks if the completed item is already done.
            throw new IllegalCommandsException();
        }
        System.out.println("One more down, but at what cost...");
        listOfItems.get(indexOfItem - 1).markAsDone();
        System.out.println(listOfItems.get(indexOfItem - 1).getTaskDescription());
    }

    private static void printBetweenLines(String separatingLine, String s) {
        System.out.println(separatingLine);
        System.out.println(s);
        System.out.println(separatingLine);
    }

    private static void printArrangedList(ArrayList<Task> list) throws IllegalCommandsException {
        //throws an error exception if list is empty
        if (list.size() == 0) {
            throw new IllegalCommandsException();
        }
        //a function that prints the array without returning any values
        System.out.println("Here's what you have:");
        String[] newList = new String[list.size()];
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
    private static void writeToFile(String filePath, ArrayList<Task> list) throws IOException {

        FileWriter fw = new FileWriter(filePath);
        for(Task item: list ) {
            if (item != null) {
                fw.write(item.getTaskDescription()+ System.lineSeparator());
            }
        }
        fw.close();
    }


    private static void deleteItem(String inputStatement, ArrayList<Task> list) {
        int indexOfRemovedItem = Integer.parseInt(inputStatement.substring(6).trim());
        System.out.println("Must be nice to have less things to do, right? Here's what you removed:\n"
                + list.get(indexOfRemovedItem - 1).getTaskDescription());
        list.remove(indexOfRemovedItem - 1);
    }


    private static void loadSavedText(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int listIndex = 0;
        while (s.hasNext()) {
            String importedCommand = s.nextLine();
            //System.out.println(s.nextLine());
            Integer currentStatusSymbol = importedCommand.codePointAt(4);

            if (importedCommand.startsWith("[T]")){
                list.add(new Todo(importedCommand.substring(7).trim()));
            }else if (importedCommand.startsWith("[D]")){
                list.add(new Deadline(importedCommand.substring(7,importedCommand.indexOf("(")-1),
                        importedCommand.substring(importedCommand.indexOf(":")+1,importedCommand.indexOf(")"))));
            }else if (importedCommand.startsWith("[E]")){
                list.add(new Event(importedCommand.substring(7,importedCommand.indexOf("(")-1),
                        importedCommand.substring(importedCommand.indexOf(":")+1,importedCommand.indexOf(")"))));
            }
            if (currentStatusSymbol.equals(10003)){
                list.get(listIndex).markAsDone();
            }
            listIndex+=1;

        }

    }





}
