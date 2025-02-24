package chitti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ui {
    public String getTaskListString(ArrayList<Task> tasks) {
        Collections.sort(tasks, (task1, task2) -> task1.toString().compareTo(task2.toString()));

        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }

    public String getTaskMarkedString(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String getTaskUnmarkedString(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public String getTaskDeletedString(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String getTaskAddedString(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String getFoundListString(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }
}