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
import java.util.ArrayList;
import java.util.List;

public class Problem5 implements Executable {

    private Connection connection;
    private Writer writer;
    private Reader reader;

    public Problem5(Connection connection){
        this.connection = connection;
        this.writer = new ConsoleWriter();
        this.reader = new ConsoleReader();
    }

    public void execute() throws IOException, SQLException {

        this.writer.writeLine("Problem 5: \n\n");

        String countryName = this.reader.readLine();

        if(checkIfCountryExists(countryName)){
            this.updateNamesOfTownsAndPrintMessage(countryName);
        }else {
            this.writer.writeLine("No town names were affected.\n");
        }


    }

    private boolean checkIfCountryExists(String name) throws SQLException {

        String query = "SELECT * FROM towns\n" +
                "WHERE country = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1,name);

        ResultSet rs = stmt.executeQuery();

        return rs.next();

    }

    private void updateNamesOfTownsAndPrintMessage(String countryName) throws SQLException {

        List<String> townNames = new ArrayList<String>();

        String query = "SELECT name FROM towns\n" +
                "WHERE country = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1,countryName);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            townNames.add(rs.getString("name"));
        }

        for (String townName : townNames) {
            setNameToUpperCase(townName);
        }

        this.writer.writeLine(String
                .format("%d town names were affected. \n",townNames.size()));

        for (int i = 0; i < townNames.size()-1; i++) {
            townNames.add(i,townNames.get(i).toUpperCase());
            townNames.remove(i+1);
        }

        this.writer.writeLine(String.format("%s\n",townNames));


    }

    private void setNameToUpperCase(String townName) throws SQLException {

        String query = "UPDATE towns\n" +
                "SET name = ?\n" +
                "WHERE name = ?";

        PreparedStatement stmt = this.connection.prepareStatement(query);
        stmt.setString(1,townName.toUpperCase());
        stmt.setString(2,townName);

        stmt.execute();

    }

}
