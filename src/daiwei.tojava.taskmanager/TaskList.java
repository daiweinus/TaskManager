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
    private Sort sort = new Sort();

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
    public TaskList() { }

    /**
     * This function is to add todo.
     *
     * @param fullCommand tasks'fullcommand
     * @throws TaskManagerException if createtodo have error can throw this exception.
     */
    public void addTodo(String fullCommand) throws TaskManagerException {
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
            assert index <= tasks.size() : "Command number is invalid"; //assert error when index bigger than tasks size.
            if (index <= tasks.size() && index > 0) {
                tasks.get(index - 1).setDone(true);
                System.out.println("Tasks: " + index + " has marked as DONE.");
            } else System.out.println("Error:Marking as done range should be 1 to " + tasks.size());
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
            if (index <= tasks.size() && index > 0) {
                tasks.remove(index - 1);
                System.out.println("Tasks " + index + " has been DELETED.");
            } else System.out.println("Error:Deleting range should be 1 to " + tasks.size());
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
            if (index <= tasks.size() && index > 0) {
                String description = ui.readNewCommand();
                if (description.contains("/by")&&(tasks.get(index - 1) instanceof Deadline)) {
                    tasks.set(index - 1, new Deadline(description.split(" /by")[0].trim(),
                            description.split("/by ")[1].trim()));
                } else if(!description.isEmpty() &&(tasks.get(index - 1) instanceof Todo)) {
                    tasks.set(index - 1, new Todo(description.trim()));
                } else System.out.println("Error:Edit command cannot be empty");
                System.out.println("Tasks " + index + " has been EDIT.");
            } else System.out.println("Error:Editing range should be 1 to " + tasks.size());
        } catch (NumberFormatException e) {
            System.out.println("Error:Edit command not Integer.");
        }
    }


    /**
     * this function is to search tasks with a any keyword
     *
     * @param fullCommand tasks'fullcommand
     */
    public void searchTasks(String fullCommand) {
        String description = fullCommand.trim().substring("search".length()).trim();
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String taskget = tasks.get(i).toString();
            if (taskget.contains(description)) {
                System.out.println(System.lineSeparator() +
                        "Task [" + i + "] " + taskget + System.lineSeparator());
                count++;
            }
        }
        if (count != 0) System.out.println("TaskManager search done,total " + count + " tasks contain "
                + "'" + description + "'.");
        else System.out.println("TaskManager don't have any task contain " + "'" + description + "'.");
    }


    /**
     * This function is to print tasks.
     */
    public void showTasks(String fullCommand) {
        String description = fullCommand.trim().substring("print".length()).trim();
        if(description.isEmpty()){
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + tasks.get(i));
            }
        }
        else try{
            int index = Integer.parseInt(description);
            if (index <= tasks.size() && index > 0){
            System.out.println("[" + (index) + "] " + tasks.get(index-1));
            }else System.out.println("Error:Printing range should be 1 to " + tasks.size());
        }catch (NumberFormatException e) {
            System.out.println("Error:Print command should be ‘print' or 'print INTEGER'");
        }
    }

    /**
     * This function is to call Storage.save method to save tasks.
     */
    public void saveTasks() { Storage.save(tasks); }


    /**
     * This function is to clear TaskManager.
     */
    public void clearTasks() {
        tasks.clear();
        System.out.println("TaskManager have been cleared.");
    }


    /**
     * This function it to sort tasks with deadline time.
     */
    @SuppressWarnings("unchecked")
    public void sortTasks() {
        List<Task> newDeadlines = new ArrayList<>();
        List<Task> newTodos = new ArrayList<>();
        List<Task> newDones = new ArrayList<>();

        newDeadlines.sort(sort); //sorting tasks

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDone()) {
                newDones.add(tasks.get(i));//filter isDone = yes tasks out
            }
            if (tasks.get(i) instanceof Deadline && !tasks.get(i).isDone()) {
                newDeadlines.add(tasks.get(i)); // filter isDone = no and with deadline time tasks out
            } else if (!tasks.get(i).isDone()) {
                newTodos.add(tasks.get(i)); // filter isDone = no and without deadline time tasks out
            }
        }

        System.out.println(System.lineSeparator() + "Below tasks have sorted with deadline: ");
        for (Task newDeadline : newDeadlines) {
            System.out.println(newDeadline);
        }

        System.out.println(System.lineSeparator() + "Below tasks without deadline: ");
        for (Task newTodo : newTodos) {
            System.out.println(newTodo);
        }

        System.out.println(System.lineSeparator() + "Below tasks have done: ");
        for (Task newDone : newDones) {
            System.out.println(newDone);
        }
    }
}

