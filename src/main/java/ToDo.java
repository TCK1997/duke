public class ToDo extends Task {

    /**
     * Method to create ToDo with description.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method to create ToDo with description with completion.
     * @param description
     * @param isCompleted
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * return the task letter for ToDo which is T.
     * @return
     */
    @Override
    public String getTaskLetter() {
        return "T";
    }
}
