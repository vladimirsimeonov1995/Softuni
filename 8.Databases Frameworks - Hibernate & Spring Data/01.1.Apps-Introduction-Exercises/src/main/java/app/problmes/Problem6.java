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

public class Problem6 implements Executable {

    private Connection connection;
    private Writer writer;
    private Reader reader;

    public Problem6(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
        this.reader = new ConsoleReader();
    }

    public void execute() throws IOException, SQLException {

        this.writer.writeLine("Problem 6: \n\n");

        int villainId = Integer.parseInt(this.reader.readLine());

        if(this.checkIfVillainExist(villainId)){

            String vilainName = this.getVilainName(villainId);

            int countOfMinions = this.getNumberOfMinions(villainId);

            this.removeConnectionWithMinions(villainId);

            this.removeVilain(villainId);

            this.writer.writeLine(String
                    .format("%s was deleted\n%d minions released\n",
                            vilainName,countOfMinions));


        }else {
            this.writer.writeLine("No such villain was found\n");
        }


    }

    private boolean checkIfVillainExist(int villainId) throws SQLException {

        String query = "SELECT name \n" +
                "FROM villains\n" +
                "WHERE id = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,villainId);

        ResultSet rs = stmt.executeQuery();

        return rs.next();
    }

    private void removeConnectionWithMinions(int villainId) throws SQLException {

        String query = "DELETE FROM minions_villains WHERE villain_id = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,villainId);
        stmt.execute();

    }

    private int getNumberOfMinions(int villainId) throws SQLException {
        String query = "SELECT COUNT(minion_id) AS minionsCount\n" +
                "FROM minions_villains\n" +
                "WHERE villain_id = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,villainId);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        return rs.getInt("minionsCount");


    }

    private void removeVilain(int id) throws SQLException {

        String query = "DELETE FROM villains" +
                " WHERE id = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,id);

        stmt.execute();

    }

    private String  getVilainName(int id) throws SQLException {

        String query = "SELECT name FROM villains" +
                " WHERE id = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,id);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        return rs.getString("name");

    }

}
