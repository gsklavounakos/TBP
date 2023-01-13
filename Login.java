import java.util.Scanner;

public class Login {
    /**
	 * Login user.
	 */
	public User signInUser() throws Exception {
		boolean wrongLogin = true;
		do {
			try (Scanner in = new Scanner(System.in)) {
				System.out.println("Username: ");   // enters user's username
				String username = in.nextLine();

				System.out.println("Password: ");   //enters user's password
				String password = in.nextLine();

				UserDAO userdao = new UserDAO();

				User thisuser = userdao.authenticate(username, password); //this method is used to authenticate a user
				wrongLogin = false;
				return thisuser;
		        } catch (Exception e) {
				e.printStackTrace();
			}

            
			
        	} while(wrongLogin != true); //loop ends when username and password are valid
	}
}
