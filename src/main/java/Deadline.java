import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    public String getBy() {
        return by.toString();
    }

    public String getByFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM y, hh:mm a");
        return by.format(formatter);
    }

    @Override
    public String getTaskLetter() {
        return "D";
    }

    @Override
    public String saveString() {
        return getTaskLetter() + ","
                + this.isCompleted + ","
                + this.description + ","
                + this.by + "\n";
    }
}
