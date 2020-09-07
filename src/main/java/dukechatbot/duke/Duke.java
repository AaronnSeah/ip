package dukechatbot.duke;

import java.util.Objects;

import dukechatbot.command.Command;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.parser.CommandParser;
import dukechatbot.storage.Storage;
import dukechatbot.tasklist.TaskList;

public class Duke {
    
    private final TaskList taskList = new TaskList(Storage.load());

    public String getResponse(String input) {
        return this.respond(input);
    }
    
    private String respond(String input) {
        try {
            Command command = CommandParser.parse(input);
            if (Objects.isNull(command)) {
                return this.handleInvalidInput();
            } else {
                return command.getCommandExecutor().execute(command, taskList);
            }
        } catch (IndexOutOfBoundsException exception) {
            return DukeOutput.output(exception.getMessage());
        }
    }

    private String handleInvalidInput() {
        return DukeOutput.output("\u2639 OOPS!!! I'm sorry,"
                + " but I don't know what that means :-(");
    }

    public static String getGreetingMessage() {
        return DukeOutput.output(DukeConstants.INITIAL_RESPONSE);
    }
}
