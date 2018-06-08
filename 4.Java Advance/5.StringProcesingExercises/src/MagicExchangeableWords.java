
import java.util.*;

public class MagicExchangeableWords {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] enteredWords = input.nextLine().split(" ");

        String shortWord = "";
        String longWord = "";

        if(enteredWords[0].length() < enteredWords[1].length()){
            shortWord = enteredWords[0];
            longWord = enteredWords[1];
        }
        else {
            shortWord = enteredWords[1];
            longWord = enteredWords[0];
        }

        Map<Character,Character> letters = new HashMap<>();
        boolean check = true;

        for (int i = 0; i < shortWord.length(); i++) {
            if(!letters.containsKey(shortWord.charAt(i))){
                if(!letters.containsValue(longWord.charAt(i))){
                    letters.put(shortWord.charAt(i),longWord.charAt(i));
                }
                else {
                    check = false;
                }
            }
            else {
                if(letters.get(shortWord.charAt(i)) != longWord.charAt(i)){
                    check = false;
                }
            }
        }

        for (int i = shortWord.length()-1; i < longWord.length(); i++) {
            if(!letters.containsValue(longWord.charAt(i))){
                check = false;
            }
        }

        System.out.println(check);


    }
}