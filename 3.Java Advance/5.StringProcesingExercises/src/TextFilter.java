
import java.util.*;

public class TextFilter {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] bannedWords = input.nextLine().split(", ");

        String text = input.nextLine();

        for (String bannedWord : bannedWords) {
            text = text.replace(bannedWord,getText('*',bannedWord.length()));
        }

        System.out.println(text);


    }

    public static String getText(char c,int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(c);
        }
        return sb.toString();
    }


}