import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class Posts provides all the necessary methods related to liking, commenting or creating a post.
 *
 */

public class Posts {

    private int postid;         //The id of the post
    private String post;        //The post
    private int stars;          //The rating
    private int likes;          //The likes of the post
    private int commentsnumber; //The number of the comments


    public Posts(int postid, String post, int stars, int likes, int commentsnumber) {           //Constructor
        this.postid = postid;
        this.post = post;
        this.stars = stars;
        this.likes = likes;
        this.commentsnumber = commentsnumber;
    }

    public Posts() {         //No args constructor
        postid = 0;
        post = null;
        stars = 0;
        likes = 0;
        commentsnumber = 0;
    }
    Scanner in = new Scanner(System.in);

    /**
        *With this method a user can like a post, see the comments of a post, comment himself or return to Home Menu.
        * 
        * @param thisuser, User
        * @throws Exception, if encounter any error.
        */


    
    public void showPosts(User thisuser) throws Exception{
        UserDAO userdao = new UserDAO();
		boolean likePost = false;
        try {
            //int postsnum = userdao.getPostSize();
            int i = 1 ;
            Posts thispost = new Posts();
            do {
                thispost = userdao.getPost(i);
                int option = 0;
				if (thispost.postid!=0){
						System.out.println("\nSHOWING POST NUMBER " + thispost.postid +"\n\nPOST:\n'" + thispost.post + "'\nRATING: " + thispost.stars + "\nLIKES NUMBER: " + thispost.likes + "\nNUMBER OF COMMENTS: " + thispost.commentsnumber);
						System.out.println("\nPlease choose an option:\n1.Like this Post\n2.Show Comments\n3.Add comment\n4.Next Post\n5.Go to Main Menu\n");  
						option = 0;
						boolean read = false;
						while (read != true) {
							try {
								Scanner in = new Scanner(System.in);
								option = in.nextInt();
								read = true;           //The user types a number between 1 and 5
							} catch (Exception e) {
								read = false;
							}
							if (option != 1 & option != 2 & option != 3 & option != 4 & option != 5) { //checks if the value is within limits
								read = false;
								System.out.println ("Please enter an integer value between 1 and 5.");
							}
						} 
				
						switch(option) {
							case 1:          //The user wants to like the post
								if (likePost == false) {
									userdao.addLike(thispost.postid); //userdao handles the like adding
									likePost = true;
									System.out.println("\nPost liked successfully\n");
								} else {
									System.out.println("\n!You have already liked this post!\n");
								}
								break;

							case 2:          //The user wants to see the comments of the post


								ArrayList<Integer> commentsids = userdao.getCommentsIds(thispost.postid);	//gets all comment's ID's for the current post
								if (commentsids.size()==0) {
									System.out.println("No comments in this post yet");
								} else {
									Comments comments = new Comments();
									comments.showComments(commentsids);		//sends the arrayList commentids to showcomments to show each comment
								}
								break;

							case 3:             //The user wants to add a comment
								
								String username = thisuser.getUsername();
								System.out.println("\n---------------------\nType your Comment\n");
								String comment = in.nextLine();
								userdao.addComment(comment, i, username);
								break;

							case 4:           //The user wants to see the next post
								i += 1;
								likePost = false;
								break;
							case 5:           //The user wants to go to Home Menu
								//boolean b = Menu.printMenu(thisuser);
								i = 0;
								thispost.postid = 0;
								break;

						}
				} else{
					System.out.println("\nThere are no more posts, returning...");			
				}
            } while(thispost.postid != 0 );
			Menu.printMenu(thisuser);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }//End of showPosts

 /**
        *With this method a user can add a new post.
        * 
        * @param thisuser, User
        * @throws Exception, if encounter any error.
        */

    public void newPost(User thisuser ) {
        boolean added = false;
        do{               // loop ends when a post is added
            try {
                System.out.println("\n---------------------\nType your Post\n");
                String post = in.nextLine();
                
                Rating r = new Rating();
                int stars = r.addRating();

                UserDAO user = new UserDAO();
                System.out.println("Loading...");
                user.addPost(post, stars, thisuser.getUsername());
                System.out.println("Post successfully added");
                added = true;
            } catch (Exception e) {
                System.out.println("Exception occurred while trying to add post");
            }
        } while(added = false);
    }//End of newPost
}//End of class Posts