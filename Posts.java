import java.util.Scanner;


public class Posts {

    Scanner in = new Scanner(System.in);

    public void showPosts(){
        UserDAO user = new UserDAO();
        try {
            int postsnum = user.getPostSize();
            for (int i = 1; i <= postsnum; i++) {
                String post = user.getPost(i);
                System.out.println(post);
            }
        } catch (Exception e) {
        }
        
    }

    public void newPost() {
        System.out.println("Type your Post");
        String post = in.nextLine();
        UserDAO user = new UserDAO();
        try {
            System.out.println("Loading...");
            user.addPost(post);
            System.out.println("Post successfully added");
        } catch (Exception e) {
            try {
                System.out.println(e.getMessage()); 
            } catch (Exception e1) {
            }
        }
    }
}
