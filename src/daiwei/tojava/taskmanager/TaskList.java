package daiwei.tojava.taskmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * This TaskList class to add Task,add deadline in TaskManager,
 * and edit, delete the TaskManager.
 *
 * @author David
 * @version 2018.11.2
 * @since TaskManager lvl6
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    /**
     * This constructs a TaskList with a List<Task> parameter
     *
     * @param tasks tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * entity TaskList with empty parameter
     */
    public TaskList() {
    }

    /**
     * This function is to add todo.
     *
     * @param fullCommand tasks'fullcommand
     * @throws TaskManagerException if createtodo have error can throw this exception.
     */
    public void addTask(String fullCommand) throws TaskManagerException {
        Todo t = Parser.createTodo(fullCommand);
        tasks.add(t);
        System.out.println("Tasks in the list: " + tasks.size());
    }

    /**
     * This function is to add deadline.
     *
     * @param fullCommand tasks'fullcommand
     * @throws TaskManagerException if create deadline have error can throw this exception.
     */
    public void addDeadline(String fullCommand) throws TaskManagerException {
        Deadline t = Parser.createDeadline(fullCommand);
        tasks.add(t);
        System.out.println("Tasks in the list: " + tasks.size());
    }

    /**
     * This function is to mark tasks as done.
     *
     * @param fullCommand tasks'fullcommand
     */
    public void markAsDone(String fullCommand) {
        try {
            int index = Integer.parseInt(fullCommand.substring("done".length()).trim());
            if (index <= tasks.size()) {
                tasks.get(index - 1).setDone(true);
                System.out.println("Tasks in the list: " + tasks.size());
            } else System.out.println("Error:Command number is out of Task size:" + tasks.size());
        } catch (NumberFormatException e) {
            System.out.println("Error:markAsDone command not Integer!");
        }
    }

    /**
     * This function is to delete tasks.
     *
     * @param fullCommand tasks'fullcommand
     */
    public void deleteTasks(String fullCommand) {
        try {
            int index = Integer.parseInt(fullCommand.substring("delete".length()).trim());
            if (index <= tasks.size()) {
                tasks.remove(index - 1);
                System.out.println("Tasks " + index + " delete done.");
            } else System.out.println("Error:Command number is out of Task size:" + tasks.size());
        } catch (NumberFormatException e) {
            System.out.println("Error:Deleted command not Integer!");
        }
    }

    /**
     * this function is to edit tasks.
     *
     * @param fullCommand tasks'fullcommand
     */
    public void editTasks(String fullCommand) {
        try {
            int index = Integer.parseInt(fullCommand.substring("edit".length()).trim());
            if (index <= tasks.size()) {
                String description = ui.readNewCommand();
                if (tasks.get(index - 1) instanceof Deadline) {
                    tasks.set(index - 1, new Deadline(description.split(" /by")[0].trim(),
                            description.split("/by ")[1].trim()));
                } else if (tasks.get(index - 1) instanceof Todo) {
                    tasks.set(index - 1, new Todo(description.trim()));
                }
            } else System.out.println("Error:Command number is out of Task size:" + tasks.size());
        } catch (NumberFormatException e) {
            System.out.println("Error:Edit command not Integer!");
        }
    }

    /**
     * This function is to print tasks.
     */
    public void showTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i));
        }
    }

    /**
     * This function is to call Storage.save method to save tasks.
     */
    public void saveTasks() {
        Storage.save(tasks);
    }
}

