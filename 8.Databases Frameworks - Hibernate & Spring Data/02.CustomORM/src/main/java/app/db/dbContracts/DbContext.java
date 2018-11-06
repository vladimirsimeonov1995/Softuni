package app.db.dbContracts;

import java.sql.SQLException;

public interface DbContext<T> {

    boolean persist(T entity) throws IllegalAccessException, SQLException;

    Iterable<T> find() throws SQLException, InstantiationException, IllegalAccessException;

    Iterable<T> find(String where) throws SQLException, InstantiationException, IllegalAccessException;

    T findFirst() throws IllegalAccessException, SQLException, InstantiationException;

    T findFirst(String where) throws IllegalAccessException, SQLException, InstantiationException;

    T findById(long id) throws IllegalAccessException, SQLException, InstantiationException;

    boolean delete(String where) throws SQLException;
}
