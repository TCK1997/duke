import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**\
     * Method to save state to a file.
     * @param taskList takes the taskList to save.
     * @param ui takes ui to output.
     */
    public void save(TaskList taskList, Ui ui) {
        new Thread(new Runnable() {
            PrintWriter writer;
            @Override
            public void run() {
                try {
                    writer = new PrintWriter(filepath, "UTF-8");
                    for (int i = 0; i < taskList.length(); i++) {
                        writer.write(taskList.getTaskAtIndex(i).saveString());
                    }

                } catch (Exception e) {
                    ui.printSaveError(e);
                } finally {
                    writer.close();
                }
            }
        }).start();
    }

    /**
     * Method to load from the filepath.
     * @param taskList takes the taskList to load.
     * @param ui takes the ui to output.
     * @return TaskList.
     * @throws DukeException throws DukeException if file not found.
     */
    public TaskList load(TaskList taskList, Ui ui) throws DukeException {
        try {
            Scanner scanner = new Scanner(new File(filepath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(taskList, ui, line.split(","));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("     No save state found.");
        } catch (Exception e) {
            ui.printLoadError(e);
        }
        return taskList;
    }

    private void processLine(TaskList taskList, Ui ui, String[] line) throws DukeException {
        switch (line[0]) {
        case "T":
            taskList.add(new ToDo(line[2], Boolean.parseBoolean(line[1])));
            break;
        case "E":
            taskList.add(new Event(line[2], Boolean.parseBoolean(line[1]), LocalDateTime.parse(line[3])));
            break;
        case "D":
            taskList.add(new Deadline(line[2], Boolean.parseBoolean(line[1]), LocalDateTime.parse(line[3])));
            break;
        default:
            throw new DukeException("     Error loading task in file: Not all task are load properly.");
        }
    }
}
