package daiwei.tojava.taskmanager;

/**
 * This Parser class is to parser full command and create todo, deadline tasks.
 *
 * @author David
 * @version 2018.11.02
 * @since TaskManager lvl6
 */
public class Parser {
    private static String commandWord;

    public Parser(String commandWord) {
        Parser.commandWord = commandWord;
    }

    /**
     * This function is get command word from full command.
     *
     * @param fullCommand tasks' full command
     * @return tasks' commandWord
     */
    public static String getCommandWord(String fullCommand) {
        commandWord = fullCommand.trim().split(" ")[0];
        return commandWord;
    }

    /**
     * This function is create todo.
     *
     * @param line tasks' full command
     * @return tasks' Todo
     * @throws TaskManagerException if empty description for todo and /by can
     *                              throw this exception.
     */
    public static Todo createTodo(String line) throws TaskManagerException {
        String description = line.trim().substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Error: Empty description for TODO");
        }
        return new Todo(line.trim().substring("todo".length()).trim());
    }

    /**
     * This function is create deadline.
     *
     * @param line tasks' full command
     * @return tasks' deadline and by
     * @throws TaskManagerException if empty description for deadline and /by can
     *                              throw this exception.
     */
    public static Deadline createDeadline(String line) throws TaskManagerException {
        String description = line.substring("deadline".trim().length());

        if (description.isEmpty()) {
            throw new TaskManagerException("Error: Empty description for DEADLINE");
        }
        if (!description.contains("/by")) {
            throw new TaskManagerException("Error: need '/by' for DEADLINE");
        }
        return new Deadline(line.trim().substring("deadline".length()).trim().split(" /by")[0],
                line.trim().split("/by ")[1]);  //[bB][yY] ignore /by or /BY
    }

    /**
     * This function to parser task to object todo and deadline.
     *
     * @param line tasks
     * @return tasks
     */
    public static Task createTask(String line) {
        Task task = null;
        String type = line.split("\\|")[0].trim();
        boolean isDone = line.split("\\|")[1].trim().equals("1");
        String desc = line.split("\\|")[2].trim();
        switch (type) {
            case "T":
                task = new Todo(desc);
                task.setDone(isDone);
                break;
            case "D":
                String by = line.split("\\|")[3].trim();
                task = new Deadline(desc, by);
                task.setDone(isDone);
                break;
        }
        return task;
    }
}
