import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
public class Chitti {
    private static final String FILE_PATH = "./chitti.txt";
    public static void main(String[] args) {

        System.out.println("Hello! I'm Chitti");
        System.out.println("What can I do for you?");
        Scanner s = new Scanner(System.in);
        ArrayList<Task> taskList = loadTasksFromFile();
        String input = s.nextLine();
        while (!input.equals("bye")){
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
            } else if (input.startsWith("mark")){
                int ind = Integer.parseInt(input.substring(5));
                Task task = taskList.get(ind-1);
                task.doTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else if (input.startsWith("unmark")){
                int ind = Integer.parseInt(input.substring(7));
                Task task = taskList.get(ind-1);
                task.undoTask();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            } else if (input.startsWith("delete")){
                int ind = Integer.parseInt(input.substring(7));
                Task toRemove = taskList.get(ind-1);
                taskList.remove(ind-1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(toRemove);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else {
                try {
                    Task newTask;
                    if (input.startsWith("deadline")) {
                        int ind = input.indexOf("/");
                        if (ind == -1 | ind == input.length() -1){
                            throw new InvalidTaskException();
                        }
                        String name = input.substring(9, ind - 1);
                        String deadline = input.substring(ind + 4);
                        newTask = new DeadlineTask(name, deadline);
                    } else if (input.startsWith("event")) {
                        int firstInd = input.indexOf("/");
                        int secondInd = input.lastIndexOf("/");
                        if (firstInd == -1 | firstInd == secondInd | secondInd == input.length()-1){
                            throw new InvalidTaskException();
                        }
                        String name = input.substring(6, firstInd - 1);
                        String start = input.substring(firstInd + 6, secondInd - 1);
                        String end = input.substring(secondInd + 4);
                        newTask = new EventTask(name, start, end);
                    } else if (input.startsWith("todo")) {
                        if (input.length()==4){
                            throw new InvalidTaskException();
                        }
                        newTask = new ToDoTask(input.substring(5));
                    } else {
                        throw new UnknownMessageException();
                    }
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (UnknownMessageException | InvalidTaskException e){
                    System.out.println(e);
                }
            }
            input = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return taskList;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.add(Task.fromFileFormat(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskList;
    }

    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
