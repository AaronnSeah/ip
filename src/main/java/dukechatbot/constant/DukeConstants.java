package dukechatbot.constant;

/**
 * Represents the constants of Duke.
 */
public class DukeConstants {
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String INDENT = "    ";
    public static final String LINE = INDENT + "------------------------------";
    public static final String INITIAL_RESPONSE = "Hello! I'm Duke\n\tWhat can I do for you?";
    public static final String EXIT_INPUT = "bye";
    public static final String EXIT_RESPONSE = "Bye. Hope to see you again soon!";

    public static final String DONE_COMMAND = "done";
    public static final String DONE_OUTPUT = "Nice! I've marked this task as done:";
    public static final String DELETE_COMMAND = "delete";
    public static final String DELETE_OUTPUT = "Noted. I've removed this task: ";
    public static final String LIST_COMMAND = "list";
    public static final String LIST_OUTPUT = "Here are the tasks in your list:";
    public static final String FIND_COMMAND = "find";
    public static final String FIND_OUTPUT = "Here are the matching tasks in your list:";

    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    public static final String ADD_TASK_OUTPUT = "Got it. I've added this task:";
}
