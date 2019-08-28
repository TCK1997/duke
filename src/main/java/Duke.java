import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for Duke.
 */
public class Duke {
    private static List<Task> tasks = new ArrayList<Task>();

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
            output = "     Bye. Hope to see you again soon!\n";
            stop = true;
            break;
        case "list":
            output = taskString();
            break;
        default:
            if (userInput.substring(0,4).equals("done")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                tasks.get(taskIndex).setCompleted(true);
                output = "     Nice! I've marked this task as done:\n"
                        + "       [✓] " + tasks.get(taskIndex).getDescription() + "\n";
            } else {
                output = "     added: " + userInput + "\n";
                tasks.add(new Task(userInput));
            }
        }
        finalOutput =
                  "    ____________________________________________________________\n"
                + output
                + "    ____________________________________________________________\n";
        System.out.println(finalOutput);
        return stop;
    }

    private static String taskString() {
        String temp = "";
        if (tasks.isEmpty()) {
            temp += "     There is no task.\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            temp += ("     " + (i + 1) + ".[" + tasks.get(i).getStatusIcon() + "] "
                    + tasks.get(i).getDescription() + "\n");
        }
        return temp;
    }
}
