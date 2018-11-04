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

public class Problem3 implements Executable {

    private Connection connection;
    private Writer writer;
    private Reader reader;

    public Problem3(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
        this.reader = new ConsoleReader();
    }

    public void execute() throws IOException, SQLException {

        this.writer.writeLine("Problem 3: \n\n");

        int villainId = Integer.parseInt(this.reader.readLine());

        if(this.getVillainName(villainId) == null){
            this.writer.writeLine(String
                    .format("No villain with ID %d exists in the database.",villainId));
        }

        else {
            this.writer.writeLine(String.format("Villain: %s\n",this.getVillainName(villainId)));

            this.getMinionsNames(villainId);
        }

    }

    private boolean checkIfResultSetIsntEmpty(ResultSet rs) throws SQLException {
        return rs.next();
    }

    private String getVillainName(int villainId) throws SQLException {

        String query = "SELECT v.name\n" +
                "FROM villains v\n" +
                "WHERE v.id = ?;";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,villainId);

        ResultSet rs = stmt.executeQuery();

        if(rs.next()){
            return rs.getString("name");
        }else {
            return null;
        }


    }

    private void getMinionsNames(int villainId) throws SQLException {

        String query = "SELECT m.name, m.age\n" +
                "FROM minions m\n" +
                "JOIN minions_villains mv\n" +
                "ON m.id = mv.minion_id\n" +
                "JOIN villains v\n" +
                "ON mv.villain_id = v.id\n" +
                "WHERE v.id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,villainId);

        ResultSet rs = stmt.executeQuery();

        StringBuilder result = new StringBuilder();

        if(!this.checkIfResultSetIsntEmpty(rs)){
            result.append("<no minions>");
        }else {

            while (rs.next()){

                String name = rs.getString("name");
                int age = rs.getInt("age");
                int row = rs.getRow();

                result.append(String.format("%d. %s %d\n",row -1,name,age));

            }
        }

        this.writer.writeLine(result.toString());


    }

}
