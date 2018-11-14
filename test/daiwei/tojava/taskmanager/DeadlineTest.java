package daiwei.tojava.taskmanager;

import org.junit.Assert;
import org.junit.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Assert.assertEquals("description: submit" +System.lineSeparator()+
                "is done? No" +System.lineSeparator()+
                "do by: sunday", new Deadline("submit", "sunday").toString());
    }
}