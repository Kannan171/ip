import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    void showWelcomeMessage() {
        System.out.println("Hello! I'm Chitti");
        System.out.println("What can I do for you?");
    }

    String readCommand() {
        return scanner.nextLine();
    }

    void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
