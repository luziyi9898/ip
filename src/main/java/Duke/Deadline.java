package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline task, a task with a specified deadline.
 */
public class Deadline extends Todo{
    protected LocalDate dateBy;

    public Deadline(String description, LocalDate dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.letterRepresentingTask = Ui.DEADLINE_ICON;
    }

    /**
     * Generate the deadline for a deadline class.
     * @return a string representing the deadline of the task.
     */
    public String getDateBy() {
        return dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
    }
    @Override
    public void setDate(LocalDate dateBy) {
        this.dateBy = dateBy;
    }

    @Override
    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] " + this.description +" (by:"+ this.getDateBy()+")";
    }
}

