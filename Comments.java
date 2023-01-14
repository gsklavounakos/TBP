import java.util.ArrayList;


/**
 * Comments provides the necessary methods related to commenting a post.
 *
 */

public class Comments {

    private int commentid;          //The id of the comment
    private String comment;         //The comment
    private int postid;				//The id of the post that was commented
    private String username;		//The username of the user

	public Comments(int commentid, String comment, int postid, String username) {			 //Constructor
        this.commentid = commentid;
        this.comment = comment;
        this.postid = postid;
        this.username = username;
    }

    public Comments() {				 //Constructor
        commentid = 0; 
        comment = null;
        postid = 0;
        username = null;
    }
    
    /**
	 * This method is used to show the comments to the user.
	 * 
	 * @param commentsids, ArrayList<Integer>
	 * @throws Exception, if encounter any error.
	 */
	
	
	
	public void showComments(ArrayList<Integer> commentsids) throws Exception{
        UserDAO userdao = new UserDAO();
        try{
            int commentssnum = commentsids.size();
            int i = 1 ;
            Comments comment = userdao.getComment(commentsids.get(0));
            Posts thepost = userdao.getPost(comment.postid);
            do {
                comment = userdao.getComment(commentsids.get(i-1));
                System.out.println("Comment's ID " + comment.commentid +"\n\nComment:\n" + comment.comment + "\nAuthor "  + comment.username);
                
                
            }while(i<=commentssnum);
        } catch (Exception e) {
            e.getMessage();
        }
    }//End of showComments
    
}//End of class Comments