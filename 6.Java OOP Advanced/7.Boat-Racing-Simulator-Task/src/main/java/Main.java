import Core.CommandHandler;
import Core.Engine;
import controllers.BoatSimulatorController;
import io.ConsoleReader;
import io.ConsoleWriter;

public class Main {

    public static void main(String[] args) {

        Engine engine = new Engine(new ConsoleReader(),
                new ConsoleWriter(),
                new CommandHandler(new BoatSimulatorController()));

        engine.run();
    }
}
