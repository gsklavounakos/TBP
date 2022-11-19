import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
  		Scanner in = new Scanner(System.in);
  		boolean wrongRequest;
  		System.out.println("Welcome to ToEater!");

  		do {

			wrongRequest = false;

			System.out.println("Enter: " + "\n" +
                        "\r" + "1.Register" + "\n" +
                        "\r" + "2.Log in" + "\n" +
                        "\r" + "3.Exit.");
			int option = in.nextInt();

			switch(option) {
				case 1:
					Register r = new Register();
					r.registerNewUser();
					System.out.println("Enter: " + "\n" +
                        "\r" + "1.Personal account" + "\n" +
                        "\r" + "2.Professional account");
					int typeAccount = in.nextInt();
					break;
				case 2:
					Login l = new Login();
					l.signInUser();
					break;
				case 3:
					break;
				default:
					System.out.println("This option doesn't exist, choose 1, 2 or 3");
					wrongRequest = true;
					break;
				}

		}while(wrongRequest == true);
	}

}








