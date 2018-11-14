package daiwei.tojava.taskmanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is to create load task from file Path and
 * save task in file Path function.
 *
 * @author David
 * @version 2018.11.02
 * @since TaskManager lvl5
 */
public class Storage {
    private static String filePath;

    /**
     * This constructs s TaskManager with string filepath
     *
     * @param filePath task storage file path
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * This function is to save tasks in storage file.
     *
     * @param tasks tasks
     */
    public static void save(List<Task> tasks) {
        File out = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) instanceof Deadline) {
                    bw.append("D ");
                    bw.append(" | ").append(tasks.get(i).isDone() ? "1" : "0").append(" | ").append(tasks.get(i).getDescription()).append(" | ").append(((Deadline) tasks.get(i)).getBy());
                } else if (tasks.get(i) instanceof Todo) {
                    bw.append("T ");
                    bw.append(" | ").append(tasks.get(i).isDone() ? "1" : "0").append(" | ").append(tasks.get(i).getDescription());
                }
                bw.append(System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Tasks save to disk " + filePath);
    }

    /**
     * This function is to read task from storage file line by line.
     *
     * @param filePath tasks'storage filepath
     * @return tasks line by line
     */
    public List<String> getLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for (String line; (line = br.readLine()) != null; ) {
                lines.add(line);
                System.out.println(lines);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return lines;
    }

    /**
     * This function is to load tasks from storage file.
     *
     * @return loaded tasks
     * @throws TaskManagerException if problem reading file can throw this exception.
     */
    public List<Task> load() throws TaskManagerException {
        List<Task> loadedTasks = new ArrayList<>();
        List<String> lines = getLines(filePath);
        for (String line : lines) {
            if (line.trim().isEmpty()) { //ignore empty lines
                throw new TaskManagerException("Problem reading file. Starting with an empty task list");
            }
            loadedTasks.add(Parser.createTask(line)); //convert the line to a task and add to the list
        }
        return loadedTasks;
    }
}
