package daiwei.tojava.taskmanager;

/**
 * This class extends from Object todo.
 *
 * @author David
 * @version 2018.11.02
 * @since TaskManager lvl2
 */
public class Deadline extends Todo {

    protected String by;

    /**
     * entity deadline.
     *
     * @param description tasks' description
     * @param by          tasks' deadline time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
}
