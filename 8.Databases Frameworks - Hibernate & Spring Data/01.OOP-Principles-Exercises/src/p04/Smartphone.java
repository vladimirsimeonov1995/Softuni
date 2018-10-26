package p04;

public class Smartphone implements Callable,Browsable {

    public Smartphone(){
    }

    @Override
    public String browse(String site) {
        if(site.matches(".*\\d+.*")){
            return "Invalid URL!";
        }

        return String.format("Browsing: %s!",site);
    }

    @Override
    public String call(String number) {
        //Invalid number!
        if(!number.matches("^\\d+$")){
            return "Invalid number!";
        }

        return String.format("Calling... %s",number);
    }
}
