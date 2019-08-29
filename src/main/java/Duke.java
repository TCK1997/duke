import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        load();
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
            try {
                if (parseUserInput(userInput.nextLine().trim())) {
                    break;
                }
            } catch (Exception e) {
                String errorOutput =
                          "    ____________________________________________________________\n"
                        + "     ☹ Unexpected Error\n"
                        + "     " + e.toString() + "\n"
                        + "    ____________________________________________________________\n";
                System.out.println(errorOutput);
            }
        }
    }


    private static boolean parseUserInput(String userInput) {
        String output = null;
        String finalOutput;
        String description = null;
        boolean stop = false;
        boolean taskChange = false;
        if (userInput.isBlank()) {
            output = "     ☹ Empty command.\n";
        } else {
            String firstWord = userInput.contains(" ") ? userInput.split(" ")[0] : userInput;
            switch (firstWord) {
            case "bye":
                output = "     Bye. Hope to see you again soon!\n";
                stop = true;
                break;
            case "list":
                output = taskString();
                break;
            case "done":
                if (userInput.length() < 6) {
                    output = "     ☹ OOPS!!! Please specify which task.\n";
                    break;
                }
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    if (taskIndex >= tasks.size()) {
                        output = "     ☹ OOPS!!! There is no such task\n";
                        break;
                    } else {
                        tasks.get(taskIndex).setCompleted(true);
                        taskChange = true;
                    }
                    output =  "     Nice! I've marked this task as done:\n"
                            + "       [✓] " + tasks.get(taskIndex).getDescription() + "\n";
                } catch (NumberFormatException ex) {
                    output = "     ☹ OOPS!!! Please input a number to indicate which task.\n";
                }
                break;
            case "todo":
                if (userInput.length() < 6) {
                    output = "     ☹ OOPS!!! The description of a todo cannot be empty.\n";
                    break;
                }
                description = userInput.substring(5);
                tasks.add(new ToDo(description));
                taskChange = true;
                output =  "     Got it. I've added this task:\n"
                        + "       [T][✗] " + description + "\n"
                        + "     Now you have " + tasks.size() + " task"
                        + (tasks.size() == 1 ? "" : "s") + " in the list\n";
                break;
            case "deadline":
                String by = null;
                try {
                    description = userInput.substring(9, userInput.indexOf("/by") - 1);
                    by = userInput.substring(userInput.indexOf("/by") + 4);
                    if (description.isBlank() || by.isBlank()) {
                        throw new DukeException("Description or Date is blank.\n");
                    }
                } catch (StringIndexOutOfBoundsException | DukeException e) {
                    output = "     ☹ OOPS!!! The description or date of a deadline cannot be empty.\n";
                    break;
                }
                tasks.add(new Deadline(description, by));
                taskChange = true;
                output =  "     Got it. I've added this task:\n"
                        + "       [D][✗] " + description + " (by: " + by + ")" + "\n"
                        + "     Now you have " + tasks.size() + " task"
                        + (tasks.size() == 1 ? "" : "s") + " in the list\n";
                break;
            case "event":
                String at = null;
                try {
                    description = userInput.substring(6, userInput.indexOf("/at") - 1);
                    at = userInput.substring(userInput.indexOf("/at") + 4);
                    if (description.isBlank() || at.isBlank()) {
                        throw new DukeException("Description or Date is blank.\n");
                    }
                } catch (StringIndexOutOfBoundsException | DukeException e) {
                    output = "     ☹ OOPS!!! The description or date of a event cannot be empty.\n";
                    break;
                }
                tasks.add(new Event(description, at));
                taskChange = true;
                output =  "     Got it. I've added this task:\n"
                        + "       [E][✗] " + description + " (at: " + at + ")" + "\n"
                        + "     Now you have " + tasks.size() + " task"
                        + (tasks.size() == 1 ? "" : "s") + " in the list\n";
                break;
            default:
                output = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            }
        }
        finalOutput =
                  "    ____________________________________________________________\n"
                + output
                + "    ____________________________________________________________\n";
        System.out.println(finalOutput);
        if (taskChange) {
            save();
        }
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
                temp += " (by: " + ((Deadline) currentTask).getBy() + ")\n";
            } else if (currentTask instanceof Event) {
                temp += " (at: " + ((Event) currentTask).getAt() + ")\n";
            }
        }
        return temp;
    }

    private static void save() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PrintWriter writer = new PrintWriter("state.txt", "UTF-8");
                    for (int i = 0; i < tasks.size(); i++) {
                        writer.write(tasks.get(i).saveString());
                    }
                    writer.close();
                } catch (Exception e) {
                    String errorOutput =
                              "    ____________________________________________________________\n"
                            + "     ☹ Save Error\n"
                            + "     " + e.toString() + "\n"
                            + "    ____________________________________________________________\n";
                    System.out.println(errorOutput);
                }
            }
        }).start();
    }

    private static void load() {
        try {
            Scanner scanner = new Scanner(new File("state.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line.split(","));
            }
        } catch (FileNotFoundException e) {
            assert true;
        } catch (Exception e) {
            String errorOutput =
                      "    ____________________________________________________________\n"
                    + "     ☹ Load Error\n"
                    + "     " + e.toString() + "\n"
                    + "    ____________________________________________________________\n";
            System.out.println(errorOutput);
        }
    }

    private static void processLine(String[] line) {
        switch (line[0]) {
        case "T":
            tasks.add(new ToDo(line[2], Boolean.parseBoolean(line[1])));
            break;
        case "E":
            tasks.add(new Event(line[2], Boolean.parseBoolean(line[1]), line[3]));
            break;
        case "D":
            tasks.add(new Deadline(line[2], Boolean.parseBoolean(line[1]), line[3]));
            break;
        default:
            System.out.println("Error loading task in file: Not all task are load properly.");
        }
    }

}
