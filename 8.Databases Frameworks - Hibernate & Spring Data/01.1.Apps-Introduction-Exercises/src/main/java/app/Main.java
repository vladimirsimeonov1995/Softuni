package app;

import app.engines.Engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","00000000");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        Engine engine = new Engine(connection);

        engine.run();

    }

}
