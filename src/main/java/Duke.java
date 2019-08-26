import java.util.Scanner;

/**
 * Class for Duke.
 */
public class Duke {
    /**
     * Main method for duke.
     */
    public static void main(String[] args) {
        String startUp =
                  "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(startUp);
        runDuke();
    }

    private static void runDuke() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            if (parseUserInput(userInput.nextLine())) {
                break;
            }
        }
    }

    private static boolean parseUserInput(String userInput) {
        String output = null;
        String finalOutput;
        boolean stop = false;
        switch (userInput) {
        case "bye":
            output = "Bye. Hope to see you again soon!";
            stop = true;
            break;
        default:
            output = userInput;
        }
        finalOutput =
                "    ____________________________________________________________\n"
                + "     " + output + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(finalOutput);
        return stop;
    }
}
