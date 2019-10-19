import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class for Duke.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Main method for duke.
     */
    public static void main(String[] args) {
        new Duke().runDuke();
    }

    /**
     * Constructor method for duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("state.txt");
        tasks = new TaskList();
        try {
            tasks = storage.load(tasks, ui);
        } catch (DukeException e) {
            ui.printLoadError(e);
        }
    }


    String getResponse(String input) {
        return "Duke heard: " + input;
    }


    /**
     * Main program loop.
     */
    public void runDuke() {
        ui.printStartUp();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                String[] parsedCommand = new Parser(fullCommand).parse();
                isExit = runCommand(parsedCommand);
            } catch (DukeException de) {
                ui.printDukeException(de);
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Takes in a array of Command and return true if user wants to exit.
     * @param parsedCommand
     * @return boolean where user wants to exit.
     */
    private boolean runCommand(String[] parsedCommand) {
        Task currentTask;
        switch (parsedCommand[0]) {
        case "bye":
            ui.printExit();
            return true;
        case "list":
            ui.printAllTaskString(tasks);
            break;
        case "done":
            currentTask = tasks.getTaskAtIndex(Integer.parseInt(parsedCommand[1]));
            currentTask.setCompleted(true);
            ui.printTaskComplete(currentTask);
            storage.save(tasks, ui);
            break;
        case "todo":
            currentTask = new ToDo(parsedCommand[1]);
            tasks.add(currentTask);
            ui.printAddTask(currentTask, tasks.length());
            storage.save(tasks,ui);
            break;
        case "deadline":
            currentTask = new Deadline(parsedCommand[1], parseDateTime(parsedCommand[2]));
            tasks.add(currentTask);
            ui.printAddTask(currentTask, tasks.length());
            storage.save(tasks,ui);
            break;
        case "event":
            currentTask = new Event(parsedCommand[1], parseDateTime(parsedCommand[2]));
            tasks.add(currentTask);
            ui.printAddTask(currentTask, tasks.length());
            storage.save(tasks,ui);
            break;
        case "delete":
            currentTask = tasks.getTaskAtIndex(Integer.parseInt(parsedCommand[1]));
            tasks.delete(Integer.parseInt(parsedCommand[1]));
            ui.printTaskDelete(currentTask, tasks.length());
            storage.save(tasks,ui);
            break;
        case "find":
            ui.printMatchTaskString(tasks, parsedCommand[1]);
            break;
        default:
            break;
        }
        return false;
    }

    /**
     * LocalDateTime parser function: probably should change to a class for better testing.
     * @param input the input string of the date.
     * @return return the correct LocalDateTime parsed from input.
     */
    private static LocalDateTime parseDateTime(String input) {
        String[] dateTimeStrings = {
            "[[[d][dd][-][ ][/][,][MMMM][MMM][M][-][ ][/][,][uuuu][uu]] [[h][hh][H][HH][:][ ][mm][ ][a]]]",
            "[[[d][dd][-][ ][/][,][MMMM][MMM][M][-][ ][/][,][uuuu][uu]] [hh[ ][:][mm][ ]a][[hhmm a][HHmm][HH mm]]",
            "[[[ddMMyyyy][ddMMuu][ddMMuuuu][dMMMMuu][dMMMMuuuu]] [hh[ ][:][mm][ ]a][HHmm][HH mm][HH:mm]"
        };
        for (int i = 0; i < dateTimeStrings.length; i++) {
            try {
                input.replace("am", "AM");
                input.replace("pm", "PM");
                DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive().appendPattern(dateTimeStrings[i]).toFormatter();
                LocalDateTime output = LocalDateTime.parse(input, formatter);
                return output;
            } catch (DateTimeParseException e) {
                assert true;
            }
        }
        return null;
    }
}
