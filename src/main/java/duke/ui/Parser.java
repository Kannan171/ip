package duke.ui;

public class Parser {
    void handleCommand(String input, TaskList taskList, Ui ui) {
        if (input.equals("list")) {
            ui.showTaskList(taskList.getTasks());
        } else if (input.startsWith("mark")) {
            int ind = Integer.parseInt(input.substring(5));
            Task task = taskList.getTask(ind);
            task.doTask();
            ui.showTaskMarked(task);
        } else if (input.startsWith("unmark")) {
            int ind = Integer.parseInt(input.substring(7));
            Task task = taskList.getTask(ind);
            task.undoTask();
            ui.showTaskUnmarked(task);
        } else if (input.startsWith("delete")) {
            int ind = Integer.parseInt(input.substring(7));
            Task toRemove = taskList.getTask(ind);
            taskList.deleteTask(ind);
            ui.showTaskDeleted(toRemove, taskList.size());
        } else {
            try {
                Task newTask = parseTask(input);
                taskList.addTask(newTask);
                ui.showTaskAdded(newTask, taskList.size());
            } catch (UnknownMessageException | InvalidTaskException e) {
                System.out.println(e);
            }
        }
    }

    private Task parseTask(String input) throws InvalidTaskException, UnknownMessageException {
        if (input.startsWith("deadline")) {
            int ind = input.indexOf("/");
            if (ind == -1 || ind == input.length() - 1) {
                throw new InvalidTaskException();
            }
            String name = input.substring(9, ind - 1);
            String deadline = input.substring(ind + 4);
            return new DeadlineTask(name, deadline);
        } else if (input.startsWith("event")) {
            int firstInd = input.indexOf("/");
            int secondInd = input.lastIndexOf("/");
            if (firstInd == -1 || firstInd == secondInd || secondInd == input.length() - 1) {
                throw new InvalidTaskException();
            }
            String name = input.substring(6, firstInd - 1);
            String start = input.substring(firstInd + 6, secondInd - 1);
            String end = input.substring(secondInd + 4);
            return new EventTask(name, start, end);
        } else if (input.startsWith("todo")) {
            if (input.length() == 4) {
                throw new InvalidTaskException();
            }
            return new ToDoTask(input.substring(5));
        } else {
            throw new UnknownMessageException();
        }
    }
}
