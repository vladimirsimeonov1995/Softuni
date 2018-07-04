package p04_Telephony;

public class Smartphone implements Callable, Browsable {

    public Smartphone() {
    }

    @Override
    public String browse(String url) {
        if (url.matches(".*\\d+.*")) {
            return "Invalid URL!";
        }

        return String.format("Browsing: %s!", url);
    }

    @Override
    public String calling(String number) {
        if (!number.matches("\\d+"))
            return "Invalid number!";

        return String.format("Calling... %s", number);
    }
}
