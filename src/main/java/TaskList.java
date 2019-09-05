import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int taskIndex) {
        tasks.remove(taskIndex);
    }


    public Boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task getTaskAtIndex(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int length() {
        return tasks.size();
    }
}
