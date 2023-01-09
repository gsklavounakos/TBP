import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;

public class Register {

	public static void registerNewUser() {
		boolean wrongRegister;
		do {
			System.out.println("Create your account");
			Scanner in = new Scanner(System.in);
			System.out.println("Enter username: ");
			String username = in.nextLine();
			System.out.println("Enter password: ");
			String password = in.nextLine();

			boolean wrongtype;
            int typeaccount = 1;
			do {
				wrongtype = false;
				System.out.println("Enter: " + "\n" +
					"\r" + "1.Personal account" + "\n" +
					"\r" + "2.Professional account");
				typeaccount = in.nextInt();
				switch(typeaccount) {
					case 1:
						break;
					case 2:
						break;
					default:
					 	System.out.println("This option doesn't exist, choose 1 or 2");
					 	wrongtype = true;
					 	break;
				}
			}while(wrongtype == true);

			User newUser = new User(username,typeaccount,password);
			String testusername = newUser.getUsername();
			String testpassword = newUser.getPassword();
			int testtypeacc = newUser.getTypeAccount();

			String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			                   "databaseName=DB11;user=G511;password=313679;";
			Connection dbconnect = null;
			Statement statement = null;
			Statement statementInsert = null;
			ResultSet rs = null;

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch(java.lang.ClassNotFoundException e) {
				System.err.println("ClassNotFoundException");
				System.out.println(e.getMessage());
			}

			String usernameDB = "";
			String passwordDB = "";
			int typeaccDB = 0;
			wrongRegister = true;

			String query = "SELECT UserID,TypeAccount,UserPassword FROM UserProfile";


			try {
				dbconnect = DriverManager.getConnection(url);
				statement = dbconnect.createStatement();
				statementInsert = dbconnect.createStatement();
				rs = statement.executeQuery(query);
				while (rs.next()) {
					usernameDB = rs.getString("UserID");
					passwordDB = rs.getString("UserPassword");
					typeaccDB = rs.getInt("TypeAccount");
					if (!(testusername.equals(usernameDB))) {
						wrongRegister = false;
						String insert = "INSERT INTO UserProfile(UserID, TypeAccount, UserPassword) VALUES('" +
						testusername + "'," + testtypeacc + ",'" +testpassword + "')";
						statementInsert.executeUpdate(insert);
						System.out.println("Successful register");
						break;
					}
				}
				rs.close();
				statementInsert.close();
				statement.close();
				dbconnect.close();
			}catch(SQLException e) {
				System.out.print("SQLException: ");
				System.out.println(e.getMessage());
				System.out.println("Username already taken, insert new one");
				wrongRegister = true;
			}
		}while (wrongRegister == true);

		Login l = new Login();
		l.signInUser();
	}

}
