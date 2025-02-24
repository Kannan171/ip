package chitti;

import java.util.ArrayList;

public class Parser {
    /**
     * Handles the different commands that users can input.
     * @param input User's command as a string.
     * @param taskList The list of tasks.
     * @param ui The UI instance for responses.
     */
    String handleCommand(String input, TaskList taskList, Ui ui) {
        assert input != null : "Input should not be null";

        // Extract the command (first word of input)
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list":
                return ui.getTaskListString(taskList.getTasks());

            case "mark":
                return handleMark(args, taskList, ui, true);

            case "unmark":
                return handleMark(args, taskList, ui, false);

            case "delete":
                return handleDelete(args, taskList, ui);

            case "find":
                return ui.getFoundListString(taskList.findTasks(args));

            case "deadline":
            case "event":
            case "todo":
                return handleTaskCreation(input, taskList, ui);

            default:
                return "I'm sorry, but I don't understand that command!";
        }
    }

    /**
     * Marks or unmarks a task based on the command.
     */
    private String handleMark(String args, TaskList taskList, Ui ui, boolean isMark) {
        try {
            int index = Integer.parseInt(args);
            Task task = taskList.getTask(index);
            if (isMark) {
                task.doTask();
                return ui.getTaskMarkedString(task);
            } else {
                task.undoTask();
                return ui.getTaskUnmarkedString(task);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task number. Please enter a valid index.";
        }
    }

    /**
     * Handles task deletion.
     */
    private String handleDelete(String args, TaskList taskList, Ui ui) {
        try {
            int index = Integer.parseInt(args);
            Task toRemove = taskList.getTask(index);
            taskList.deleteTask(index);
            return ui.getTaskDeletedString(toRemove, taskList.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task number. Please enter a valid index.";
        }
    }

    /**
     * Handles the creation of tasks.
     */
    private String handleTaskCreation(String input, TaskList taskList, Ui ui) {
        try {
            Task newTask = parseTask(input);
            taskList.addTask(newTask);
            return ui.getTaskAddedString(newTask, taskList.size());
        } catch (UnknownMessageException | InvalidTaskException e) {
            return e.toString();
        }
    }

    /**
     * Parses the input string and creates a task.
     */
    private Task parseTask(String input) throws InvalidTaskException, UnknownMessageException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "deadline":
                int ind = args.indexOf("/");
                if (ind == -1 || ind == args.length() - 1) throw new InvalidTaskException();
                String name = args.substring(0, ind - 1);
                String deadline = args.substring(ind + 4);
                return new DeadlineTask(name, deadline);

            case "event":
                int firstInd = args.indexOf("/");
                int secondInd = args.lastIndexOf("/");
                if (firstInd == -1 || firstInd == secondInd || secondInd == args.length() - 1)
                    throw new InvalidTaskException();
                String eventName = args.substring(0, firstInd - 1);
                String start = args.substring(firstInd + 6, secondInd - 1);
                String end = args.substring(secondInd + 4);
                return new EventTask(eventName, start, end);

            case "todo":
                if (args.isEmpty()) throw new InvalidTaskException();
                return new ToDoTask(args);

            default:
                throw new UnknownMessageException();
        }
    }
}
