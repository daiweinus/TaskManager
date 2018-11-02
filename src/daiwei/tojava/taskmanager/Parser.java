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
        String description = line.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Error: Empty description for TODO");
        }
        return new Todo(line.substring("todo".length()).trim());
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
        String description = line.substring("deadline".length()).trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Error: Empty description for DEADLINE");
        }
        if (!line.contains("/by")) {
            throw new TaskManagerException("Error: need '/by' for DEADLINE");
        }
//        String a=line.split("/[bB][yY] ")[1].trim();
//        System.out.println(a);
//        if(a.isEmpty()){
//            throw new TaskManagerException("Error: need daedline time after '/by' for DEADLINE");
//        }
        return new Deadline(line.trim().substring("deadline".length()).split(" /by")[0].trim(),
                line.split("/by ")[1].trim());  //[bB][yY] ignore /by or /BY
    }
}
