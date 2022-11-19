public class User {

	private String username;
	private String password;
	private String[] usernameDatabase;
	private String[] passwordDatabase;
	public static int count = 0;

	public User() {
		this.username = " ";
		this.password = " ";
		for (int i = 0; i <= usernameDatabase.length; i++) {
			this.usernameDatabase[i] = " ";
		}
		for (int i = 0; i <= passwordDatabase.length; i++) {
			this.passwordDatabase[i] = " ";
		}
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.usernameDatabase[count] = this.username;
		this.passwordDatabase[count] = this.password;
		count++;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String[] getUsernameDatabase() {
			return usernameDatabase;
	}

	public String[] getPasswordDatabase() {
			return passwordDatabase;
	}
	public boolean findUsername(String x) {
		boolean found = false;
		for (int i = 0; i <= usernameDatabase.length; i++) {
			if (usernameDatabase[i] == x) {
				found = true;
			}
		}
		return found;
	}

}

