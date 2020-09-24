package Duke;

import java.time.LocalDate;

/**
 * Represents a task in Duke.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String letterRepresentingTask;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.letterRepresentingTask = Ui.TODO_ICON;
    }

    /**
     * Generates complete task description.
     * @return complete task description.
     */
    public String getTaskDescription() {
        return this.letterRepresentingTask + "[" + this.getStatusIcon() +"] " + this.description;
    }

    public String getTaskName(){
        return this.description;
    }

    /**
     * Generates the status icon of the task based on whether it's done.
     * @return the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Checks for if the task is done.
     * @return Boolean value representing current status of task.
     */
    public Boolean getStatus() {
        return (this.isDone);
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Sets the date of a task.
     * @param Date date of a task.
     */
    public void setDate(LocalDate Date){

    }
}