package daiwei.tojava.taskmanager;

import java.util.Scanner;

/**
 * This class is to read user command and print error.
 *
 * @author David
 * @version 2018.11.02
 * @since TaskManage lvl 6
 */
public class Ui {
    private Scanner in = new Scanner(System.in);

    /**
     * entity Ui.
     */
    public Ui() {
    }

    /**
     * This function is to read user command.
     *
     * @return user command
     */
    public String readUserCommand() {
        System.out.println(System.lineSeparator() + "Your task? ");
        String command=in.nextLine().trim();
        return command.toLowerCase();
    }

    /**
     * This function is to read user new command that its user want edit to.
     *
     * @return user new command
     */
    public String readNewCommand() {
        System.out.println("Please key in your New Task: ");
        String command=in.nextLine().trim();
        return command.toLowerCase();
    }

    /**
     * This function is show user welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Welcome to TaskManager!");
    }

    public void showToUser(String s) {
        System.out.println(s);
    }

    public void showError() {
        System.out.println("Unknown command! please try again.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * This function is show user 'bye' when exit task manager.
     */
    public void exit() {
        System.out.println("Bye!");
    }

    public void showHelp() {
        System.out.println("help       show to user all command and instructions" + System.lineSeparator()
                + "           e.g.  help" + System.lineSeparator()
                + "print      print all tasks or any one task" + System.lineSeparator()
                + "           e.g.  print all" + System.lineSeparator()
                + "           e.g.  print 1" + System.lineSeparator()
                + "todo       add todo task in user TaskManager" + System.lineSeparator()
                + "           e.g.  todo swimming" + System.lineSeparator()
                + "deadline   add task with deadline use command '/by' in user TaskManager" + System.lineSeparator()
                + "           e.g.  deadline swimming /by sunday" + System.lineSeparator()
                + "done       mark any task as done in user TaskManager" + System.lineSeparator()
                + "           e.g.  done 1" + System.lineSeparator()
                + "edit       edit any task in user TaskManager" + System.lineSeparator()
                + "           e.g.  edit 1" + System.lineSeparator()
                + "search     search any task in user TaskManager with a keyword" + System.lineSeparator()
                + "           e.g.  search sunday" + System.lineSeparator()
                + "sort       sorting user TaskManager by completed status and deadline time" + System.lineSeparator()
                + "           e.g.  sort" + System.lineSeparator()
                + "delete     delete any task in user TaskManager" + System.lineSeparator()
                + "           e.g.  delete 1" + System.lineSeparator()
                + "clear      clear user TaskManager,delete everything inside" + System.lineSeparator()
                + "           e.g.  clear" + System.lineSeparator()
                + "save       save user tasks in user TaskManager" + System.lineSeparator()
                + "           e.g.  save" + System.lineSeparator()
                + "exit       exit the user TaskManager" + System.lineSeparator()
                + "           e.g.  exit" + System.lineSeparator());
    }
}
