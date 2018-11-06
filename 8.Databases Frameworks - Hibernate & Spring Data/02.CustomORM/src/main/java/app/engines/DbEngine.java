package app.engines;

import app.contracts.Runnable;
import app.db.EntityDbContext;
import app.db.dbContracts.DbContext;
import app.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DbEngine implements Runnable {

    private Connection connection;

    public DbEngine(Connection connection) {
        this.connection = connection;
    }

    public void run() throws IllegalAccessException, SQLException, InstantiationException {

        DbContext<User> context = new EntityDbContext<>(User.class,connection);

        System.out.println("You need to change the logic in engines/DbEngine to test" +
                "the full functionality of the program !");

    }

}
