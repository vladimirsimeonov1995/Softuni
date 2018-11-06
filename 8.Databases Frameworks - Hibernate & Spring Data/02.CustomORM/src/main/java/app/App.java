package app;

import app.contracts.Runnable;
import app.engines.DbEngine;

import java.sql.*;

public class App {

    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/soft_uni";

    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException {

        Connection connection = getConnection();

        Runnable engine = new DbEngine(connection);

        engine.run();

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING,
                "root",
                "00000000");
    }

}
