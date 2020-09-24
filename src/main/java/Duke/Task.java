package Duke;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String letterRepresentingTask;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.letterRepresentingTask = Ui.TODO_ICON;
    }

    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] " + this.description;
    }

    public String getTaskName(){
        return this.description;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Boolean getStatus() {
        return (this.isDone);
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void setDate(LocalDate Date){

    }
}