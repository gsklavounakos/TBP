import java.util.ArrayList;
import java.util.Scanner;


public class Posts {
    ArrayList<String> allPosts = new ArrayList<String>();
    ArrayList<Integer> likes = new ArrayList<Integer>();

    public void addPost(){
        CreatePost x = new createPost();
        String y= x.newPost();
        allPosts.add(y);
    }

    public void likePost(int i){
        likes(i)==0;

    }

    public void showSinglePost(int i){
        System.out.println(allPosts(i));

    }
    
    public void showPosts(){
        
        if (allPosts.size() =! 0) {
            Scanner in = new Scanner(System.in);
            int y=0;
            showSinglePost(y);
            do {
                System.out.println("Press: (1) to show Next Post, (2) to Like this post, (3) to go back to menu")
                int option = in.nextInt();

                switch(option) {
                    case 1: 
                        y++;
                        showSinglePost(y);
                        break;
                    case 2:
                        likePost(y);
                        break;
                    case 3:
                    int g=0;
                    break;
                }
            
            }while (g == 0);
        }

    }

}
