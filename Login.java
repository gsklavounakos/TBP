import java.util.Scanner;

public class Login {
    /**
	 * Login user.
	 */
	public User signInUser() throws Exception {
		boolean wrongLogin = true;
		do {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter your username: ");
			String username = in.nextLine();

			System.out.println("Enter your password: ");
			String password = in.nextLine();

			System.out.println("\n ... ");
			UserDAO userdao = new UserDAO();

			User thisuser = userdao.authenticate(username, password);
			wrongLogin = false;
			return thisuser;


            
			
        	} while(wrongLogin != true);
    	}
}
