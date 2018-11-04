package app.engines;

import app.Interfaces.Executable;
import app.problmes.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Engine implements Runnable {

    private Connection connection;

    public Engine(Connection connection){
        this.connection = connection;
    }

    public void run() {
        Executable executable = new Problem8(this.connection);

        try {
            executable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Instructions to the user:

        System.out.println("\n\nTo test other problem just change the number of the problem\n" +
                "in engines/Engine row 18");
    }
}
