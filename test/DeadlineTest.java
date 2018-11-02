import daiwei.tojava.taskmanager.Deadline;
import org.junit.Test;
import org.junit.Assert;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Assert.assertEquals("description: submit" +System.lineSeparator()+
                "is done? No" +System.lineSeparator()+
                "do by: sunday", new Deadline("submit", "sunday").toString());
    }
}