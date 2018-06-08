
import java.util.*;


public class Palindromes {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] words = input.nextLine().split("[!,.? ]+");

        Set<String> palindromes = new TreeSet<>();

        for (String word : words) {
            if(checkWord(word))
                palindromes.add(word);
        }

        System.out.printf("[%s]",String.join(", ",palindromes));

    }


    public static boolean checkWord(String word){
        StringBuilder sb = new StringBuilder(word);
        if(sb.toString().equals(sb.reverse().toString()))
            return true;
        return false;
    }
}

