public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isCompleted, String at) {
        super(description, isCompleted);
        this.at = at;
    }

    @Override
    public String getTaskLetter() {
        return "E";
    }

    @Override
    public String saveString() {
        return getTaskLetter() + ","
                + this.isCompleted + ","
                + this.description + ","
                + this.at + "\n";
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }
}