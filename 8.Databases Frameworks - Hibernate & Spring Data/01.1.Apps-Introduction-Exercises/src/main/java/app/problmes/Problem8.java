package app.problmes;

import app.Interfaces.Executable;
import app.Interfaces.Reader;
import app.Interfaces.Writer;
import app.io.ConsoleReader;
import app.io.ConsoleWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem8 implements Executable {

    private Connection connection;
    private Writer writer;
    private Reader reader;

    public Problem8(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
        this.reader = new ConsoleReader();
    }

    public void execute() throws IOException, SQLException {

        this.writer.writeLine("Problem 8: \n\n");

        List<Integer> ids = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        for (Integer id : ids) {

            String minionName = this.getMinionName(id);
            this.setMinionsNameWithFirstLetterUppercase(minionName);
            this.updateMinionsAge(id);

        }

        this.printAllMinions();



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

    private void setMinionsNameWithFirstLetterUppercase(String minionName) throws SQLException {

        String newName = Character.toUpperCase(minionName.charAt(0)) + minionName.substring(1);

        String query =
                "UPDATE minions SET name = '"+ newName + "' WHERE name = '" + minionName +"'";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.execute();

    }

    private void updateMinionsAge(int id) throws SQLException {

        String query = "UPDATE minions SET age = age + 1 WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,id);

        stmt.execute();

    }

    private void printAllMinions() throws SQLException {

        String query = "SELECT name,age\n" +
                "FROM minions";

        PreparedStatement stmt = this.connection.prepareStatement(query);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){

            String minionName = rs.getString("name");
            int minionAge = rs.getInt("age");

            this.writer.writeLine(String.format("%s %d\n",minionName,minionAge));
        }

    }
}
