import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P04_FootballStats {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String,List<Match>> teams = new HashMap<>();

        while (true){
            String[] line = reader.readLine().split("[ :-]+");
            if("Season".equals(line[0]))
                break;

            String firstTeam = line[0];
            String secondTeam = line[1];
            String firstTeamScore = line[3];
            String secondTeamScore = line[4];

            addMatch(teams, firstTeam, secondTeam, firstTeamScore, secondTeamScore);
            addMatch(teams,secondTeam,firstTeam,secondTeamScore,firstTeamScore);

        }

        String[] line = reader.readLine().split(", ");

        for (String team : line) {
            teams.get(team).stream()
                    .sorted(Comparator.comparing(Match::getSecondTeam))
                    .forEach(match -> {
                        System.out.println(team + " - " + match.getSecondTeam() + " -> " + match.getScore());
                    });
        }






    }

    private static void addMatch(Map<String, List<Match>> teams, String firstTeam, String secondTeam, String firstTeamScore, String secondTeamScore) {
        if(!teams.containsKey(firstTeam)){
            List<Match> matches = new ArrayList<>();
            String score = firstTeamScore + ":" + secondTeamScore;
            matches.add(new Match(secondTeam,score));
            teams.put(firstTeam,matches);
        }
        else {
            String score = firstTeamScore + ":" + secondTeamScore;
            teams.get(firstTeam).add(new Match(secondTeam,score));
        }
    }

    public static class Match{

        String secondTeam;
        String score;

        public Match() {
        }

        public Match(String secondTeam, String score) {
            this.secondTeam = secondTeam;
            this.score = score;
        }

        public String getSecondTeam() {
            return secondTeam;
        }

        public void setSecondTeam(String secondTeam) {
            this.secondTeam = secondTeam;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }


}
