import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Method to create Event with description and date.
     * @param description
     * @param at
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Method to create Event with description and date with completion.
     * @param description
     * @param isCompleted
     * @param at
     */
    public Event(String description, boolean isCompleted, LocalDateTime at) {
        super(description, isCompleted);
        this.at = at;
    }

    /**
     * Return task letter for event which is E.
     * @return
     */
    @Override
    public String getTaskLetter() {
        return "E";
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
                + this.at + "\n";
    }

    /**
     * return the date function.
     * @return
     */
    @Override
    public String getDate() {
        return " (at: " + getAtFormat() + ")";
    }

    /**
     * return the date string.
     * @return
     */
    public String getAt() {
        return at.toString();
    }

    /**
     * return the date with correct format.
     * @return
     */
    public String getAtFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM y, hh:mm a");
        return at.format(formatter);
    }

    /**
     * sets the correct date.
     * @param at
     */
    public void setAt(LocalDateTime at) {
        this.at = at;
    }
}