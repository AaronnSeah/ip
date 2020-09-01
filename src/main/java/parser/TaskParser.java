package parser;

import enums.TaskEnum;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import java.time.format.DateTimeParseException;

public class TaskParser {

    public static Task parseTask(String title, TaskEnum taskType) throws IndexOutOfBoundsException {
        Task task;
        switch(taskType) {
            case TODO:
                task = parseTodoTask(title);
                break;
            case DEADLINE:
                task = parseDeadlineTask(title);
                break;
            default:
                task = parseEventTask(title);
        }
        return task;
    }

    private static Task parseTodoTask(String title) {
        return new ToDoTask(title);
    }

    private static Task parseDeadlineTask(String title) {
        try {
            String[] titleComponents = title.split("/by", 2);
            return new DeadlineTask(titleComponents[0].trim(), titleComponents[1].trim());
        } catch (IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException("\u2639 OOPS!!! The date and time of a deadline cannot be empty.");
        } catch (DateTimeParseException exception) {
            throw new IndexOutOfBoundsException("\u2639 OOPS!!! Both date and time (24 hour format) must be in the form \"DD/MM/YYYY HH:MM\"");
        }
    }

    private static Task parseEventTask(String title) {
        try {
            String[] titleComponents = title.split("/at", 2);
            String[] dateAndTime = titleComponents[1].trim().split("\\s+", 2);
            String[] times = dateAndTime[1].split("-", 2);
            return new EventTask(titleComponents[0].trim(),
                    dateAndTime[0].trim(), times[0].trim(), times[1].trim());
        } catch (IndexOutOfBoundsException exception) {
            throw new IndexOutOfBoundsException("\u2639 OOPS!!! The start and end time of an event cannot be empty.");
        }
    }
}
