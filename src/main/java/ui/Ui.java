package ui;

import dukeoutput.DukeOutput;
import parser.CommandParser;
import command.Command;
import constant.DukeConstants;
import storage.Storage;
import tasklist.TaskList;

import java.util.Objects;
import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    private final TaskList taskList = new TaskList(Storage.load());

    private boolean isOn = true;

    public void run() {
        printGreetingMessage();
        while (isOn) {
            String input = this.getInput();
            if (CommandParser.isExit(input)) {
                this.exit();
            } else {
                this.respond(input);
            }
        }
    }

    private void exit() {
        DukeOutput.output(DukeConstants.EXIT_RESPONSE);
        Storage.save(this.taskList.getList());
        this.isOn = false;
    }

    private void respond(String input) {
        try {
            Command command = CommandParser.parse(input);
            if (Objects.isNull(command)) {
                this.handleInvalidInput();
            } else {
                command.getCommandExecutor().execute(command, taskList);
            }
        } catch (IndexOutOfBoundsException exception) {
            DukeOutput.output(exception.getMessage());
        }
    }

    private void handleInvalidInput() {
        DukeOutput.output("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void printGreetingMessage() {
        DukeOutput.outputGreeting();
        DukeOutput.output(DukeConstants.INITIAL_RESPONSE);
    }

    private String getInput() {
        return scanner.nextLine();
    }
}
