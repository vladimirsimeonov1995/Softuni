package app.problmes;

import app.Interfaces.Executable;
import app.Interfaces.Writer;
import app.io.ConsoleWriter;

import java.sql.Connection;

public class Problem9 implements Executable {

    private Connection connection;
    private Writer writer;

    public Problem9(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
    }

    public void execute() {

        this.writer.writeLine("Problem 9: \n\n");

    }

}
