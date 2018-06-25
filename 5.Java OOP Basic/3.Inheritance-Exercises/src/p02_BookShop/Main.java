package p02_BookShop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String author = reader.readLine();
        String title = reader.readLine();
        double price = Double.parseDouble(reader.readLine());

        try {

            Book book = new Book(title,author,price);
            System.out.println("Type: Book");
            System.out.println(book);

            GoldenEditionBook goldenEditionBook = new GoldenEditionBook(title,author,price);
            System.out.println("Type: GoldenEditionBook");
            System.out.println(goldenEditionBook);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }

    }

}
