package p09_TraficLights;

public enum TraficColors {

    RED("GREEN"), GREEN("YELLOW"), YELLOW("RED");

    String next;

    TraficColors(String next){
        this.next = next;
    }

}
