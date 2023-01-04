import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.lang.*;

public class Mention {

	public static String mentionUser() {
		boolean wrongLogin;
		String usernameDB;

		do {
			Scanner in = new Scanner(System.in);

			System.out.println("Username: ");
			String username = in.nextLine();

			User newUser = new User(username);
			String testusername = newUser.getUsername();

			String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
                   "databaseName=DB11;user=G511;password=313679;";
			Connection dbconnect = null;
			Statement statement = null;
			ResultSet rs = null;

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch(java.lang.ClassNotFoundException e) {
				System.err.println("ClassNotFoundException");
				System.out.println(e.getMessage());
			}

			wrongLogin = true;
			usernameDB = "";

			try {
				dbconnect = DriverManager.getConnection(url);
				statement = dbconnect.createStatement();
		    	rs = statement.executeQuery("SELECT UserID FROM UserProfile");
		    	while (rs.next()) {
		    		usernameDB = rs.getString("UserID");
		 			if (testusername.equals(usernameDB)) {
		        		System.out.println("Successful mention");
		        		wrongLogin = false;
					}
				}
				rs.close();
				statement.close();
				dbconnect.close();
				if (wrongLogin == true) {
					System.err.println("No user found, try again");
				}
			}catch(SQLException e) {
	       		System.out.print("SQLException: ");
				System.out.println(e.getMessage());
	 		}
		} while (wrongLogin == true);

		return usernameDB;

	}

}
