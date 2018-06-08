import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P01_Shockwave {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] matrixParam = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int[][] matrix = new int[matrixParam[0]][matrixParam[1]];

        while (true){

            String line = reader.readLine();
            if("Here we go".equalsIgnoreCase(line))
                break;
            
            int[] waveParam = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int i = waveParam[0]; i <= waveParam[2]; i++) {
                for (int j = waveParam[1]; j <= waveParam[3]; j++) {
                    matrix[i][j] ++ ;
                }
            }
        }

        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }


    }

}
