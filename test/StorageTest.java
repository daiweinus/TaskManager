import daiwei.tojava.taskmanager.Storage;
import daiwei.tojava.taskmanager.Task;
import org.junit.Test;
import java.util.*;


import static junit.framework.TestCase.assertEquals;

public class StorageTest {
    public Storage storage;


    @Test
    public void createTask() {
        Task actual = storage.createTask("T | 1 | read book");
        Task expected = new Task("todo read book");
        assertEquals(expected,actual);
    }

    @Test
    public void getLines() {
        Storage storage = new Storage("D:/tasks.txt");

        List<String> lines = storage.getLines("D:/tasks.txt");
        for (String line : lines) {
            System.out.println(line);
        }
    }


    @Test
    public void load() {
    }

    @Test
    public void save() {
    }
}