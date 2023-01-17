import java.util.Scanner;

public class Rating {
    public int addRating(){
        System.out.println("Set rating for your meal in this restaurant, from 1 to 5 stars.");
        int stars = 0;
        boolean read = false;
        while (read != true) {
            try {

		Scanner in = new Scanner(System.in);
                stars = in.nextInt();
                read = true;

            } catch (Exception e) {
                read = false;
            }
            if (stars < 1 | stars > 5) {
                read = false;
                System.out.println ("Please enter an integer value from 1 to 5.");
            }
        }
        return stars;
    }
}
