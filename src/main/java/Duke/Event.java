package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Todo{
    protected LocalDate dateAt;

    public Event(String description, LocalDate dateAt) {
        super(description);
        this.dateAt = dateAt;
        this.letterRepresentingTask = "[E]";
    }

    public String getDateAt() {
        return dateAt.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
    }

    @Override
    public void setDate(LocalDate dateAt) {
        this.dateAt = dateAt;
    }


    @Override
    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] " + this.description +" (at:" +this.getDateAt() + ")";
    }
}
