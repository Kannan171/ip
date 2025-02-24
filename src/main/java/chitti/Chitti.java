package chitti;

import java.util.*;

public class Chitti {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Chitti(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (Exception e) {
            System.out.println(e);
            tasks = new TaskList(new ArrayList<>());
        }
        assert ui != null : "Ui instance should not be null";
        assert storage != null : "Storage instance should not be null";
        assert parser != null : "Parser instance should not be null";
    }

    /**
     * Runs the chatbot
     */
    /**public void run() {
        ui.showWelcomeMessage();
        String input = ui.readCommand();
        Parser parser = new Parser();

        while (!input.equals("bye")) {
            parser.handleCommand(input, tasks, ui);
            storage.saveTasksToFile(tasks);
            input = ui.readCommand();
        }

        ui.showExitMessage();
    }**/
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return "Bye! Hope to see you again soon!";
        }
        return parser.handleCommand(input, tasks, ui);
    }
    /**public static void main(String[] args) {
        new Chitti("./tasks.txt").run();
    }**/
}