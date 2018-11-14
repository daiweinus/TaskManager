package daiwei.tojava.taskmanager;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("description: read book" + System.lineSeparator() +
                "is done? No",
                new Task("read book").toString());
    }
}