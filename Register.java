import java.util.Scanner;

public class Register {

	public static void registerNewUser() {
		boolean found;
		do {
			Scanner in = new Scanner(System.in);

			System.out.println("Enter username: ");
			String username = in.nextLine();

			System.out.println("Enter password: ");
			String password = in.nextLine();

			User newUser = new User(username,password);
			found = newUser.findUsername(username);
			if (found == true) {
				System.out.println("The username is already taken, enter a new username");
				System.out.println("Enter username: ");
			}

		} while(found = false);
	}

}



