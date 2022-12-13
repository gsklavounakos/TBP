import java.util.Scanner;

public class Like {
    public void addLike() {
		boolean a = false;
		do {
			try {
				System.out.println("Press L on your keyboard to like this post");

				Scanner in = new Scanner(System.in);
				String option = in.nextLine();
				if (option.equals("L")) {
					System.out.println("Please enter the post number of the post you want to like");
					int postid = in.nextInt();
					UserDAO user = new UserDAO();
					user.addLike(postid);
					a = true;
				} else {
					a = true;
					break;
				}
			}
			catch (Exception e) {
				System.out.println("This option doesn't exist,press 1 if you like this post or press 0 if you don't like this post");
			}
		} while (a == false);
	}
}