import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Team team = new Team("SoftUniTeam");

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] info = reader.readLine().split(" ");

            Player player = null;

            try {
                player = new Player(info[0],info[1],Integer.parseInt(info[2]),Double.parseDouble(info[3]));
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }

            team.addPlayer(player);
        }

        System.out.println(team);

    }
}
