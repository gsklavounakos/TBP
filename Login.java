import java.util.Scanner;

public class Login {
    /**
	 * Login user.
	 */
	public User signInUser() throws Exception {
		boolean wrongLogin = true;
		do {
		try {
			Scanner in = new Scanner(System.in)
			System.out.println("Username: ");
			String username = in.nextLine();

			System.out.println("Password: ");
			String password = in.nextLine();

			UserDAO userdao = new UserDAO();

                    	User thisuser = userdao.authenticate(username, password);
                    	wrongLogin = false;
                   	return thisuser;
		}

            
			
        	} while(wrongLogin != true);
    	}
}
