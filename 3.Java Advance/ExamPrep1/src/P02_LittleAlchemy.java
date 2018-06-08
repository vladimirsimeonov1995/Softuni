import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class P02_LittleAlchemy {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Integer> stones = new ArrayDeque<>();
        int gold = 0;

        String[] stonesArray = reader.readLine().split(" ");

        for (String stone : stonesArray) {
            stones.add(Integer.parseInt(stone));
        }

        while (true){

            String[] command = reader.readLine().split(" ");
            if("Revision".equals(command[0]))
                break;


            switch (command[0]){

                case "Apply":
                    gold = applyAcid(stones, gold, command[2]);
                    break;
                case "Air":
                    if(gold > 0){
                        gold -- ;
                        int newStone = Integer.parseInt(command[2]);
                        stones.add(newStone);
                    }
                    break;
            }
        }

        while (!stones.isEmpty())
            System.out.print(stones.poll() + " ");
        System.out.println("\n" + gold);


    }

    private static int applyAcid(ArrayDeque<Integer> stones, int gold, String s) {
        int acid = Integer.parseInt(s);
        while (acid-- > 0){
            try {
                int stone = stones.poll();
                if (--stone <= 0)
                    gold++;
                else
                    stones.add(stone);
            }catch (Exception e){
                //Ignore command
            }
        }
        return gold;
    }

}
