mport java.util.Scanner;
import static java.lang.System.exit;
import java.util.InputMismatchException;

public class Main {

	public static void main(String args[]) {
  		boolean read = false;
  		System.out.println("Welcome to ToEater!");
		System.out.println("Enter: " + "\n" +
                        "\r" + "1.Register" + "\n" +
                        "\r" + "2.Log in" + "\n" +
                        "\r" + "3.Exit.");
        int option = 0;
		while (read != true) {
			try{
				Scanner in = new Scanner(System.in);
				option = in.nextInt();
				read = true;
			}catch (InputMismatchException e) {
				read = false;
			}
			if (option != 1 & option != 2 & option != 3) {
				read = false;
				System.err.println("This option doesn't exist, choose 1, 2 or 3");
			}
		}
		switch(option) {
			case 1:
				Register r = new Register();
				r.registerNewUser();
				break;
			case 2:
				Login l = new Login();
				l.signInUser();
				break;
			case 3:
				exit(0);
		}
	}
}






