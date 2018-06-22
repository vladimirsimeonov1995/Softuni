import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {

    private String name;
    private List<Player> firstTeam;
    private List<Player> reverseTeam;

    public Team(String name) {
        this.name = name;
        this.firstTeam = new ArrayList<>();
        this.reverseTeam = new ArrayList<>();
    }

    public void addPlayer(Player newPlayer){
        if(newPlayer.getAge() < 40) {
            this.firstTeam.add(newPlayer);
        } else {
            this.reverseTeam.add(newPlayer);
        }
    }

    public List<Player> getFirstTeam(){
        return Collections.unmodifiableList(this.firstTeam);
    }

    public List<Player> getReverseTeam(){
        return Collections.unmodifiableList(this.reverseTeam);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //First team have 4 players
        //Reserve team have 1 players
        sb.append("First team have ")
                .append(this.getFirstTeam().size())
                .append(" players\n")
                .append("Reserve team have ")
                .append(this.getReverseTeam().size())
                .append(" players");
        return sb.toString();
    }
}
