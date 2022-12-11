import java.util.InputMismatchException;
import java.util.Scanner;

public class Likes {
    public void addLike() {
		boolean a = false;
		do {
			try {
				System.out.println("Press 1 if you like this post or press 0 if you don't like this post");

				Scanner in = new Scanner(System.in);
				int option = in.nextInt();
				if (option == 1) {
					// the like must be placed in the db//
					a = true;
				} else if (option == 0) {
					a = true;
					break;
				}
			}
			catch (Exception e) {
				System.out.println("This option doesn't exist,press 1 if you like this post or press 0 if you don't like this post");
			}
		} while (a == true);
	}
}
