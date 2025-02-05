package chitti;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    Task getTask(int index) {
        return tasks.get(index - 1);
    }

    int size() {
        return tasks.size();
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }
}
