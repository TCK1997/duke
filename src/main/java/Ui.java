import java.util.Scanner;

public class Ui {
    Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     */
    public void printStartUp() {
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }

    /**
     * Receive input from user.
     * @return userInput
     */
    public String readCommand() {
        String fullCommand = null;
        try {
            fullCommand = userInput.nextLine().trim();
        } catch (Exception e) {
            System.out.println("     ☹ Error while reading next line");
            System.out.println(e.toString());
        }
        return fullCommand;
    }

    public void printException(Exception e) {
        System.out.println("     ☹ Unexpected Error");
        System.out.println(e.toString());
    }

    public void printDukeException(DukeException de) {
        System.out.println(de.getMessage());
    }

    public void printExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void printTaskLine(Task task) {
        System.out.print("       [" + task.getTaskLetter() + "][" + task.getStatusIcon() + "] ");
        System.out.println(task.getDescription() + task.getDate());
    }

    public void printTaskLeft(int taskSize) {
        System.out.println("     Now you have " + taskSize + " task" + (taskSize == 1 ? "" : "s") + " in the list");
    }

    public void printTaskComplete(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        printTaskLine(task);
    }

    /**
     * Print the output when a task is added.
     * @param task the task that is added.
     * @param taskSize the number of task left in the list.
     */
    public void printAddTask(Task task, int taskSize) {
        System.out.println("     Got it. I've added this task:");
        printTaskLine(task);
        printTaskLeft(taskSize);
    }

    /**
     * Print the output when a task is deleted.
     * @param task the task that is deleted.
     * @param taskSize the number of task left in the list.
     */
    public void printTaskDelete(Task task, int taskSize) {
        System.out.println("     Noted. I've removed this task:");
        printTaskLine(task);
        printTaskLeft(taskSize);
    }

    private void printTaskString(Task task, int taskIndex) {
        System.out.print("     " + taskIndex + ".[" + task.getTaskLetter() + "][" + task.getStatusIcon() + "] ");
        System.out.println(task.getDescription() + task.getDate());
    }

    /**
     * Prints all the task.
     * Method is used for list command.
     * @param taskList outputs all task in the tasklist.
     */
    public void printAllTaskString(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("     There is no task.");
        }
        for (int i = 0; i < taskList.length(); i++) {
            Task currentTask = taskList.getTaskAtIndex(i);
            printTaskString(currentTask, i + 1);
        }
    }

    /**
     * Print the tasks that matches the keyword.
     * @param taskList require taskList.
     * @param keyword requires the keyword.
     */
    public void printMatchTaskString(TaskList taskList, String keyword) {
        boolean foundMatch = false;
        for (int i = 0; i < taskList.length(); i++) {
            Task currentTask = taskList.getTaskAtIndex(i);
            if (currentTask.matchDescription(keyword)) {
                if (!foundMatch) {
                    foundMatch = true;
                    System.out.println("     Here are the matching tasks in your list:");
                }
                printTaskString(currentTask, i + 1);
            }
        }
        if (!foundMatch) {
            System.out.println("     There is no matching tasks in your list");
        }
    }

    public void printSaveError(Exception e) {
        System.out.println("     ☹ Save Error");
        System.out.println("     " + e.toString());
    }

    public void printLoadError(Exception e) {
        System.out.println("     ☹ Load Error");
        System.out.println("     " + e.toString());
    }
}
