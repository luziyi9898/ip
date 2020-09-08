package Duke;

public class Deadline extends Todo{
    protected String dateBy;

    public Deadline(String description, String dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.letterRepresentingTask = "[D]";
    }

    public String getDateBy() {
        return dateBy;
    }
    @Override
    public void setDate(String dateBy) {
        this.dateBy = dateBy;
    }

    @Override
    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] " + this.description +" (by:"+ this.getDateBy()+")";
    }
}

