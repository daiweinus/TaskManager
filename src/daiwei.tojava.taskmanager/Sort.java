package daiwei.tojava.taskmanager;

import java.util.Comparator;


public class Sort implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Deadline task1 = (Deadline) o1;
        Deadline task2 = (Deadline) o2;

        int flag = task1.getBy().compareTo(task2.getBy());

        return flag;
    }

}


