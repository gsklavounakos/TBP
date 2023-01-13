import java.util.ArrayList;

public class Comments {

    private int commentid;
    private String comment;
    private int postid;
    private String username;


    public Comments(int commentid, String comment, int postid, String username) {
        this.commentid = commentid;
        this.comment = comment;
        this.postid = postid;
        this.username = username;
    }

    public Comments() {
        commentid = 0;
        comment = null;
        postid = 0;
        username = null;
    }
    
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
    }
    
}
