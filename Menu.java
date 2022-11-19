import java.util.Scanner;

public class Menu {
    
    do {

        System.out.println("Welcome to toEater! Please choose an option: "+ "\n" +
                    "\r" + "1.Show posts" + "\n" +
                    "\r" + "2.Create post" + "\n" +
                    "\r" + "3.Close app");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();

        switch(option) {
            case 1:
                Posts y = new Posts();
                y.showPosts();
                break;
            case 2:
                CreatePost p = new CreatePost();
                p.newPost();
                break;
            case 3:
                break;
                boolean g = true;
            default:
                System.out.println("This option doesn't exist, choose 1, 2 or 3");
                break;
            }
        }while(g == true);

}
