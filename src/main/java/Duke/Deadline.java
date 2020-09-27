package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline task, a task with a specified deadline.
 */
public class Deadline extends Todo{
    protected LocalDate dateBy;
    protected String dateInString;

    public Deadline(String description, LocalDate dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.letterRepresentingTask = Ui.DEADLINE_ICON;
    }

    public Deadline(String description, String dateInString) {
        super(description);
        this.dateInString = dateInString;
        this.letterRepresentingTask = Ui.DEADLINE_ICON;
    }


    /**
     * Generate the deadline for a deadline class.
     * @return a string representing the deadline of the task.
     */
    public String getDateBy() {
        if (dateBy != null) {
            return dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        } else{
            return dateInString;
        }
    }
    @Override
    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] "
                + this.description +" (by:"+ this.getDateBy()+")";
    }
}

