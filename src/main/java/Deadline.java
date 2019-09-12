import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Method to create Deadline with description and date.
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Method to create Deadline with description and date with completion.
     * @param description
     * @param isCompleted
     * @param by
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * sets the correct date.
     * @param by
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * get the string of the date.
     * @return
     */
    public String getBy() {
        return by.toString();
    }

    /**
     * format the date for Deadline.
     * @return
     */
    public String getByFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM y, hh:mm a");
        return by.format(formatter);
    }

    /**
     * Return task letter for event which is D.
     * @return
     */
    @Override
    public String getTaskLetter() {
        return "D";
    }

    /**
     * return the a string corresponding
     * to a task letter, completion,
     * description and date.
     * @return
     */
    @Override
    public String saveString() {
        return getTaskLetter() + ","
                + this.isCompleted + ","
                + this.description + ","
                + this.by + "\n";
    }

    /**
     * return the date string.
     * @return
     */
    @Override
    public String getDate() {
        return " (by: " + getByFormat() + ")";
    }
}
