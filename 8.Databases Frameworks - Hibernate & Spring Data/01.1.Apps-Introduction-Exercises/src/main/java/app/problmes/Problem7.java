package app.problmes;

import app.Interfaces.Executable;
import app.Interfaces.Writer;
import app.io.ConsoleWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Problem7 implements Executable {

    private Connection connection;
    private Writer writer;

    public Problem7(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
    }

    public void execute() throws SQLException {

        this.writer.writeLine("Problem 7: \n\n");

        int minionsNumber = this.getMinionsCount();

        for (int i = 0; i < minionsNumber; i++) {

            this.writer.writeLine(String
                    .format("%s\n",this.getMinionName(i)));

            if(i == minionsNumber / 2){
                break;
            }

            this.writer.writeLine(String
                    .format("%s\n",this.getMinionName(minionsNumber -1 - i)));

            if((minionsNumber -1 - i) == minionsNumber / 2){
                break;
            }

        }


    }

    private String getMinionName(int id) throws SQLException {

        String query = "SELECT name FROM minions\n" +
                "WHERE id = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,id + 1);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        return rs.getString(1);

    }

    private int getMinionsCount() throws SQLException {

        String query = "SELECT COUNT(id) AS number FROM minions";

        PreparedStatement stmt = this.connection.prepareStatement(query);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        return rs.getInt(1);

    }
}
