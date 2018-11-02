package daiwei.tojava.taskmanager;

import java.util.Scanner;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;

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
        System.out.println("Your task? ");
        return toLowerCase(in.nextLine().trim());
    }

    /**
     * This function is to read user new command that its user want edit to.
     *
     * @return user new command
     */
    public String readNewCommand() {
        System.out.println("Please key in your New Task: ");
        return toLowerCase(in.nextLine().trim());
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
}
