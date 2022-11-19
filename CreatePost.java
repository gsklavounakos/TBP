import java.util.Scanner;

public class CreatePost{

    public String newPost() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write your post:");
        String post = in.nextLine();
        return post;
    }
    
}