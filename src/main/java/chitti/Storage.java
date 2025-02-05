package chitti;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
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

    void saveTasksToFile(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
