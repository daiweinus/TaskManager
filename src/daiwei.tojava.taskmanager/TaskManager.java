package daiwei.tojava.taskmanager;

/**
 * This class implements simple task manager ,that can write, load and storage
 * tasks in the assigned filePath.
 * This TaskManger class contained 3 methods TaskManager ,run and main.
 *
 * @author David
 * @version Finial version 2018.11.2
 * @since TaskManager lvl1
 */
public class TaskManager {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * This constructs s TaskManager with a filePath,
     * This TaskManager try call storage.load to load tasks from filePath
     * and throw an exception if storage load file fail.
     *
     * @param filePath tasks'storage file path
     */
    public TaskManager(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TaskManagerException e) {
            ui.showToUser("Problem reading file. Starting with an empty task list!");
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new TaskManager("D:/tasks.txt").run();
    }


    /**
     * This run method switch different task command to different task method.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                String commandWord = Parser.getCommandWord(fullCommand);//convert command word to lowercase
                switch (commandWord) {
                    case "exit":
                    case "":
                        isExit = true;
                        break;
                    case "todo":
                        tasks.addTodo(fullCommand);
                        break;
                    case "deadline":
                        tasks.addDeadline(fullCommand);
                        break;
                    case "done":
                        tasks.markAsDone(fullCommand);
                        break;
                    case "print":
                        tasks.showTasks(fullCommand);
                        break;
                    case "delete":
                        tasks.deleteTasks(fullCommand);
                        break;
                    case "clear":
                        tasks.clearTasks();
                        break;
                    case "edit":
                        tasks.editTasks(fullCommand);
                        break;
                    case "search":
                        tasks.searchTasks(fullCommand);
                        break;
                    case "sort":
                        tasks.sortTasks();
                        break;
                    case "help":
                        ui.showHelp();
                        break;
                    case "save":
                        tasks.saveTasks();
                        break;
                    default:
                        ui.showError();
                }
            } catch (TaskManagerException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.exit();
    }
}

