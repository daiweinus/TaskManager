import daiwei.tojava.taskmanager.Deadline;
import daiwei.tojava.taskmanager.Parser;
import daiwei.tojava.taskmanager.TaskManagerException;
import daiwei.tojava.taskmanager.Todo;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class ParserTest {

    @Test
    public void getCommandWord() {
        assertEquals("todo", Parser.getCommandWord("todo read book"));
        assertEquals("deadline", Parser.getCommandWord("deadline return book /by next Friday"));
        assertEquals("exit", Parser.getCommandWord("exit"));
        assertEquals("xyz", Parser.getCommandWord("   xyz   "));// leading and trailing spaces
        assertEquals("", Parser.getCommandWord("     "));
    }

    @Test
    public void createTodo() throws TaskManagerException {
        Todo actual = Parser.createTodo("todo read book");
        Todo expected = new Todo("read book");
        assertEquals(expected.toString(), actual.toString());
        Todo actual1 = Parser.createTodo("     TODO read book    ");//leading,trailing spaces and up/down case
        Todo expected1 = new Todo("read book");
        assertEquals(expected1.toString(), actual1.toString());
    }

    @Test
    public void createDeadline() throws TaskManagerException {
        Deadline actual = Parser.createDeadline("deadline read book /by sunday");
        Deadline expected = new Deadline("read book","sunday");
        assertEquals(expected.toString(), actual.toString());
        Deadline actual1= Parser.createDeadline("    DeadLine read book by sunday /BY sunday    ");//leading,trailing spaces and up/down case
        Deadline expected1 = new Deadline("read book by sunday","sunday");
        assertEquals(expected1.toString(), actual1.toString());
    }
}