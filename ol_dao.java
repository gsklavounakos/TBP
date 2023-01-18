import java.sql.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * UserDAO provides all the necessary methods related to users.
 *
 */
public class ol_dao {

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
		
		String sql = "SELECT * FROM user WHERE username=? and upassword=?";
        Connection con = null;
        DB db = new DB();

        try {
            Statement statement = null;
	        Statement statementInsert = null;

            con = db.getConnection();
            statement = con.createStatement();
			statementInsert = con.createStatement();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
			stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {

                rs.close();
                stmt.close();
                db.close();
                throw new Exception("No User found with username: " + username +"and this password");

            }

            User user = new User(rs.getString("username"),rs.getString("upassword"),
                rs.getInt("typeaccount"));
            rs.close();
            stmt.close();
            db.close();

            return user;

            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
                
            }
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

		if (rs.next()) {

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
			System.err.println("Over here");
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
			
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "INSERT INTO allposts(post,rating,likes,commentnumber,username) VALUES (?,?,0,0,?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, newpost);
            stmt.setInt(2, stars);
            stmt.setString(3, username);
            
            stmt.executeUpdate();	
			stmt.close();
            db.close();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
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
			
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "INSERT INTO allcomments(postid, comment, username) VALUES (?, ?, ?);";
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, postid);
			stmt.setString(2, newcomment);
            stmt.setString(3, username);

            stmt.executeUpdate();	
			stmt.close();
            db.close();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
		}
	}//end of addcomment
    
    /**
	 * returns a list of all the cocmment's id's for the post with id = the parameter's postid
	 * @param postid, int
	 * @throws Exception, if encounter any error.
	 */
    public ArrayList<Integer> getCommentsIds(int postid) throws Exception {
			
        Connection con = null;
        DB db = new DB();

        try {

            con = db.getConnection();
			String sql = "SELECT * FROM allcomments where postid=? ;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, postid);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {

                rs.close();
                stmt.close();
                db.close();
                throw new Exception("Post with id = " + postid + "doesn't have any comments" );

            }

            ArrayList<Integer> commentsids = new ArrayList<>();

            do {

                commentsids.add(rs.getInt("commentid"));

            } while (rs.next());

            stmt.close();
            db.close();
            return commentsids;
        

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
		}
	}//end of getCommentsIds

    /**
	 * returns a single comment with id = the parameter's commentid
	 * @param commentid, int
	 * @throws Exception, if encounter any error.
	 */
    public Comments getComment(int commentid) throws Exception {
			
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "SELECT * FROM allcomments where commentid=? ;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, commentid);
            ResultSet rs = stmt.executeQuery();
            String comment = rs.getString("comment");
            Comments c = new Comments(rs.getInt("commentid"),rs.getString("comment"),rs.getInt("postid"),rs.getString("username"));
            
            stmt.close();
            db.close();
            return c;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
		}
	}//end of getComment
	
    /**
	 * returns a single post with id = the parameter's postid
	 * @param postid, int
	 * @throws Exception, if encounter any error.
	 */
    public Posts getPost(int postid) throws Exception {
			
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "SELECT * FROM allposts where postid=? ;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, postid);
            ResultSet rs = stmt.executeQuery();
            Posts p = new Posts(rs.getInt("postid"),rs.getString("posts"),rs.getInt("stars"),rs.getInt("likes"),rs.getInt("commentsnumber"));
            stmt.close();
            db.close();
            return p;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
		}
	}//end of getPost

    /**
	 * returns a single post with id = the parameter's postid
	 * @param postid, int
	 * @throws Exception, if encounter any error.
	 */
    public String getPostRating(int postid) throws Exception {
			
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "SELECT * FROM allposts where postid=? ;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, postid);
            ResultSet rs = stmt.executeQuery();
            String post = rs.getString("posts");
            stmt.close();
            db.close();
            return post;


        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
		}
	}//end of getPostRating


    /**
	 * Return size of the allposts table.
	 * 
	 * @throws Exception, if encounter any error.
	 */
    public int getPostSize() throws Exception {
        
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "SELECT LAST_INSERT_ID();";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int post = rs.getInt("SELECT LAST_INSERT_ID();");
            db.close();
            return post;
        }  catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
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
			
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "UPDATE allposts SET likes = likes + 1 WHERE postid = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, postid);
            stmt.executeUpdate();	
			stmt.close();
            db.close();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {

            try {
                db.close();
            } catch (Exception e) {
                
            }
		
		}
	}//end of addLike


} //End of class