import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String IDENT = "\t";
    private static final String LINE = IDENT + "------------------------------";
    private static final String INITIAL_RESPONSE = "Hello! I'm Duke\n\tWhat can I do for you?";
    private static final String EXIT_INPUT = "bye";
    private static final String EXIT_RESPONSE = "Bye. Hope to see you again soon!";

    private static final String DONE_COMMAND = "done";
    private static final String DONE_OUTPUT = "Nice! I've marked this task as done:";
    private static final String LIST_COMMAND = "list";
    private static final String LIST_OUTPUT = "Here are the tasks in your list:";

    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static final String ADD_TASK_OUTPUT = "Got it. I've added this task:";

    private static final List<Task> TASK_LIST = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String response;
        printResponse(INITIAL_RESPONSE);
        while (true) {
            response = scanner.nextLine();
            if (response.equals(EXIT_INPUT)) {
                printResponse(EXIT_RESPONSE);
                break;
            } else if (response.equals(LIST_COMMAND)) {
                printList();
            } else if (response.split("\\s+")[0].equals(DONE_COMMAND)) {
                try {
                    String[] components = response.split("\\s+");
                    if (components.length == 2) {
                        int parameter = Integer.parseInt(response.split("\\s+")[1]);
                        markTaskDone(parameter - 1);
                    }
                } catch (Exception e) {

                }
            } else if (response.split("\\s+")[0].equals(TODO_COMMAND)) {
                Task task = new ToDoTask(response.split("\\s+", 2)[1]);
                addToList(task);
                outputTask(task);
            } else if (response.split("\\s+")[0].equals(DEADLINE_COMMAND)) {
                String title = response.split("\\s+", 2)[1];
                String[] titleComponents = title.split("/", 2);
                title = String.format("%s (%s)", titleComponents[0], titleComponents[1]);
                Task task = new DeadlineTask(title);
                addToList(task);
                outputTask(task);
            } else if (response.split("\\s+")[0].equals(EVENT_COMMAND)) {
                String title = response.split("\\s+", 2)[1];
                String[] titleComponents = title.split("/", 2);
                title = String.format("%s (%s)", titleComponents[0], titleComponents[1]);
                Task task = new EventTask(title);
                addToList(task);
                outputTask(task);
            }
            else {
                addToList(response);
                printResponse("added: " + response);
            }
        }
    }

    private static void markTaskDone(int idx) {
        Task task = TASK_LIST.get(idx);
        task.markDone();
        String response = String.format("%s\n%s%s[%s] %s", DONE_OUTPUT, IDENT, IDENT,
                task.getStatusIcon(), task.getTitle());
        printResponse(response);
    }
    private static void printResponse(String response) {
        System.out.println(LINE);
        System.out.printf("%s%s\n", IDENT, response);
        System.out.println(LINE);
    }

    private static void outputTask(Task task) {
        System.out.println(LINE);
        System.out.println(IDENT + ADD_TASK_OUTPUT);
        System.out.printf("%s%s%s\n",IDENT, IDENT, task);
        System.out.printf("%sNow you have %d tasks in the list.%n", IDENT, TASK_LIST.size());
        System.out.println(LINE);
    }

    private static void addToList(String response) {
        TASK_LIST.add(new Task(response));
    }

    private static void addToList(Task task) {
        TASK_LIST.add(task);
    }

    private static void printList() {
        System.out.println(LINE);
        System.out.println(IDENT + LIST_OUTPUT);
        int num = 0;
        for (Task output : TASK_LIST) {
            num++;
            System.out.printf("%s%d.%s\n", IDENT, num, output);
        }
        System.out.println(LINE);
    }
}
