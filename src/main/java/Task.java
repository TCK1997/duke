public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getStatusIcon() {
        return (isCompleted ? "✓" : "✗");
    }

    String getDescription() {
        return description;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

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

    public Boolean matchDescription(String keyword) {
        return this.description.contains(keyword);
    }

    public String getDate() {
        return "";
    }
}
