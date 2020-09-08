package Duke;

public class Event extends Todo{
    protected String dateAt;

    public Event(String description, String dateAt) {
        super(description);
        this.dateAt = dateAt;
        this.letterRepresentingTask = "[E]";
    }

    public String getDateAt() {
        return dateAt;
    }

    @Override
    public void setDate(String dateAt) {
        this.dateAt = dateAt;
    }


    @Override
    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] " + this.description +" (at:" +this.getDateAt() + ")";
    }
}
