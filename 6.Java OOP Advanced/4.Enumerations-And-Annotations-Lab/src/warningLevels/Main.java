package warningLevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Logger logger = new Logger(reader.readLine());

        while (true){
            String[] commands = reader.readLine().split(": ");
            if("END".equals(commands[0]))
                break;

            logger.addMessage(new Message(commands[1],commands[0]));
        }

        for (Message message : logger.getMessages()) {
            System.out.println(message);
        }

    }

}
