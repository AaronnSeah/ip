package storage;

import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loads and saves task list from / to file.
 */
public class Storage {

    private static final String LIST_FILE_PATH = "./data/duke.txt";

    /**
     * Loads the task list from stored tasks in a file.
     * 
     * @return Task list.
     */
    public static List<Task> load() {
        File file = new File(LIST_FILE_PATH);
        File parentFile = file.getParentFile();
        List<Task> list = new ArrayList<>();
        if (!parentFile.exists()) {
            return list;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] components = line.split("\\|");
                for (int i = 0 ; i < components.length ; i++) {
                    components[i] = components[i].trim();
                }
                Task task;
                boolean isDone = Integer.parseInt(components[1]) == 1;
                switch(components[0]) {
                    case "T":
                        task = new ToDoTask(components[2], isDone);
                        break;
                    case "D":
                        task = new DeadlineTask(components[2], components[3], isDone);
                        break;
                    default:
                        String[] dateTimeComp = components[3].trim().split("\\s+", 2);
                        String date = dateTimeComp[0];
                        String[] timeComp = dateTimeComp[1].split("-", 2);
                        String startTime = timeComp[0];
                        String endTime = timeComp[1];
                        task = new EventTask(components[2], date, startTime, endTime, isDone);
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\u2639 OOPS! some error saving the list.");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Saves the tasks in the task list to a file.
     * 
     * @param taskList
     */
    public static void save(List<Task> taskList) {
        File file = new File(LIST_FILE_PATH);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : taskList) {
                fileWriter.write(task.getSaveFormat());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("\u2639 OOPS! some error saving the list." );
            e.printStackTrace();
        }
    }
}
