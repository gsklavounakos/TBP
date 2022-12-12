import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;

public class Register {

	public static void registerNewUser() {
		boolean wrongRegister;
		do {
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
			                   "databaseName=DB11;user=G511;password=313678;";
			Connection dbconnect = null;
			Statement statement = null;
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

			try {
				dbconnect = DriverManager.getConnection(url);
				statement = dbconnect.createStatement();
				rs = statement.executeQuery("SELECT UserID,TypeAccount,UserPassword FROM UserProfile");
				while (rs.next()) {
					usernameDB = rs.getString("UserID");
					passwordDB = rs.getString("UserPassword");
					typeaccDB = rs.getInt("TypeAccount");
					if (testusername.equals(usernameDB)) {
						System.out.println("Username already taken, insert new one");
						wrongRegister = false;
					} else {
						System.out.println("User does not exists in database");
						wrongRegister = true;
						//String insert = "INSERT INTO UserProfile ('UserID','TypeAccount','UserPassword') VALUES (?,?,?)";
						//PreparedStatement st = dbconnect.prepareStatement(insert);
						//st.setString(1,testusername);
						//st.setInt(2,testtypeacc);
						//st.setString(3,testpassword);
						//ResultSet resultSet = st.executeQuery();
						//st.close();
						//resultSet.close();
					}
				}
				rs.close();
				statement.close();
				dbconnect.close();
			}catch(SQLException e) {
				System.out.print("SQLException: ");
				System.out.println(e.getMessage());
			}
		}while (wrongRegister == true);
	}

}
