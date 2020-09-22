package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Todo{
    protected LocalDate dateBy;

    public Deadline(String description, LocalDate dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.letterRepresentingTask = "[D]";
    }

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

