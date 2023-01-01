import java.sql.*;


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
	public User authenticate(String username, String password, int typeaccount) throws Exception {
		
		String sql = "SELECT * FROM user WHERE username=? and password=? and typeaccount=?";
        Connection con = null;
        DB db = new DB();

        try {

            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
			stmt.setString(2, password);
            stmt.setInt(3, typeaccount);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {

                rs.close();
                stmt.close();
                db.close();
                throw new Exception("No User found with username: " + username +"and this password");

            }

            User user = new User(rs.getString("username"),rs.getString("password"),
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
			
		String sql = "SELECT * FROM user where username=? ;";
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

                rs.close();
                stmt.close();
                db.close();
                throw new Exception("Sorry, username "+ user.getUsername() + " already registered");

            }

			String sql2 = "INSERT INTO user " 
           		+ " (username,password,typeaccount) VALUES (?, ? ,?);";
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			stmt2.setString(1, user.getUsername());
            stmt2.setString(2, user.getPassword());
            stmt2.setInt(6, user.getTypeAccount());
			stmt2.executeUpdate();	
			stmt2.close();	
            rs.close();
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
	}//end of register

    /**
	 * adds the post to the posts column of the allposts table
     * 
	 * 
	 * @param user, user
	 * @throws Exception, if encounter any error.
	 */
    public void addPost(String newpost, int stars) throws Exception {
			
        Connection con = null;
        DB db = new DB();

        try {
            
            con = db.getConnection();
			String sql = "INSERT INTO allposts(posts,rating,likes) VALUES (?,?,0);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, newpost);
            stmt.setInt(1, stars);
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
            Posts p = new Posts(rs.getInt("postid"),rs.getString("posts"),rs.getInt("stars"),rs.getInt("likes"));
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
