package app.problmes;

import app.Interfaces.Executable;
import app.Interfaces.Writer;
import app.io.ConsoleWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Problem2 implements Executable {

    private Connection connection;
    private Writer writer;

    public Problem2(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
    }

    public void execute() throws SQLException {

        this.writer.writeLine("Problem 2: \n\n");

        String query = "" +
                "SELECT v.name,COUNT(mv.minion_id) AS countMinions\n" +
                "FROM villains v\n" +
                "JOIN minions_villains mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY mv.villain_id\n" +
                "HAVING countMinions > 3\n" +
                "ORDER BY  countMinions DESC ;";

        PreparedStatement stmt = connection.prepareStatement(query);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){

            String vallainName = rs.getString("name");
            int countOfMinions = rs.getInt("countMinions");

            String result = String.format("%s %d\n",vallainName,countOfMinions);

            this.writer.writeLine(result);

        }
    }

}
