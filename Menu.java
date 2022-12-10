import java.util.Scanner;
import static java.lang.System.exit;
import java.util.InputMismatchException;

public class Menu {
    public static void printMenu() {
        System.out.println("Welcome to toEater! Please choose an option:\n1.Show posts\n2.Create a post\n3.Close app");
        int option = 0;
        boolean read = false;
        while (read != true) {
            try {
				Scanner in = new Scanner(System.in);
                option = in.nextInt();
                read = true;
            } catch (InputMismatchException e) {
                read = false;
            }
            if (option != 1 & option != 2 & option != 3) {
                read = false;
                System.out.println ("Please enter an integer value between 1 and 3.");
            }
        }

        switch(option) {
            case 1:
                Posts y = new Posts();
                y.showPosts();
                break;
            case 2:
                CreatePost p = new CreatePost();
                p.newPost();
                break;
            case 3:
                exit(0);
            }
    }
}
