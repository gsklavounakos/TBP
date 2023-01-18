import java.util.Scanner;

public class Register {
    /**
	 * Register/create new user.
	 * 
	 */
	public User registerNewUser() {
		boolean wrongRegister = true;
		do {
			try (Scanner in = new Scanner(System.in)) {
                System.out.println("\nPlease enter a username: ");
                String username = in.nextLine();                   //enters username for his new account
                System.out.println("\nAdd a password for your account: \n");         //enters password for his new account
                String password = in.nextLine();

                boolean wrongtype;
                int typeaccount = 1;
                do {
                	wrongtype = false;
                	System.out.println("\nPlease type: " + "\n\n" +       //choose personal or professional account
                		"\r" + "[1] for Personal account" + "\n\n" +
                		"\r" + "[2] for Professional account\n");
                	typeaccount = in.nextInt();
                	switch(typeaccount) {           //checks if the input is valid
                		case 1:
                            wrongtype = true;
                			break;
                		case 2:
                            wrongtype = true;
                			break;
                		default:
                		 	System.out.println("This option doesn't exist, choose 1 or 2");
                		 	wrongtype = true;
                		 	break;
                	}
                }while(wrongtype == false);

                User newUser = new User(username,password,typeaccount);     //creates a 'User' object with the values assigned by the user
                UserDAO userdao = new UserDAO();         //creates a 'UserDAO' object
                try {
                    userdao.register(newUser); 
					System.out.println("\nAccount successfully created!");
                    wrongRegister = false;
                } catch (Exception e) {
                    wrongRegister = true;
                    e.printStackTrace();
                }
                return newUser;
            }
        }while(wrongRegister = true); //loop ends when the values inserted are valid
    }
}