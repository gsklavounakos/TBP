public class User {

	private String username = " ";
	private String password = " ";
	private int typeaccount = 0;

	public User(String username, int typeaccount, String password) {
		this.username = username;
		this.typeaccount = typeaccount;
		this.password = password;
	}
	
	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setUsername(String username) {
        this.username = username;
	}

	public void setPassword(String password) {
        this.password = password;
	}

	public void setTypeAccount(int typeaccount) {
		this.typeaccount = typeaccount;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getTypeAccount() {
		return typeaccount;
	}
}


