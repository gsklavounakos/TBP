import java.util.Scanner;
import java.lang.Exception;



public class Menu {

    /**
     * prints Home Menu
     * 
     * @param user , user
	 * @throws Exception , if encounter any error.
     */
    public static boolean printMenu(User thisuser) throws Exception{
        System.out.println("======================================");
        System.out.println("          Welcome, "+ thisuser.getUsername()+ "!            ");
		System.out.println("|            MAIN MENU               |");
        System.out.println("|     Please select and option:      |");
        System.out.println("======================================");
        System.out.println("| Options:                           |");
        System.out.println("|        [1] Show posts              |");
        System.out.println("|        [2] Create a post           |");
        System.out.println("|        [3] Close app               |");
        System.out.println("======================================");
        int option = 0;
        boolean read = false;
        while (read != true) {  
            try {
		        Scanner in = new Scanner(System.in);
				option = in.nextInt();
				read = true;  //read the answer
                
            } catch (Exception e) {
				read = false;
				throw new Exception(e.getMessage());
            }
            if (option != 1 & option != 2 & option != 3) {  //if the answer is invalid print the message
                read = false;
                System.out.println ("Please enter an integer value between 1 and 3.");
            }
        }

        switch(option) {
            case 1:                         //option 1 show a post
                Posts y = new Posts();
                y.showPosts(thisuser);
                boolean b = true;
                return b;
            case 2:                        //option 2 create a post
                Posts p = new Posts();
                p.newPost(thisuser);
                boolean w = true;
                return w;
            case 3:                        //close application
				Runtime runtime = Runtime.getRuntime();
				runtime.exit(1);
                boolean q = false;
                return q;
				
            }
	    return read;
    }//end of printMenu
    
}// end of class Menu
