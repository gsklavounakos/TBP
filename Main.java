import static java.lang.System.exit;
import java.util.Scanner;
import java.lang.Exception;



public class Main {
	/**
	 * the maim method. Its responsible for showing the starting menu (register, log in , exit) 
	 * and redirects accordingly
	 */
	public static void main(String args[]) {
  		boolean read = false;
  		System.out.println("Welcome to ToEater!");
		System.out.println("Enter: " + "\n" +
                        "\r" + "1.Register" + "\n" +
                        "\r" + "2.Log in" + "\n" +
                        "\r" + "3.Exit.");
        int option = 0;
		while (read != true) {  //checks the user's input value for the option
			try{
				Scanner in = new Scanner(System.in);
				option = in.nextInt();
				read = true;
			}catch (Exception e) {
				read = false;
			}
			if (option != 1 & option != 2 & option != 3) {
				read = false;
				System.err.println("This option doesn't exist, choose 1, 2 or 3");
			}
		}
		
		switch(option) {
			case 1:	                                                 //option 1 for register
				try {
					Register r = new Register();
					User tempuser = r.registerNewUser();

					Boolean b = true;

					while (b == true) {

						User thisuser = tempuser;
						b = Menu.printMenu(thisuser); 

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

					break;


			case 2:											//option 2 for login
				
				Login l = new Login();
				 try {
					User thisuser = l.signInUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:								//option 3 exit from the app
				exit(0);

		}

	}
}
