
import java.util.*;


public class UnicodeCharacters {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);


        StringBuilder sb = new StringBuilder(input.nextLine());
        for (int i = 0; i < sb.length(); i++) {
            System.out.printf("\\u%04x",(int)sb.charAt(i));
        }

    }

}