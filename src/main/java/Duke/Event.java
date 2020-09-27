package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/**
 * Represents a deadline task, a task with a specified date.
 */
public class Event extends Todo{
    protected LocalDate dateAt;
    protected String dateInString;

    public Event(String description, LocalDate dateAt) {
        super(description);
        this.dateAt = dateAt;
        this.letterRepresentingTask = Ui.EVENT_ICON;
    }
    public Event(String description, String dateInString) {
        super(description);
        this.dateInString = dateInString;
        this.letterRepresentingTask = Ui.EVENT_ICON;
    }

    /**
     * Generate the deadline for a event class.
     * @return a string representing the date of the task.
     */

    public String getDateAt() {
        if (dateAt != null) {
            return dateAt.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        } else{
            return dateInString;
        }
    }
    @Override
    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] "
                + this.description +" (at:" +this.getDateAt() + ")";
    }
}
