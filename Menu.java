import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    public static boolean printMenu() {
        System.out.println("\n\nWelcome to toEater! Please choose an option:\n1.Show posts\n2.Create a post\n3.Close app\n");
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
                boolean b = true;
                return b;
            case 2:
                Posts p = new Posts();
                p.newPost();
                boolean w = true;
                return w;
            case 3:
                boolean q = false;
                return q;
            }
        return read;
    }
}
