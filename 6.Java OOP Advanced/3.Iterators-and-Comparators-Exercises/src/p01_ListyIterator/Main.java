package p01_ListyIterator;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> create = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
        create.remove(0);
        ListyIterator<String> listyIterator = new ListyIterator(create.toArray(new String[create.size()]));

        String command = reader.readLine();

        while (!"END".equals(command)){

            switch (command){
                case "HasNext":
                    System.out.println(listyIterator.HasNext());
                    break;
                case "Move":
                    System.out.println(listyIterator.Move());
                    break;
                case "Print":
                    listyIterator.Print();
                    break;
                case "PrintAll":
                    for (String s : listyIterator) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                    break;
            }

            command = reader.readLine();

        }


    }

}
