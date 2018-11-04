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

public class Problem4 implements Executable {

    private Connection connection;
    private Writer writer;
    private Reader reader;

    public Problem4(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
        this.reader = new ConsoleReader();
    }

    public void execute() throws IOException, SQLException {

        this.writer.writeLine("Problem 4: \n\n");

        List<String> minionParams = getParams();
        List<String> villainParams = getParams();

        //Get minion params
        String minionName = minionParams.get(1);
        int minionAge = Integer.parseInt(minionParams.get(2));
        String townName = minionParams.get(3);

        //Get villainParams
        String villianName = villainParams.get(1);

        if(!checkIfEntityExists("towns",townName)){
            this.insertTown(townName);
            this.writer.writeLine(String
                    .format("Town %s was added to the database.\n",townName));
        }

        if(!checkIfEntityExists("villains",villianName)){
            this.insertVillain(villianName);
            this.writer.writeLine(String
                    .format("Villain Poppy was added to the database.\n",villianName));
        }

        this.insertMinion(minionName,minionAge,this.getIdFromEntity("towns",townName));

        this.setMinionToBeSevant(minionName,villianName);

        writer.writeLine(String
                .format("Successfully added %s to be minion of %s\n",minionName,villianName));



    }

    private List<String> getParams() throws IOException {
        return Arrays.asList(reader.readLine().split(" "));
    }

    private boolean checkIfEntityExists(String tableName, String name) throws SQLException {

        String query = "" +
                "SELECT * " +
                "FROM " + tableName +
                " WHERE name = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1,name);

        ResultSet rs = stmt.executeQuery();

        return rs.next();
    }

    private int getIdFromEntity(String tableName, String name) throws SQLException {

        String query = "" +
                "SELECT id " +
                "FROM " + tableName +
                " WHERE name = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1,name);

        ResultSet rs = stmt.executeQuery();

        rs.next();

        return rs.getInt("id");
    }

    private void insertTown(String townName) throws SQLException {

        String query = "INSERT INTO towns(name,country) VALUE (?, NULL)";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1,townName);

        stmt.execute();
    }

    private void insertVillain(String name) throws SQLException {

        String query = "INSERT INTO villains(name, evilness_factor) VALUES (?, 'evil')";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1,name);

        stmt.execute();

    }

    private void insertMinion(String name, int age, int townId) throws SQLException {

        String query = "INSERT INTO minions(name, age, town_id) VALUES (?, ?, ?)";

        PreparedStatement stmt = this.connection.prepareStatement(query);

        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setInt(3, townId);

        stmt.execute();

    }

    private void setMinionToBeSevant(String minionName, String villainName) throws SQLException {

        int minionId = this.getIdFromEntity("minions",minionName);
        int villainId = this.getIdFromEntity("villains",villainName);

        String query = "INSERT INTO minions_villains(minion_id, villain_id) VALUES (?, ?)";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setInt(1,minionId);
        stmt.setInt(2,villainId);

        stmt.execute();

    }
}
