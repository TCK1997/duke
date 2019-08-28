import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for Duke.
 */
public class Duke {
    private static List<Task> tasks = new ArrayList<>();

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
        String description;
        String firstWord = userInput.contains(" ") ? userInput.split(" ")[0] : userInput;
        boolean stop = false;
        switch (firstWord) {
        case "bye":
            output = "     Bye. Hope to see you again soon!\n";
            stop = true;
            break;
        case "list":
            output = taskString();
            break;
        case "done":
            int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
            tasks.get(taskIndex).setCompleted(true);
            output = "     Nice! I've marked this task as done:\n"
                    + "       [✓] " + tasks.get(taskIndex).getDescription() + "\n";
            break;
        case "todo":
            description = userInput.substring(5);
            tasks.add(new ToDo(description));
            output =  "     Got it. I've added this task:\n"
                    + "       [T][✗] " + description + "\n"
                    + "     Now you have " + tasks.size() + " task"
                    + (tasks.size() == 1 ? "" : "s") + " in the list\n";
            break;
        case "deadline":
            description = userInput.substring(9, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            tasks.add(new Deadline(description, by));
            output =  "     Got it. I've added this task:\n"
                    + "       [D][✗] " + description + " (by: " + by + ")" + "\n"
                    + "     Now you have " + tasks.size() + " task"
                    + (tasks.size() == 1 ? "" : "s") + " in the list\n";
            break;
        case "event":
            description = userInput.substring(6, userInput.indexOf("/at") - 1);
            String at = userInput.substring(userInput.indexOf("/at") + 4);
            tasks.add(new Event(description, at));
            output =  "     Got it. I've added this task:\n"
                    + "       [E][✗] " + description + " (at: " + at + ")" + "\n"
                    + "     Now you have " + tasks.size() + " task"
                    + (tasks.size() == 1 ? "" : "s") + " in the list\n";
            break;
        default:
            output = "     Command not recognised\n";
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
            Task currentTask = tasks.get(i);
            temp += "     " + (i + 1) + ".["
                    + currentTask.getTaskLetter() + "]["
                    + currentTask.getStatusIcon() + "] "
                    + currentTask.getDescription();
            if (currentTask instanceof ToDo) {
                temp += "\n";
            } else if (currentTask instanceof Deadline) {
                temp += " (by: " + ((Deadline) currentTask).getBy()  + ")\n";
            } else if (currentTask instanceof Event) {
                temp += " (by: " + ((Event) currentTask).getAt()  + ")\n";
            }
        }
        return temp;
    }
}
