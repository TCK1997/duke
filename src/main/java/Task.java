public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Method to create Task with description.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Method to create Task with description with completion.
     * @param description
     * @param isCompleted
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * returns the status icon base on the completion.
     * @return
     */
    public String getStatusIcon() {
        return (isCompleted ? "✓" : "✗");
    }

    /**
     * return the description of the task.
     * @return
     */
    String getDescription() {
        return description;
    }

    /**
     * set the completion boolean.
     * @param isCompleted
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * return the task letter of the task.
     * @return
     */
    public String getTaskLetter() {
        return "";
    }

    /**
     * This method would change all it's variable
     * into a standard string of:
     * task letter, completed boolean value, description.
     * @return The string for this particular task
     */
    public String saveString() {
        return getTaskLetter() + ","
                + this.isCompleted + ","
                + this.description + "\n";
    }

    /**
     * find if a keyword is inside a description.
     * @param keyword
     * @return
     */
    public Boolean matchDescription(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * return the date of the task.
     * @return
     */
    public String getDate() {
        return "";
    }
}
