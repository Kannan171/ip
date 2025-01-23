import java.util.Scanner;
public class Chitti {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chitti");
        System.out.println("What can I do for you?");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")){
            System.out.println(input);
            input = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
