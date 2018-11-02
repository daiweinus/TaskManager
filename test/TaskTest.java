import daiwei.tojava.taskmanager.Task;
import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test
    public void testStringConversion() {
        Assert.assertEquals("description: read book" + System.lineSeparator() +
                "is done? No",
                new Task("read book").toString());
    }
}