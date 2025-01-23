import java.util.Scanner;
import java.util.ArrayList;
public class Chitti {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chitti");
        System.out.println("What can I do for you?");
        Scanner s = new Scanner(System.in);
        ArrayList<Task> inputList = new ArrayList<>();
        String input = s.nextLine();
        while (!input.equals("bye")){
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println((i + 1) + ". " + inputList.get(i));
                }
            } else if (input.startsWith("mark")){
                int ind = Integer.parseInt(input.substring(5));
                Task task = inputList.get(ind-1);
                task.doTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else if (input.startsWith("unmark")){
                int ind = Integer.parseInt(input.substring(7));
                Task task = inputList.get(ind-1);
                task.undoTask();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            }
            else{
                    System.out.println("added: " + input);
                    inputList.add(new Task(input));
            }
            input = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
