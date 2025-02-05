package chitti;

import java.util.*;

public class Chitti {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Chitti(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (Exception e) {
            System.out.println(e);
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        String input = ui.readCommand();
        Parser parser = new Parser();

        while (!input.equals("bye")) {
            parser.handleCommand(input, tasks, ui);
            storage.saveTasksToFile(tasks);
            input = ui.readCommand();
        }

        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Chitti("./tasks.txt").run();
    }
}