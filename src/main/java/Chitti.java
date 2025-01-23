import java.util.Scanner;
import java.util.ArrayList;
public class Chitti {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chitti");
        System.out.println("What can I do for you?");
        Scanner s = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<>();
        String input = s.nextLine();
        while (!input.equals("bye")){
            if (input.equals("list")) {
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println((i + 1) + ". " + inputList.get(i));
                }
            }
            else{
                    System.out.println("added: " + input);
                    inputList.add(input);
            }
            input = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
