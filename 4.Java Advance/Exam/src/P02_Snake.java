import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P02_Snake {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());

        String[][] game = new String[size][size];

        String[] moves = reader.readLine().split(", ");

        for (int i = 0; i < size; i++) {

            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                game[i][j] = line[j];
            }

        }

        startGame(game, moves);


    }

    private static void startGame(String[][] game, String[] moves) {
        int positionRow = 0;
        int positionCol = 0;
        int food = 0;


        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                if (game[i][j].equals("s")) {
                    positionRow = i;
                    positionCol = j;
                }
                if (game[i][j].equals("f"))
                    food++;
            }
        }

        int snakeLength = 1;


        for (String move : moves) {

            game[positionRow][positionCol] = "*";

            if ("up".equals(move)) {
                if (positionRow == 0)
                    positionRow = snakeLength;
                else
                    positionRow--;

                if (game[positionRow][positionCol].equals("f")) {
                    food--;
                    snakeLength++;
                } else if (game[positionRow][positionCol].equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                }
            } else if ("down".equals(move)) {
                if (positionRow == game.length - 1) {
                    positionRow = game.length - 1  - snakeLength;
                } else
                    positionRow++;
                if (game[positionRow][positionCol].equals("f")) {
                    snakeLength++;
                    food--;
                } else if (game[positionRow][positionCol].equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                }
            } else if ("right".equals(move)) {
                if (positionCol == game.length - 1)
                    positionCol = game.length -1 - snakeLength;
                else
                    positionCol++;
                if (game[positionRow][positionCol].equals("f")) {
                    food--;
                    snakeLength++;
                } else if (game[positionRow][positionCol].equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                }
            } else if ("left".equals(move)) {
                if (positionCol == 0)
                    positionCol = snakeLength;
                else
                    positionCol--;
                if (game[positionRow][positionCol].equals("f")) {
                    food--;
                    snakeLength++;
                } else if (game[positionRow][positionCol].equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                }
            }


            game[positionRow][positionCol] = "s";

        }


        if (food == 0) {
            System.out.printf("You win! Final snake length is %s", snakeLength);
        } else {
            System.out.printf("You lose! There is still %d food to be eaten.", (food));
        }


    }


}
