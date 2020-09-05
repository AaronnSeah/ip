package dukechatbot.executor;

import dukechatbot.command.Command;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.tasklist.TaskList;

/**
 * Represents executor of list command.
 * Executes the action of listing tasks in the task list.
 */
public class ListCommandExecutor extends CommandExecutor {

    /**
     * Lists all the tasks in the task list.
     *
     * @param command
     * @param taskList
     */
    @Override
    public String execute(Command command, TaskList taskList) {
        return DukeOutput.output(taskList.getListInfo());
    }
}