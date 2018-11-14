package daiwei.tojava.taskmanager;


/**
 * This class to create task object.
 *
 * @author David
 * @version 2018.11.02
 * @since TaskManager lvl2
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * entity task.
     *
     * @param description tasks' description
     */
    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * This function is to override task.
     *
     * @return task description and iDone.
     */
    @Override
    public String toString() {
        String status;
        if (isDone) {
            status = "Yes";
        } else {
            status = "No";
        }
        return "description: " + description + System.lineSeparator() + "is done? " + status;
    }
}