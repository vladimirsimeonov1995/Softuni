package p04_MordorsCrueltyPlan;

public class Gandalf {

    private int happiness;

    public Gandalf(){
        this.happiness = 0;
    }

    public void eat(String[] foods){
        for (String food : foods) {
            addPoints(food);
        }
    }

    private void addPoints(String food){
        switch (food.toLowerCase()){
            case "cram":
                this.happiness += 2;
                break;
            case "lembas":
                this.happiness += 3;
                break;
            case "apple":
                this.happiness += 1;
                break;
            case "melon":
                this.happiness += 1;
                break;
            case "honeycake":
                this.happiness  += 5;
                break;
            case "mushrooms":
                this.happiness -= 10;
                break;
                default:
                    this.happiness -= 1;
                    break;
        }
    }

    private String getMood(){
        if(this.happiness < -5)
            return "Angry";
        else if(this.happiness < 0)
            return "Sad";
        else if(this.happiness < 15)
            return "Happy";
        else
            return "JavaScript";
    }

    @Override
    public String toString(){
        return String.format("%d\n%s\n",this.happiness,this.getMood());
    }
}
