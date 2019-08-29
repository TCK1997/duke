import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isCompleted, LocalDateTime at) {
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
        return at.toString();
    }

    public String getAtFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM y, hh:mm a");
        return at.format(formatter);
    }

    public void setAt(LocalDateTime at) {
        this.at = at;
    }
}