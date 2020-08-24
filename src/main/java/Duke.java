//import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String inputStatement;
        int endEchoFlag = 0;
        String separatingLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        //greeting lines start:
        System.out.println(separatingLine);
        System.out.println("Greetings, care for a cup of coffee?");
        System.out.println(separatingLine);
        //greeting lines end:

        //echo loop begins:
        while (endEchoFlag == 0) {
            Scanner in = new Scanner(System.in);
            inputStatement = in.nextLine();
            if (!inputStatement.equalsIgnoreCase("bye")) {
                System.out.println(separatingLine);
                System.out.println("Did you say: " + inputStatement);
                System.out.println(separatingLine);
            }
            else{
                endEchoFlag = 1;
            }
        }
        //echo loop ends
        System.out.println(separatingLine);
        System.out.println("We shall meet again...");
        System.out.println(separatingLine);







    }


}
