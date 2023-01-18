import java.sql.*;
import java.util.ArrayList;


/**
 * UserDAO provides all the necessary methods related to users.
 *
 */
public class UserDAO {

	/**
	 * This method is used to authenticate a user.
	 * 
	 * @param username, String
	 * @param password, String
     * @param typeaccount, int
	 * @return user, the user object
	 * @throws Exception, if the credentials are not valid
	 */
    
	public User authenticate(String username, String password) throws Exception {
		
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }

		String sql = "SELECT * FROM allusers where username = '" + username + "' and upassword= '" + password + "'";
        

        try {
            
            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            rs = statement.executeQuery(sql);

		if (!rs.next()) {

			rs.close();
			statement.close();
            dbconnect.close();
			throw new Exception("No User found with username: |" + username +"| and this password("+password+")");
		}

        User user = new User(rs.getString("username"),rs.getString("upassword"),
                rs.getInt("typeaccount"));

		System.out.println("SUccesful login!");
        rs.close();
        statement.close();
        dbconnect.close();
        return user;
            
        } catch (Exception e) {
			throw new Exception(e.getMessage());
        }
			
		
	} //End of authenticate
	
	/**
	 * Register/create new user.
	 * 
	 * @param user, user
	 * @throws Exception, if encounter any error.
	 */
	public void register(User user) throws Exception {
			

        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
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

		String sql = "SELECT * FROM allusers where username = '" + user.getUsername() + "'";
        

        try {
            
            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            statementInsert = dbconnect.createStatement();

            rs = statement.executeQuery(sql);

		if (rs.next()) {			//if the result set has a next value, it means that it found an account with the same username

			rs.close();
			statementInsert.close();
            statement.close();
            dbconnect.close();
			throw new Exception("Sorry, username "+ user.getUsername() + " already registered");
		}
		
		String sql2 = "insert into allusers(username, upassword, typeaccount) values ('" +
		user.getUsername() + "','" + user.getPassword() + "',' " + user.getTypeAccount() + "')";

        statementInsert.executeUpdate(sql2);
		statementInsert.close();	
		rs.close();
		statement.close();
		dbconnect.close();

        } catch (Exception e) {
			throw new Exception(e.getMessage());
        }
	}//end of register

    /**
	 * adds the post to the posts column of the allposts table
     * 
	 * 
	 * @param newpost , the post user typed
	 * @throws Exception, if encounter any error.
	 */
    public void addPost(String newpost, int stars, String username) throws Exception {
			
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }

        String sql = "INSERT INTO allposts(post,rating,likes,commentnumber,username) VALUES ('" + newpost + "','" + stars + "',0,0,'"+ username + "')";
        try {
            
            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            statement.executeUpdate(sql);

            statement.close();
            dbconnect.close();


        } catch (Exception e) {
            statement.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
	}//end of addpost

    /**
	 * adds comment to the comments column of the comments table
     * 
	 * 
	 * @param user, user
	 * @throws Exception, if encounter any error.
	 */
    public void addComment(String newcomment, int postid, String username) throws Exception {
			
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;
		Statement statement1 = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }

        String sql = "INSERT INTO allcomments(comment,postid,username) VALUES ('" + newcomment + "','" + postid + "','"+ username + "')";	//inserts comment in allcomments table
		String sql1 = "UPDATE allposts SET commentnumber = commentnumber + 1 WHERE postid ='"+postid+"'";			// updates allposts table to change the commentsnumber to the current comments number (+1) 
        
        try {
            
            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
			statement1 = dbconnect.createStatement();
            statement.executeUpdate(sql);
			statement1.executeUpdate(sql1);

            statement.close();
			statement1.close();
            dbconnect.close();


        } catch (Exception e) {
            statement.close();
			statement1.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
	}//end of addcomment
    
    /**
	 * returns a list of all the cocmment's id's for the post with id = the parameter's postid
	 * @param postid, int
	 * @throws Exception, if encounter any error.
	 */
    public ArrayList<Integer> getCommentsIds(int postid) throws Exception {
			
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }

        String sql = "SELECT * FROM allcomments where postid = '" + postid + "'";
        

        try {
            
            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            rs = statement.executeQuery(sql);

            if (!rs.next()) {

                rs.close();
                statement.close();
                dbconnect.close();
                throw new Exception("Post with id = " + postid + " doesn't have any comments" );
            }

            ArrayList<Integer> commentsids = new ArrayList<>();

            do {

                commentsids.add(rs.getInt("commentid"));

            } while (rs.next());

            rs.close();
            statement.close();
            dbconnect.close();
            return commentsids;

        } catch (Exception e) {
            rs.close();
            statement.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
	}//end of getCommentsIds

    /**
	 * returns a single comment with id = the parameter's commentid
	 * @param commentid, int
	 * @throws Exception, if encounter any error.
	 */
    public Comments getComment(int commentid) throws Exception {
			
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }

        String sql = "SELECT * FROM allcomments where commentid='" + commentid + "'";			
			
        try{

            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            rs = statement.executeQuery(sql);
			
			if (!rs.next()) {

				rs.close();
				statement.close();
				dbconnect.close();
				throw new Exception("No comments found" );
			}
			
            Comments c = new Comments(rs.getInt("commentid"),rs.getString("comment"),rs.getInt("postid"),rs.getString("username"));
            
            rs.close();
            statement.close();
            dbconnect.close();
            return c;


        } catch (Exception e) {
            rs.close();
            statement.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
	}//end of getComment
	
    /**
	 * returns a single post with id = the parameter's postid
	 * @param postid, int
	 * @throws Exception, if encounter any error.
	 */
    public Posts getPost(int postid) throws Exception {
			
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }
        
		String sql = "SELECT * FROM allposts where postid='" + postid + "'";

        try{

            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            rs = statement.executeQuery(sql);
			
			if (!rs.next()) {

                rs.close();
                statement.close();
                dbconnect.close();
				Posts e = new Posts();
				return e;
            }
			
            Posts p = new Posts(rs.getInt("postid"),rs.getString("post"),rs.getInt("rating"),rs.getInt("likes"),rs.getInt("commentnumber"));
            rs.close();
            statement.close();
            dbconnect.close();
            return p;


        } catch (Exception e) {
            rs.close();
            statement.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
	}//end of getPost

    /**
	 * returns a single post with id = the parameter's postid
	 * @param postid, int
	 * @throws Exception, if encounter any error.
	 */
    public String getPostRating(int postid) throws Exception {
			
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }

		String sql = "SELECT * FROM allposts where postid='" + postid + "'";

		if (!rs.next()) {

			rs.close();
			statement.close();
			dbconnect.close();
			throw new Exception("unable to retrieve posts's rating" );
		}

        try{

            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            rs = statement.executeQuery(sql);
            String post = rs.getString("posts");
            rs.close();
            statement.close();
            dbconnect.close();
            return post;


        } catch (Exception e) {
            rs.close();
            statement.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
	}//end of getPostRating


    /**
	 * Return size of the allposts table.
	 * 
	 * @throws Exception, if encounter any error.
	 */
    public int getPostSize() throws Exception {
        
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
			        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            System.out.println(e.getMessage());
        }

		String sql = "SELECT IDENT_CURRENT('allposts');";

        try{

            dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            rs = statement.executeQuery(sql);
            int post = rs.getInt("(No column name)");
            rs.close();
            statement.close();
            dbconnect.close();
            return post;
        } catch (Exception e) {
            rs.close();
            statement.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
    }

    /**
	 * adds a like to the post specified by the user with postid
     * 
	 * 
	 * @param user, user
	 * @throws Exception, if encounter any error.
	 */
    public void addLike(int postid) throws Exception {
			
        String url = "jdbc:sqlserver://sqlserver.dmst.aueb.gr;" +
        "databaseName=DB98;user=G598;password=39gr00;";
        Connection dbconnect = null;
        Statement statement = null;

        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(java.lang.ClassNotFoundException e) {
        System.err.println("ClassNotFoundException");
        System.out.println(e.getMessage());
        }

		String sql = "UPDATE allposts SET likes = likes + 1 WHERE postid ='"+postid+"'";

        try{
			dbconnect = DriverManager.getConnection(url);
            statement = dbconnect.createStatement();
            statement.executeUpdate(sql);

            statement.close();
            dbconnect.close();

        } catch (Exception e) {
            statement.close();
            dbconnect.close();
            throw new Exception(e.getMessage());
        }
	}//end of addLike


} //End of class