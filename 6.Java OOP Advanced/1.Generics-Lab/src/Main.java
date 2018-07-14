import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        List<Integer> intList = new ArrayList<>(){{
            add(10);
            add(-5);
        }};

        List<Integer> intSecondList = new ArrayList<>(){{
            add(3);
            add(2);
        }};

        List<List<? extends Number>> sourse = new ArrayList<>();

        sourse.add(intList);
        sourse.add(intSecondList);

        List<Number> destination = new ArrayList<>();

        ListUtils.flatten(destination,sourse);

        ListUtils.addAll(destination,intList);

        for (Number number : destination) {
            System.out.println(number);
        }



    }

}
