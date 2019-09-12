import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Create object TaskList which is wrapper for ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add a task to taskList.
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deleted task from taskList at taskIndex.
     * @param taskIndex
     */
    public void delete(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * return True if taskList is empty.
     * @return
     */
    public Boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Return task at taskIndex.
     * @param taskIndex
     * @return
     */
    public Task getTaskAtIndex(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Return length of the taskList.
     * @return
     */
    public int length() {
        return tasks.size();
    }
}
