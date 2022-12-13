import java.util.Scanner;


public class Posts {

    private int postid;
    private String post;
	private int stars;
	private int likes;

    public Posts(int postid, String post, int stars, int likes) {
        this.postid = postid;
        this.post = post;
        this.stars = stars;
        this.likes = likes;
    }

    public Posts() {
        postid = 0;
        post = null;
        stars = 0;
        likes = 0;
    }
    Scanner in = new Scanner(System.in);

    
    public void showPosts(){
        UserDAO user = new UserDAO();
        try {
            int postsnum = user.getPostSize();
            for (int i = 1; i <= postsnum; i++) {
                Posts post = user.getPost(i);
                System.out.println("Post number " + post.postid +"\n\nPost:\n" + post.post + "\nRating: " + post.stars + "\nLikes: " + post.likes);
            }
        } catch (Exception e) {
        }
        
    }

    public void newPost() {

        System.out.println("\n---------------------\nType your Post\n");
        String post = in.nextLine();
        
        Rating r = new Rating();
        int stars = r.addRating();

        UserDAO user = new UserDAO();
        try {
            System.out.println("Loading...");
            user.addPost(post, stars);
            System.out.println("Post successfully added");
        } catch (Exception e) {
            try {
                System.out.println(e.getMessage()); 
            } catch (Exception e1) {
            }
        }
    }
}
