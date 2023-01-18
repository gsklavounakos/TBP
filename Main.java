import static java.lang.System.exit;
import java.util.Scanner;



public class Main {
	/**
	 * the maim method. Its responsible for showing the starting menu (register, log in , exit) 
	 * and redirects accordingly
	 */
	public static void main(String args[]) {
  		boolean read = false;
		System.out.println("======================================");
		System.out.println("|        Welcome to toEater!         |");
		System.out.println("|     Please select and option:      |");
		System.out.println("======================================");
		System.out.println("| Options:                           |");
		System.out.println("|        [1] Register                |");
		System.out.println("|        [2] Log in                  |");
		System.out.println("|        [3] Exit                    |");
		System.out.println("======================================");
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
			case 1:	                         //option 1 for register
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


			case 2:							//option 2 for login
				
				Login l = new Login();
				 try {
					User thisuser = l.signInUser();
					Boolean b = true;

					while (b == true) {

						b = Menu.printMenu(thisuser); 

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:							//option 3 exit from the app
				exit(0);

		}

	}
}
