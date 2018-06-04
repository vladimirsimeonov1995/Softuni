
import java.util.*;


public class CountSubstringOccurrences {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String text = input.nextLine().toLowerCase();
        String pattern = input.nextLine().toLowerCase();

        int count = 0;

        while (true){
            if(!text.contains(pattern))
                break;

            count ++ ;

            text = text.substring(text.indexOf(pattern)+1,text.length());
        }

        System.out.println(count);



    }


}
