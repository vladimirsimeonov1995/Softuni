package p02_CustomList;

import p02_CustomList.GenericList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        GenericList<String> list = new GenericList<>();

        while (true){

            String[] cmdArgs = reader.readLine().split(" ");

            switch (cmdArgs[0]){
                case "Add":
                    list.add(cmdArgs[1]);
                    break;
                case "Remove":
                    list.remove(Integer.parseInt(cmdArgs[1]));
                    break;
                case "Contains":
                    System.out.println(list.contains(cmdArgs[1]));
                    break;
                case "Swap":
                    int firstIndex = Integer.parseInt(cmdArgs[1]);
                    int secondIndex = Integer.parseInt(cmdArgs[2]);
                    list.swap(firstIndex,secondIndex);
                    break;
                case "Greater":
                    System.out.println(list.countGreaterThan(cmdArgs[1]));
                    break;
                case "Max":
                    System.out.println(list.getMax());
                    break;
                case "Min":
                    System.out.println(list.getMin());
                    break;
                case "Print":
                    System.out.print(list.toString());
                    break;
                case "Sort":
                    list.sort();
                    break;
                case "END":
                    return;
            }
        }





    }


}
