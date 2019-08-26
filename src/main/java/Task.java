public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description){
        this.description = description;
        this.isCompleted = false;
    }

    public String getStatusIcon(){
        return (isCompleted ? "✓" : "X");
    }

    public String getDescription(){
        return description;
    }

    public void setCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }
}
