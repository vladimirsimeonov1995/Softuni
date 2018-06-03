import java.util.Scanner;

public class ReadInput {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstWord = input.next();
        String secondWord = input.next();
        int firstInt = input.nextInt();
        int secondInt = input.nextInt();
        int thirdInt = input.nextInt();
        String line = input.nextLine();
        line = input.nextLine();
        System.out.println(firstWord + " " + secondWord + " " + line + " " + (firstInt+secondInt+thirdInt));
    }
}