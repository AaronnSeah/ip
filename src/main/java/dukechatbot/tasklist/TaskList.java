package dukechatbot.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dukechatbot.constant.DukeConstants;
import dukechatbot.task.Task;

/**
 * Represents a list of task where you can add, delete tasks and list the tasks.
 */
public class TaskList {

    private final List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds task to task list.
     * 
     * @param task
     */
    public void add(Task task) {
        assert(!Objects.isNull(task));
        this.list.add(task);
    }

    /**
     * Returns task info for each task in task list.
     * 
     * @return List of task info.
     */
    public List<String> getListInfo() {
        List<String> listInfo = new ArrayList<>();
        listInfo.add(DukeConstants.LIST_OUTPUT);

        int num = 0;
        for (Task output : this.list) {
            num++;
            listInfo.add(String.format("%d.%s", num, output));
        }
        return listInfo;
    }

    /**
     * Marks a task done in the task list given its index.
     * 
     * @param idx
     * @return Task String information.
     */
    public String markDone(int idx) {
        assert(idx >= 0 && idx < this.list.size());
        Task task = this.list.get(idx);
        task.markDone();
        return task.toString();
    }

    /**
     * Returns the task list.
     * @return task list.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Returns the current size of the task list.
     * @return Current size of task list.
     */
    public int getCurrentSize() {
        return this.list.size();
    }

    /**
     * Deletes a task in the task list given its index.
     * 
     * @param idx
     * @return Deleted task string information.
     */
    public String delete(int idx) {
        assert(idx >= 0 && idx < this.list.size());
        Task task = this.list.remove(idx);
        return task.toString();
    }

    /**
     * Returns a list of tasks in the task list that contains the search keyword.
     * 
     * @param searchKeyword
     * @return List of tasks in the task list that contains the search keyword.
     */
    public List<Task> findMatches(String searchKeyword) {
        return this.list.stream()
                .filter(x -> x.contains(searchKeyword))
                .collect(Collectors.toList());
    } 
}
